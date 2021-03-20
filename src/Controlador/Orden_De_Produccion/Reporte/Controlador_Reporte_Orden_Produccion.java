/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion.Reporte;

import Modelo.Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Panel_Orden_De_Produccion;
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
public class Controlador_Reporte_Orden_Produccion {

    Panel_Orden_De_Produccion panel_Orden_Produccion;

    public Controlador_Reporte_Orden_Produccion(Panel_Orden_De_Produccion panel_Orden_De_Produccion) {
        this.panel_Orden_Produccion = panel_Orden_De_Produccion;
    }

    public void iniciar() {
        ArrayList<Orden_Produccion> lista_Trabajo = new ArrayList<Orden_Produccion>();

        for (int i = 0; i < this.panel_Orden_Produccion.tabla_Productos_Maquila.getRowCount(); i++) {
            Orden_Produccion modelo_Orden_Produccion = new Orden_Produccion(this.panel_Orden_Produccion.etiqueta_No_Orden.getText(), this.panel_Orden_Produccion.calendario(), Double.valueOf(this.panel_Orden_Produccion.campo_Total_Orden.getText()), String.valueOf(this.panel_Orden_Produccion.combo_Estado_Orden.getSelectedItem()), this.panel_Orden_Produccion.caja_Observaciones_Maquila.getText(), this.panel_Orden_Produccion.combo_Maquila_Orden.getText(),
                    String.valueOf(this.panel_Orden_Produccion.tabla_Productos_Maquila.getValueAt(i, 0)), String.valueOf(this.panel_Orden_Produccion.tabla_Productos_Maquila.getValueAt(i, 1)), String.valueOf(this.panel_Orden_Produccion.tabla_Productos_Maquila.getValueAt(i, 2)), String.valueOf(this.panel_Orden_Produccion.tabla_Productos_Maquila.getValueAt(i, 3)));
            lista_Trabajo.add(modelo_Orden_Produccion);
        }

        try {
            Map parametro = new HashMap();
            parametro.put("no_Orden", this.panel_Orden_Produccion.etiqueta_No_Orden.getText());
            parametro.put("RUC", this.panel_Orden_Produccion.campo_RUC_Orden.getText());
            parametro.put("telefono", this.panel_Orden_Produccion.campo_Telefono_Orden.getText());
            parametro.put("direccion", this.panel_Orden_Produccion.campo_Direccion_Orden.getText());
            parametro.put("estado", this.panel_Orden_Produccion.combo_Estado_Orden.getSelectedItem());
            parametro.put("maquila", this.panel_Orden_Produccion.combo_Maquila_Orden.getText());
            parametro.put("total", this.panel_Orden_Produccion.campo_Total_Orden.getText());
            parametro.put("observaciones", this.panel_Orden_Produccion.caja_Observaciones_Maquila.getText());
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Orden.jasper"), parametro, new JRBeanCollectionDataSource(lista_Trabajo)), false).setVisible(true);
        } catch (Exception e) {
        }
    }
}
