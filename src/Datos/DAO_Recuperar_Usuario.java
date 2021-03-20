/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Sesion;
import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class DAO_Recuperar_Usuario{
    private Connection conexion;
    private Usuario modelo_Registro;
    
    public DAO_Recuperar_Usuario( Connection conexion_Database ){
        this.conexion = conexion_Database;
    }
    
    public Usuario consultar_Usuario(String usuario, String cedula) {
        try {
            CallableStatement procedimiento_Iniciar_Sesion = this.conexion.prepareCall("{CALL consulta_Datos_Usuario(?, ?)}");
            procedimiento_Iniciar_Sesion.setString(1, usuario);
            procedimiento_Iniciar_Sesion.setString(2, cedula);
            ResultSet consulta = procedimiento_Iniciar_Sesion.executeQuery();

            while (consulta.next()) {
                modelo_Registro = new Usuario(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getString(5), consulta.getString(6), consulta.getInt(7));
            }
            consulta.close();
        } catch (SQLException e1) {
        }
        return modelo_Registro;
    }

    public int editar(Usuario clase) throws SQLException {
        int bandera = 0;
        try {
            CallableStatement procedimiento_Editar_Usuario = this.conexion.prepareCall("{CALL editar_Usuario(?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Editar_Usuario.setString(1, clase.getCedula());
            procedimiento_Editar_Usuario.setString(2, clase.getNombre());
            procedimiento_Editar_Usuario.setString(3, clase.getApellido());
            procedimiento_Editar_Usuario.setString(4, clase.getCorreo());
            procedimiento_Editar_Usuario.setString(5, clase.getUsuario());
            procedimiento_Editar_Usuario.setString(6, clase.getClave());
            procedimiento_Editar_Usuario.setInt(7, clase.getRol());
            bandera = procedimiento_Editar_Usuario.executeUpdate();
            this.conexion.commit();
        } 
        catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }
    
    public int recuperacion_Clave(Sesion clase) throws SQLException {
        int bandera = 1;
        
        try {
            CallableStatement procedimiento_Cambio_Clave = this.conexion.prepareCall("{CALL recuperacion_Clave(?, ?)}");
            procedimiento_Cambio_Clave.setString(1, clase.getUsuario());
            procedimiento_Cambio_Clave.setString(2, clase.getClave());
            bandera = procedimiento_Cambio_Clave.executeUpdate();
           this.conexion.commit();
        } 
        catch (SQLException e1) {
            bandera = 1;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }
}
