/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author David
 */
public class Render_Tabla_Productos_Cotizacion extends DefaultTableCellRenderer{
    private final int columna_Patron;
    
    public Render_Tabla_Productos_Cotizacion(int col_Tabla) {
        this.columna_Patron = col_Tabla;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

        switch (this.columna_Patron) {
            case 0:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            case 1:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

        }
        super.getTableCellRendererComponent(table, value, Selected, Selected, row, col);
        return this;
    }
}
