/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Inventario;

import Modelo.Inventario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Inventario_Implementacion implements DAO_Inventario {
    private Inventario modelo_Inventario;
    private Connection conexion;
    
    public DAO_Inventario_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }
    
    @Override
    public boolean crear( Inventario modelo_Inventarios ) throws SQLException{
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Inventario = this.conexion.prepareCall( "{CALL crear_Inventario(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Inventario.setString( 1, modelo_Inventarios.getCodigo() );
            procedimiento_Crear_Inventario.setString( 2, modelo_Inventarios.getDescripcion() );
            procedimiento_Crear_Inventario.setInt( 3, modelo_Inventarios.getCantidad_Disponible() );
            procedimiento_Crear_Inventario.setDouble( 4, modelo_Inventarios.getPrecio_Compra() );
            procedimiento_Crear_Inventario.setDouble( 5, modelo_Inventarios.getPrecio_Venta() );
            procedimiento_Crear_Inventario.setString( 6, modelo_Inventarios.getProveedor() );
            procedimiento_Crear_Inventario.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = false;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }


    @Override
    public int editar( Inventario modelo_Inventarios ) throws SQLException{
        int bandera = 0;
        try{
            CallableStatement procedimiento_Editar_Inventario = this.conexion.prepareCall( "{CALL editar_Inventario(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Inventario.setString( 1, modelo_Inventarios.getCodigo() );
            procedimiento_Editar_Inventario.setString( 2, modelo_Inventarios.getDescripcion() );
            procedimiento_Editar_Inventario.setInt( 3, modelo_Inventarios.getCantidad_Disponible() );
            procedimiento_Editar_Inventario.setDouble( 4, modelo_Inventarios.getPrecio_Compra() );
            procedimiento_Editar_Inventario.setDouble( 5, modelo_Inventarios.getPrecio_Venta() );
            procedimiento_Editar_Inventario.setString( 6, modelo_Inventarios.getProveedor() );
            bandera = procedimiento_Editar_Inventario.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int eliminar( String producto ) throws SQLException{
       int bandera = 0;
        try{
            CallableStatement procedimiento_Eliminar_Inventarios = this.conexion.prepareCall( "{CALL eliminar_Inventario(?)}" );
            procedimiento_Eliminar_Inventarios.setString( 1, producto );
            bandera = procedimiento_Eliminar_Inventarios.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public ArrayList<Inventario> consultar( Object producto ) {
       ArrayList<Inventario> inventario = new ArrayList<Inventario>();
        
        try{
            CallableStatement procedimiento_Inventario = this.conexion.prepareCall( "{CALL consulta_Inventario(?)}" );
            procedimiento_Inventario.setObject( 1, producto );
            ResultSet consulta = procedimiento_Inventario.executeQuery();
            
            while( consulta.next() ){
                modelo_Inventario = new Inventario( consulta.getString( 1 ), consulta.getString( 4 ), consulta.getInt( 5 ), consulta.getDouble( 6 ), consulta.getDouble( 2 ), consulta.getString( 7 ) );
                inventario.add( modelo_Inventario );
            }
            consulta.close();
        }catch( SQLException e1 ){System.out.println(e1);}finally{}
        return inventario;
    }

    @Override
    public ArrayList<Inventario> consultar_Inventario_Descripcion(String productos) {
        ArrayList<Inventario> inventario = new ArrayList<Inventario>();
        
        try{
            CallableStatement procedimiento_Inventario = this.conexion.prepareCall( "{CALL consulta_Inventario_Descripcion(?)}" );
            procedimiento_Inventario.setString( 1, productos );
            ResultSet consulta = procedimiento_Inventario.executeQuery();
            
            while( consulta.next() ){
                modelo_Inventario = new Inventario( consulta.getString( 1 ), consulta.getString( 4 ), consulta.getInt( 5 ), consulta.getDouble( 6 ), consulta.getDouble( 2 ), consulta.getString( 7 ) );
                inventario.add( modelo_Inventario );
            }
            consulta.close();
        }catch( SQLException e1 ){}finally{}
        return inventario;
    }
}
