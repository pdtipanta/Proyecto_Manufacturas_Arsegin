/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Estado_De_Cuenta.Reporte;

import Modelo.Factura;
import Vista.Estado_De_Cuenta.Panel_Estado_De_Cuenta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class Controlador_Reporte_Estado_De_Cuenta {

    private ArrayList<Factura>        lista_Factura;
    private Panel_Estado_De_Cuenta    panel_Estado_De_Cuenta;

    public Controlador_Reporte_Estado_De_Cuenta(Panel_Estado_De_Cuenta panel_Estado_De_Cuenta, ArrayList<Factura> lista_Factura) {
        this.lista_Factura = lista_Factura;
        this.panel_Estado_De_Cuenta = panel_Estado_De_Cuenta;
    }

    public void iniciar() {

        try {
            Map parametro = new HashMap();
            
            parametro.put("RUC", this.panel_Estado_De_Cuenta.campo_RUC.getText());
            parametro.put("telefono", this.panel_Estado_De_Cuenta.campo_Telefono.getText());
            parametro.put("direccion", this.panel_Estado_De_Cuenta.campo_Direccion.getText());
            parametro.put("cliente", this.panel_Estado_De_Cuenta.campo_Cliente.getText());
            parametro.put("ciudad", this.panel_Estado_De_Cuenta.campo_Ciudad.getText());
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Estado_Cuenta.jasper"), parametro, new JRBeanCollectionDataSource(this.lista_Factura)), false).setVisible(true);
        } catch (Exception e) {
        }
    }
}
