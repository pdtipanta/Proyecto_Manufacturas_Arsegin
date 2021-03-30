/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clientes;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Modelo.Cliente;
import Modelo.Usuario;
import Vista.Cliente.Panel_Clientes;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Panel_Cliente implements ActionListener {
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Panel_Clientes          panel_Cliente = new Panel_Clientes();
    private final Usuario                 usuario;
    private final String                  rol;
    private Cliente                       cliente;

    public Controlador_Panel_Cliente(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Cliente.boton_Modificar.addActionListener(this);
        this.panel_Cliente.boton_Eliminar.addActionListener(this);
        this.panel_Cliente.boton_Reportes_Clientes.addActionListener(this);
        this.panel_Cliente.boton_Nuevo_Cliente.addActionListener(this);
        this.panel_Cliente.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.removeAll();
        this.vista.Panel_Contenedor.add(this.panel_Cliente);
        this.panel_Cliente.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.cargar_Clientes();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Cliente.boton_Modificar) {
            Cliente cliente = new Cliente((String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 0), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 1), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 4), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 3), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 5), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 6), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 2), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 8), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 7), (String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 9));
            if (new Controlador_Dialogo_Cliente(this.vista, this.conexion_Database, this.usuario, cliente, "Modificar").iniciar()) {
                this.panel_Cliente.boton_Modificar.setEnabled(false);
                this.panel_Cliente.boton_Eliminar.setEnabled(false);
                this.cargar_Clientes();
            }
        }

        if (ae.getSource() == this.panel_Cliente.boton_Eliminar) {

            try {
                if (new DAO_Cliente_Implementacion(this.conexion_Database).eliminar((String) this.panel_Cliente.tabla_Clientes.getValueAt(this.panel_Cliente.tabla_Clientes.getSelectedRow(), 0)) > 0) {
                    this.cargar_Clientes();
                    this.panel_Cliente.boton_Modificar.setEnabled(false);
                    this.panel_Cliente.boton_Eliminar.setEnabled(false);
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente, debido a que mantiene documentos pendientes", "Clientes", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
            }
        }

        if (ae.getSource() == this.panel_Cliente.boton_Reportes_Clientes) {
            if (this.panel_Cliente.tabla_Clientes.getRowCount() > 0) {
                ArrayList<Cliente> cliente = new ArrayList<Cliente>();
                for (int i = 0; i < this.panel_Cliente.tabla_Clientes.getRowCount(); i++) {
                    cliente.add(new Cliente((String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 0), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 1), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 4), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 3), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 5), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 6), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 2), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 8), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 7), (String) this.panel_Cliente.tabla_Clientes.getValueAt(i, 9)));
                }
                new Controlador_Reporte_Cliente(cliente).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Cliente.boton_Cerrar_Sesion) {
            this.vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }

        if (ae.getSource() == this.panel_Cliente.boton_Nuevo_Cliente) {
            if (new Controlador_Dialogo_Cliente(this.vista, this.conexion_Database, this.usuario, cliente, "Registrar").iniciar()) {
                this.cargar_Clientes();
                this.panel_Cliente.boton_Modificar.setEnabled(false);
                this.panel_Cliente.boton_Eliminar.setEnabled(false);
            }
        }
    }

    public void set_Usuario() {
        this.panel_Cliente.set_Usuario(this.usuario, this.rol);
    }

    public void cargar_Clientes() {
        new Controlador_Dialogo_Buscar(this.panel_Cliente, this.conexion_Database, this.usuario, this.rol).iniciar();
    }
}
