/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clientes;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Modelo.Cliente;
import Modelo.Usuario;
import Vista.Cliente.Dialogo_Buscar_Cliente;
import Vista.Vista_Principal;
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
public class Controlador_Dialogo_Buscar_Cliente implements KeyListener, MouseListener{
    private final Vista_Principal           vista;
    private final Connection                conexion;
    private final Usuario                   usuario;
    private final String                    rol;   
    private final Dialogo_Buscar_Cliente    dialogo_Buscar_Cliente;
    private final DefaultTableModel         modelo_Tabla_Clientes;
    private TableRowSorter                  TRSFiltro;
    private ArrayList<Cliente>             cliente = new ArrayList< Cliente>();

    public Controlador_Dialogo_Buscar_Cliente(Vista_Principal vista, Connection conexion, Usuario usuario, String rol) {
        this.vista = vista;
        this.usuario = usuario;
        this.rol = rol;
        this.conexion = conexion;
        this.dialogo_Buscar_Cliente = new Dialogo_Buscar_Cliente(this.vista, true);
        this.dialogo_Buscar_Cliente.campo_Buscar.addKeyListener(this);
        this.dialogo_Buscar_Cliente.tabla_Clientes.addMouseListener(this);
        this.modelo_Tabla_Clientes = (DefaultTableModel) this.dialogo_Buscar_Cliente.tabla_Clientes.getModel();
    }

    public ArrayList< Cliente> iniciar() {
        this.consultar_Datos_Clientes();
        this.dialogo_Buscar_Cliente.setVisible(true);
        return cliente;
    }

    public void consultar_Datos_Clientes() {
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

        ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(valor);

        if (cliente.size() > 0) {
            for (int i = 0; i < cliente.size(); i++) {
                Object[] fila = {cliente.get(i).getCodigo_Cliente(), cliente.get(i).getCliente(), cliente.get(i).getRUC(), cliente.get(i).getCiudad(), cliente.get(i).getDireccion(), cliente.get(i).getTelefono(), cliente.get(i).getCelular(), cliente.get(i).getPersona_Contacto(), cliente.get(i).getCorreo(), cliente.get(i).getEmpleado()};
                this.modelo_Tabla_Clientes.addRow(fila);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (this.dialogo_Buscar_Cliente.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.dialogo_Buscar_Cliente.campo_Buscar.setEditable(false);
        } else {
            this.dialogo_Buscar_Cliente.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.dialogo_Buscar_Cliente.campo_Buscar) {
                this.dialogo_Buscar_Cliente.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.dialogo_Buscar_Cliente.tabla_Clientes.getModel());
                this.dialogo_Buscar_Cliente.tabla_Clientes.setRowSorter(TRSFiltro);
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
        if (this.dialogo_Buscar_Cliente.combo_Opciones.getSelectedItem() == "Por codigo") {
            filtrar_Tabla(0);
        } else if (this.dialogo_Buscar_Cliente.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.dialogo_Buscar_Cliente.combo_Opciones.getSelectedItem() == "Por RUC / Cedula") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.dialogo_Buscar_Cliente.campo_Buscar.getText(), valor));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.dialogo_Buscar_Cliente.tabla_Clientes && me.getButton() == 1) {
            this.cliente = new DAO_Cliente_Implementacion(this.conexion).consultar(this.dialogo_Buscar_Cliente.tabla_Clientes.getValueAt(this.dialogo_Buscar_Cliente.tabla_Clientes.getSelectedRow(), 0) + ";" + this.dialogo_Buscar_Cliente.tabla_Clientes.getValueAt(this.dialogo_Buscar_Cliente.tabla_Clientes.getSelectedRow(), 9));
            if (this.cliente.size() == 1) {
                this.dialogo_Buscar_Cliente.dispose();
            }
        }
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
