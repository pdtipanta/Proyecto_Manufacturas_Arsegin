/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Cotizacion.DAO_Cotizacion_Implementacion;
import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Usuario;
import Vista.Cotizacion.Panel_Cotizacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Cotizacion implements ActionListener {
    private final Panel_Cotizacion              panel_Cotizacion;
    private final Connection                    conexion;
    private final Usuario                       usuario;
    private final String                        rol;
    private String                              valor = null;
    private final DefaultTableModel             modelo_Tabla_Cotizacion;

    public Controlador_Dialogo_Buscar_Cotizacion(Panel_Cotizacion panel_Cotizacion, Connection conexion, Usuario usuario, String rol) {
        this.panel_Cotizacion = panel_Cotizacion;
        this.conexion = conexion;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Cotizacion.combo_Opciones.addActionListener(this);
        this.panel_Cotizacion.boton_Fecha.addActionListener(this);
        this.modelo_Tabla_Cotizacion = (DefaultTableModel) this.panel_Cotizacion.tabla_Consulta_Cotizacion.getModel();
    }

    public DefaultTableModel iniciar() {
        return this.consultar_Datos_Cotizacion();
    }

    public DefaultTableModel consultar_Datos_Cotizacion() {

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
        return presentar_Cotizacion(new DAO_Cotizacion_Implementacion(this.conexion).consultar(valor));
    }

    public DefaultTableModel presentar_Cotizacion(ArrayList<Cotizacion> cotizacion) {
        this.modelo_Tabla_Cotizacion.setRowCount(0);

        if (cotizacion.size() > 0) {
            for (int i = 0; i < cotizacion.size(); i++) {
                String valor_Cliente = cotizacion.get(i).getCliente() + ";" + cotizacion.get(i).getEmisor();
                ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor_Cliente);
                Object[] fila = {cotizacion.get(i).getNo_Cotizacion(), cliente.get(0).getCliente(), cliente.get(0).getRUC(), cliente.get(0).getEmpleado(), cotizacion.get(i).getFecha(), cotizacion.get(i).getValor_Total()};
                this.modelo_Tabla_Cotizacion.addRow(fila);
            }
        }
        return this.modelo_Tabla_Cotizacion;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Cotizacion.boton_Fecha) {
            if (this.panel_Cotizacion.verificar_Campos()) {
                this.presentar_Cotizacion(new DAO_Cotizacion_Implementacion(this.conexion).consultar_Cotizacion_Fechas(this.valor + ";" + this.panel_Cotizacion.calendario_Inicio() + ";" + this.panel_Cotizacion.calendario_Final()));
                this.panel_Cotizacion.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Cotizacion.etiqueta_Error_Etiqueta(true);
            }
        }
    }
}
