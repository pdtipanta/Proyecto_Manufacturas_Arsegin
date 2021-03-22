/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Orden_De_Compra.Reporte.Controlador_Reporte_Orden_Compra;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Orden_De_Compra.Panel_Orden_Compra;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Controlador_Orden_De_Compra implements ActionListener {
    private final Vista_Principal       vista;
    private final Connection            conexion_Database;
    private Orden_Compra                modelo_Orden_Compra;
    private Proveedor                   modelo_Proveedor;
    private final Panel_Orden_Compra    panel_Orden_Compra = new Panel_Orden_Compra();
    private final Usuario               usuario;
    private final String                rol;
    
    public Controlador_Orden_De_Compra(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Orden_Compra.boton_Modificar_Orden_Compra.addActionListener(this);
        this.panel_Orden_Compra.boton_Nueva_Orden_Compra.addActionListener(this);
        this.panel_Orden_Compra.boton_Generar_Orden.addActionListener(this);
        this.panel_Orden_Compra.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Orden_Compra);
        panel_Orden_Compra.setVisible(true);
        vista.Panel_Contenedor.validate();
        this.cargar_Ordenes();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Orden_Compra.boton_Nueva_Orden_Compra) {
            if (new Controlador_Dialogo_Orden_Compra(this.vista, this.conexion_Database, this.usuario, this.rol, this.modelo_Orden_Compra, this.modelo_Proveedor, "Registrar").iniciar()) {
                this.cargar_Ordenes();
            }
        }

        if (ae.getSource() == panel_Orden_Compra.boton_Modificar_Orden_Compra) {

            ArrayList<Orden_Compra> orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 5));

            ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar(orden_Compra.get(0).getProveedor());

            if (proveedor.size() == 1 && orden_Compra.size() == 1) {
                if (new Controlador_Dialogo_Orden_Compra(this.vista, this.conexion_Database, this.usuario, this.rol, orden_Compra.get(0), proveedor.get(0), "Modificar").iniciar()) {
                    this.cargar_Ordenes();
                }
            }
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Generar_Orden) {
            ArrayList<Orden_Compra> orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 5));

            ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar(orden_Compra.get(0).getProveedor());

            if (proveedor.size() == 1 && orden_Compra.size() == 1) {
                new Controlador_Reporte_Orden_Compra(orden_Compra.get(0), proveedor.get(0)).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void cargar_Ordenes(){
        new Controlador_Dialogo_Buscar_Orden(this.panel_Orden_Compra, this.conexion_Database, this.usuario, this.rol).iniciar();
    }
    
    public void habilitar_Rol() {
        this.panel_Orden_Compra.Roles(rol);
    }
    
    public void set_Usuario(){
        this.panel_Orden_Compra.set_Usuario(this.usuario, this.rol);
    }
}
    
