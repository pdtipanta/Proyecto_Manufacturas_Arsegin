/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Cifrado.MD5Encryptacion;
import Datos.Conexion_Database;
import Datos.DAO_Recuperar_Usuario;
import Mail.Email;
import Modelo.Sesion;
import Modelo.Usuario;
import Vista.Panel_Recuperar_Clave;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Recuperacion_Clave implements ActionListener, KeyListener{
    private Vista_Principal         vista;
    private Connection              conexion_DataBase;
    private Usuario                 modelo_Registro;
    private String                  codigo;
    private Panel_Recuperar_Clave   panel_Recuperar_Clave = new Panel_Recuperar_Clave();

    public Controlador_Recuperacion_Clave(Vista_Principal vista, Connection conexion_DataBase) {
        this.vista = vista;
        this.conexion_DataBase = conexion_DataBase;
        this.panel_Recuperar_Clave.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Recuperar_Clave.boton_Recuperacion_Recuperar.addActionListener(this);
        this.panel_Recuperar_Clave.boton_Recuperacion_Guardar.addActionListener(this);
        this.panel_Recuperar_Clave.campo_Recuperacion_Codigo.addKeyListener(this);
    }

    public void iniciar() {
        this.vista.menu_Principal.setVisible(false);
        this.vista.Panel_Contenedor.add(panel_Recuperar_Clave);
        this.panel_Recuperar_Clave.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.modelo_Ingreso();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Recuperar_Clave.boton_Recuperacion_Recuperar) {
            if (this.panel_Recuperar_Clave.etiquetas()) {
                this.modelo_Registro = new DAO_Recuperar_Usuario(this.conexion_DataBase).consultar_Usuario(this.panel_Recuperar_Clave.campo_Recuperacion_Usuario.getText(), this.panel_Recuperar_Clave.campo_Recuperacion_CI.getText());

                if (modelo_Registro != null) {
                    new Email().envio_Correo(modelo_Registro.getCorreo(), codigo_Verificacion());
                    this.panel_Recuperar_Clave.activar_Ingreso_Codigo(true);
                    this.panel_Recuperar_Clave.etiqueta_Usuario_No_Existe.setVisible(false);
                    this.panel_Recuperar_Clave.etiqueta_Correo.setVisible(true);
                } else {
                    this.panel_Recuperar_Clave.etiqueta_Usuario_No_Existe.setVisible(true);
                }
            }
        }

        if (ae.getSource() == this.panel_Recuperar_Clave.boton_Recuperacion_Guardar) {
            if (this.panel_Recuperar_Clave.nueva_Clave()) {
                if (this.panel_Recuperar_Clave.campo_Recuperacion_Nueva.getText().equals(this.panel_Recuperar_Clave.campo_Recuperacion_Confirmar.getText())) {

                    try {
                        if (new DAO_Recuperar_Usuario(this.conexion_DataBase).recuperacion_Clave(new Sesion(this.panel_Recuperar_Clave.campo_Recuperacion_Usuario.getText(), cifrado_MD5(this.panel_Recuperar_Clave.campo_Recuperacion_Confirmar.getText()))) == 0) {
                            JOptionPane.showMessageDialog(null, "Clave actualizada", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                            this.vista.Panel_Contenedor.removeAll();
                            new Controlador_Panel_Ingreso(this.vista).iniciar();
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    this.panel_Recuperar_Clave.etiqueta_Contraseñas_No_Coinciden.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar valores", "Recuperacion Clave", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (ae.getSource() == this.panel_Recuperar_Clave.boton_Cerrar_Sesion) {
            this.vista.Panel_Contenedor.removeAll();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == this.panel_Recuperar_Clave.campo_Recuperacion_Codigo) {
            if (this.panel_Recuperar_Clave.campo_Recuperacion_Codigo.getText().equals(this.codigo)) {
                this.panel_Recuperar_Clave.etiqueta_Aceptado.setVisible(true);
                this.panel_Recuperar_Clave.activar_Ingreso_Nueva_Clave(true);
                this.panel_Recuperar_Clave.boton_Recuperacion_Guardar.setEnabled(true);
            } else {
                this.panel_Recuperar_Clave.etiqueta_Aceptado.setVisible(false);
                this.panel_Recuperar_Clave.activar_Ingreso_Nueva_Clave(false);
                this.panel_Recuperar_Clave.boton_Recuperacion_Guardar.setEnabled(false);
            }
        }
    }
    
    public void modelo_Ingreso() {
        Conexion_Database conexion_Database = new Conexion_Database(new Sesion("root", ""));
        this.conexion_DataBase = conexion_Database.iniciar();
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

    public String codigo_Verificacion() {
        String codigo = "";
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < 6; i++) {
                codigo = codigo + number.nextInt(10);
            }
        } catch (NoSuchAlgorithmException nsae) {
        }
        this.codigo = codigo;
        return codigo;
    }
}
