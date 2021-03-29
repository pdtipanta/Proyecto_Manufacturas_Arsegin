/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Orden_De_Compra.Panel_Orden_Compra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Orden implements ActionListener {
    private final Panel_Orden_Compra        panel_Orden_Compra;
    private final Connection                conexion;
    private final String                    rol;
    private String                          valor = null;
    private final DefaultTableModel         modelo_Tabla_Cotizacion;
    private final Usuario                   usuario;

    public Controlador_Dialogo_Buscar_Orden(Panel_Orden_Compra panel_Orden_Compra, Connection conexion, Usuario usuario, String rol) {
        this.panel_Orden_Compra = panel_Orden_Compra;
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Orden_Compra.boton_Fecha.addActionListener(this);
        this.panel_Orden_Compra.combo_Opciones.addActionListener(this);
        this.modelo_Tabla_Cotizacion = (DefaultTableModel) this.panel_Orden_Compra.tabla_Consulta_Compra.getModel();
    }

    public void iniciar() {
        consultar_Datos_Orden_Compra();
    }

    public void consultar_Datos_Orden_Compra() {

        switch (this.rol) {
            case "Vendedor":
                valor = "Todos" + ";" + this.usuario.getCedula();
                break;

            case "Contador":
                valor = "Todos" + ";" + "Todos";
                break;

            case "Administrador":
                valor = "Todos" + ";" + "Todos";
                break;
        }
        presentar_Ordenes(new DAO_Orden_Compra_Implementacion(this.conexion).consultar(valor));
    }

    public void presentar_Ordenes(ArrayList<Orden_Compra> orden_Compra) {
        this.modelo_Tabla_Cotizacion.setRowCount(0);

        if (orden_Compra.size() > 0) {
            for (int i = 0; i < orden_Compra.size(); i++) {
                ArrayList<Proveedor> proveedor = this.consultar_Proveedor(orden_Compra.get(i).getProveedor());
                Object[] fila = {orden_Compra.get(i).getNo_Orden(), proveedor.get(0).getProveedor(), proveedor.get(0).getRUC(), orden_Compra.get(i).getValor_Total(), orden_Compra.get(i).getFecha(), orden_Compra.get(i).getSolicitante(), orden_Compra.get(i).getEstado()};
                this.modelo_Tabla_Cotizacion.addRow(fila);
            }
        }
    }

    public ArrayList<Proveedor> consultar_Proveedor(String i) {
        return new DAO_Proveedor_Implementacion(this.conexion).consultar(i);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Orden_Compra.boton_Fecha) {
            if (this.panel_Orden_Compra.verificar_Campos()) {
                this.presentar_Ordenes(new DAO_Orden_Compra_Implementacion(this.conexion).consultar_Ordenes_Fechas(this.valor + ";" + this.panel_Orden_Compra.calendario_Inicio() + ";" + this.panel_Orden_Compra.calendario_Final()));
                this.panel_Orden_Compra.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Orden_Compra.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
