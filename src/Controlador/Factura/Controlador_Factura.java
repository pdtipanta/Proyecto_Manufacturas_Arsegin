/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Controlador.Factura.Reporte.Controlador_Reporte_Factura;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Factura.DAO_Factura_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Usuario;
import Vista.Factura.Panel_Factura;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.event.EventListenerList;

/**
 *
 * @author David
 */
public class Controlador_Factura extends EventListenerList implements ActionListener {
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Panel_Factura           panel_Factura = new Panel_Factura(); 
    private ArrayList<Factura>            factura;
    private ArrayList<Cliente>            cliente;
    private final Usuario                 usuario;
    private final String                  rol;
    
    public Controlador_Factura(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Factura.boton_Modificar_Factura.addActionListener(this);
        this.panel_Factura.boton_Nueva_Factura.addActionListener(this);
        this.panel_Factura.boton_Imprimir_Facturacion.addActionListener(this);
        this.panel_Factura.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(panel_Factura);
        this.panel_Factura.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.cargar_Facturas();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == this.panel_Factura.boton_Nueva_Factura) {
            new Controlador_Dialogo_Factura(this.vista, this.conexion_Database, factura, cliente, this.usuario, this.rol, "Registrar").iniciar();
        }
  
        if (ae.getSource() == this.panel_Factura.boton_Modificar_Factura) {
            ArrayList<Factura> factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar(this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 0) + ";" + this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 6));

            if (factura.size() == 1) {
                String valor_Cliente = factura.get(0).getCliente() + ";" + factura.get(0).getVendedor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    if (new Controlador_Dialogo_Factura(this.vista, this.conexion_Database, factura, modelo_Cliente, this.usuario, this.rol, "Modificar").iniciar()) {
                        this.panel_Factura.boton_Modificar_Factura.setEnabled(false);
                        this.panel_Factura.boton_Imprimir_Facturacion.setEnabled(false);
                        this.cargar_Facturas();
                    }
                }
            }
        }
        
        if (ae.getSource() == panel_Factura.boton_Imprimir_Facturacion) {
            ArrayList<Factura> factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar(this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 0) + ";" + this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 6));

            if (factura.size() == 1) {
                ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(factura.get(0).getCliente() + ";" + factura.get(0).getVendedor());
                new Controlador_Reporte_Factura(factura.get(0), cliente.get(0)).iniciar();
            }
        }
  
        if (ae.getSource() == this.panel_Factura.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }
    
    public void cargar_Facturas() {
        new Controlador_Dialogo_Buscar_Factura(this.panel_Factura, this.conexion_Database, this.usuario, this.rol).iniciar();
    }

    public void habilitar_Rol() {
        this.panel_Factura.Roles(rol);
    }

    public void set_Usuario() {
        this.panel_Factura.set_Usuario(this.usuario, this.rol);
    }
}
