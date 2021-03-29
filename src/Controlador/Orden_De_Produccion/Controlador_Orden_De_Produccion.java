/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Orden_De_Produccion.Reporte.Controlador_Reporte_Orden_Produccion;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Maquila;
import Modelo.Orden_Produccion;
import Modelo.Usuario;
import Vista.Maquilas.Orden_De_Produccion.Panel_Orden_De_Produccion;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Orden_De_Produccion extends EventListenerList implements ActionListener{
    private final Vista_Principal             vista;
    private final Connection                  conexion_Database;
    private final Usuario                     usuario;
    private final String                      rol;
    private Orden_Produccion                  modelo_Orden_Produccion;
    private final Panel_Orden_De_Produccion   panel_Orden_Produccion = new Panel_Orden_De_Produccion();
    private final DefaultTableModel           modelo_Tabla_Maquila; 

    public Controlador_Orden_De_Produccion(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Orden_Produccion.boton_Modificar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Nueva_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Generar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Orden_Produccion.boton_Fecha.addActionListener(this);
        this.modelo_Tabla_Maquila = (DefaultTableModel) panel_Orden_Produccion.tabla_Consulta_Orden_Produccion.getModel();
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(this.panel_Orden_Produccion);
        this.panel_Orden_Produccion.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.cargar_Ordenes_Produccion();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Orden_Produccion.boton_Nueva_Orden) {
            if (new Controlador_Dialogo_Orden_Produccion(this.vista, this.conexion_Database, this.modelo_Orden_Produccion, "Registrar").iniciar()) {
                this.panel_Orden_Produccion.boton_Modificar_Orden.setEnabled(false);
                this.panel_Orden_Produccion.boton_Generar_Orden.setEnabled(false);
                this.cargar_Ordenes_Produccion();
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Modificar_Orden) {
            ArrayList<Orden_Produccion> orden_Produccion = new DAO_Orden_Produccion_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Produccion.tabla_Consulta_Orden_Produccion.getValueAt(this.panel_Orden_Produccion.tabla_Consulta_Orden_Produccion.getSelectedRow(), 0));

            if (orden_Produccion.size() == 1) {
                if (new Controlador_Dialogo_Orden_Produccion(this.vista, this.conexion_Database, orden_Produccion.get(0), "Modificar").iniciar()) {
                    this.panel_Orden_Produccion.boton_Modificar_Orden.setEnabled(false);
                    this.panel_Orden_Produccion.boton_Generar_Orden.setEnabled(false);
                    this.cargar_Ordenes_Produccion();
                }
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Generar_Orden) {
            ArrayList<Orden_Produccion> orden_Produccion = new DAO_Orden_Produccion_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Produccion.tabla_Consulta_Orden_Produccion.getValueAt(this.panel_Orden_Produccion.tabla_Consulta_Orden_Produccion.getSelectedRow(), 0));
            if (orden_Produccion.size() == 1) {
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar(orden_Produccion.get(0).getMaquila());
                new Controlador_Reporte_Orden_Produccion(orden_Produccion.get(0), maquila.get(0)).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Fecha) {
            if (this.panel_Orden_Produccion.verificar_Campos()) {
                this.presentar_Ordenes(new DAO_Orden_Produccion_Implementacion(this.conexion_Database).consultar_Ordenes_Produccion_Fechas(this.panel_Orden_Produccion.calendario_Inicio() + ";" + this.panel_Orden_Produccion.calendario_Final()));
                this.panel_Orden_Produccion.etiqueta_Error_Etiqueta(false);
            } else {
                this.panel_Orden_Produccion.etiqueta_Error_Etiqueta(true);
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void cargar_Ordenes_Produccion() {
        new Controlador_Dialogo_Buscar_Orden_Produccion(this.panel_Orden_Produccion, this.conexion_Database).iniciar();

    }

    public void set_Usuario() {
        this.panel_Orden_Produccion.set_Usuario(this.usuario, this.rol);
    }

    public void habilitar_Rol() {
        this.panel_Orden_Produccion.Roles(rol);
    }
    
    public DefaultTableModel presentar_Ordenes(ArrayList<Orden_Produccion> orden_Produccion) {
        this.modelo_Tabla_Maquila.setRowCount(0);

        if (orden_Produccion.size() > 0) {
            for (int i = 0; i < orden_Produccion.size(); i++) {
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar(orden_Produccion.get(i).getMaquila());
                Object[] fila = {orden_Produccion.get(i).getNumero_Orden(), maquila.get(0).getMaquila(), maquila.get(0).getRUC(), orden_Produccion.get(i).getFecha(), maquila.get(0).getTelefono()};
                this.modelo_Tabla_Maquila.addRow(fila);
            }
        }
        return this.modelo_Tabla_Maquila;
    }
}
