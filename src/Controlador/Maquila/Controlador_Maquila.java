/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Maquila;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Modelo.Maquila;
import Modelo.Usuario;
import Vista.Maquilas.Panel_Maquilas;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Maquila implements ActionListener{
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Usuario                 usuario;
    private final String                  rol;
    private Maquila                       maquilas;
    private final DefaultTableModel       modelo_Tabla_Maquilas;
    private final Panel_Maquilas          panel_Maquilas = new Panel_Maquilas();
    
    public Controlador_Maquila(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Maquilas.boton_Modificar.addActionListener(this);
        this.panel_Maquilas.boton_Eliminar.addActionListener(this);
        this.panel_Maquilas.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Maquilas.boton_Nuevo_Maquila.addActionListener(this);
        this.modelo_Tabla_Maquilas = (DefaultTableModel) this.panel_Maquilas.tabla_Maquilas.getModel();
    }

    public void iniciar() {
        this.cargar_Maquilas();
        this.vista.Panel_Contenedor.add(this.panel_Maquilas);
        this.panel_Maquilas.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Maquilas.boton_Nuevo_Maquila) {

            if (new Controlador_Dialogo_Maquilas(this.vista, this.conexion_Database, this.maquilas, this.usuario, this.rol, "Registrar").iniciar()) {
                this.panel_Maquilas.boton_Modificar.setEnabled(false);
                this.panel_Maquilas.boton_Eliminar.setEnabled(false);
                this.cargar_Maquilas();
            }
        }

        if (ae.getSource() == this.panel_Maquilas.boton_Modificar) {
            ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar(this.panel_Maquilas.tabla_Maquilas.getValueAt(this.panel_Maquilas.tabla_Maquilas.getSelectedRow(), 0));

            if (maquila.size() == 1) {
                if (new Controlador_Dialogo_Maquilas(this.vista, this.conexion_Database, maquila.get(0), this.usuario, this.rol, "Modificar").iniciar()) {
                    this.panel_Maquilas.boton_Modificar.setEnabled(false);
                    this.panel_Maquilas.boton_Eliminar.setEnabled(false);
                    this.cargar_Maquilas();
                }
            }
        }
        if (ae.getSource() == this.panel_Maquilas.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }

        if (ae.getSource() == this.panel_Maquilas.boton_Eliminar) {
            try {
                if (new DAO_Maquila_Implementacion(this.conexion_Database).eliminar((String) this.panel_Maquilas.tabla_Maquilas.getValueAt(this.panel_Maquilas.tabla_Maquilas.getSelectedRow(), 0)) > 0) {
                    this.cargar_Maquilas();
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar la maquila, debido a que mantiene documentos pendientes", "Maquilas", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
            }
        }
    }

    public void cargar_Maquilas() {
        this.modelo_Tabla_Maquilas.setRowCount(0);
        ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar("Todos");
        if (maquila.size() > 0) {
            for (int i = 0; i < maquila.size(); i++) {
                Object[] fila = {maquila.get(i).getId_Maquila(), maquila.get(i).getMaquila(), maquila.get(i).getRUC(), maquila.get(i).getServicio(), maquila.get(i).getDireccion(), maquila.get(i).getTelefono()};
                this.modelo_Tabla_Maquilas.addRow(fila);
            }
        }
    }

    public void set_Usuario() {
        this.panel_Maquilas.set_Usuario(this.usuario, this.rol);
    }

    public void habilitar_Rol() {
        this.panel_Maquilas.Roles(rol);
    }
}
