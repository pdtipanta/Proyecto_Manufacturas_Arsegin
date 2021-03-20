/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor.Reporte_Proveedor;

import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Proveedor;
import Vista.Proveedor.Reportes.Dialogo_Reporte_Proveedor;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class Controlador_Reporte_Proveedor implements ActionListener {
    private Vista_Principal               vista;
    private final Connection              conexion;
    ArrayList<Proveedor>                  lista_Proveedores;
    private Dialogo_Reporte_Proveedor     panel_Reporte_Proveedor;

    public Controlador_Reporte_Proveedor(Vista_Principal vista, Connection conexion, ArrayList<Proveedor> lista_Proveedores) {
        this.vista = vista;
        this.conexion = conexion;
        this.lista_Proveedores = lista_Proveedores;
        this.panel_Reporte_Proveedor = new Dialogo_Reporte_Proveedor(this.vista, true);
        this.panel_Reporte_Proveedor.boton_Generar_Reporte.addActionListener(this);
    }

    public void iniciar() {
        try {
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Proveedor.jasper"), new HashMap(), new JRBeanCollectionDataSource(lista_Proveedores)), false).setVisible(true);
            //this.panel_Reporte_Proveedor.dispose();
        } catch (Exception e) {
        }
        //this.panel_Reporte_Proveedor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {/*
        if (ae.getSource() == this.panel_Reporte_Proveedor.boton_Generar_Reporte) {
            ArrayList< Proveedor> proveedor = new ArrayList< Proveedor>();

            if (this.panel_Reporte_Proveedor.combo_Opcion.getSelectedItem() == "Por proveedor") {
                this.buscar_Reporte(this.panel_Reporte_Proveedor.campo_Busqueda_Reporte.getText());
            } else if (this.panel_Reporte_Proveedor.combo_Opcion.getSelectedItem() == "Por producto") {
                proveedor = new DAO_Proveedor_Implementacion(this.conexion).consulta_Proveedor_Producto(this.panel_Reporte_Proveedor.campo_Busqueda_Reporte.getText());
                if (proveedor.size() > 0) {
                    generar_Reporte(proveedor);
                }
            } else if (this.panel_Reporte_Proveedor.combo_Opcion.getSelectedItem() == "Todos") {
                this.buscar_Reporte("");
            }
        }*/
    }

    public void buscar_Reporte(String valor) {/*
        ArrayList< Proveedor> proveedor = new ArrayList< Proveedor>();
        proveedor = new DAO_Proveedor_Implementacion(this.conexion).consultar(valor);

        if (proveedor.size() > 0) {
            generar_Reporte(proveedor);
        }*/
    }

    public void generar_Reporte(ArrayList lista_Proveedores) {/*
        try {
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Proveedor.jasper"), new HashMap(), new JRBeanCollectionDataSource(lista_Proveedores)), false).setVisible(true);
            this.panel_Reporte_Proveedor.dispose();
        } catch (Exception e) {
        }*/
    }
}
