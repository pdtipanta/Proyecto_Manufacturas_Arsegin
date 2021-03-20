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
public class Controlador_Reporte_Compras {
    private Proveedor proveedor;
    private ArrayList<Orden_Compra> lista_Productos;

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
            JasperReport reporte_Inventario = (JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Orden_Compra.jasper");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte_Inventario, parametro, new JRBeanCollectionDataSource(lista_Productos));
            JasperViewer visualizar = new JasperViewer(imprimir, false);
            visualizar.setVisible(true);

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
