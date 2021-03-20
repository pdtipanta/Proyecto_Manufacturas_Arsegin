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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Usuario {
    private Connection conexion;
    private Usuario usuario;
    
    public DAO_Usuario(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }

    public int registrar(Usuario modelo_Registro, String rol) throws SQLException {
        int bandera = 0;

        try {
            CallableStatement procedimiento_Crear_Usuario = this.conexion.prepareCall("{CALL crear_Usuario(?, ?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Crear_Usuario.setString(1, modelo_Registro.getCedula());
            procedimiento_Crear_Usuario.setString(2, modelo_Registro.getNombre());
            procedimiento_Crear_Usuario.setString(3, modelo_Registro.getApellido());
            procedimiento_Crear_Usuario.setString(4, modelo_Registro.getCorreo());
            procedimiento_Crear_Usuario.setString(5, modelo_Registro.getUsuario());
            procedimiento_Crear_Usuario.setString(6, modelo_Registro.getClave());
            procedimiento_Crear_Usuario.setInt(7, modelo_Registro.getRol());
            procedimiento_Crear_Usuario.setString(8, rol);
            bandera = procedimiento_Crear_Usuario.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        }catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    public ArrayList<Usuario> consultar(String id) {
        ArrayList<Usuario> valores_Usuarios = new ArrayList<Usuario>();

        try {
            CallableStatement procedimiento_Consultar_Usuario = this.conexion.prepareCall("{CALL consultar_Usuarios_Registrados(?)}");
            procedimiento_Consultar_Usuario.setString(1, id);
            ResultSet consulta = procedimiento_Consultar_Usuario.executeQuery();

            while (consulta.next()) {
                usuario = new Usuario(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getInt(5));
                valores_Usuarios.add(usuario);
            }
            consulta.close();
        } catch (SQLException e1) {
        }
        return valores_Usuarios;
    }

    public int editar(Usuario clase, String clave) throws SQLException {
        int bandera = 0;
        try {
            CallableStatement procedimiento_Editar_Usuario = this.conexion.prepareCall("{CALL actualizar_Informacion_Usuario(?, ?, ?, ?, ?, ?)}");
            procedimiento_Editar_Usuario.setString(1, clase.getCedula());
            procedimiento_Editar_Usuario.setString(2, clase.getNombre());
            procedimiento_Editar_Usuario.setString(3, clase.getApellido());
            procedimiento_Editar_Usuario.setString(4, clase.getCorreo());
            procedimiento_Editar_Usuario.setInt(5, clase.getRol());
            procedimiento_Editar_Usuario.setString(6, clave);
            bandera = procedimiento_Editar_Usuario.executeUpdate();
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
}
