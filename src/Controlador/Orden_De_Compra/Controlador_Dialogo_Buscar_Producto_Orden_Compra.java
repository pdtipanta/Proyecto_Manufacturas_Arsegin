/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;

import Controlador.Cotizacion.Controlador_Dialogo_Agregar_Producto_Cotizacion;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Inventario;
import Vista.Inventario.Dialogo_Buscar_Inventario;
import Vista.Orden_De_Compra.Dialogo_Orden_Compra;
import Vista.Orden_De_Compra.Panel_Orden_Compra;
import Vista.Vista_Principal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Producto_Orden_Compra implements ActionListener, KeyListener, MouseListener{
    private final Vista_Principal                   vista;
    private final Connection                        conexion;
    private final Dialogo_Orden_Compra              dialogo_Orden_Compra;
    private final Dialogo_Buscar_Inventario         dialogo_Buscar_Inventario;
    private DefaultTableModel                       modelo_Tabla_Productos;
    private TableRowSorter                          TRSFiltro;
    private ArrayList< Inventario>                  inventario = new ArrayList<Inventario>();
    //Panel_Orden_Compra                              panel_Orden_Compra;
    private Object[]                                filas;

    public Controlador_Dialogo_Buscar_Producto_Orden_Compra(Vista_Principal vista, Connection conexion, Dialogo_Orden_Compra dialogo_Orden_Compra) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Orden_Compra = dialogo_Orden_Compra;
        //this.panel_Orden_Compra = panel_Orden_Compra;
        this.dialogo_Buscar_Inventario = new Dialogo_Buscar_Inventario(this.vista, true); 
        this.dialogo_Buscar_Inventario.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Inventario.tabla_Inventario.addMouseListener(this);
        this.dialogo_Buscar_Inventario.boton_Nuevo_Producto.addActionListener(this);
        this.modelo_Tabla_Productos = ( DefaultTableModel ) this.dialogo_Buscar_Inventario.tabla_Inventario.getModel();
    }
    
    public Object[] iniciar() {
        consultar_Datos_Inventario();
        this.dialogo_Buscar_Inventario.boton_Nuevo_Producto.setVisible(true);
        this.dialogo_Buscar_Inventario.setVisible(true);
        return this.filas;
    }

    public void consultar_Datos_Inventario() {
        inventario = new DAO_Inventario_Implementacion(this.conexion).consultar("");

        if (inventario.size() > 0) {
            for (int i = 0; i < inventario.size(); i++) {
                Object[] fila = {inventario.get(i).getCodigo(), inventario.get(i).getDescripcion(), inventario.get(i).getProveedor(), inventario.get(i).getCantidad_Disponible(), inventario.get(i).getPrecio_Compra(), inventario.get(i).getPrecio_Venta()};
                this.modelo_Tabla_Productos.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Inventario.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Inventario.campo_Buscar.setEditable(false);
        } else {
            this.dialogo_Buscar_Inventario.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Inventario.campo_Buscar) {
                this.dialogo_Buscar_Inventario.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Inventario.tabla_Inventario.getModel());
                this.dialogo_Buscar_Inventario.tabla_Inventario.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Inventario.combo_Opciones.getSelectedItem() == "Por Codigo") {
            filtrar_Tabla(0);
        } else if (this.dialogo_Buscar_Inventario.combo_Opciones.getSelectedItem() == "Por Descripcion") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Inventario.combo_Opciones.getSelectedItem() == "Por Proveedor") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Inventario.campo_Buscar.getText(), valor));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Inventario.tabla_Inventario) {

            this.inventario = new DAO_Inventario_Implementacion(this.conexion).consultar(String.valueOf(this.dialogo_Buscar_Inventario.tabla_Inventario.getValueAt(this.dialogo_Buscar_Inventario.tabla_Inventario.getSelectedRow(), 0)));

            if (inventario.size() == 1) {

                if (this.validar_Existencia_Productos_Tabla()) {
                    Object[] fila = new Controlador_Dialogo_Agregar_Producto_Cotizacion(this.vista, inventario.get(0)).iniciar();
                    this.filas = fila;
                    this.dialogo_Buscar_Inventario.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
                }

            }
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Buscar_Inventario.boton_Nuevo_Producto) {
            Object[] fila = new Controlador_Dialogo_Nuevo_Producto(this.vista).iniciar();

            if (this.validar_Existencia_Nuevos_Productos_Tabla(fila)) {
                this.filas = fila;
                this.dialogo_Buscar_Inventario.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public boolean validar_Existencia_Productos_Tabla() {
        boolean bandera = true;
        for (int i = 0; i < this.dialogo_Orden_Compra.tabla_Productos_Orden_Compra.getRowCount(); i++) {
            if (inventario.get(0).getCodigo().equals(this.dialogo_Orden_Compra.tabla_Productos_Orden_Compra.getValueAt(i, 1))) {
                bandera = false;
            }
        }
        return bandera;
    }

    public boolean validar_Existencia_Nuevos_Productos_Tabla(Object[] fila) {
        boolean bandera = true;
        for (int i = 0; i < this.dialogo_Orden_Compra.tabla_Productos_Orden_Compra.getRowCount(); i++) {
            if (fila[1].equals(this.dialogo_Orden_Compra.tabla_Productos_Orden_Compra.getValueAt(i, 1))) {
                bandera = false;
            }
        }
        return bandera;
    }
}
