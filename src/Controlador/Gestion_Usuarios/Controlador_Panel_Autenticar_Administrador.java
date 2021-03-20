/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Cifrado.MD5Encryptacion;
import Datos.Conexion_Database;
import Datos.DAO_Ingreso;
import Modelo.Sesion;
import Vista.Panel_Autenticar_Administrador;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

/**
 *
 * @author LENOVO
 */
public class Controlador_Panel_Autenticar_Administrador implements ActionListener {
    private Vista_Principal                 vista;
    private Connection                      conexion_DataBase;
    private Panel_Autenticar_Administrador  panel_Autenticar_Administrador = new Panel_Autenticar_Administrador();
    
    public Controlador_Panel_Autenticar_Administrador(Vista_Principal vista) {
        this.vista = vista;
        panel_Autenticar_Administrador.boton_Iniciar_Administrador.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Autenticar_Administrador);
        panel_Autenticar_Administrador.setVisible(true);
        vista.Panel_Contenedor.validate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == panel_Autenticar_Administrador.boton_Iniciar_Administrador) {
            Sesion modelo_Ingreso = new Sesion(panel_Autenticar_Administrador.campo_Usuario.getText(), cifrado_MD5(panel_Autenticar_Administrador.campo_Clave.getText()));
            
            Conexion_Database conexion_Database = new Conexion_Database(modelo_Ingreso);
            this.conexion_DataBase = conexion_Database.iniciar();

            if (this.conexion_DataBase != null) {
                if (new DAO_Ingreso(this.conexion_DataBase).rol_Sesion(conexion_Database.usuario()).split("`")[1].equals("Administrador")) {
                    vista.Panel_Contenedor.removeAll();
                    new Controlador_Panel_Registro(vista, conexion_DataBase).iniciar();
                }
            } else {
                this.panel_Autenticar_Administrador.etiqueta_Datos_Incorrectos.setVisible(true);
            }
        }
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
