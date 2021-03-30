/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra.Reporte;

import Modelo.Orden_Compra;
import Modelo.Proveedor;
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
public class Controlador_Reporte_Orden_Compra {

    private final Orden_Compra orden_Compra;
    private final Proveedor proveedor;

    public Controlador_Reporte_Orden_Compra(Orden_Compra orden_Compra, Proveedor proveedor) {
        this.orden_Compra = orden_Compra;
        this.proveedor = proveedor;
    }

    public void iniciar() {

        try {
            Map parametro = new HashMap();
            parametro.put("Numero_Orden", this.orden_Compra.getNo_Orden());
            parametro.put("RUC", this.proveedor.getRUC());
            parametro.put("telefono", this.proveedor.getTelefono());
            parametro.put("direccion", this.proveedor.getDireccion());
            parametro.put("correo", this.proveedor.getCorreo());
            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Orden_Compra.jasper"), parametro, new JRBeanCollectionDataSource(construir_Orden())), false).setVisible(true);
        } catch (Exception e) {
        }
    }

    public ArrayList<Orden_Compra> construir_Orden() {
        ArrayList<Orden_Compra> lista_Productos = new ArrayList<Orden_Compra>();

        String[] cantidad = this.orden_Compra.getCantidad().split(";");
        String[] codigo = this.orden_Compra.getCodigo().split(";");
        String[] descripcion = this.orden_Compra.getDescripcion().split(";");
        String[] v_Unitario = this.orden_Compra.getV_Unitario().split(";");
        String[] v_Total = this.orden_Compra.getV_Total().split(";");

        for (int i = 0; i < cantidad.length - 1; i++) {
            Orden_Compra modelo_Orden_Compra = new Orden_Compra(this.orden_Compra.getNo_Orden(), this.orden_Compra.getFecha(), this.orden_Compra.getV_Subtotal(), this.orden_Compra.getIVA(), this.orden_Compra.getValor_Total(), this.orden_Compra.getEstado(), this.orden_Compra.getTipo_Pago(), this.orden_Compra.getSolicitante(), this.orden_Compra.getProveedor(), cantidad[i], codigo[i], descripcion[i], v_Unitario[i], v_Total[i]);
            lista_Productos.add(modelo_Orden_Compra);
        }
        return lista_Productos;
    }
}
