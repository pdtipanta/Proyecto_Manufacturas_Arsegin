/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Maquila;

import Datos.Maquila.DAO_Maquila_Implementacion;
import Modelo.Maquila;
import Vista.Maquilas.Dialogo_Buscar_Maquilas;
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
public class Controlador_Dialogo_Buscar_Maquila implements KeyListener, MouseListener{
    private final Vista_Principal           vista;
    private final Connection                conexion;
    private final Dialogo_Buscar_Maquilas   dialogo_Buscar_Maquilas;
    private DefaultTableModel               modelo_Tabla_Maquilas;
    private TableRowSorter                  TRSFiltro;
    private ArrayList<Maquila>              maquila = new ArrayList<Maquila>();

    public Controlador_Dialogo_Buscar_Maquila(Vista_Principal vista, Connection conexion) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Buscar_Maquilas = new Dialogo_Buscar_Maquilas(this.vista, true); 
        this.dialogo_Buscar_Maquilas.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Maquilas.tabla_Maquilas.addMouseListener(this);
        this.modelo_Tabla_Maquilas = ( DefaultTableModel ) this.dialogo_Buscar_Maquilas.tabla_Maquilas.getModel();
    }
    
    public ArrayList<Maquila> iniciar(){
        consultar_Datos_Maquila();
        this.dialogo_Buscar_Maquilas.setVisible(true);
        return maquila;
    }
    
    public void consultar_Datos_Maquila(){
        ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion).consultar("Todos");

        if (maquila.size() > 0) {
            for (int i = 0; i < maquila.size(); i++) {
                Object[] fila = {maquila.get(i).getId_Maquila(), maquila.get(i).getMaquila(), maquila.get(i).getRUC(), maquila.get(i).getServicio(), maquila.get(i).getDireccion(), maquila.get(i).getTelefono()};
                this.modelo_Tabla_Maquilas.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Maquilas.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Maquilas.campo_Buscar.setEditable(false);
        } else {
            this.dialogo_Buscar_Maquilas.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Maquilas.campo_Buscar) {
                this.dialogo_Buscar_Maquilas.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }});

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Maquilas.tabla_Maquilas.getModel());
                this.dialogo_Buscar_Maquilas.tabla_Maquilas.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Maquilas.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Maquilas.combo_Opciones.getSelectedItem() == "Por RUC / CI") {
            filtrar_Tabla(2);
        } else if (this.dialogo_Buscar_Maquilas.combo_Opciones.getSelectedItem() == "Por servicio") {
            filtrar_Tabla(3);
        }
    }
    
    public void filtrar_Tabla(int valor){
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Maquilas.campo_Buscar.getText(), valor ));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Maquilas.tabla_Maquilas) {
            maquila = new DAO_Maquila_Implementacion(this.conexion).consultar(String.valueOf(this.dialogo_Buscar_Maquilas.tabla_Maquilas.getValueAt( this.dialogo_Buscar_Maquilas.tabla_Maquilas.getSelectedRow(), 0)));

            if (maquila.size()==1) {
                this.dialogo_Buscar_Maquilas.dispose();
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
