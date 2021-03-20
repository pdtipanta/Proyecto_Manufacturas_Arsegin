/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Maquila;

import Modelo.Maquila;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Maquila_Implementacion implements DAO_Maquila {
    private Maquila modelo_Maquila;
    private Connection conexion;
    
    public DAO_Maquila_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }
    
    @Override
    public boolean crear( Maquila modelo_Maquila ) throws SQLException{
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Maquila = this.conexion.prepareCall( "{CALL crear_Maquila(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Maquila.setString( 1, modelo_Maquila.getId_Maquila() );
            procedimiento_Crear_Maquila.setString( 2, modelo_Maquila.getMaquila() );
            procedimiento_Crear_Maquila.setString( 3, modelo_Maquila.getRUC() );
            procedimiento_Crear_Maquila.setString( 4, modelo_Maquila.getDireccion() );
            procedimiento_Crear_Maquila.setString( 5, modelo_Maquila.getTelefono() );
            procedimiento_Crear_Maquila.setString( 6, modelo_Maquila.getServicio() );
            procedimiento_Crear_Maquila.executeUpdate();
            this.conexion.commit();
        }catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        }    
        catch( SQLException e1 ){
            bandera = false;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public int editar( Maquila modelo_Maquila ) throws SQLException{
        int bandera = 0;
        try{
            CallableStatement procedimiento_Editar_Maquila = this.conexion.prepareCall( "{CALL editar_Maquila(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Maquila.setString( 1, modelo_Maquila.getId_Maquila() );
            procedimiento_Editar_Maquila.setString( 2, modelo_Maquila.getMaquila() );
            procedimiento_Editar_Maquila.setString( 3, modelo_Maquila.getRUC() );
            procedimiento_Editar_Maquila.setString( 4, modelo_Maquila.getDireccion() );
            procedimiento_Editar_Maquila.setString( 5, modelo_Maquila.getTelefono() );
            procedimiento_Editar_Maquila.setString( 6, modelo_Maquila.getServicio() );
            bandera = procedimiento_Editar_Maquila.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        }   
        catch( SQLSyntaxErrorException e1 ){
            bandera = 0;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public int eliminar( String maquila ) throws SQLException{
       int bandera = 0;
        try{
            CallableStatement procedimiento_eliminar_Maquila = this.conexion.prepareCall( "{CALL eliminar_Maquila(?)}" );
            procedimiento_eliminar_Maquila.setString( 1, maquila );
            bandera = procedimiento_eliminar_Maquila.executeUpdate();
            this.conexion.commit();
        }catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        }
        catch( SQLException e1 ){
            bandera = 0;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public ArrayList<Maquila> consultar( Object valor ) {
       ArrayList<Maquila> maquila = new ArrayList<Maquila>();
        
        try{
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall( "{CALL consulta_Maquila(?)}" );
            procedimiento_Usuario.setObject( 1, valor );
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            
            while( consulta.next() ){
                modelo_Maquila = new Maquila( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getString( 3 ), consulta.getString( 4 ), consulta.getString( 5 ), consulta.getString( 6 ) );
                maquila.add( modelo_Maquila );
            }
            consulta.close();
        }catch( SQLException e1 ){ 
        }finally{}
        return maquila;
    }
    
     @Override
    public String consultar_Numero_Maquila() {
        String numero_Maquila = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Maquila = this.conexion.prepareCall( "{CALL numero_Codigo_Maquila()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Maquila.executeQuery();
            while(consulta.next() ){
                numero_Maquila = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Maquila;
    }
}
