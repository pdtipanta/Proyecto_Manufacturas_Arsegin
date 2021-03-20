/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Estado_De_Cuenta;

import Controlador.Clientes.Controlador_Dialogo_Buscar_Cliente;
import Controlador.Estado_De_Cuenta.Reporte.Controlador_Reporte_Estado_De_Cuenta;
import Controlador.Factura.Reporte.Controlador_Reporte_Factura;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.DAO_Usuario;
import Datos.Factura.DAO_Factura_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Usuario;
import Vista.Estado_De_Cuenta.Panel_Estado_De_Cuenta;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Estado_Cuenta implements MouseListener, ActionListener{
    private Vista_Principal         vista;
    private Connection              conexion_Database;
    private Usuario                 usuario;
    private String                  rol;
    private Panel_Estado_De_Cuenta  panel_Estado_De_Cuenta = new Panel_Estado_De_Cuenta();
    private DefaultTableModel       modelo_Tabla_Consulta_Estado_Cuenta = ( DefaultTableModel ) panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getModel();
    private ArrayList<Factura>      lista_Factura = new ArrayList<Factura>();
    
    public Controlador_Estado_Cuenta(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.addMouseListener(this);
        this.panel_Estado_De_Cuenta.boton_Buscar.addActionListener(this);
        this.panel_Estado_De_Cuenta.boton_Reporte.addActionListener(this);
        this.panel_Estado_De_Cuenta.combo_Opcion.addActionListener(this);
        this.panel_Estado_De_Cuenta.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(this.panel_Estado_De_Cuenta);
        this.panel_Estado_De_Cuenta.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Estado_De_Cuenta.boton_Buscar) {
            ArrayList<Cliente> cliente = new Controlador_Dialogo_Buscar_Cliente(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cliente.size() == 1) {
                ArrayList<Usuario> usuario = new DAO_Usuario(this.conexion_Database).consultar(cliente.get(0).getEmpleado());
                this.panel_Estado_De_Cuenta.valores_Clientes(cliente.get(0).getCodigo_Cliente(), cliente.get(0).getCliente(), cliente.get(0).getRUC(), cliente.get(0).getDireccion(), cliente.get(0).getTelefono(), cliente.get(0).getCiudad(), cliente.get(0).getCorreo(), usuario.get(0).getNombre() + " " + usuario.get(0).getApellido());
                this.panel_Estado_De_Cuenta.activar_Botones(true, true);
            }
        }

        if (ae.getSource() == this.panel_Estado_De_Cuenta.combo_Opcion) {
            if (this.panel_Estado_De_Cuenta.combo_Opcion.getSelectedItem() == "Seleccione.........") {
                facturas("Seleccione.........");
            } else if (this.panel_Estado_De_Cuenta.combo_Opcion.getSelectedItem() == "Todas") {
                facturas("Todas");
            } else if (this.panel_Estado_De_Cuenta.combo_Opcion.getSelectedItem() == "Por cobrar") {
                facturas("Credito");
            } else if (this.panel_Estado_De_Cuenta.combo_Opcion.getSelectedItem() == "Anuladas") {
                facturas("Anular");
            } else if (this.panel_Estado_De_Cuenta.combo_Opcion.getSelectedItem() == "Pagadas") {
                facturas("Pagado");
            }
        }

        if (ae.getSource() == this.panel_Estado_De_Cuenta.boton_Reporte) {
            new Controlador_Reporte_Estado_De_Cuenta(this.panel_Estado_De_Cuenta, this.lista_Factura).iniciar();
        }

        if (ae.getSource() == this.panel_Estado_De_Cuenta.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void facturas(String opcion) {
        this.modelo_Tabla_Consulta_Estado_Cuenta.setRowCount(0);
        
        String valor = null;
        
        switch (this.rol) {
            case "Vendedor":
                valor = this.usuario.getCedula();
                break;

            case "Contador":
                valor = "Todos";
                break;

            case "Administrador":
                valor = "Todos";
                break;
        }
        
        this.lista_Factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar_Facturas_Adeudadas(this.panel_Estado_De_Cuenta.campo_Codigo.getText(), opcion, valor);

        new Tabla_PDF__Estado_Cuenta_Facturacion().construir_TablaPDF(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion, this.lista_Factura);

        if (this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getRowCount() > 0) {
            this.panel_Estado_De_Cuenta.boton_Reporte.setEnabled(true);
        } else {
            this.panel_Estado_De_Cuenta.boton_Reporte.setEnabled(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion) {
            int column = this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getColumnModel().getColumnIndexAtX(me.getX());
            int row = me.getY() / this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getRowHeight();

            if (column > 0) {
                Object ubicacion_BotonTabla = this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getValueAt(row, column);
                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();
                    JButton boton_Tabla = (JButton) ubicacion_BotonTabla;

                    ArrayList<Factura> lista_Factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar(this.panel_Estado_De_Cuenta.tabla_Estado_Cuenta_Facturacion.getValueAt(row, 1) + ";" + "Todos");
                    ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(lista_Factura.get(0).getCliente() + ";" + "Todos");
                    
                    if (lista_Factura.size() == 1 && cliente.size() == 1) {
                        new Controlador_Reporte_Factura(lista_Factura.get(0), cliente.get(0)).iniciar();
                    }
                }
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
    
    public void set_Usuario(){
        this.panel_Estado_De_Cuenta.set_Usuario(this.usuario, this.rol);
    }
}
