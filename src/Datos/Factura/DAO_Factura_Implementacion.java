/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Factura;

import Modelo.Factura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Factura_Implementacion implements DAO_Factura{
    private Factura modelo_Factura;
    private Connection conexion;
    
    public DAO_Factura_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }
    
    @Override
    public boolean crear( Factura factura ) throws SQLException{
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Factura = this.conexion.prepareCall( "{CALL crear_Factura(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Factura.setString( 1, factura.getNo_Factura() );
            procedimiento_Crear_Factura.setString( 2, factura.getFecha() );
            procedimiento_Crear_Factura.setDouble( 3, factura.getV_Subtotal() );
            procedimiento_Crear_Factura.setDouble( 4, factura.getIVA() );
            procedimiento_Crear_Factura.setDouble( 5, factura.getValor_Total() );
            procedimiento_Crear_Factura.setString( 6, factura.getEstado() );
            procedimiento_Crear_Factura.setString( 7, factura.getCliente() );
            procedimiento_Crear_Factura.setString( 8, factura.getCantidad() );
            procedimiento_Crear_Factura.setString( 9, factura.getCodigo() );
            procedimiento_Crear_Factura.setString( 10, factura.getDescripcion() );
            procedimiento_Crear_Factura.setString( 11, factura.getV_Unitario() );
            procedimiento_Crear_Factura.setString( 12, factura.getV_Total() );
            procedimiento_Crear_Factura.setString( 13, factura.getVendedor() );
            procedimiento_Crear_Factura.setString( 14, factura.getObservaciones() );
            procedimiento_Crear_Factura.executeUpdate();
            this.conexion.commit();
        }    
        catch( SQLException e1 ){
            bandera = false;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public ArrayList<Factura> consultar( Object numero ) {
        ArrayList<Factura> lista_Factura = new ArrayList<Factura>();
        
        String cadena = String.valueOf(numero);
        String valor[] =  cadena.split(";");
        
        try{
            CallableStatement procedimiento_consulta_Factura = this.conexion.prepareCall( "{CALL consultar_Factura(?, ?)}" );
            procedimiento_consulta_Factura.setObject(1, valor[0]);
            procedimiento_consulta_Factura.setObject(2, valor[1]);
            ResultSet consulta = procedimiento_consulta_Factura.executeQuery();
            
            while( consulta.next() ){
                modelo_Factura = new Factura( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getDouble( 3 ), consulta.getDouble( 4 ), consulta.getDouble( 5 ), consulta.getString( 6 ), consulta.getString( 9 ), consulta.getString( 11 ), consulta.getString( 12 ), consulta.getString( 13 ), consulta.getString( 14 ), consulta.getString( 15 ), consulta.getString( 7 ), consulta.getString( 8 )  );
                lista_Factura.add( modelo_Factura );
            }
            consulta.close();
        } catch ( SQLException ex ) {}
        return lista_Factura;
    }

    @Override
    public int editar( Factura factura ) throws SQLException{
        int bandera = 0;
        
        try{
            CallableStatement procedimiento_Editar_Factura = this.conexion.prepareCall( "{CALL editar_Factura(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Factura.setString( 1, factura.getNo_Factura() );
            procedimiento_Editar_Factura.setString( 2, factura.getFecha() );
            procedimiento_Editar_Factura.setDouble( 3, factura.getV_Subtotal() );
            procedimiento_Editar_Factura.setDouble( 4, factura.getIVA() );
            procedimiento_Editar_Factura.setDouble( 5, factura.getValor_Total() );
            procedimiento_Editar_Factura.setString( 6, factura.getEstado() );
            procedimiento_Editar_Factura.setString( 7, factura.getCliente() );
            procedimiento_Editar_Factura.setString( 8, factura.getCantidad() );
            procedimiento_Editar_Factura.setString( 9, factura.getCodigo() );
            procedimiento_Editar_Factura.setString( 10, factura.getDescripcion() );
            procedimiento_Editar_Factura.setString( 11, factura.getV_Unitario() );
            procedimiento_Editar_Factura.setString( 12, factura.getV_Total() );
            procedimiento_Editar_Factura.setString( 13, factura.getVendedor() );
            procedimiento_Editar_Factura.setString( 14, factura.getObservaciones() );
            bandera = procedimiento_Editar_Factura.executeUpdate();
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
    public String consultar_Numero_Factura() {
        String numero_Factura = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Factura = this.conexion.prepareCall( "{CALL numero_Factura()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Factura.executeQuery();
            while(consulta.next() ){
                numero_Factura = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Factura;
    }

    @Override
    public ArrayList<Factura> consultar_Facturas_Adeudadas(String cliente, String tipo, String id) {
        ArrayList<Factura> lista_Factura = new ArrayList<Factura>();
        
        try{
            CallableStatement procedimiento_consulta_Factura = this.conexion.prepareCall( "{CALL consultar_Facturas_Adeudadas(?,?, ?)}" );
            procedimiento_consulta_Factura.setString( 1, cliente );
            procedimiento_consulta_Factura.setString( 2, tipo );
            procedimiento_consulta_Factura.setString( 3, id );
            ResultSet consulta = procedimiento_consulta_Factura.executeQuery();
            
            while( consulta.next() ){
                modelo_Factura = new Factura( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getDouble( 3 ), consulta.getDouble( 4 ), consulta.getDouble( 5 ), consulta.getString( 6 ), consulta.getString( 7 ), consulta.getString( 8 ), consulta.getString( 9 ), consulta.getString( 10 ), consulta.getString( 11 ), consulta.getString( 12 ), consulta.getString( 13 ), consulta.getString( 14 )  );
                lista_Factura.add( modelo_Factura );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Factura;
    }
    
    public ArrayList<Factura> consultar_Facturas_Fechas(String valor){
         ArrayList<Factura> lista_Factura = new ArrayList<Factura>();
         
        String cadena = String.valueOf(valor);
        String valores[] =  cadena.split(";");
        
        try{
            CallableStatement procedimiento_consulta_Factura = this.conexion.prepareCall( "{CALL consultar_Facturas_Fechas(?,?,?,?)}" );
            procedimiento_consulta_Factura.setString( 1, valores[0] );
            procedimiento_consulta_Factura.setString( 2, valores[1] );
            procedimiento_consulta_Factura.setString( 3, valores[2] );
            procedimiento_consulta_Factura.setString( 4, valores[3] );
            ResultSet consulta = procedimiento_consulta_Factura.executeQuery();
            
            while( consulta.next() ){
                modelo_Factura = new Factura( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getDouble( 3 ), consulta.getDouble( 4 ), consulta.getDouble( 5 ), consulta.getString( 6 ), consulta.getString( 9 ), consulta.getString( 11 ), consulta.getString( 12 ), consulta.getString( 13 ), consulta.getString( 14 ), consulta.getString( 15 ), consulta.getString( 7 ), consulta.getString( 8 )  );
                lista_Factura.add( modelo_Factura );
            }
            consulta.close();
        } catch ( SQLException ex ) {
        }finally{}
        return lista_Factura;
    }
}
