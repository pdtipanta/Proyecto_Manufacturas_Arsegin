/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario;

import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Inventario;
import Vista.Inventario.Dialogo_Buscar_Inventario;
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
public class Controlador_Dialogo_Buscar_Inventario implements KeyListener, MouseListener{
    private final Vista_Principal           vista;
    private final Connection                conexion;
    private final Dialogo_Buscar_Inventario dialogo_Buscar_Inventario;
    private final DefaultTableModel         modelo_Tabla_Productos;
    private TableRowSorter                  TRSFiltro;
    private ArrayList< Inventario>          inventario = new ArrayList<Inventario>();

    public Controlador_Dialogo_Buscar_Inventario(Vista_Principal vista, Connection conexion, boolean botones) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Buscar_Inventario = new Dialogo_Buscar_Inventario(this.vista, true);
        this.dialogo_Buscar_Inventario.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Inventario.tabla_Inventario.addMouseListener(this);
        this.modelo_Tabla_Productos = (DefaultTableModel) this.dialogo_Buscar_Inventario.tabla_Inventario.getModel();
        this.dialogo_Buscar_Inventario.boton_Nuevo_Producto.setVisible(botones);
    }

    public ArrayList<Inventario> iniciar() {
        consultar_Datos_Inventario();
        this.dialogo_Buscar_Inventario.setVisible(true);
        return inventario;
    }

    public void consultar_Datos_Inventario() {
        ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(this.conexion).consultar("");

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
            inventario = new DAO_Inventario_Implementacion(this.conexion).consultar(String.valueOf(this.dialogo_Buscar_Inventario.tabla_Inventario.getValueAt(this.dialogo_Buscar_Inventario.tabla_Inventario.getSelectedRow(), 0)));
            if (inventario.size() == 1) {
                this.dialogo_Buscar_Inventario.dispose();
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
}

