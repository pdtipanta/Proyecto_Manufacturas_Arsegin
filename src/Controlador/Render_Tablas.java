/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author David
 */
public class Render_Tablas extends DefaultTableCellRenderer{
    private final int columna_Patron;
    Font font = new Font("Arial", Font.BOLD, 14);
    public Render_Tablas(int col_Tabla){
        this.columna_Patron = col_Tabla;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

        switch (this.columna_Patron) {
            case 0:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            case 2:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            case 3:
                setHorizontalAlignment(SwingConstants.RIGHT);
                break;

            case 4:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            case 5:
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            case 6:
                setHorizontalAlignment(SwingConstants.CENTER);
                if (table.getValueAt(row, 6).toString().equals("Credito")) {
                    setBackground(Color.BLUE);
                    setForeground(Color.WHITE);
                } else if (table.getValueAt(row, 6).toString().equals("Pagado")) {
                    setBackground(Color.GREEN);
                    setForeground(Color.BLACK);
                } else if (table.getValueAt(row, 6).toString().equals("Anular")) {
                    setBackground(Color.BLACK);
                    setForeground(Color.WHITE);
                }
                break;
        }
        super.getTableCellRendererComponent(table, value, Selected, Selected, row, col);
        return this;
    }
}
