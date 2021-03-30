/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Controlador.Cotizacion.Reporte.Controlador_Reporte_Cotizacion;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Cotizacion.DAO_Cotizacion_Implementacion;
import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Usuario;
import Vista.Cotizacion.Panel_Cotizacion;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Controlador_Cotizacion implements ActionListener{
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Panel_Cotizacion        panel_Cotizacion = new Panel_Cotizacion();
    private final Usuario                 usuario;
    private final String                  rol;
    private Cliente                       modelo_Cliente;
    private Cotizacion                    modelo_Cotizacion;

    public Controlador_Cotizacion(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Cotizacion.boton_Modificar_Cotizacion.addActionListener(this);
        this.panel_Cotizacion.boton_Nueva_Cotizacion.addActionListener(this);
        this.panel_Cotizacion.boton_Generar_Cotizacion.addActionListener(this);
        this.panel_Cotizacion.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(panel_Cotizacion);
        this.panel_Cotizacion.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.cargar_Cotizaciones();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == panel_Cotizacion.boton_Nueva_Cotizacion) {
            if (new Controlador_Dialogo_Cotizacion(this.vista, this.conexion_Database, this.modelo_Cliente, this.modelo_Cotizacion, this.usuario, this.rol, "Registrar").iniciar()) {
                this.cargar_Cotizaciones();
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Modificar_Cotizacion) {
            ArrayList<Cotizacion> cotizacion = new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 3));
            if (cotizacion.size() == 1) {

                String valor_Cliente = cotizacion.get(0).getCliente() + ";" + cotizacion.get(0).getEmisor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    if (new Controlador_Dialogo_Cotizacion(this.vista, this.conexion_Database, modelo_Cliente.get(0), cotizacion.get(0), this.usuario, this.rol, "Modificar").iniciar()) {
                        this.panel_Cotizacion.boton_Modificar_Cotizacion.setEnabled(false);
                        this.panel_Cotizacion.boton_Generar_Cotizacion.setEnabled(false);
                        this.cargar_Cotizaciones();
                    }
                }
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Generar_Cotizacion) {
            ArrayList<Cotizacion> cotizacion = new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 3));
            if (cotizacion.size() == 1) {
                String valor_Cliente = cotizacion.get(0).getCliente() + ";" + cotizacion.get(0).getEmisor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);
                if (modelo_Cliente.size() == 1) {
                    new Controlador_Reporte_Cotizacion(modelo_Cliente.get(0), cotizacion.get(0), this.usuario).iniciar();
                }
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void habilitar_Rol() {
        this.panel_Cotizacion.Roles(rol);
    }

    public void set_Usuario() {
        this.panel_Cotizacion.set_Usuario(this.usuario, this.rol);
    }

    public void cargar_Cotizaciones() {
        new Controlador_Dialogo_Buscar_Cotizacion(this.panel_Cotizacion, this.conexion_Database, this.usuario, this.rol).iniciar();
    }
}
