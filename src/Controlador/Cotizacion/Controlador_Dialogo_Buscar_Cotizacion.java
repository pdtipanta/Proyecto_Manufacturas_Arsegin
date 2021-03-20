/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Cotizacion.DAO_Cotizacion_Implementacion;
import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Usuario;
import Vista.Cotizacion.Dialogo_Buscar_Cotizacion;
import Vista.Cotizacion.Panel_Cotizacion;
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
public class Controlador_Dialogo_Buscar_Cotizacion implements ActionListener, KeyListener, MouseListener {
    private final Panel_Cotizacion              panel_Cotizacion;
    //private final Vista_Principal               vista;
    private final Connection                    conexion;
    private final Usuario                       usuario;
    private final String                        rol;
    private String                              valor = null;
    //private final Dialogo_Buscar_Cotizacion     dialogo_Buscar_Cotizacion;
    private TableRowSorter                      TRSFiltro;
    private DefaultTableModel                   modelo_Tabla_Cotizacion;
    private ArrayList<Cotizacion>               cotizacion = new ArrayList<Cotizacion>();

    public Controlador_Dialogo_Buscar_Cotizacion(Panel_Cotizacion panel_Cotizacion, Connection conexion, Usuario usuario, String rol) {
        //this.vista = vista;
        this.panel_Cotizacion = panel_Cotizacion;
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        //this.dialogo_Buscar_Cotizacion = new Dialogo_Buscar_Cotizacion(this.vista, true);
        this.panel_Cotizacion.campo_Busqueda.addKeyListener(this);
        this.panel_Cotizacion.tabla_Consulta_Cotizacion.addMouseListener(this);
        this.panel_Cotizacion.combo_Opciones.addActionListener(this);
        this.panel_Cotizacion.boton_Fecha.addActionListener(this);
        this.modelo_Tabla_Cotizacion = (DefaultTableModel) this.panel_Cotizacion.tabla_Consulta_Cotizacion.getModel();
    }

    public DefaultTableModel iniciar() {
        return this.consultar_Datos_Cotizacion();
        //this.dialogo_Buscar_Cotizacion.setVisible(true);
        //return this.cotizacion;
    }

    public DefaultTableModel consultar_Datos_Cotizacion() {

        switch (this.rol) {
            case "Vendedor":
                valor = "Todos" + ";" + this.usuario.getCedula();
                break;

            case "Contador":
                valor = "Todos" + ";" + "Todos";
                break;

            case "Administrador":
                valor = "Todos" + ";" + "Todos";
                break;
        }

        return presentar_Cotizacion(new DAO_Cotizacion_Implementacion(this.conexion).consultar(valor));
    }

    public DefaultTableModel presentar_Cotizacion(ArrayList<Cotizacion> cotizacion) {
        this.modelo_Tabla_Cotizacion.setRowCount(0);

        if (cotizacion.size() > 0) {
            for (int i = 0; i < cotizacion.size(); i++) {
                String valor_Cliente = cotizacion.get(i).getCliente() + ";" + cotizacion.get(i).getEmisor();
                ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor_Cliente);
                Object[] fila = {cotizacion.get(i).getNo_Cotizacion(), cliente.get(0).getCliente(), cliente.get(0).getRUC(), cliente.get(0).getEmpleado(), cotizacion.get(i).getFecha(), cotizacion.get(i).getValor_Total()};
                this.modelo_Tabla_Cotizacion.addRow(fila);
            }
        }
        return this.modelo_Tabla_Cotizacion;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (this.panel_Cotizacion.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Cotizacion.campo_Busqueda.setEditable(false);
        } else {
            this.panel_Cotizacion.campo_Busqueda.setEditable(true);
            if (ke.getSource() == this.panel_Cotizacion.campo_Busqueda) {
                this.panel_Cotizacion.campo_Busqueda.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getModel());
                this.panel_Cotizacion.tabla_Consulta_Cotizacion.setRowSorter(TRSFiltro);
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
        if (this.panel_Cotizacion.combo_Opciones.getSelectedItem() == "Por numero") {
            filtrar_Tabla(0);
        } else if (this.panel_Cotizacion.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Cotizacion.combo_Opciones.getSelectedItem() == "Por RUC") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Cotizacion.campo_Busqueda.getText(), valor));
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Cotizacion.boton_Modificar_Cotizacion.setEnabled(true);
            this.panel_Cotizacion.boton_Generar_Cotizacion.setEnabled(true);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Cotizacion.boton_Generar_Cotizacion.setEnabled(false);
            this.panel_Cotizacion.boton_Modificar_Cotizacion.setEnabled(false);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        seleccion_Tabla(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow());
        /*
        if (me.getSource() == this.dialogo_Buscar_Cotizacion.tabla_Consulta_Cotizacion) {

            String valor = this.dialogo_Buscar_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.dialogo_Buscar_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.dialogo_Buscar_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.dialogo_Buscar_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 3);

            this.cotizacion = new DAO_Cotizacion_Implementacion(this.conexion).consultar(valor);
            if (this.cotizacion.size() == 1) {
                this.dialogo_Buscar_Cotizacion.dispose();
            }
        }*/
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
        if (ae.getSource() == this.panel_Cotizacion.combo_Opciones) {
            if (this.panel_Cotizacion.combo_Opciones.getSelectedItem().equals("Por fecha")) {
                this.panel_Cotizacion.desactivar_Calendarios(true);
            } else {
                this.panel_Cotizacion.desactivar_Calendarios(false);
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Fecha) {
            if (this.panel_Cotizacion.verificar_Campos()) {
                this.presentar_Cotizacion(new DAO_Cotizacion_Implementacion(this.conexion).consultar_Cotizacion_Fechas(this.valor + ";" + this.panel_Cotizacion.calendario_Inicio() + ";" + this.panel_Cotizacion.calendario_Final()));
                this.panel_Cotizacion.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Cotizacion.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
