/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clientes;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Modelo.Cliente;
import Modelo.Usuario;
import Vista.Cliente.Panel_Clientes;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar{
    private final Connection                conexion;
    private final Usuario                   usuario;
    private final String                    rol;  
    private final Panel_Clientes            panel_Clientes;
    private final DefaultTableModel         modelo_Tabla_Clientes;

    public Controlador_Dialogo_Buscar(Panel_Clientes panel_Clientes, Connection conexion, Usuario usuario, String rol) {
        this.panel_Clientes = panel_Clientes;
        this.usuario = usuario;
        this.rol = rol;
        this.conexion = conexion;
        this.modelo_Tabla_Clientes = (DefaultTableModel) this.panel_Clientes.tabla_Clientes.getModel();
    }

    public DefaultTableModel iniciar() {
        return this.consultar_Datos_Clientes();
    }

    public DefaultTableModel consultar_Datos_Clientes() {
        String valor = null;
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
        
        this.modelo_Tabla_Clientes.setRowCount(0);
        ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor);

        if (cliente.size() > 0) {
            for (int i = 0; i < cliente.size(); i++) {
                Object[] fila = {cliente.get(i).getCodigo_Cliente(), cliente.get(i).getCliente(), cliente.get(i).getRUC(), cliente.get(i).getCiudad(), cliente.get(i).getDireccion(), cliente.get(i).getTelefono(), cliente.get(i).getCelular(), cliente.get(i).getPersona_Contacto(), cliente.get(i).getCorreo(), cliente.get(i).getEmpleado()};
                this.modelo_Tabla_Clientes.addRow(fila);
            }
        }
        return this.modelo_Tabla_Clientes;
    }
}
