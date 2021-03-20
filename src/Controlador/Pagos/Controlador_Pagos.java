/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Pagos;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Pagos.Reporte.Controlador_Reporte_Pagos;
import Controlador.Proveedor.Controlador_Dialogo_Buscar_Proveedor;
import Datos.Compras.DAO_Compras_Implementacion;
import Modelo.Compras;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Pagos.Panel_Pagos;
import Vista.Vista_Principal;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Pagos implements  ActionListener, MouseListener {
    private Vista_Principal         vista;
    private Connection              conexion_Database;
    private Usuario                 usuario;
    private String                  rol;
    private String                  id_Proveedor;
    private Panel_Pagos             panel_Pagos = new Panel_Pagos();;
    private Tabla_PDF_Pagos         tabla_PDF_Pagos;
    private ArrayList<Compras>      lista_Compras = new ArrayList<Compras>();
    private ArrayList< Orden_Compra >          lista_Orden_Compra = new ArrayList<Orden_Compra>();
    private DefaultTableModel       modelo_Tabla_Consulta_Pagos = ( DefaultTableModel ) this.panel_Pagos.tabla_Estado_Pagos.getModel();
    
    public Controlador_Pagos( Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol ){
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Pagos.boton_Buscar.addActionListener(this);
        this.panel_Pagos.combo_Opcion.addActionListener(this);
        this.panel_Pagos.boton_Reporte.addActionListener(this);
        this.panel_Pagos.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Pagos.tabla_Estado_Pagos.addMouseListener( this );
    }
    
    public void iniciar() {
        this.vista.Panel_Contenedor.add(this.panel_Pagos);
        this.panel_Pagos.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Pagos.boton_Buscar) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();

            if (proveedor.size() == 1) {
                this.panel_Pagos.valores_Proveedores(proveedor.get(0).getProveedor(), proveedor.get(0).getDireccion(), proveedor.get(0).getTelefono(), proveedor.get(0).getCorreo(), proveedor.get(0).getRUC());
                this.panel_Pagos.activar_Botones(true, true);
                this.id_Proveedor = proveedor.get(0).getId_Proveedor();
            }
        }

        if (ae.getSource() == this.panel_Pagos.combo_Opcion) {
            facturas(this.panel_Pagos.combo_Opcion.getSelectedItem().toString());
        }
        
        if(ae.getSource() == this.panel_Pagos.boton_Reporte){
            new Controlador_Reporte_Pagos(this.panel_Pagos, this.lista_Compras).iniciar();
        }
        
        if (ae.getSource() == this.panel_Pagos.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }
    
    public void facturas(String opcion) { 
        this.modelo_Tabla_Consulta_Pagos.setRowCount(0);

        this.lista_Compras = new DAO_Compras_Implementacion(this.conexion_Database).consultar_Pagos_Adeudados(this.id_Proveedor, opcion);
        tabla_PDF_Pagos = new Tabla_PDF_Pagos();
        tabla_PDF_Pagos.construir_TablaPDF(this.panel_Pagos.tabla_Estado_Pagos, lista_Compras);
        
        if(this.panel_Pagos.tabla_Estado_Pagos.getRowCount()>0){
            this.panel_Pagos.boton_Reporte.setEnabled(true);
        }else{
            this.panel_Pagos.boton_Reporte.setEnabled(false);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Pagos.tabla_Estado_Pagos) {
            int column = this.panel_Pagos.tabla_Estado_Pagos.getColumnModel().getColumnIndexAtX(me.getX());
            int row = me.getY() / this.panel_Pagos.tabla_Estado_Pagos.getRowHeight();

            if (column == 4) {
                Object ubicacion_BotonTabla = this.panel_Pagos.tabla_Estado_Pagos.getValueAt(row, column);

                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();
                    ejecutar_archivoPDF(this.lista_Compras.get(row).getFactura());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void ejecutar_archivoPDF(byte[] factura) {
        try {
            InputStream datos_SerialesPDF = new ByteArrayInputStream(factura);
            int tamano_Entrada = datos_SerialesPDF.available();
            byte[] datos_PDF = new byte[tamano_Entrada];
            datos_SerialesPDF.read(datos_PDF, 0, tamano_Entrada);

            OutputStream salida_PDF = new FileOutputStream("new.pdf");
            salida_PDF.write(datos_PDF);

            Desktop.getDesktop().open(new File("new.pdf"));
            salida_PDF.close();
            datos_SerialesPDF.close();

        } catch (IOException | NumberFormatException ex) {
        }
    }

    public void set_Usuario() {
        this.panel_Pagos.set_Usuario(this.usuario, this.rol);
    }
}



