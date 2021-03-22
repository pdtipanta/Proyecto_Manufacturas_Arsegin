/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Gestion_Usuarios;

import Datos.DAO_Roles;
import Datos.DAO_Usuario;
import Modelo.Rol;
import Modelo.Usuario;
import Vista.Registro_Usuarios.Panel_Registro;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Usuarios implements KeyListener, MouseListener{
    private final Panel_Registro             panel_Registro;
    private final Connection                 conexion_DataBase;
    private String[]                         valor;
    private final DefaultTableModel          modelo_Tabla_Usuarios;
    private TableRowSorter                   TRSFiltro;

    public Controlador_Dialogo_Buscar_Usuarios(Panel_Registro panel_Registro, Connection  conexion_DataBase) {
        this.conexion_DataBase = conexion_DataBase; 
        this.panel_Registro = panel_Registro;
        this.panel_Registro.campo_Buscar.addKeyListener(this);
        this.panel_Registro.tabla_Usuarios.addMouseListener(this);
        this.modelo_Tabla_Usuarios = (DefaultTableModel) this.panel_Registro.tabla_Usuarios.getModel();
    }

    public String[] iniciar() {
        consultar_Datos_Usuarios();
        return this.valor;
    }

    public void consultar_Datos_Usuarios() {
        this.modelo_Tabla_Usuarios.setRowCount(0);
        ArrayList<Usuario> modelo_Usuario = new DAO_Usuario(this.conexion_DataBase).consultar("Todos");

        if (modelo_Usuario.size() > 0) {

            for (int i = 0; i < modelo_Usuario.size(); i++) {
                Rol rol = new DAO_Roles(this.conexion_DataBase).consultar_Funcion_Rol(modelo_Usuario.get(i).getRol());

                Object[] fila = {modelo_Usuario.get(i).getCedula(), modelo_Usuario.get(i).getNombre(), modelo_Usuario.get(i).getApellido(), modelo_Usuario.get(i).getCorreo(), rol.getFuncion()};
                this.modelo_Tabla_Usuarios.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.panel_Registro.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Registro.campo_Buscar.setEditable(false);
        } else {
            this.panel_Registro.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Registro.campo_Buscar) {
                this.panel_Registro.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Registro.tabla_Usuarios.getModel());
                this.panel_Registro.tabla_Usuarios.setRowSorter(TRSFiltro);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void filtro() {
        if (this.panel_Registro.combo_Opciones.getSelectedItem() == "Por CI") {
            filtrar_Tabla(0);
        } else if (this.panel_Registro.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Registro.combo_Opciones.getSelectedItem() == "Por rol") {
            filtrar_Tabla(4);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Registro.tabla_Usuarios.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Registro.campo_Buscar.getText(), valor));
    }

    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Registro.boton_Modificar.setEnabled(true);
        } else {
            this.panel_Registro.boton_Modificar.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Registro.tabla_Usuarios) {
            seleccion_Tabla(this.panel_Registro.tabla_Usuarios.getSelectedRow());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
