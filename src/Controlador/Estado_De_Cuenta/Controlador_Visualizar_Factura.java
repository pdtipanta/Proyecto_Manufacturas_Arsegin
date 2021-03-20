/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Estado_De_Cuenta;

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
public class Controlador_Visualizar_Factura {
    private ArrayList<Factura> lista_Factura;
    private Panel_Estado_De_Cuenta panel_Estado_De_Cuenta;

    public Controlador_Visualizar_Factura(ArrayList<Factura> factura, Panel_Estado_De_Cuenta panel_Estado_De_Cuenta) {
        this.lista_Factura = factura;
        this.panel_Estado_De_Cuenta = panel_Estado_De_Cuenta;
    }

    public void iniciar() {
        ArrayList<Factura> lista_Facturas = new ArrayList<Factura>();
        String[] cantidad = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getCantidad().split(";");
        String[] codigo = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getCodigo().split(";");
        String[] descripcion = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getDescripcion().split(";");
        String[] v_Unitario = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getV_Unitario().split(";");
        String[] v_Total = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getV_Total().split(";");
        String[] fecha = this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getFecha().split("-");

        for (int i = 0; i < cantidad.length - 1; i++) {
            Factura factura = new Factura(this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getNo_Factura(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getFecha(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getV_Subtotal(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getIVA(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getValor_Total(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getEstado(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getCliente(), cantidad[i], codigo[i], descripcion[i], v_Unitario[i], v_Total[i], this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getVendedor(), this.lista_Factura.get(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow()).getObservaciones());
            lista_Facturas.add(factura);
        }

        try {
            Map parametro = new HashMap();
            parametro.put("nombre", this.panel_Estado_De_Cuenta.campo_Cliente.getText());
            parametro.put("no_Factura", this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getValueAt(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getSelectedRow(), 1));
            parametro.put("RUC", this.panel_Estado_De_Cuenta.campo_RUC.getText());
            parametro.put("telefono", this.panel_Estado_De_Cuenta.campo_Telefono.getText());
            parametro.put("direccion", this.panel_Estado_De_Cuenta.campo_Direccion.getText());
            parametro.put("ciudad", this.panel_Estado_De_Cuenta.campo_Ciudad.getText());
            parametro.put("dia", fecha[2]);
            parametro.put("mes", fecha[1]);
            parametro.put("anio", fecha[0]);

            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Factura.jasper"), parametro, new JRBeanCollectionDataSource(lista_Facturas)), false).setVisible(true);

        } catch (Exception e) {
        }
    }
}
