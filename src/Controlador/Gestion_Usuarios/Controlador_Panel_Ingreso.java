/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Controlador.Vista_Principal.Controlador_Vista_Principal;
import Cifrado.MD5Encryptacion;
import Datos.Conexion_Database;
import Datos.DAO_Ingreso;
import Modelo.Sesion;
import Vista.Panel_Ingreso;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class Controlador_Panel_Ingreso implements ActionListener {
    private Vista_Principal                vista;
    private Connection                     conexion_DataBase;
    private Panel_Ingreso                  panel_Ingreso = new Panel_Ingreso();

    public Controlador_Panel_Ingreso(Vista_Principal vista) {
        this.vista = vista;
        this.panel_Ingreso.boton_Crear_Usuario.addActionListener(this);
        this.panel_Ingreso.boton_Iniciar_Sesion.addActionListener(this);
        this.panel_Ingreso.boton_Olvido_Clave.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Ingreso);
        panel_Ingreso.setVisible(true);
        vista.Panel_Contenedor.validate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if( ae.getSource() == panel_Ingreso.boton_Crear_Usuario ){
            vista.Panel_Contenedor.removeAll();
            new Controlador_Panel_Autenticar_Administrador( vista ).iniciar();
        }

        if (ae.getSource() == panel_Ingreso.boton_Iniciar_Sesion) {
            Sesion modelo_Ingreso = new Sesion(panel_Ingreso.campo_Usuario_Ingreso.getText(), cifrado_MD5(panel_Ingreso.campo_Clave_Ingreso.getText()));
            Conexion_Database conexion_Database = new Conexion_Database(modelo_Ingreso);
            this.conexion_DataBase = conexion_Database.iniciar();
           
            if (this.conexion_DataBase != null) {
                this.panel_Ingreso.setVisible(false);
                try {
                    new Controlador_Vista_Principal(this.vista, this.conexion_DataBase, new DAO_Ingreso(this.conexion_DataBase).iniciar_Sesion(this.conexion_DataBase.getMetaData().getUserName().split("@")[0]), new DAO_Ingreso(this.conexion_DataBase).rol_Sesion(conexion_Database.usuario()).split("`")[1]).iniciar();
                } catch (SQLException ex) {
                }
            } else {
                this.panel_Ingreso.limpiar_Campos();
                this.panel_Ingreso.etiqueta_Datos_Incorrectos.setVisible(true);
            }
        }
 
        if( ae.getSource() == this.panel_Ingreso.boton_Olvido_Clave ){
            vista.Panel_Contenedor.removeAll();
            new Controlador_Recuperacion_Clave( this.vista, this.conexion_DataBase ).iniciar();
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
