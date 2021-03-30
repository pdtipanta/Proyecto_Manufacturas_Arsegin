/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Clientes.Controlador_Panel_Cliente;
import Controlador.Compras.Controlador_Compras;
import Controlador.Cotizacion.Controlador_Cotizacion;
import Controlador.Estado_De_Cuenta.Controlador_Estado_Cuenta;
import Controlador.Factura.Controlador_Factura;
import Controlador.Inventario.Controlador_Inventarios;
import Controlador.Maquila.Controlador_Maquila;
import Controlador.Orden_De_Compra.Controlador_Orden_De_Compra;
import Controlador.Orden_De_Produccion.Controlador_Orden_De_Produccion;
import Controlador.Pagos.Controlador_Pagos;
import Controlador.Proveedor.Controlador_Proveedor;
import Modelo.Usuario;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JMenuBar;

/**
 *
 * @author David
 */
public class Menu implements ActionListener{
    private final Vista_Principal vista;
    private final Connection conexion_Database;
    private final Usuario usuario;
    private final String  rol;
    
    public javax.swing.JPanel Panel_Contenedor;
    public javax.swing.JMenu menu_Cliente;
    public javax.swing.JMenu menu_Inventario;
    public javax.swing.JMenu menu_Maquila;
    public javax.swing.JMenuBar menu_Principal;
    public javax.swing.JMenu menu_Proveedor;
    public javax.swing.JMenuItem submenu_Cliente;
    public javax.swing.JMenuItem submenu_Compras;
    public javax.swing.JMenuItem submenu_Cotizacion;
    public javax.swing.JMenuItem submenu_Estado_Cuenta;
    public javax.swing.JMenuItem submenu_Factura;
    public javax.swing.JMenuItem submenu_Inventarios;
    public javax.swing.JMenuItem submenu_Maquilas;
    public javax.swing.JMenuItem submenu_Orden_Compra;
    public javax.swing.JMenuItem submenu_Orden_Produccion;
    public javax.swing.JMenuItem submenu_Pagos;
    public javax.swing.JMenuItem submenu_Proveedores;
    
    public Menu(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
        this.vista = vista;
        this.conexion_Database = conexion_Database;

        Panel_Contenedor = new javax.swing.JPanel();
        menu_Principal = new javax.swing.JMenuBar();
        menu_Cliente = new javax.swing.JMenu();
        submenu_Cliente = new javax.swing.JMenuItem();
        submenu_Estado_Cuenta = new javax.swing.JMenuItem();
        submenu_Factura = new javax.swing.JMenuItem();
        submenu_Cotizacion = new javax.swing.JMenuItem();
        menu_Proveedor = new javax.swing.JMenu();
        submenu_Proveedores = new javax.swing.JMenuItem();
        submenu_Pagos = new javax.swing.JMenuItem();
        submenu_Compras = new javax.swing.JMenuItem();
        submenu_Orden_Compra = new javax.swing.JMenuItem();
        menu_Inventario = new javax.swing.JMenu();
        submenu_Inventarios = new javax.swing.JMenuItem();
        menu_Maquila = new javax.swing.JMenu();
        submenu_Maquilas = new javax.swing.JMenuItem();
        submenu_Orden_Produccion = new javax.swing.JMenuItem();
    }

    public void construir() {
        this.vista.setJMenuBar(menu_Principal);
    }

    public void construir_Menu_Cliente() {
        menu_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes.png")));
        menu_Cliente.setText("Cliente");
        menu_Principal.add(menu_Cliente);
    }

    public void construir_Menu_Proveedor() {
        menu_Proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tranvia.png")));
        menu_Proveedor.setText("Proveedor");
        menu_Principal.add(menu_Proveedor);

    }

    public void construir_Menu_Inventario() {
        menu_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/almacenamiento.png")));
        menu_Inventario.setText("Inventario");
        menu_Principal.add(menu_Inventario);
    }

    public void construir_Menu_Maquila() {
        menu_Maquila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sastre_1.png")));
        menu_Maquila.setText("Maquila");
        menu_Principal.add(menu_Maquila);
    }

    public void construir_Submenu_Cliente() {
        submenu_Cliente.setText("Clientes");
        this.submenu_Cliente.addActionListener(this);
        menu_Cliente.add(submenu_Cliente);
    }

    public void construir_Submenu_Estado_Cuenta() {
        submenu_Estado_Cuenta.setText("Estado de cuenta");
        this.submenu_Estado_Cuenta.addActionListener(this);
        menu_Cliente.add(submenu_Estado_Cuenta);
    }

    public void construir_Submenu_Cotizacion() {
        submenu_Cotizacion.setText("Cotizacion");
        this.submenu_Cotizacion.addActionListener(this);
        menu_Cliente.add(submenu_Cotizacion);
    }

    public void construir_Submenu_Factura() {
        submenu_Factura.setText("Factura");
        this.submenu_Factura.addActionListener(this);
        menu_Cliente.add(submenu_Factura);
    }

    public void construir_Submenu_Proveedor() {
        submenu_Proveedores.setText("Proveedores");
        this.submenu_Proveedores.addActionListener(this);
        menu_Proveedor.add(submenu_Proveedores);
    }

    public void construir_Submenu_Pagos() {
        submenu_Pagos.setText("Pagos");
        this.submenu_Pagos.addActionListener(this);
        menu_Proveedor.add(submenu_Pagos);
    }

    public void construir_Submenu_Compras() {
        submenu_Compras.setText("Compras");
        this.submenu_Compras.addActionListener(this);
        menu_Proveedor.add(submenu_Compras);
    }

    public void construir_Submenu_Orden_Compra() {
        submenu_Orden_Compra.setText("Orden de compra");
        this.submenu_Orden_Compra.addActionListener(this);
        menu_Proveedor.add(submenu_Orden_Compra);
    }

    public void construir_Submenu_Inventario() {
        submenu_Inventarios.setText("Inventarios");
        this.submenu_Inventarios.addActionListener(this);
        menu_Inventario.add(submenu_Inventarios);
    }

    public void construir_Submenu_Maquila() {
        submenu_Maquilas.setText("Maquilas");
        this.submenu_Maquilas.addActionListener(this);
        menu_Maquila.add(submenu_Maquilas);
    }

    public void construir_Submenu_Orden_Produccion() {
        submenu_Orden_Produccion.setText("Orden de produccion");
        this.submenu_Orden_Produccion.addActionListener(this);
        menu_Maquila.add(submenu_Orden_Produccion);
    }

    public void limpiar_Panel_Contenedor() {
        vista.Panel_Contenedor.removeAll();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.submenu_Cliente) {
            limpiar_Panel_Contenedor();
            Controlador_Panel_Cliente controlador_Cliente = new Controlador_Panel_Cliente(vista, conexion_Database, this.usuario, this.rol);
            controlador_Cliente.iniciar();
        }

        if (ae.getSource() == this.submenu_Proveedores) {
            limpiar_Panel_Contenedor();
            Controlador_Proveedor controlador_Proveedor = new Controlador_Proveedor(vista, conexion_Database, this.usuario, this.rol);
            controlador_Proveedor.iniciar();
        }

        if (ae.getSource() == this.submenu_Cotizacion) {
            limpiar_Panel_Contenedor();
            Controlador_Cotizacion controlador_Cotizacion = new Controlador_Cotizacion(vista, conexion_Database, this.usuario, this.rol);
            controlador_Cotizacion.iniciar();
            controlador_Cotizacion.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Inventarios) {
            limpiar_Panel_Contenedor();
            Controlador_Inventarios controlador_Inventarios = new Controlador_Inventarios(vista, conexion_Database, this.usuario, this.rol);
            controlador_Inventarios.iniciar();
            controlador_Inventarios.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Factura) {
            limpiar_Panel_Contenedor();
            Controlador_Factura controlador_Factura = new Controlador_Factura(vista, conexion_Database, this.usuario, this.rol);
            controlador_Factura.iniciar();
            controlador_Factura.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Estado_Cuenta) {
            limpiar_Panel_Contenedor();
            Controlador_Estado_Cuenta controlador_Estado_Cuenta = new Controlador_Estado_Cuenta(vista, conexion_Database, this.usuario, this.rol);
            controlador_Estado_Cuenta.iniciar();
        }

        if (ae.getSource() == this.submenu_Orden_Compra) {
            limpiar_Panel_Contenedor();
            Controlador_Orden_De_Compra controlador_Orden_De_Compra = new Controlador_Orden_De_Compra(vista, conexion_Database, this.usuario, this.rol);
            controlador_Orden_De_Compra.iniciar();
            controlador_Orden_De_Compra.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Maquilas) {
            limpiar_Panel_Contenedor();
            Controlador_Maquila controlador_Maquila = new Controlador_Maquila(vista, conexion_Database, this.usuario, this.rol);
            controlador_Maquila.iniciar();
            controlador_Maquila.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Orden_Produccion) {
            limpiar_Panel_Contenedor();
            Controlador_Orden_De_Produccion controlador_Orden_De_Produccion = new Controlador_Orden_De_Produccion(vista, conexion_Database, this.usuario, this.rol);
            controlador_Orden_De_Produccion.iniciar();
            controlador_Orden_De_Produccion.habilitar_Rol();
        }

        if (ae.getSource() == this.submenu_Pagos) {
            limpiar_Panel_Contenedor();
            Controlador_Pagos controlador_Pagos = new Controlador_Pagos(vista, conexion_Database, this.usuario, this.rol);
            controlador_Pagos.iniciar();
        }

        if (ae.getSource() == this.submenu_Compras) {
            limpiar_Panel_Contenedor();
            Controlador_Compras controlador_Compras = new Controlador_Compras(vista, conexion_Database, this.usuario, this.rol);
            controlador_Compras.iniciar();
        }
    }

    public JMenuBar menu() {
        return this.menu_Principal;
    }
}
