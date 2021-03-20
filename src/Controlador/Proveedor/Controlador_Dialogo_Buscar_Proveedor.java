/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor;

import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Proveedor;
import Vista.Proveedor.Dialogo_Buscar_Proveedor;
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
public class Controlador_Dialogo_Buscar_Proveedor implements ActionListener, KeyListener, MouseListener{
    private final Vista_Principal               vista;
    private final Connection                    conexion;
    private final Dialogo_Buscar_Proveedor      dialogo_Buscar_Proveedor;
    private DefaultTableModel                   modelo_Tabla_Proveedor;
    private TableRowSorter                      TRSFiltro;
    private ArrayList< Proveedor>               proveedor = new ArrayList<Proveedor>();

    public Controlador_Dialogo_Buscar_Proveedor(Vista_Principal vista, Connection conexion) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Buscar_Proveedor = new Dialogo_Buscar_Proveedor(this.vista, true); 
        this.dialogo_Buscar_Proveedor.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Proveedor.tabla_Proveedores.addMouseListener(this);
        this.dialogo_Buscar_Proveedor.combo_Opciones.addActionListener(this);
        this.modelo_Tabla_Proveedor = ( DefaultTableModel ) this.dialogo_Buscar_Proveedor.tabla_Proveedores.getModel();
    }
    
    public ArrayList<Proveedor> iniciar(){
        this.consultar_Datos_Proveedores();
        this.dialogo_Buscar_Proveedor.setVisible(true);
        return proveedor;
    }
    
    public void consultar_Datos_Proveedores(){
        ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion).consultar("");

        if (proveedor.size() > 0) {
            for (int i = 0; i < proveedor.size(); i++) {
                Object[] fila = {proveedor.get(i).getId_Proveedor(), proveedor.get(i).getProveedor(), proveedor.get(i).getRUC(), proveedor.get(i).getProductos(), proveedor.get(i).getDireccion(), proveedor.get(i).getCorreo(), proveedor.get(i).getTelefono()};
                this.modelo_Tabla_Proveedor.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Proveedor.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Proveedor.campo_Buscar.setEditable(false);
        } else {
            this.dialogo_Buscar_Proveedor.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Proveedor.campo_Buscar) {
                this.dialogo_Buscar_Proveedor.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }});

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Proveedor.tabla_Proveedores.getModel());
                this.dialogo_Buscar_Proveedor.tabla_Proveedores.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Proveedor.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Proveedor.combo_Opciones.getSelectedItem() == "Por RUC / CI") {
            filtrar_Tabla(2);
        } else if (this.dialogo_Buscar_Proveedor.combo_Opciones.getSelectedItem() == "Por producto") {
            filtrar_Tabla(3);
        }
    }
    
    public void filtrar_Tabla(int valor){
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Proveedor.campo_Buscar.getText(), valor ));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Proveedor.tabla_Proveedores) {
            this.proveedor = new DAO_Proveedor_Implementacion(this.conexion).consultar(String.valueOf(this.dialogo_Buscar_Proveedor.tabla_Proveedores.getValueAt( this.dialogo_Buscar_Proveedor.tabla_Proveedores.getSelectedRow(), 0)));

            if (proveedor.size()==1) {
                this.dialogo_Buscar_Proveedor.dispose();
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
    }
}
