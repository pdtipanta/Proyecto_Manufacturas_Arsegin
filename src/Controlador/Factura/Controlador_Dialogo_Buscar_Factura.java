/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Factura.DAO_Factura_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Usuario;
import Vista.Factura.Panel_Factura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Factura implements ActionListener {
    private Panel_Factura                       panel_Factura;
    private final Connection                    conexion;
    private final Usuario                       usuario;
    private final String                        rol;
    private final DefaultTableModel             modelo_Tabla_Facturas;
    private String                              valor = null;

    public Controlador_Dialogo_Buscar_Factura(Panel_Factura panel_Factura, Connection conexion, Usuario usuario, String rol) {
        this.panel_Factura = panel_Factura; 
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Factura.boton_Fecha.addActionListener(this);
        this.panel_Factura.combo_Opciones.addActionListener(this);
        this.modelo_Tabla_Facturas = (DefaultTableModel) this.panel_Factura.tabla_Consulta_Factura.getModel();
    }

    public DefaultTableModel iniciar() {
        return this.consultar_Datos_Facturas();
    }

    public DefaultTableModel consultar_Datos_Facturas() {
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
        return presentar_Facturas(new DAO_Factura_Implementacion(this.conexion).consultar(valor));
    }

    public DefaultTableModel presentar_Facturas(ArrayList<Factura> factura) {
        this.modelo_Tabla_Facturas.setRowCount(0);

        if (factura.size() > 0) {
            for (int i = 0; i < factura.size(); i++) {
                String valor_Cliente = factura.get(i).getCliente() + ";" + factura.get(i).getVendedor();
                ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor_Cliente);
                Object[] fila = {factura.get(i).getNo_Factura(), cliente.get(0).getCliente(), cliente.get(0).getRUC(), factura.get(i).getValor_Total(), factura.get(i).getFecha(), cliente.get(0).getEmpleado(), factura.get(i).getEstado()};
                this.modelo_Tabla_Facturas.addRow(fila);
            }
        }
        return this.modelo_Tabla_Facturas;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Factura.boton_Fecha) {
            if (this.panel_Factura.verificar_Campos()) {
                this.presentar_Facturas(new DAO_Factura_Implementacion(this.conexion).consultar_Facturas_Fechas(this.valor + ";" + this.panel_Factura.calendario_Inicio() + ";" + this.panel_Factura.calendario_Final()));
                this.panel_Factura.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Factura.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
