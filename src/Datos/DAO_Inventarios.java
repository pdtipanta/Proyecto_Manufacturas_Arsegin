/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Inventario;
import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class DAO_Inventarios {
    private Conexion_Database conexion_Database;
    private PreparedStatement codigoSQL;
    private Connection conexion;
    private Inventario modelo_Inventario;
    
    public DAO_Inventarios( Conexion_Database conexion_Database ){
        this.conexion_Database = conexion_Database;
        this.conexion = conexion_Database.obtenerConeccion();
    }
    
    public boolean crear_Inventario( Inventario modelo_Inventario ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Inventario = conexion.prepareCall( "{CALL crear_Inventario(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Inventario.setString( 1, modelo_Inventario.getCodigo() );
            procedimiento_Crear_Inventario.setString( 2, modelo_Inventario.getDescripcion() );
            procedimiento_Crear_Inventario.setInt( 3, modelo_Inventario.getCantidad_Disponible() );
            procedimiento_Crear_Inventario.setDouble( 4, modelo_Inventario.getPrecio_Compra() );
            procedimiento_Crear_Inventario.setDouble( 5, modelo_Inventario.getPrecio_Venta() );
            procedimiento_Crear_Inventario.setString( 6, modelo_Inventario.getProveedor() );
            ResultSet escritura_Datos =  procedimiento_Crear_Inventario.executeQuery();
            escritura_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
            System.out.println("no se escribio");
        }
        return bandera;
    }
   

    public ArrayList<Inventario> consultar_Inventarios( String codigo ){
        ArrayList<Inventario> inventario = new ArrayList<Inventario>();
        
        try{
            CallableStatement procedimiento_Inventario = conexion.prepareCall( "{CALL consulta_Inventario(?)}" );
            procedimiento_Inventario.setString( 1, codigo );
            ResultSet consulta = procedimiento_Inventario.executeQuery();
            
            while( consulta.next() ){
                modelo_Inventario = new Inventario( consulta.getString( 1 ), consulta.getString( 4 ), consulta.getInt( 5 ), consulta.getDouble( 6 ), consulta.getDouble( 2 ), consulta.getString( 7 ) );
                inventario.add( modelo_Inventario );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }
        return inventario;
    }
    
    public Inventario consultar_Inventario( String producto ){
        try{
            CallableStatement procedimiento_Inventario = conexion.prepareCall( "{CALL consultar_Inventario(?)}" );
            procedimiento_Inventario.setString( 1, producto );
            ResultSet consulta = procedimiento_Inventario.executeQuery();
            
            while( consulta.next() ){
                modelo_Inventario = new Inventario( consulta.getString( 1 ), consulta.getString( 4 ), consulta.getInt( 5 ), consulta.getDouble( 6 ), consulta.getDouble( 2 ), consulta.getString( 7 ) );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }
        return modelo_Inventario;
    }
    
    public boolean editar_Inventarios( Inventario modelo_Inventarios ){
        boolean bandera = true;
       
        try{
            CallableStatement procedimiento_Editar_Inventario = conexion.prepareCall( "{CALL editar_Inventario(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Inventario.setString( 1, modelo_Inventarios.getCodigo() );
            procedimiento_Editar_Inventario.setString( 2, modelo_Inventarios.getDescripcion() );
            procedimiento_Editar_Inventario.setInt( 3, modelo_Inventarios.getCantidad_Disponible() );
            procedimiento_Editar_Inventario.setDouble( 4, modelo_Inventarios.getPrecio_Compra() );
            procedimiento_Editar_Inventario.setDouble( 5, modelo_Inventarios.getPrecio_Venta() );
            procedimiento_Editar_Inventario.setString( 6, modelo_Inventarios.getProveedor() );
            ResultSet editar_Datos = procedimiento_Editar_Inventario.executeQuery();
            editar_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public boolean eliminar_Inventarios( String producto ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Eliminar_Inventarios = conexion.prepareCall( "{CALL eliminar_Inventario(?)}" );
            procedimiento_Eliminar_Inventarios.setString( 1, producto );
            ResultSet consulta = procedimiento_Eliminar_Inventarios.executeQuery();
        }
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
}
