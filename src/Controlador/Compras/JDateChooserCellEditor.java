/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Compras;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author David
 */
class JDateChooserCellEditor extends AbstractCellEditor implements
        TableCellEditor {

    private static final long serialVersionUID = 917881575221755609L;

    private JDateChooser dateChooser = new JDateChooser();
    private AbstractTableModel model;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, final int row, final int column) {
        try {
            model = (AbstractTableModel) table.getModel();
        } catch (Exception e) {
        };

        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String pname = evt.getPropertyName();
                if ("date".equals(pname)) {
                    try {
                        model.fireTableCellUpdated(row, column);
                    } catch (Exception e) {
                    };
                }
            }
        });

        return dateChooser;
    }

    @Override
    public Object getCellEditorValue() {
        return dateChooser.getDate();
    }
}