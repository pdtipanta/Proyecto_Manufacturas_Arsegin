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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Buscar implements KeyListener, MouseListener{
    private Connection                      conexion;
    private final Usuario                   usuario;
    private final String                    rol;  
    private final Panel_Clientes            panel_Clientes;
    private DefaultTableModel               modelo_Tabla_Clientes;
    private TableRowSorter                  TRSFiltro;

    public Controlador_Dialogo_Buscar(Panel_Clientes panel_Clientes, Connection conexion, Usuario usuario, String rol) {
        this.panel_Clientes = panel_Clientes;
        this.usuario = usuario;
        this.rol = rol;
        this.conexion = conexion;
        this.panel_Clientes.campo_Buscar.addKeyListener(this);
        this.panel_Clientes.tabla_Clientes.addMouseListener(this);
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

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.panel_Clientes.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Clientes.campo_Buscar.setEditable(false);
        } else {
            this.panel_Clientes.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Clientes.campo_Buscar) {
                this.panel_Clientes.campo_Buscar.addKeyListener(new KeyAdapter() {
                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Clientes.tabla_Clientes.getModel());
                this.panel_Clientes.tabla_Clientes.setRowSorter(TRSFiltro);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void filtro() {
        if (this.panel_Clientes.combo_Opciones.getSelectedItem() == "Por codigo") {
            filtrar_Tabla(0);
        } else if (this.panel_Clientes.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Clientes.combo_Opciones.getSelectedItem() == "Por RUC / Cedula") {
            filtrar_Tabla(2);
        } else if (this.panel_Clientes.combo_Opciones.getSelectedItem() == "Ciudad") {
            filtrar_Tabla(3);
        }
    }

    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Clientes.boton_Modificar.setEnabled(true);
            this.panel_Clientes.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Clientes.boton_Modificar.setEnabled(false);
            this.panel_Clientes.boton_Eliminar.setEnabled(false);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Clientes.tabla_Clientes.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Clientes.campo_Buscar.getText(), valor));
        if(this.panel_Clientes.tabla_Clientes.getRowCount() > 0 ){
            this.panel_Clientes.boton_Reportes_Clientes.setEnabled(true);
        }else{
            this.panel_Clientes.boton_Reportes_Clientes.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        seleccion_Tabla(this.panel_Clientes.tabla_Clientes.getSelectedRow());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
