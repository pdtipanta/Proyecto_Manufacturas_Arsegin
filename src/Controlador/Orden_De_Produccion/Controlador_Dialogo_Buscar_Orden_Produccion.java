/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Datos.Maquila.DAO_Maquila_Implementacion;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Maquila;
import Modelo.Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Dialogo_Buscar_Orden_Produccion;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Controlador_Dialogo_Buscar_Orden_Produccion implements ActionListener, KeyListener, MouseListener {

    private final Vista_Principal                     vista;
    private final Connection                          conexion;
    private final Dialogo_Buscar_Orden_Produccion     dialogo_Buscar_Orden_Produccion;
    private TableRowSorter                            TRSFiltro;
    private DefaultTableModel                         modelo_Tabla_Orden_Produccion;
    private ArrayList<Orden_Produccion>               orden_Produccion = new ArrayList<Orden_Produccion>();

    public Controlador_Dialogo_Buscar_Orden_Produccion(Vista_Principal vista, Connection conexion) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Buscar_Orden_Produccion = new Dialogo_Buscar_Orden_Produccion(this.vista, true);
        this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.addKeyListener(this);
        this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.addMouseListener(this);
        this.dialogo_Buscar_Orden_Produccion.combo_Opciones.addActionListener(this);
        this.dialogo_Buscar_Orden_Produccion.boton_Fecha.addActionListener(this);
        this.modelo_Tabla_Orden_Produccion = (DefaultTableModel) this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.getModel();
    }

    public ArrayList<Orden_Produccion> iniciar() {
        consultar_Datos_Orden_Produccion();
        this.dialogo_Buscar_Orden_Produccion.setVisible(true);
        return this.orden_Produccion;
    }

    public void consultar_Datos_Orden_Produccion() {
        String valor = "Todas";
        
        presentar_Ordenes(new DAO_Orden_Produccion_Implementacion(this.conexion).consultar(valor));    
    }
    
    public void presentar_Ordenes(ArrayList<Orden_Produccion> orden_Produccion) {
        this.modelo_Tabla_Orden_Produccion.setRowCount(0);
        
        if (orden_Produccion.size() > 0) {
            for (int i = 0; i < orden_Produccion.size(); i++) {
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion).consultar(orden_Produccion.get(i).getMaquila());
                Object[] fila = {orden_Produccion.get(i).getNumero_Orden(), maquila.get(0).getMaquila(), maquila.get(0).getRUC(), orden_Produccion.get(i).getFecha(), maquila.get(0).getTelefono()};
                this.modelo_Tabla_Orden_Produccion.addRow(fila);
            }
        }
    }
    

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Orden_Produccion.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.setEditable(false);
        } else {
            this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Orden_Produccion.campo_Busqueda) {
                this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.getModel());
                this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Orden_Produccion.combo_Opciones.getSelectedItem() == "Por numero") {
            filtrar_Tabla(0);
        } else if (this.dialogo_Buscar_Orden_Produccion.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Orden_Produccion.combo_Opciones.getSelectedItem() == "Por RUC") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.getText(), valor));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion) {
            
            String valor = String.valueOf(this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.getValueAt(this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.getSelectedRow(), 0));

            this.orden_Produccion = new DAO_Orden_Produccion_Implementacion(this.conexion).consultar(valor);
            if (this.orden_Produccion.size() == 1) {
                this.dialogo_Buscar_Orden_Produccion.dispose();
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
        if (ae.getSource() == this.dialogo_Buscar_Orden_Produccion.combo_Opciones) {
            if (this.dialogo_Buscar_Orden_Produccion.combo_Opciones.getSelectedItem().equals("Por fecha")) {
                this.dialogo_Buscar_Orden_Produccion.desactivar_Calendarios(true);
            } else {
                this.dialogo_Buscar_Orden_Produccion.desactivar_Calendarios(false);
            }
        }

        if (ae.getSource() == this.dialogo_Buscar_Orden_Produccion.boton_Fecha) {
            if (this.dialogo_Buscar_Orden_Produccion.verificar_Campos()) {
                this.presentar_Ordenes(new DAO_Orden_Produccion_Implementacion(this.conexion).consultar_Ordenes_Produccion_Fechas( this.dialogo_Buscar_Orden_Produccion.calendario_Inicio() + ";" + this.dialogo_Buscar_Orden_Produccion.calendario_Final()));
                this.dialogo_Buscar_Orden_Produccion.etiqueta_Error_Etiqueta(false);
            } else {
                this.dialogo_Buscar_Orden_Produccion.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
