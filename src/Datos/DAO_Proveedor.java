/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author David
 */
public class DAO_Proveedor {
    private Conexion_Database conexion_Database;
    private PreparedStatement codigoSQL;
    private Connection conexion;
    
    public DAO_Proveedor( Conexion_Database conexion_Database ){
        this.conexion_Database = conexion_Database;
        this.conexion = conexion_Database.obtenerConeccion();
    }
    
    public boolean crear_Proveedor( Proveedor modelo_Proveedor ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Proveedor = conexion.prepareCall( "{CALL crear_Proveedores(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Proveedor.setString( 1, modelo_Proveedor.getProveedor() );
            procedimiento_Crear_Proveedor.setString( 2, modelo_Proveedor.getRUC() );
            procedimiento_Crear_Proveedor.setString( 3, modelo_Proveedor.getDireccion() );
            procedimiento_Crear_Proveedor.setString( 4, modelo_Proveedor.getCorreo() );
            procedimiento_Crear_Proveedor.setString( 5, modelo_Proveedor.getTelefono() );
            procedimiento_Crear_Proveedor.setString( 6, modelo_Proveedor.getProductos() );
            ResultSet escritura_Datos =  procedimiento_Crear_Proveedor.executeQuery();
            escritura_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public String[] consultar_Proveedores( String proveedor ){
        String datos_Usuario[] = new String[6];
        try{
            CallableStatement procedimiento_Usuario = conexion.prepareCall( "{CALL consulta_Proveedores(?)}" );
            procedimiento_Usuario.setString( 1, proveedor );
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            
            while(consulta.next() ){
                datos_Usuario[0] = consulta.getString( 1 );
                datos_Usuario[1] = consulta.getString( 2 ); 
                datos_Usuario[2] = consulta.getString( 3 );
                datos_Usuario[3] = consulta.getString( 4 );
                datos_Usuario[4] = consulta.getString( 5 );
                datos_Usuario[5] = consulta.getString( 6 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }
        return datos_Usuario;
        
    }
    
    public boolean editar_Proveedores( Proveedor modelo_Proveedor ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Editar_Proveedores = conexion.prepareCall( "{CALL editar_Proveedores(?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Proveedores.setString( 1, modelo_Proveedor.getProveedor() );
            procedimiento_Editar_Proveedores.setString( 2, modelo_Proveedor.getRUC() );
            procedimiento_Editar_Proveedores.setString( 3, modelo_Proveedor.getDireccion() );
            procedimiento_Editar_Proveedores.setString( 4, modelo_Proveedor.getCorreo() );
            procedimiento_Editar_Proveedores.setString( 5, modelo_Proveedor.getTelefono() );
            procedimiento_Editar_Proveedores.setString( 6, modelo_Proveedor.getProductos() );
            ResultSet editar_Datos =  procedimiento_Editar_Proveedores.executeQuery();
            editar_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public boolean eliminar_Proveedores( String proveedor ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_eliminarProveedor = conexion.prepareCall( "{CALL eliminar_Proveedores(?)}" );
            procedimiento_eliminarProveedor.setString( 1, proveedor );
            ResultSet consulta = procedimiento_eliminarProveedor.executeQuery();
        }
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public DefaultComboBoxModel consultar_Proveedor( String proveedor ){
        DefaultComboBoxModel modelo_Autocompletar = new DefaultComboBoxModel();
        
        try{
            CallableStatement procedimiento_consultarProveedores = conexion.prepareCall( "{CALL consulta_Proveedores(?)}" );
            procedimiento_consultarProveedores.setString( 1, proveedor );
            ResultSet consulta = procedimiento_consultarProveedores.executeQuery();
            
            while( consulta.next() ){
                 modelo_Autocompletar.addElement( consulta.getString( 1 ) ); 
            }
            
        }catch( SQLException ex ){
        }
        return modelo_Autocompletar;
    }
}
