/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Pagos.Reporte;

import Modelo.Compras;
import Vista.Pagos.Panel_Pagos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class Controlador_Reporte_Pagos {

    private Panel_Pagos panel_Pagos;
    private ArrayList<Compras> lista_Compras;

    public Controlador_Reporte_Pagos(Panel_Pagos panel_Pagos, ArrayList<Compras> lista_Compras) {
        this.lista_Compras = lista_Compras;
        this.panel_Pagos = panel_Pagos;
    }

    public void iniciar() {
        try {
            Map parametro = new HashMap();

            parametro.put("RUC", this.panel_Pagos.campo_RUC.getText());
            parametro.put("telefono", this.panel_Pagos.campo_Telefono.getText());
            parametro.put("direccion", this.panel_Pagos.campo_Direccion.getText());
            parametro.put("proveedor", this.panel_Pagos.combo_Proveedor.getText());
            parametro.put("correo", this.panel_Pagos.campo_Correo.getText());
            JasperReport reporte_Inventario = (JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Pagos.jasper");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte_Inventario, parametro, new JRBeanCollectionDataSource(this.lista_Compras));
            JasperViewer visualizar = new JasperViewer(imprimir, false);
            visualizar.setVisible(true);
        } catch (Exception e) {
        }
    }
}
