/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario;

import Datos.Creador;
import Datos.Factory;
import Modelo.Inventario;
import Vista.Inventario.Dialogo_Devoluciones_Inventario;
import Vista.Vista_Principal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Devoluciones implements MouseListener{
    private final Vista_Principal                   vista;
    private final Dialogo_Devoluciones_Inventario   dialogo_Devoluciones_Inventario;
    private ArrayList< Inventario>                  inventario = new ArrayList<Inventario>();
    private DefaultTableModel                       modelo_Tabla_Productos;

    public Controlador_Dialogo_Devoluciones(Vista_Principal vista) {
        this.vista = vista;
        this.dialogo_Devoluciones_Inventario = new Dialogo_Devoluciones_Inventario(this.vista, true);
        this.dialogo_Devoluciones_Inventario.tabla_Inventario.addMouseListener(this);
        this.modelo_Tabla_Productos = (DefaultTableModel) this.dialogo_Devoluciones_Inventario.tabla_Inventario.getModel();
    }
    
    public void iniciar() {
        consultar_Datos_Inventario();
        this.dialogo_Devoluciones_Inventario.setVisible(true);
    }

    public void consultar_Datos_Inventario() {
        /*
        Factory fabrica = new Creador_Inventario().consultar();
        Creador dao = fabrica.consultar();

        inventario = dao.consultar("");

        if (inventario.size() > 0) {
            for (int i = 0; i < inventario.size(); i++) {
                Object[] fila = {inventario.get(i).getCodigo(), inventario.get(i).getDescripcion(), inventario.get(i).getCantidad_Disponible()};
                this.modelo_Tabla_Productos.addRow(fila);
            }
        }*/
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Devoluciones_Inventario.tabla_Inventario) {
            this.dialogo_Devoluciones_Inventario.valores_Inventario(String.valueOf(this.dialogo_Devoluciones_Inventario.tabla_Inventario.getValueAt(this.dialogo_Devoluciones_Inventario.tabla_Inventario.getSelectedRow(), 0)), String.valueOf(this.dialogo_Devoluciones_Inventario.tabla_Inventario.getValueAt(this.dialogo_Devoluciones_Inventario.tabla_Inventario.getSelectedRow(), 1)));
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
