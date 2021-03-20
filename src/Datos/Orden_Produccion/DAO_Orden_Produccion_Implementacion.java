/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Orden_Produccion;

import Modelo.Orden_Produccion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Orden_Produccion_Implementacion implements DAO_Orden_Produccion{
    private Orden_Produccion modelo_Orden_Produccion;
    private Connection conexion;
    
    public DAO_Orden_Produccion_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }
    
    @Override
    public boolean crear( Orden_Produccion orden ) throws SQLException{
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Orden = this.conexion.prepareCall( "{CALL crear_Orden_Produccion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Orden.setString( 1, orden.getNumero_Orden() );
            procedimiento_Crear_Orden.setString( 2, orden.getFecha() );
            procedimiento_Crear_Orden.setDouble( 3, orden.getV_Pagar() );
            procedimiento_Crear_Orden.setString( 4, orden.getEstado() );
            procedimiento_Crear_Orden.setString( 5, orden.getObservaciones() );
            procedimiento_Crear_Orden.setString( 6, orden.getMaquila() );
            procedimiento_Crear_Orden.setString( 7, orden.getCantidad() );
            procedimiento_Crear_Orden.setString( 8, orden.getDescripcion() );
            procedimiento_Crear_Orden.setString( 9, orden.getV_Unitario() );
            procedimiento_Crear_Orden.setString( 10, orden.getV_Total() );
            procedimiento_Crear_Orden.executeUpdate();
            this.conexion.commit();
        }    
        catch( SQLException e1 ){
            bandera = false;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public ArrayList<Orden_Produccion> consultar( Object maquila ) {
        ArrayList<Orden_Produccion> lista_Orden = new ArrayList<Orden_Produccion>();
        
        try{
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall( "{CALL consultar_Orden_Produccion(?)}" );
            procedimiento_consulta_Orden.setObject( 1, maquila );
            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();
            
            while( consulta.next() ){
                modelo_Orden_Produccion = new Orden_Produccion( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getDouble( 3 ), consulta.getString( 4 ), consulta.getString( 5 ), consulta.getString( 6 ), consulta.getString( 8 ), consulta.getString( 9 ), consulta.getString( 10 ), consulta.getString( 11 ) );
                lista_Orden.add( modelo_Orden_Produccion );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Orden;
    }

    @Override
    public int editar( Orden_Produccion orden ) throws SQLException{
        int bandera = 0;
        
        try{
            CallableStatement procedimiento_Editar_Orden = this.conexion.prepareCall( "{CALL editar_Orden_Produccion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Orden.setString( 1, orden.getNumero_Orden() );
            procedimiento_Editar_Orden.setString( 2, orden.getFecha() );
            procedimiento_Editar_Orden.setDouble( 3, orden.getV_Pagar() );
            procedimiento_Editar_Orden.setString( 4, orden.getEstado() );
            procedimiento_Editar_Orden.setString( 5, orden.getObservaciones() );
            procedimiento_Editar_Orden.setString( 6, orden.getMaquila() );
            procedimiento_Editar_Orden.setString( 7, orden.getCantidad() );
            procedimiento_Editar_Orden.setString( 8, orden.getDescripcion() );
            procedimiento_Editar_Orden.setString( 9, orden.getV_Unitario() );
            procedimiento_Editar_Orden.setString( 10, orden.getV_Total() );
            bandera = procedimiento_Editar_Orden.executeUpdate();
            this.conexion.commit();
        }    
        catch( SQLException e1 ){
            bandera = 0;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public int eliminar(String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consultar_Numero_Orden() {
        String numero_Factura = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Orden = this.conexion.prepareCall( "{CALL numero_Orden()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Orden.executeQuery();
            while(consulta.next() ){
                numero_Factura = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Factura;
    }

    @Override
    public ArrayList<Orden_Produccion> consultar_Ordenes_Produccion_Fechas(String valor) {
        ArrayList<Orden_Produccion> lista_Ordenes = new ArrayList<Orden_Produccion>();

        String cadena = String.valueOf(valor);
        String valores[] = cadena.split(";");
        
        try{
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall( "{CALL consultar_Orden_Produccion_Fechas(?, ?)}" );
            procedimiento_consulta_Orden.setString( 1, valores[0] );
            procedimiento_consulta_Orden.setString( 2, valores[1] );
            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();
            
            while( consulta.next() ){
                modelo_Orden_Produccion = new Orden_Produccion( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getDouble( 3 ), consulta.getString( 4 ), consulta.getString( 5 ), consulta.getString( 6 ), consulta.getString( 8 ), consulta.getString( 9 ), consulta.getString( 10 ), consulta.getString( 11 ) );
                lista_Ordenes.add( modelo_Orden_Produccion );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Ordenes;
    }
}
