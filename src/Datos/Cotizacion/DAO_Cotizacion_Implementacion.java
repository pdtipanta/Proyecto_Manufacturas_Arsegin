/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Cotizacion;

import Modelo.Cotizacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Cotizacion_Implementacion implements DAO_Cotizaciones{
    private Cotizacion modelo_Cotizacion;
    private Connection conexion;
    
    public DAO_Cotizacion_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }

    @Override
    public boolean crear( Cotizacion cotizacion ) throws SQLException{
        boolean bandera = true;

        try{
            CallableStatement procedimiento_Crear_Cotizacion = this.conexion.prepareCall( "{CALL crear_Cotizacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Cotizacion.setString( 1, cotizacion.getNo_Cotizacion() );
            procedimiento_Crear_Cotizacion.setString( 2, cotizacion.getFecha() );
            procedimiento_Crear_Cotizacion.setString( 3, cotizacion.getTipo_Pago() );
            procedimiento_Crear_Cotizacion.setDouble( 4, cotizacion.getV_Subtotal() );
            procedimiento_Crear_Cotizacion.setDouble( 5, cotizacion.getIVA() );
            procedimiento_Crear_Cotizacion.setDouble( 6, cotizacion.getValor_Total() );
            procedimiento_Crear_Cotizacion.setString( 7, cotizacion.getCliente() );
            procedimiento_Crear_Cotizacion.setString( 8, cotizacion.getEmisor() );
            procedimiento_Crear_Cotizacion.setString( 9, cotizacion.getCantidad() );
            procedimiento_Crear_Cotizacion.setString( 10, cotizacion.getCodigo() );
            procedimiento_Crear_Cotizacion.setString( 11, cotizacion.getDescripcion() );
            procedimiento_Crear_Cotizacion.setString( 12, cotizacion.getV_Unitario() );
            procedimiento_Crear_Cotizacion.setString( 13, cotizacion.getV_Total() );
            procedimiento_Crear_Cotizacion.executeUpdate();
            this.conexion.commit();
        }    
        catch( SQLException e1 ){
            bandera = false;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public ArrayList<Cotizacion> consultar( Object cliente ) {
        ArrayList<Cotizacion> lista_Cotizaciones = new ArrayList<Cotizacion>();
        
        String cadena = String.valueOf(cliente);
        String valor[] =  cadena.split(";");
        
        try{
            CallableStatement procedimiento_consulta_Compras = this.conexion.prepareCall( "{CALL consultar_Cotizacion(?, ?)}" );
            procedimiento_consulta_Compras.setObject( 1, valor[0] );
            procedimiento_consulta_Compras.setObject( 2, valor[1] );
            ResultSet consulta = procedimiento_consulta_Compras.executeQuery();
            
            while( consulta.next() ){
                modelo_Cotizacion = new Cotizacion( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getString( 3 ), consulta.getDouble( 4 ), consulta.getDouble( 5 ), consulta.getDouble( 6 ), consulta.getString( 7 ), consulta.getString( 8 ), consulta.getString( 10 ), consulta.getString( 11 ), consulta.getString( 12 ), consulta.getString( 13 ), consulta.getString( 14 )  );
                lista_Cotizaciones.add( modelo_Cotizacion );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Cotizaciones;
    }

    @Override
    public int editar( Cotizacion cotizacion ) throws SQLException{
        int bandera = 0;
        
        try{
            CallableStatement procedimiento_Editar_Cotizacion = this.conexion.prepareCall( "{CALL editar_Cotizacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Cotizacion.setString( 1, cotizacion.getNo_Cotizacion() );
            procedimiento_Editar_Cotizacion.setString( 2, cotizacion.getFecha() );
            procedimiento_Editar_Cotizacion.setString( 3, cotizacion.getTipo_Pago() );
            procedimiento_Editar_Cotizacion.setDouble( 4, cotizacion.getV_Subtotal() );
            procedimiento_Editar_Cotizacion.setDouble( 5, cotizacion.getIVA() );
            procedimiento_Editar_Cotizacion.setDouble( 6, cotizacion.getValor_Total() );
            procedimiento_Editar_Cotizacion.setString( 7, cotizacion.getCliente() );
            procedimiento_Editar_Cotizacion.setString(8, cotizacion.getEmisor());
            procedimiento_Editar_Cotizacion.setString(9, cotizacion.getCantidad());
            procedimiento_Editar_Cotizacion.setString(10, cotizacion.getCodigo());
            procedimiento_Editar_Cotizacion.setString(11, cotizacion.getDescripcion());
            procedimiento_Editar_Cotizacion.setString(12, cotizacion.getV_Unitario());
            procedimiento_Editar_Cotizacion.setString(13, cotizacion.getV_Total());
            bandera = procedimiento_Editar_Cotizacion.executeUpdate();
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
    public String consultar_Numero_Cotizacion() {
        String numero_Factura = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Cotizacion = this.conexion.prepareCall( "{CALL numero_Cotizacion()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Cotizacion.executeQuery();
            while(consulta.next() ){
                numero_Factura = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Factura;
    }
    
    @Override
    public ArrayList<Cotizacion> consultar_Cotizacion_Fechas(String valor) {
        ArrayList<Cotizacion> lista_Cotizacion = new ArrayList<Cotizacion>();

        String cadena = String.valueOf(valor);
        String valores[] = cadena.split(";");

        try{
            CallableStatement procedimiento_consulta_Factura = this.conexion.prepareCall( "{CALL consultar_Cotizaciones_Fechas(?,?,?,?)}" );
            procedimiento_consulta_Factura.setString( 1, valores[0] );
            procedimiento_consulta_Factura.setString( 2, valores[1] );
            procedimiento_consulta_Factura.setString( 3, valores[2] );
            procedimiento_consulta_Factura.setString( 4, valores[3] );
            ResultSet consulta = procedimiento_consulta_Factura.executeQuery();
            
            while( consulta.next() ){
                modelo_Cotizacion = new Cotizacion( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getString( 3 ), consulta.getDouble( 4 ), consulta.getDouble( 5 ), consulta.getDouble( 6 ), consulta.getString( 7 ), consulta.getString( 8 ), consulta.getString( 10 ), consulta.getString( 11 ), consulta.getString( 12 ), consulta.getString( 13 ), consulta.getString( 14 ) );
                lista_Cotizacion.add( modelo_Cotizacion );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Cotizacion;
    }
}
