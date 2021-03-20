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
import Vista.Registro_Usuarios.Dialogo_Buscar_Usuarios;
import Vista.Vista_Principal;
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
    private final Vista_Principal            vista;
    private Connection                       conexion_DataBase;
    private String[]                         valor;
    private final Dialogo_Buscar_Usuarios    dialogo_Buscar_Usuarios;
    private final DefaultTableModel          modelo_Tabla_Clientes;
    private TableRowSorter                   TRSFiltro;
    private ArrayList<Usuario>               modelo_Usuario = new ArrayList< Usuario>();

    public Controlador_Dialogo_Buscar_Usuarios(Vista_Principal vista, Connection  conexion_DataBase) {
        this.conexion_DataBase = conexion_DataBase; 
        this.vista = vista;
        this.dialogo_Buscar_Usuarios = new Dialogo_Buscar_Usuarios(this.vista, true);
        this.dialogo_Buscar_Usuarios.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Usuarios.tabla_Usuarios.addMouseListener(this);
        this.modelo_Tabla_Clientes = (DefaultTableModel) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getModel();
    }

    public String[] iniciar() {
        consultar_Datos_Usuarios();
        this.dialogo_Buscar_Usuarios.setVisible(true);
        return this.valor;
    }

    public void consultar_Datos_Usuarios() {
        ArrayList<Usuario> modelo_Usuario = new DAO_Usuario(this.conexion_DataBase).consultar("Todos");

        if (modelo_Usuario.size() > 0) {

            for (int i = 0; i < modelo_Usuario.size(); i++) {
                Rol rol = new DAO_Roles(this.conexion_DataBase).consultar_Funcion_Rol(modelo_Usuario.get(i).getRol());

                Object[] fila = {modelo_Usuario.get(i).getCedula(), modelo_Usuario.get(i).getNombre(), modelo_Usuario.get(i).getApellido(), modelo_Usuario.get(i).getCorreo(), rol.getFuncion()};
                this.modelo_Tabla_Clientes.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Usuarios.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Usuarios.campo_Buscar.setEditable(false);
        } else {
            this.dialogo_Buscar_Usuarios.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Usuarios.campo_Buscar) {
                this.dialogo_Buscar_Usuarios.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getModel());
                this.dialogo_Buscar_Usuarios.tabla_Usuarios.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Usuarios.combo_Opciones.getSelectedItem() == "Por CI") {
            filtrar_Tabla(0);
        } else if (this.dialogo_Buscar_Usuarios.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Usuarios.combo_Opciones.getSelectedItem() == "Por rol") {
            filtrar_Tabla(4);
        }
    }

    public void filtrar_Tabla(int valor) {
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Usuarios.campo_Buscar.getText(), valor));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Usuarios.tabla_Usuarios) {
            String[] valores = {(String) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getValueAt(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getSelectedRow(), 0), (String) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getValueAt(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getSelectedRow(), 1), (String) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getValueAt(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getSelectedRow(), 2), (String) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getValueAt(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getSelectedRow(), 3), (String) this.dialogo_Buscar_Usuarios.tabla_Usuarios.getValueAt(this.dialogo_Buscar_Usuarios.tabla_Usuarios.getSelectedRow(), 4)};
            this.valor = valores;
            this.dialogo_Buscar_Usuarios.dispose();
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
