/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura.Reporte;

import Modelo.Cliente;
import Modelo.Factura;
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
public class Controlador_Reporte_Factura {
    private Factura factura;
    private Cliente cliente;

    public Controlador_Reporte_Factura(Factura factura, Cliente cliente) {
        this.factura = factura;
        this.cliente = cliente;
    }

    public void iniciar() {
        this.visualizar_Factura();
    }

    public ArrayList<Factura> construir_Factura() {
        Factura factura = null;
        ArrayList<Factura> lista_Facturas = new ArrayList<Factura>();

        String[] cantidad = this.factura.getCantidad().split(";");
        String[] codigo = this.factura.getCodigo().split(";");
        String[] descripcion = this.factura.getDescripcion().split(";");
        String[] v_Unitario = this.factura.getV_Unitario().split(";");
        String[] v_Total = this.factura.getV_Total().split(";");
        String[] fecha = this.factura.getFecha().split("-");

        for (int i = 0; i < cantidad.length - 1; i++) {
            factura = new Factura(this.factura.getNo_Factura(), this.factura.getFecha(), this.factura.getV_Subtotal(), this.factura.getIVA(), this.factura.getValor_Total(), this.factura.getEstado(), this.factura.getCliente(), cantidad[i], codigo[i], descripcion[i], v_Unitario[i], v_Total[i], this.factura.getVendedor(), this.factura.getObservaciones());
            lista_Facturas.add(factura);
        }
        return lista_Facturas;
    }

    public void visualizar_Factura() {
        if (this.factura != null && this.cliente != null) {
            String[] fecha = this.factura.getFecha().split("-");

            try {
                Map parametro = new HashMap();
                parametro.put("nombre", this.cliente.getCliente());
                parametro.put("RUC", this.cliente.getRUC());
                parametro.put("telefono", this.cliente.getTelefono());
                parametro.put("direccion", this.cliente.getDireccion());
                parametro.put("ciudad", this.cliente.getCiudad());
                parametro.put("dia", fecha[2]);
                parametro.put("mes", fecha[1]);
                parametro.put("anio", fecha[0]);
                parametro.put("tasa_IVA", (int)((this.factura.getIVA()/this.factura.getV_Subtotal())*100));
                new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Factura.jasper"), parametro, new JRBeanCollectionDataSource(construir_Factura())), false).setVisible(true);
            } catch (Exception e) {
            }
        }
    }
}
