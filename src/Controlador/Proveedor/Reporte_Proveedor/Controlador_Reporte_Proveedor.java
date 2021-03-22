/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor.Reporte_Proveedor;

import Modelo.Proveedor;
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
public class Controlador_Reporte_Proveedor {
    ArrayList<Proveedor> lista_Proveedores;

    public Controlador_Reporte_Proveedor(ArrayList<Proveedor> lista_Proveedores) {
        this.lista_Proveedores = lista_Proveedores;
    }

    public void iniciar() {
        try {
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Proveedor.jasper"), new HashMap(), new JRBeanCollectionDataSource(lista_Proveedores)), false).setVisible(true);
        } catch (Exception e) {
        }
    }
}
