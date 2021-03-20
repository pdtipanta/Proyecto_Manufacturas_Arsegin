/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Compras;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author David
 */
class JDateChooserRenderer extends JDateChooser implements TableCellRenderer {
    public JDateChooserRenderer(JDateChooser dateChooser) {
        if (dateChooser != null) {
            this.setDate(dateChooser.getDate());
        }
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Date) {
            this.setDate((Date) value);
        } else if (value instanceof String ) {
            
        }
        return this;
    }
}
