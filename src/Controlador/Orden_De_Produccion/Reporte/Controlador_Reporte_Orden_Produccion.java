/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion.Reporte;

import Modelo.Maquila;
import Modelo.Orden_Produccion;
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
    private final Orden_Produccion       orden_Produccion;
    private final Maquila                maquila;

    public Controlador_Reporte_Orden_Produccion(Orden_Produccion orden_Produccion, Maquila maquila) {
        this.orden_Produccion = orden_Produccion;
        this.maquila = maquila;
    }

    public void iniciar() {
        try {
            Map parametro = new HashMap();
            parametro.put("no_Orden", this.orden_Produccion.getNumero_Orden());
            parametro.put("RUC", this.maquila.getRUC());
            parametro.put("telefono", this.maquila.getTelefono());
            parametro.put("direccion", this.maquila.getDireccion());
            parametro.put("estado", this.orden_Produccion.getEstado());
            parametro.put("maquila", this.maquila.getMaquila());
            parametro.put("total", this.orden_Produccion.getV_Pagar());
            parametro.put("observaciones", this.orden_Produccion.getObservaciones());
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Orden.jasper"), parametro, new JRBeanCollectionDataSource(this.contruir_Orden())), false).setVisible(true);
        } catch (Exception e) {
        }
    }

    public ArrayList<Orden_Produccion> contruir_Orden() {
        ArrayList<Orden_Produccion> lista_Trabajo = new ArrayList<Orden_Produccion>();

        String[] cantidad = this.orden_Produccion.getCantidad().split(";");
        String[] descripcion = this.orden_Produccion.getDescripcion().split(";");
        String[] v_Unitario = this.orden_Produccion.getV_Unitario().split(";");
        String[] v_Total = this.orden_Produccion.getV_Total().split(";");

        for (int i = 0; i < cantidad.length - 1; i++) {

            lista_Trabajo.add(new Orden_Produccion(this.orden_Produccion.getNumero_Orden(), this.orden_Produccion.getFecha(), this.orden_Produccion.getV_Pagar(), this.orden_Produccion.getEstado(), this.orden_Produccion.getObservaciones(), this.orden_Produccion.getMaquila(), cantidad[i], descripcion[i], v_Unitario[i], v_Total[i]));
        }
        return lista_Trabajo;
    }
}
