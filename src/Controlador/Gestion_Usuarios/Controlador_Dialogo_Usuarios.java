/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Cifrado.MD5Encryptacion;
import Datos.DAO_Roles;
import Datos.DAO_Usuario;
import Modelo.Usuario;
import Vista.Registro_Usuarios.Dialogo_Usuarios;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Usuarios implements ActionListener{
    private final Vista_Principal     vista;
    private final Connection          conexion_DataBase;
    private String                    id_Usuario; 
    private final Dialogo_Usuarios    dialogo_Usuarios;
    private boolean                   bandera = false;
    private final String              actividad;
    private final String[]            valor;

    public Controlador_Dialogo_Usuarios(Vista_Principal vista, Connection conexion_DataBase, String[] valor, String actividad) {
        this.vista = vista;
        this.conexion_DataBase = conexion_DataBase;
        this.valor = valor;
        this.actividad = actividad;
        this.dialogo_Usuarios = new Dialogo_Usuarios(this.vista, true);
        this.dialogo_Usuarios.boton_Registro_Usuario.addActionListener(this);
    }

    public boolean iniciar() {
        this.cargar_Roles();
        this.tipo_Actividad();
        this.dialogo_Usuarios.setVisible(true);
        return this.bandera;
    }

    public void tipo_Actividad() {
        if (this.actividad.equals("Modificar")) {
            this.dialogo_Usuarios.insertar_Campos_Usuario(this.valor);
            this.id_Usuario = this.valor[0];
            this.dialogo_Usuarios.campo_Uusario_Registro.setEditable(false);
            this.dialogo_Usuarios.campo_Recontrasenia_Registro.setEditable(false);
            this.dialogo_Usuarios.campo_Contraseniaa_Registro.setEditable(false);
        }
    }

    public void cargar_Roles() {
        this.dialogo_Usuarios.combo_Rol_Registro.setModel(new DAO_Roles(this.conexion_DataBase).consultar_Roles());
    }

    public String cifrado_MD5(String dato_Cifrar) {
        String cifrado = null;
        try {
            cifrado = MD5Encryptacion.encrypt(dato_Cifrar);
        } catch (NoSuchAlgorithmException ex) {
        } catch (UnsupportedEncodingException ex) {
        }
        return cifrado;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Usuarios.boton_Registro_Usuario) {
            if (this.actividad.equals("Registrar")) {
                if (this.dialogo_Usuarios.etiquetas()) {
                    try {
                        if (new DAO_Usuario(this.conexion_DataBase).registrar(new Usuario(this.dialogo_Usuarios.campo_Cedula_Registro.getText(), this.dialogo_Usuarios.campo_Nombre_Registro.getText(), this.dialogo_Usuarios.campo_Apellido_Registro.getText(), this.dialogo_Usuarios.campo_Correo_Registro.getText(), this.dialogo_Usuarios.campo_Uusario_Registro.getText(), cifrado_MD5(this.dialogo_Usuarios.campo_Contraseniaa_Registro.getText()), this.dialogo_Usuarios.combo_Rol_Registro.getSelectedIndex()), (String) this.dialogo_Usuarios.combo_Rol_Registro.getSelectedItem()) > 0) {
                            this.bandera = true;
                            this.dialogo_Usuarios.dispose();
                            JOptionPane.showMessageDialog(null, "Usuario registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Usuarios.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }

            if (this.actividad.equals("Modificar")) {
                if (this.dialogo_Usuarios.verificar_Etiquetas_Usuario()) {
                    Usuario usuario = new Usuario(this.dialogo_Usuarios.campo_Cedula_Registro.getText(), this.dialogo_Usuarios.campo_Nombre_Registro.getText(), this.dialogo_Usuarios.campo_Apellido_Registro.getText(), this.dialogo_Usuarios.campo_Correo_Registro.getText(), this.dialogo_Usuarios.combo_Rol_Registro.getSelectedIndex());

                    try {
                        if (new DAO_Usuario(this.conexion_DataBase).editar(usuario, this.id_Usuario) == 1) {
                            this.bandera = true;
                            this.dialogo_Usuarios.dispose();
                            JOptionPane.showMessageDialog(null, "Usuario actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Usuarios.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }
        }
    }
}
