/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Cifrado.MD5Encryptacion;
import Vista.Registro_Usuarios.Panel_Registro;
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
public class Controlador_Panel_Registro implements ActionListener{
    private Vista_Principal     vista;
    private Connection          conexion_DataBase;
    private String[]            valor;
    private Panel_Registro      panel_Registro = new Panel_Registro();

    public Controlador_Panel_Registro(Vista_Principal vista, Connection conexion_DataBase) {
        this.vista = vista;
        this.conexion_DataBase = conexion_DataBase;
        this.panel_Registro.boton_Modificar.addActionListener(this);
        this.panel_Registro.boton_Nuevo.addActionListener(this);
        this.panel_Registro.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Registro);
        panel_Registro.setVisible(true);
        vista.Panel_Contenedor.validate();
        this.cargar_Usuarios();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Registro.boton_Nuevo) {
            if (new Controlador_Dialogo_Usuarios(this.vista, this.conexion_DataBase, this.valor, "Registrar").iniciar()) {
                this.panel_Registro.boton_Modificar.setEnabled(false);
                this.cargar_Usuarios();
            }
        }

        if (ae.getSource() == this.panel_Registro.boton_Modificar) {
            String[] valores = {(String) this.panel_Registro.tabla_Usuarios.getValueAt(this.panel_Registro.tabla_Usuarios.getSelectedRow(), 0), (String) this.panel_Registro.tabla_Usuarios.getValueAt(this.panel_Registro.tabla_Usuarios.getSelectedRow(), 1), (String) this.panel_Registro.tabla_Usuarios.getValueAt(this.panel_Registro.tabla_Usuarios.getSelectedRow(), 2), (String) this.panel_Registro.tabla_Usuarios.getValueAt(this.panel_Registro.tabla_Usuarios.getSelectedRow(), 3), (String) this.panel_Registro.tabla_Usuarios.getValueAt(this.panel_Registro.tabla_Usuarios.getSelectedRow(), 4)};

            if (valores.length > 0) {
                if (new Controlador_Dialogo_Usuarios(this.vista, this.conexion_DataBase, valores, "Modificar").iniciar()) {
                    this.panel_Registro.boton_Modificar.setEnabled(false);
                    this.cargar_Usuarios();
                }
            }
        }

        if (ae.getSource() == this.panel_Registro.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void cargar_Usuarios() {
        String[] valor = new Controlador_Dialogo_Buscar_Usuarios(this.panel_Registro, this.conexion_DataBase).iniciar();
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
