/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario.Reporte;

import Modelo.Inventario;
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
public class Controlador_Reporte_Inventario {

    ArrayList<Inventario> inventario;

    public Controlador_Reporte_Inventario(ArrayList<Inventario> inventario) {
        this.inventario = inventario;
    }

    public void iniciar() {
        try {
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Inventario.jasper"), new HashMap(), new JRBeanCollectionDataSource(this.inventario)), false).setVisible(true);
        } catch (Exception e) {
        }
    }
}
