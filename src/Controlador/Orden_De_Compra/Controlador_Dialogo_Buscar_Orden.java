/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Orden_De_Compra.Dialogo_Buscar_Compra;
import Vista.Orden_De_Compra.Panel_Orden_Compra;
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
public class Controlador_Dialogo_Buscar_Orden implements ActionListener, KeyListener, MouseListener {

    //private final Vista_Principal           vista;
    Panel_Orden_Compra          panel_Orden_Compra;
    private final Connection                conexion;
    private final String                    rol;
    private String                          valor = null;
    //private final Dialogo_Buscar_Compra     dialogo_Buscar_Compra;
    private TableRowSorter                  TRSFiltro;
    private DefaultTableModel               modelo_Tabla_Cotizacion;
    private ArrayList<Orden_Compra>         Orden_Compra = new ArrayList<Orden_Compra>();
    private Usuario                         usuario;

    public Controlador_Dialogo_Buscar_Orden(Panel_Orden_Compra panel_Orden_Compra, Connection conexion, Usuario usuario, String rol) {
        //this.vista = vista;
        this.panel_Orden_Compra = panel_Orden_Compra;
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        //this.dialogo_Buscar_Compra = new Dialogo_Buscar_Compra(this.vista, true);
       this.panel_Orden_Compra.campo_Busqueda.addKeyListener(this);
       this.panel_Orden_Compra.boton_Fecha.addActionListener(this);
       this.panel_Orden_Compra.combo_Opciones.addActionListener(this);
       this.panel_Orden_Compra.tabla_Consulta_Compra.addMouseListener(this);
        this.modelo_Tabla_Cotizacion = (DefaultTableModel) this.panel_Orden_Compra.tabla_Consulta_Compra.getModel();
    }

    public void iniciar() {
        consultar_Datos_Orden_Compra();
        //this.dialogo_Buscar_Compra.setVisible(true);
        //return this.Orden_Compra;
    }

    public void consultar_Datos_Orden_Compra() {

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

        presentar_Ordenes(new DAO_Orden_Compra_Implementacion(this.conexion).consultar(valor));
    }

    public void presentar_Ordenes(ArrayList<Orden_Compra> orden_Compra) {
        this.modelo_Tabla_Cotizacion.setRowCount(0);

        if (orden_Compra.size() > 0) {
            for (int i = 0; i < orden_Compra.size(); i++) {
                ArrayList<Proveedor> proveedor = this.consultar_Proveedor(orden_Compra.get(i).getProveedor());
                Object[] fila = {orden_Compra.get(i).getNo_Orden(), proveedor.get(0).getProveedor(), proveedor.get(0).getRUC(), orden_Compra.get(i).getValor_Total(), orden_Compra.get(i).getFecha(), orden_Compra.get(i).getSolicitante(), orden_Compra.get(i).getEstado()};
                this.modelo_Tabla_Cotizacion.addRow(fila);
            }
        }
    }

    public ArrayList<Proveedor> consultar_Proveedor(String i) {
        return new DAO_Proveedor_Implementacion(this.conexion).consultar(i);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.panel_Orden_Compra.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Orden_Compra.campo_Busqueda.setEditable(false);
        } else {
            this.panel_Orden_Compra.campo_Busqueda.setEditable(true);
            if (ke.getSource() == this.panel_Orden_Compra.campo_Busqueda) {
                this.panel_Orden_Compra.campo_Busqueda.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Orden_Compra.tabla_Consulta_Compra.getModel());
                this.panel_Orden_Compra.tabla_Consulta_Compra.setRowSorter(TRSFiltro);
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
        if (this.panel_Orden_Compra.combo_Opciones.getSelectedItem() == "Por numero") {
            filtrar_Tabla(0);
        } else if (this.panel_Orden_Compra.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Orden_Compra.combo_Opciones.getSelectedItem() == "Por RUC") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Orden_Compra.campo_Busqueda.getText(), valor));
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Orden_Compra.boton_Modificar_Orden_Compra.setEnabled(true);
            this.panel_Orden_Compra.boton_Generar_Orden.setEnabled(true);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Orden_Compra.boton_Modificar_Orden_Compra.setEnabled(false);
            this.panel_Orden_Compra.boton_Generar_Orden.setEnabled(false);
            //this.panel_Cotizacion.boton_Eliminar.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Orden_Compra.tabla_Consulta_Compra){
            seleccion_Tabla(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow());
        }
/*
        if (me.getSource() == this.dialogo_Buscar_Compra.tabla_Consulta_Compra) {

            String valor = this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getValueAt(this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getValueAt(this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getSelectedRow(), 5);

            this.Orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion).consultar(valor);

            if (this.Orden_Compra.size() == 1) {
                this.dialogo_Buscar_Compra.dispose();
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
        if (ae.getSource() == this.panel_Orden_Compra.combo_Opciones) {
            if (this.panel_Orden_Compra.combo_Opciones.getSelectedItem().equals("Por fecha")) {
                this.panel_Orden_Compra.desactivar_Calendarios(true);
            } else {
                this.panel_Orden_Compra.desactivar_Calendarios(false);
            }
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Fecha) {
            if (this.panel_Orden_Compra.verificar_Campos()) {
                this.presentar_Ordenes(new DAO_Orden_Compra_Implementacion(this.conexion).consultar_Ordenes_Fechas(this.valor + ";" + this.panel_Orden_Compra.calendario_Inicio() + ";" + this.panel_Orden_Compra.calendario_Final()));
                this.panel_Orden_Compra.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Orden_Compra.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
