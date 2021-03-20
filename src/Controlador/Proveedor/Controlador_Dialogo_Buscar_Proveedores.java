/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor;

import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Proveedor.Dialogo_Buscar_Proveedor;
import Vista.Proveedor.Panel_Proveedor;
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
public class Controlador_Dialogo_Buscar_Proveedores implements ActionListener, KeyListener, MouseListener{
    private final Panel_Proveedor panel_Proveedor; 
    private final Connection                    conexion;
    //private final Dialogo_Buscar_Proveedor      dialogo_Buscar_Proveedor;
    private DefaultTableModel                   modelo_Tabla_Proveedor;
    private TableRowSorter                      TRSFiltro;
    private ArrayList< Proveedor>               proveedor = new ArrayList<Proveedor>();

    public Controlador_Dialogo_Buscar_Proveedores(Panel_Proveedor panel_Proveedor, Connection conexion) {
        this.conexion = conexion;
        this.panel_Proveedor = panel_Proveedor;
        this.panel_Proveedor.campo_Buscar.addKeyListener(this);
        this.panel_Proveedor.tabla_Proveedores.addMouseListener(this);
        this.panel_Proveedor.combo_Opciones.addActionListener(this);
        this.modelo_Tabla_Proveedor = ( DefaultTableModel ) this.panel_Proveedor.tabla_Proveedores.getModel();
    }
    
    public void iniciar(){
        this.consultar_Datos_Proveedores();
        //return this.consultar_Datos_Proveedores();
    }
    
    public void consultar_Datos_Proveedores(){
        this.modelo_Tabla_Proveedor.setRowCount(0);
        ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion).consultar("");

        if (proveedor.size() > 0) {
            for (int i = 0; i < proveedor.size(); i++) {
                Object[] fila = {proveedor.get(i).getId_Proveedor(), proveedor.get(i).getProveedor(), proveedor.get(i).getRUC(), proveedor.get(i).getProductos(), proveedor.get(i).getDireccion(), proveedor.get(i).getCorreo(), proveedor.get(i).getTelefono()};
                this.modelo_Tabla_Proveedor.addRow(fila);
            }
        }
        //return this.modelo_Tabla_Proveedor;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.panel_Proveedor.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Proveedor.campo_Buscar.setEditable(false);
        } else {
            this.panel_Proveedor.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Proveedor.campo_Buscar) {
                this.panel_Proveedor.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }});

                TRSFiltro = new TableRowSorter(this.panel_Proveedor.tabla_Proveedores.getModel());
                this.panel_Proveedor.tabla_Proveedores.setRowSorter(TRSFiltro);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {   
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    public void filtro(){
        if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por RUC / CI") {
            filtrar_Tabla(2);
        } else if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por producto") {
            filtrar_Tabla(3);
        }
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Proveedor.boton_Modificar.setEnabled(true);
            this.panel_Proveedor.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Proveedor.boton_Modificar.setEnabled(false);
            this.panel_Proveedor.boton_Eliminar.setEnabled(false);
        }
    }
    
    public void filtrar_Tabla(int valor){
        seleccion_Tabla(this.panel_Proveedor.tabla_Proveedores.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Proveedor.campo_Buscar.getText(), valor ));
        if(this.panel_Proveedor.tabla_Proveedores.getRowCount() > 0 ){
            this.panel_Proveedor.boton_Reporte_Proveedor.setEnabled(true);
        }else{
            this.panel_Proveedor.boton_Reporte_Proveedor.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        seleccion_Tabla(this.panel_Proveedor.tabla_Proveedores.getSelectedRow());
        /*
        if (me.getSource() == this.panel_Proveedor.tabla_Proveedores) {
            this.proveedor = new DAO_Proveedor_Implementacion(this.conexion).consultar(String.valueOf(this.panel_Proveedor.tabla_Proveedores.getValueAt( this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 0)));

            if (proveedor.size()==1) {
                this.panel_Proveedor.dispose();
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
    }
    
    
}
