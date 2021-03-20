/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Factura.DAO_Factura_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Usuario;
import Vista.Factura.Dialogo_Buscar_Facturas;
import Vista.Factura.Panel_Factura;
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
public class Controlador_Dialogo_Buscar_Factura implements ActionListener, KeyListener, MouseListener {

    //private final Vista_Principal               vista;
    Panel_Factura                               panel_Factura;
    private final Connection                    conexion;
    private final Usuario                       usuario;
    private final String                        rol;
    //private final Dialogo_Buscar_Facturas       dialogo_Buscar_Facturas;
    private TableRowSorter                      TRSFiltro;
    private DefaultTableModel                   modelo_Tabla_Facturas;
    private String                              valor = null;
    private ArrayList<Factura>                  factura = new ArrayList<Factura>();

    public Controlador_Dialogo_Buscar_Factura(Panel_Factura panel_Factura, Connection conexion, Usuario usuario, String rol) {
        //this.vista = vista;
        this.panel_Factura = panel_Factura; 
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        //this.dialogo_Buscar_Facturas = new Dialogo_Buscar_Facturas(this.vista, true);
        this.panel_Factura.campo_Busqueda.addKeyListener(this);
        this.panel_Factura.boton_Fecha.addActionListener(this);
        this.panel_Factura.combo_Opciones.addActionListener(this);
        this.panel_Factura.tabla_Consulta_Factura.addMouseListener(this);
        this.modelo_Tabla_Facturas = (DefaultTableModel) this.panel_Factura.tabla_Consulta_Factura.getModel();
    }

    public DefaultTableModel iniciar() {
        return consultar_Datos_Facturas();
        //this.dialogo_Buscar_Facturas.setVisible(true);
        //return this.factura;
    }

    public DefaultTableModel consultar_Datos_Facturas() {

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
        
        return presentar_Facturas(new DAO_Factura_Implementacion(this.conexion).consultar(valor));
    }

    public DefaultTableModel presentar_Facturas(ArrayList<Factura> factura) {
        this.modelo_Tabla_Facturas.setRowCount(0);
        
        if (factura.size() > 0) {
            for (int i = 0; i < factura.size(); i++) {
                String valor_Cliente = factura.get(i).getCliente() + ";" + factura.get(i).getVendedor();
                ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor_Cliente);
                Object[] fila = {factura.get(i).getNo_Factura(), cliente.get(0).getCliente(), cliente.get(0).getRUC(), factura.get(i).getEstado(), factura.get(i).getValor_Total(), factura.get(i).getFecha(), cliente.get(0).getEmpleado()};
                this.modelo_Tabla_Facturas.addRow(fila);
            }
        }
        return this.modelo_Tabla_Facturas;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.panel_Factura.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Factura.campo_Busqueda.setEditable(false);
        } else {
            this.panel_Factura.campo_Busqueda.setEditable(true);
            if (ke.getSource() == this.panel_Factura.campo_Busqueda) {
                this.panel_Factura.campo_Busqueda.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Factura.tabla_Consulta_Factura.getModel());
                this.panel_Factura.tabla_Consulta_Factura.setRowSorter(TRSFiltro);
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
        if (this.panel_Factura.combo_Opciones.getSelectedItem() == "Por numero") {
            filtrar_Tabla(0);
        } else if (this.panel_Factura.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Factura.combo_Opciones.getSelectedItem() == "Por RUC") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow());
      TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Factura.campo_Busqueda.getText(), valor));
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Factura.boton_Modificar_Factura.setEnabled(true);
            this.panel_Factura.boton_Imprimir_Facturacion.setEnabled(true);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Factura.boton_Modificar_Factura.setEnabled(false);
            this.panel_Factura.boton_Imprimir_Facturacion.setEnabled(false);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Factura.tabla_Consulta_Factura){
            seleccion_Tabla(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow());
        }
        /*
        if (me.getSource() == this.dialogo_Buscar_Facturas.tabla_Consulta_Factura) {

            String valor = this.dialogo_Buscar_Facturas.tabla_Consulta_Factura.getValueAt(this.dialogo_Buscar_Facturas.tabla_Consulta_Factura.getSelectedRow(), 0) + ";" + this.dialogo_Buscar_Facturas.tabla_Consulta_Factura.getValueAt(this.dialogo_Buscar_Facturas.tabla_Consulta_Factura.getSelectedRow(), 6);

            this.factura = new DAO_Factura_Implementacion(this.conexion).consultar(valor);
            if (this.factura.size() == 1) {
                this.dialogo_Buscar_Facturas.dispose();
            }
        } */
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

        if (ae.getSource() == this.panel_Factura.combo_Opciones) {
            if (this.panel_Factura.combo_Opciones.getSelectedItem().equals("Por fecha")) {
                this.panel_Factura.desactivar_Calendarios(true);
            } else {
                this.panel_Factura.desactivar_Calendarios(false);
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Fecha) {
            if (this.panel_Factura.verificar_Campos()) {
                this.presentar_Facturas(new DAO_Factura_Implementacion(this.conexion).consultar_Facturas_Fechas(this.valor + ";" + this.panel_Factura.calendario_Inicio() + ";" + this.panel_Factura.calendario_Final()));
                this.panel_Factura.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Factura.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
