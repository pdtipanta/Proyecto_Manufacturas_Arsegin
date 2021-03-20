/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Maquila;
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
public class DAO_Maquilas {
    private Conexion_Database conexion_Database;
    private PreparedStatement codigoSQL;
    private Connection conexion;
    
    public DAO_Maquilas( Conexion_Database conexion_Database ){
        this.conexion_Database = conexion_Database;
        this.conexion = conexion_Database.obtenerConeccion();
    }
    
    public boolean crear_Maquila( Maquila modelo_Maquila ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Maquila = conexion.prepareCall( "{CALL crear_Maquila(?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Maquila.setString( 1, modelo_Maquila.getMaquila() );
            procedimiento_Crear_Maquila.setString( 2, modelo_Maquila.getRUC() );
            procedimiento_Crear_Maquila.setString( 3, modelo_Maquila.getDireccion() );
            procedimiento_Crear_Maquila.setString( 4, modelo_Maquila.getTelefono() );
            procedimiento_Crear_Maquila.setString( 5, modelo_Maquila.getServicio() );
            ResultSet escritura_Datos =  procedimiento_Crear_Maquila .executeQuery();
            escritura_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public String[] consultar_Maquilas( String maquila ){
        String datos_Maquila[] = new String[5];
        try{
            CallableStatement procedimiento_Usuario = conexion.prepareCall( "{CALL consulta_Maquila(?)}" );
            procedimiento_Usuario.setString( 1, maquila );
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            
            while(consulta.next() ){
                datos_Maquila[0] = consulta.getString( 1 );
                datos_Maquila[1] = consulta.getString( 2 ); 
                datos_Maquila[2] = consulta.getString( 3 );
                datos_Maquila[3] = consulta.getString( 4 );
                datos_Maquila[4] = consulta.getString( 5 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }
        return datos_Maquila;
    }
    
    public boolean editar_Maquila( Maquila modelo_Maquila ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Editar_Maquila = conexion.prepareCall( "{CALL editar_Maquila(?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Maquila.setString( 1, modelo_Maquila.getMaquila() );
            procedimiento_Editar_Maquila.setString( 2, modelo_Maquila.getRUC() );
            procedimiento_Editar_Maquila.setString( 3, modelo_Maquila.getDireccion() );
            procedimiento_Editar_Maquila.setString( 4, modelo_Maquila.getTelefono() );
            procedimiento_Editar_Maquila.setString( 5, modelo_Maquila.getServicio() );
            ResultSet editar_Datos =  procedimiento_Editar_Maquila.executeQuery();
            editar_Datos.close();
        }    
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public boolean eliminar_Maquila( String maquila ){
        boolean bandera = true;
        try{
            CallableStatement procedimiento_eliminar_Maquila = conexion.prepareCall( "{CALL eliminar_Maquila(?)}" );
            procedimiento_eliminar_Maquila.setString( 1, maquila );
            ResultSet consulta = procedimiento_eliminar_Maquila.executeQuery();
        }
        catch( SQLException e1 ){
            bandera = false;
        }
        return bandera;
    }
    
    public DefaultComboBoxModel consultar_Maquila( String maquila){
        DefaultComboBoxModel modelo_Autocompletar = new DefaultComboBoxModel();
        
        try{
            CallableStatement procedimiento_consultar_Maquila = conexion.prepareCall( "{CALL consulta_Maquila(?)}" );
            procedimiento_consultar_Maquila.setString( 1, maquila );
            ResultSet consulta = procedimiento_consultar_Maquila.executeQuery();
            
            while(consulta.next() ){
                 modelo_Autocompletar.addElement( consulta.getString( 1 ) ); 
            }
        }catch( SQLException ex ){
        }
        return modelo_Autocompletar;
    }
}
