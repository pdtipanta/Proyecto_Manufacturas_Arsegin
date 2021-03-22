/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Datos.Maquila.DAO_Maquila_Implementacion;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Maquila;
import Modelo.Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Panel_Orden_De_Produccion;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar_Orden_Produccion {
    private final Connection                      conexion;
    private final DefaultTableModel               modelo_Tabla_Orden_Produccion;
    private final Panel_Orden_De_Produccion       panel_Orden_De_Produccion;

    public Controlador_Dialogo_Buscar_Orden_Produccion(Panel_Orden_De_Produccion panel_Orden_De_Produccion, Connection conexion) {
        this.panel_Orden_De_Produccion = panel_Orden_De_Produccion;
        this.conexion = conexion;
        this.modelo_Tabla_Orden_Produccion = (DefaultTableModel) this.panel_Orden_De_Produccion.tabla_Consulta_Orden_Produccion.getModel();
    }

    public DefaultTableModel iniciar() {
        return consultar_Datos_Orden_Produccion();
    }

    public DefaultTableModel consultar_Datos_Orden_Produccion() {
        String valor = "Todas";

        return presentar_Ordenes(new DAO_Orden_Produccion_Implementacion(this.conexion).consultar(valor));
    }

    public DefaultTableModel presentar_Ordenes(ArrayList<Orden_Produccion> orden_Produccion) {
        this.modelo_Tabla_Orden_Produccion.setRowCount(0);

        if (orden_Produccion.size() > 0) {
            for (int i = 0; i < orden_Produccion.size(); i++) {
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion).consultar(orden_Produccion.get(i).getMaquila());
                Object[] fila = {orden_Produccion.get(i).getNumero_Orden(), maquila.get(0).getMaquila(), maquila.get(0).getRUC(), orden_Produccion.get(i).getFecha(), orden_Produccion.get(i).getEstado(), orden_Produccion.get(i).getV_Pagar(), maquila.get(0).getTelefono()};
                this.modelo_Tabla_Orden_Produccion.addRow(fila);
            }
        }
        return this.modelo_Tabla_Orden_Produccion;
    }
}
