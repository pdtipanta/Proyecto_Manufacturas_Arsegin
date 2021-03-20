/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Estado_De_Cuenta;


import Modelo.Factura;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author David
 */
public class Tabla_PDF__Estado_Cuenta_Facturacion {
    private Factura modelo_Factura;
    
    public void construir_TablaPDF( JTable tabla, ArrayList<Factura> lista_Facturas ) {
        tabla.setDefaultRenderer( Object.class, new Imagen_Tabla_Estado_Cuenta_Facturacion() );
        DefaultTableModel modelo_Tabla = new DefaultTableModel() {
            
            @Override
            public boolean isCellEditable( int row, int column ) {
                return false;
            }
        };
        
        modelo_Tabla.setRowCount(0);
        tabla.setModel(modelo_Tabla);
        modelo_Tabla.addColumn("Cliente");
        modelo_Tabla.addColumn("N° Factura");
        modelo_Tabla.addColumn("Fecha");
        modelo_Tabla.addColumn("Estado");
        modelo_Tabla.addColumn("Valor");
        modelo_Tabla.addColumn("Factura");

        ImageIcon icono = null;
        if (get_Image("/Imagenes/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/Imagenes/32pdf.png"));
        }

        if (lista_Facturas.size() > 0) {
            for (int i = 0; i < lista_Facturas.size(); i++) {
                Object fila[] = new Object[6]; //Si las pruebas salen exitosas, disminuir a 2, porque anteriormente era para 3 columnas en MySQL ahora trabajamos con 2
                this.modelo_Factura = lista_Facturas.get(i);
                fila[0] = modelo_Factura.getCliente();
                fila[1] = modelo_Factura.getNo_Factura();
                fila[2] = modelo_Factura.getFecha();
                fila[3] = modelo_Factura.getEstado();
                fila[4] = modelo_Factura.getValor_Total();

                if (modelo_Factura.getNo_Factura() != null) {
                    fila[5] = new JButton(icono);
                } else {
                    fila[5] = new JButton("Vacio");
                }
                modelo_Tabla.addRow(fila);
            }
            tabla.setModel(modelo_Tabla);
            tabla.setRowHeight(32);
            TableColumn columna = tabla.getColumn("Factura");
            columna.setMaxWidth(72);
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
