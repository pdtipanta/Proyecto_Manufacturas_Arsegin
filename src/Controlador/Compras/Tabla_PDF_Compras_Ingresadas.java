/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Compras;

import Modelo.Compras;
import Modelo.Orden_Compra;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Tabla_PDF_Compras_Ingresadas extends DefaultTableCellRenderer {

    JComboBox combo = new JComboBox(new String[]{"Seleccione...", "Pagado", "Por pagar", "Anulado"});

    public void construir_TablaPDF(JTable tabla, ArrayList<Orden_Compra> lista_Orden, ArrayList<Compras> lista_Compra, Orden_Compra modelo_Orden_Compra, Compras modelo_Compras) {
        tabla.setDefaultRenderer(Object.class, new Imagen_Tabla_Compras());

        DefaultTableModel modelo_Tabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                boolean bandera = false;
                if(column == 9 && (lista_Compra.get(row).getEstado().equals("Por pagar") || lista_Compra.get(row).getEstado().equals("Seleccione..."))){
                    bandera = true;
                }
                return bandera;
            }
        };

        modelo_Tabla.setRowCount(0);
        tabla.setModel(modelo_Tabla);

        modelo_Tabla.addColumn("No_Orden");//0
        modelo_Tabla.addColumn("ID Proveedor");//1
        modelo_Tabla.addColumn("Fecha");//2
        modelo_Tabla.addColumn("Pago");//3
        modelo_Tabla.addColumn("Valor");//3
        modelo_Tabla.addColumn("Orden");//4
        modelo_Tabla.addColumn("No_Factura");//5
        modelo_Tabla.addColumn("Fecha factura");//6
        modelo_Tabla.addColumn("Factura");//7
        modelo_Tabla.addColumn("Estado");
        modelo_Tabla.addColumn("Guardar");

        ImageIcon icono = null;
        if (get_Image("/Imagenes/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Imagenes/32pdf.png"));
        }
        
        ImageIcon icono_1 = null;
        if (get_Image("/Imagenes/disquete.png") != null) {
            icono_1 = new ImageIcon(get_Image("/Imagenes/disquete.png"));
        }

        if (lista_Orden.size() > 0 && (lista_Orden.size() == lista_Compra.size())) {
            for (int i = 0; i < lista_Orden.size(); i++) {

                Object fila[] = new Object[11]; //Si las pruebas salen exitosas, disminuir a 2, porque anteriormente era para 3 columnas en MySQL ahora trabajamos con 2
                modelo_Orden_Compra = lista_Orden.get(i);
                modelo_Compras = lista_Compra.get(i);

                fila[0] = modelo_Orden_Compra.getNo_Orden();
                fila[1] = modelo_Orden_Compra.getProveedor();
                fila[2] = modelo_Orden_Compra.getFecha();
                fila[3] = modelo_Orden_Compra.getTipo_Pago();
                fila[4] = modelo_Orden_Compra.getValor_Total();
                fila[6] = modelo_Compras.getNo_Factura();
                fila[7] = modelo_Compras.getFecha();
                fila[9] = modelo_Compras.getEstado();

                if (modelo_Orden_Compra.getNo_Orden() != null) {
                    if(modelo_Compras.getEstado().equals("Pagado")||modelo_Compras.getEstado().equals("Anulado")){
                        fila[10] = "Registrado";
                    }else{
                        fila[10] = new JButton(icono_1);
                    }
                    fila[5] = new JButton(icono);
                    fila[8] = new JButton(icono);
                    
                    tabla.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor( combo ));

                } else {
                    fila[5] = new JButton("Vacio");
                    fila[7] = new JButton("Vacio");
                }
                modelo_Tabla.addRow(fila);
            }
            tabla.setModel(modelo_Tabla);
            tabla.setRowHeight(32);
        }
    }

    public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) {
        }
        return null;
    }
}

   
