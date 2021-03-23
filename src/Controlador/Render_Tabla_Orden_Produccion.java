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
public class Render_Tabla_Orden_Produccion extends DefaultTableCellRenderer{
    private final int columna_Patron;
    
    public Render_Tabla_Orden_Produccion(int col_Tabla){
        this.columna_Patron = col_Tabla;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

        switch (this.columna_Patron) {
            case 0:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case 3:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case 4:
                setHorizontalAlignment(SwingConstants.CENTER);

                if (table.getValueAt(row, 4).equals("Pagado")) {
                    setBackground(Color.GREEN);
                } else if (table.getValueAt(row, 4).equals("Por pagar")) {
                    setBackground(Color.ORANGE);
                }
                break;
        }
        super.getTableCellRendererComponent(table, value, Selected, Selected, row, col);
        return this;
    }
}
