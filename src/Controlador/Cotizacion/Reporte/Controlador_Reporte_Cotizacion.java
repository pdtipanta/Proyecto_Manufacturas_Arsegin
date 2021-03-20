/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion.Reporte;

import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author David
 */
public class Controlador_Reporte_Cotizacion {
    private final Usuario        usuario;
    private final Cliente        cliente;
    private final Cotizacion     cotizacion;   

    public Controlador_Reporte_Cotizacion(Cliente cliente, Cotizacion cotizacion, Usuario usuario) {
        this.cliente = cliente;
        this.cotizacion = cotizacion;
        this.usuario = usuario;
    }

    public void iniciar() {
        if (this.cliente != null && this.cotizacion != null) {
            ArrayList<Cotizacion> lista_Productos = new ArrayList<Cotizacion>();

            String[] cantidad = this.cotizacion.getCantidad().split(";");
            String[] codigo = this.cotizacion.getCodigo().split(";");
            String[] descripcion = this.cotizacion.getDescripcion().split(";");
            String[] v_Unitario = this.cotizacion.getV_Unitario().split(";");
            String[] v_Total = this.cotizacion.getV_Total().split(";");

            for (int i = 0; i < codigo.length - 1; i++) {
                Cotizacion modelo_Cotizacion = new Cotizacion(this.cotizacion.getNo_Cotizacion(), this.cotizacion.getFecha(), this.cotizacion.getTipo_Pago(), this.cotizacion.getV_Subtotal(), this.cotizacion.getIVA(), this.cotizacion.getValor_Total(), this.cotizacion.getEmisor(), this.cliente.getCliente(), cantidad[i], codigo[i], descripcion[i], v_Unitario[i], v_Total[i]);
                lista_Productos.add(modelo_Cotizacion);
                //Object[] valores_Tabla = {cantidad[i], codigo[i], descripcion[i], Double.parseDouble(v_Unitario[i]), Double.parseDouble(v_Total[i])};
                //this.modelo_Tabla_Datos_Cotizacion.addRow(valores_Tabla);
            }
            /*
        for (int i = 0; i < this.panel_Cotizacion.tabla_Productos_Cotizacion.getRowCount(); i++) {
            Cotizacion modelo_Cotizacion = new Cotizacion(this.panel_Cotizacion.etiqueta_No_Cotizacion.getText(), this.panel_Cotizacion.calendario_Fecha(), this.panel_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.panel_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_Total_Cotizacion.getText()), this.panel_Cotizacion.campo_Emisor_Cotizacion.getText(), this.panel_Cotizacion.combo_Cliente_Cotizacion.getText(), String.valueOf(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 0)), String.valueOf(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 1)), String.valueOf(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 2)), String.valueOf(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 3)), String.valueOf(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 4)));
            lista_Productos.add(modelo_Cotizacion);
        }*/

            try {
                Map parametro = new HashMap();
                parametro.put("RUC", this.cliente.getRUC());
                parametro.put("telefono", this.cliente.getTelefono());
                parametro.put("direccion", this.cliente.getDireccion());
                parametro.put("correo", this.cliente.getCorreo());
                parametro.put("emisor", this.usuario.getNombre() + " " + this.usuario.getApellido());
                parametro.put("solicitante", this.cliente.getPersona_Contacto());
                parametro.put("observaciones", this.cotizacion.getTipo_Pago());
                parametro.put("tasa_IVA", (int) ((this.cotizacion.getIVA() / this.cotizacion.getV_Subtotal()) * 100));
                new JasperViewer(JasperFillManager.fillReport((JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "/src/Reportes/Reporte_Cotizacion.jasper"), parametro, new JRBeanCollectionDataSource(lista_Productos)), false).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}
