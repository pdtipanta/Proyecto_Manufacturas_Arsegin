/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Cifrado.MD5Encryptacion;
import Datos.DAO_Usuario;
import Datos.DAO_Roles;
import Modelo.Usuario;
import Vista.Registro_Usuarios.Panel_Registro;
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
 * @author LENOVO
 */
public class Controlador_Panel_Registro implements ActionListener{
    private Vista_Principal     vista;
    private Connection          conexion_DataBase;
    private Usuario             modelo_Registro;
    private String              id_Usuario; 
    private Panel_Registro      panel_Registro = new Panel_Registro();

    public Controlador_Panel_Registro(Vista_Principal vista, Connection conexion_DataBase) {
        this.vista = vista;
        this.conexion_DataBase = conexion_DataBase;
        this.panel_Registro.boton_Registro_Usuario.addActionListener(this);
        this.panel_Registro.boton_Buscar.addActionListener(this);
        this.panel_Registro.boton_Modificar.addActionListener(this);
        this.panel_Registro.boton_Nuevo.addActionListener(this);
        this.panel_Registro.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Registro);
        panel_Registro.setVisible(true);
        vista.Panel_Contenedor.validate();
        cargar_Roles();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Registro.boton_Registro_Usuario) {
            if (panel_Registro.etiquetas()) {

                try {
                    if (new DAO_Usuario(this.conexion_DataBase).registrar(new Usuario(panel_Registro.campo_Cedula_Registro.getText(), panel_Registro.campo_Nombre_Registro.getText(), panel_Registro.campo_Apellido_Registro.getText(), panel_Registro.campo_Correo_Registro.getText(), panel_Registro.campo_Uusario_Registro.getText(), cifrado_MD5(panel_Registro.campo_Contraseniaa_Registro.getText()), panel_Registro.combo_Rol_Registro.getSelectedIndex()), (String) panel_Registro.combo_Rol_Registro.getSelectedItem()) > 0) {
                        JOptionPane.showMessageDialog(null, "Usuario registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        vista.Panel_Contenedor.removeAll();
                        new Controlador_Panel_Ingreso(this.vista).iniciar();
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Registro.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }

        if (ae.getSource() == this.panel_Registro.boton_Modificar) {
            if (this.panel_Registro.verificar_Etiquetas_Usuario()) {
                modelo_Registro = new Usuario(panel_Registro.campo_Cedula_Registro.getText(), panel_Registro.campo_Nombre_Registro.getText(), panel_Registro.campo_Apellido_Registro.getText(), panel_Registro.campo_Correo_Registro.getText(), panel_Registro.combo_Rol_Registro.getSelectedIndex());

                try {
                    if (new DAO_Usuario(this.conexion_DataBase).editar(this.modelo_Registro, this.id_Usuario) == 1) {
                        this.panel_Registro.limpiar_Campos();
                        this.panel_Registro.limpiar_Etiquetas();
                        this.panel_Registro.botones(true, false, true, true, true, true);
                        JOptionPane.showMessageDialog(null, "Usuario actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Registro.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }

        if (ae.getSource() == this.panel_Registro.boton_Buscar) {
            String[] valor = new Controlador_Dialogo_Buscar_Usuarios(this.vista, this.conexion_DataBase).iniciar();
            
            if (valor != null) {
                this.panel_Registro.limpiar_Etiquetas();
                this.id_Usuario = valor[0];
                this.panel_Registro.insertar_Campos_Usuario(valor[0], valor[1], valor[2], valor[3], valor[4]);
                this.panel_Registro.botones(false, true, false, false, false, false);
            }
        }

        if (ae.getSource() == this.panel_Registro.boton_Nuevo) {
            this.panel_Registro.limpiar_Campos();
            this.panel_Registro.botones(true, false, true, true, true, true);
            this.panel_Registro.limpiar_Etiquetas();
            this.id_Usuario = null;
            this.modelo_Registro = null;
        }

        if (ae.getSource() == this.panel_Registro.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void cargar_Roles() {
        this.panel_Registro.combo_Rol_Registro.setModel(new DAO_Roles(this.conexion_DataBase).consultar_Roles());
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
}
