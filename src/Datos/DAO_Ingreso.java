/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class DAO_Ingreso{
    private Connection conexion;
    private Usuario usuario;
    
    public DAO_Ingreso( Connection conexion ){
        this.conexion = conexion;
    }
    
    public Usuario iniciar_Sesion(String modelo_Ingreso) {
        try {
            CallableStatement procedimiento_Iniciar_Sesion = this.conexion.prepareCall("{CALL consulta_Usuarios(?)}");
            procedimiento_Iniciar_Sesion.setString(1, modelo_Ingreso);
            ResultSet consulta = procedimiento_Iniciar_Sesion.executeQuery();

            while (consulta.next()) {
                this.usuario = new Usuario(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getInt(5));
            }
            consulta.close();
        } catch (SQLException e1) {
        }
        return this.usuario;
    }
    
    public String rol_Sesion(String usuario) {
        String rol = null;
        try {
            CallableStatement procedimiento_Iniciar_Sesion = this.conexion.prepareCall("{CALL consulta_Rol_Sesion(?)}");
            procedimiento_Iniciar_Sesion.setString(1, usuario);
            ResultSet consulta = procedimiento_Iniciar_Sesion.executeQuery();

            while (consulta.next()) {
                rol = consulta.getString(1);
            }
            consulta.close();
        } catch (SQLException e1) {
            System.out.println(e1);
        }
        return rol;
    }
}
