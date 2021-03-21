/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clientes;

import Modelo.Cliente;
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
public class Controlador_Reporte_Cliente {
    ArrayList<Cliente> lista_Clientes;

    public Controlador_Reporte_Cliente(ArrayList<Cliente> lista_Clientes) {
        this.lista_Clientes = lista_Clientes;
    }

    public void iniciar() {
        try {
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Cliente.jasper"), new HashMap(), new JRBeanCollectionDataSource(lista_Clientes)), false).setVisible(true);
        } catch (Exception e1) {
        }
    }
}
