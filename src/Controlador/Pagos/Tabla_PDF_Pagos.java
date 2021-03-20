/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Pagos;

import Modelo.Compras;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Tabla_PDF_Pagos extends DefaultTableCellRenderer{
    Compras modelo_Compras;
    
    public void construir_TablaPDF( JTable tabla, ArrayList<Compras> lista_Compras ) {
        tabla.setDefaultRenderer( Object.class, new Imagen_Tabla_Pagos() );

        DefaultTableModel modelo_Tabla = new DefaultTableModel() {
           
            @Override
            public boolean isCellEditable( int row, int column ) {
                return false;
            }
        };
        
        modelo_Tabla.setRowCount (0);
        
        tabla.setModel( modelo_Tabla );
        modelo_Tabla.addColumn( "No_Factura" );//10
        modelo_Tabla.addColumn( "Fecha" );//2
        modelo_Tabla.addColumn( "Estado" );
        modelo_Tabla.addColumn( "Valor" );//3
        modelo_Tabla.addColumn( "Factura" );//3
        
        ImageIcon icono = null;
        if ( get_Image( "/Imagenes/32pdf.png" ) != null ) {
            icono = new ImageIcon( get_Image( "/Imagenes/32pdf.png" ) );
        }

        if ( lista_Compras.size() > 0 ) {
            for ( int i = 0; i < lista_Compras.size(); i++ ) {
                Object fila[] = new Object[ 5 ]; //Si las pruebas salen exitosas, disminuir a 2, porque anteriormente era para 3 columnas en MySQL ahora trabajamos con 2
                this.modelo_Compras = lista_Compras.get( i );
                
                fila[ 0 ] = modelo_Compras.getNo_Factura();
                fila[ 1 ] = modelo_Compras.getFecha();
                fila[ 2 ] = modelo_Compras.getEstado();
                fila[ 3 ] = modelo_Compras.getValor();
                
                if ( modelo_Compras.getNo_Factura() != null ) {
                    fila[ 4 ] = new JButton( icono ); 

                } else {
                    fila[ 4 ] = new JButton( "Vacio" );
                }
                modelo_Tabla.addRow( fila );
            }
            
            tabla.setModel( modelo_Tabla );
            tabla.setRowHeight( 32 );
        }
    }
    
    public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon( getClass().getResource(ruta) );
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch ( Exception e ) {}
        return null;
    }
}
