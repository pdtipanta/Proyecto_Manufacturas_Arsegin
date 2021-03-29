/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Inventario.Reporte.Controlador_Reporte_Inventario;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Inventario.Dialogo_Imagen_Producto;
import Vista.Inventario.Panel_Inventarios;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Inventarios implements ActionListener{
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Panel_Inventarios       panel_Inventarios = new Panel_Inventarios();
    private final DefaultTableModel       modelo_Tabla_Productos;
    private Inventario                    inventario;
    private final String                  rol;
    private final Usuario                 usuario;
    
    public Controlador_Inventarios(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Inventarios.boton_Modificar.addActionListener(this);
        this.panel_Inventarios.boton_Nuevo_Producto.addActionListener(this);
        this.panel_Inventarios.boton_Eliminar.addActionListener(this);
        this.panel_Inventarios.boton_Informe.addActionListener(this);
        this.panel_Inventarios.boton_Cerrar_Sesion.addActionListener(this);
        this.modelo_Tabla_Productos = (DefaultTableModel) this.panel_Inventarios.tabla_Inventario.getModel();
    }

    public void iniciar() {
        this.consultar_Datos_Inventario();
        this.vista.Panel_Contenedor.add(panel_Inventarios);
        this.popupTable();
        this.panel_Inventarios.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Inventarios.boton_Modificar) {
            ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar(this.panel_Inventarios.tabla_Inventario.getValueAt(this.panel_Inventarios.tabla_Inventario.getSelectedRow(), 0));

            if (inventario.size() == 1) {
                if (new Controlador_Dialogo_Inventario(this.vista, this.conexion_Database, inventario.get(0), "Modificar").iniciar()) {
                    this.panel_Inventarios.boton_Modificar.setEnabled(false);
                    this.panel_Inventarios.boton_Eliminar.setEnabled(false);
                    this.consultar_Datos_Inventario();
                }
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Eliminar) {

            try {
                if (new DAO_Inventario_Implementacion(this.conexion_Database).eliminar((String) this.panel_Inventarios.tabla_Inventario.getValueAt(this.panel_Inventarios.tabla_Inventario.getSelectedRow(), 0)) > 0) {
                    this.panel_Inventarios.boton_Modificar.setEnabled(false);
                    this.panel_Inventarios.boton_Eliminar.setEnabled(false);
                    this.consultar_Datos_Inventario();
                    JOptionPane.showMessageDialog(null, "Inventario eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el producto, debido a que se encuentra en documentos", "Inventario", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Nuevo_Producto) {
            if (new Controlador_Dialogo_Inventario(this.vista, this.conexion_Database, inventario, "Registrar").iniciar()) {
                this.consultar_Datos_Inventario();
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Informe) {
            if (this.panel_Inventarios.tabla_Inventario.getRowCount() > 0) {
                ArrayList<Inventario> inventario = new ArrayList<Inventario>();

                for (int i = 0; i < this.panel_Inventarios.tabla_Inventario.getRowCount(); i++) {
                    byte imagen[] = null;
                    inventario.add(new Inventario((String) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 0), (String) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 1), (int) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 3), (Double) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 4), (Double) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 5), (String) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 2), imagen));
                }
                new Controlador_Reporte_Inventario(inventario).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void consultar_Datos_Inventario() {
        this.modelo_Tabla_Productos.setRowCount(0);
        ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar("");

        if (inventario.size() > 0) {

            for (int i = 0; i < inventario.size(); i++) {
                Object[] fila = {inventario.get(i).getCodigo(), inventario.get(i).getDescripcion(), inventario.get(i).getProveedor(), inventario.get(i).getCantidad_Disponible(), inventario.get(i).getPrecio_Compra(), inventario.get(i).getPrecio_Venta()};
                this.modelo_Tabla_Productos.addRow(fila);
            }
        }
    }

    public void habilitar_Rol() {
        this.panel_Inventarios.Roles(rol);
    }

    public void set_Usuario() {
        this.panel_Inventarios.set_Usuario(this.usuario, this.rol);
    }

    public void popupTable() {
        JPopupMenu popup_Menu = new JPopupMenu();
        final JMenuItem menu_Item1 = new JMenuItem("Visualizar producto");
        menu_Item1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(com()).consultar(valor());
                if (inventario.size() == 1) {
                    desplega_Foto(inventario.get(0));
                }
            }

        });
        popup_Menu.add(menu_Item1);
        this.panel_Inventarios.tabla_Inventario.setComponentPopupMenu(popup_Menu);
    }

    public Connection com() {
        return this.conexion_Database;
    }

    public String valor() {
        int i = this.panel_Inventarios.tabla_Inventario.getSelectedRow();
        String valor = "Ninguno";

        if (i != -1) {
            valor = (String) this.panel_Inventarios.tabla_Inventario.getValueAt(this.panel_Inventarios.tabla_Inventario.getSelectedRow(), 0);
        }

        return valor;
    }

    public void desplega_Foto(Inventario inventario) {
        new Dialogo_Imagen_Producto(this.vista, true).mostrar_Imagen(inventario);
    }
}
