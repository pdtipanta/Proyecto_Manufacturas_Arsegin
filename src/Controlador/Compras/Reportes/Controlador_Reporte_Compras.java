/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Compras.Reportes;

import Modelo.Orden_Compra;
import Modelo.Proveedor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class Controlador_Reporte_Compras {

    private final Proveedor proveedor;
    private final ArrayList<Orden_Compra> lista_Productos;

    public Controlador_Reporte_Compras(Proveedor proveedor, ArrayList<Orden_Compra> lista_Productos) {
        this.proveedor = proveedor;
        this.lista_Productos = lista_Productos;
    }

    public void iniciar() {
        try {
            Map parametro = new HashMap();
            parametro.put("RUC", this.proveedor.getRUC());
            parametro.put("telefono", this.proveedor.getTelefono());
            parametro.put("direccion", this.proveedor.getDireccion());
            parametro.put("correo", this.proveedor.getCorreo());

            new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Orden_Compra.jasper"), parametro, new JRBeanCollectionDataSource(lista_Productos)), false).setVisible(true);
        } catch (JRException e) {
        }
    }
}
