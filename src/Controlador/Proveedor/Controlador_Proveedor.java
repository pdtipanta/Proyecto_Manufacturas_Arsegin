/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Proveedor.Reporte_Proveedor.Controlador_Reporte_Proveedor;
import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Proveedor.Panel_Proveedor;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Proveedor implements ActionListener, KeyListener, MouseListener {
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final Usuario                 usuario;
    private final String                  rol;
    private final DefaultTableModel       modelo_Tabla_Proveedor;
    private TableRowSorter                TRSFiltro;
    private final Panel_Proveedor         panel_Proveedor = new Panel_Proveedor();
    private Proveedor                     modelo_Proveedor;

    public Controlador_Proveedor(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Proveedor.boton_Modificar.addActionListener(this);
        this.panel_Proveedor.boton_Eliminar.addActionListener(this);
        this.panel_Proveedor.boton_Reporte_Proveedor.addActionListener(this);
        this.panel_Proveedor.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Proveedor.boton_Nuevo_Proveedor.addActionListener(this);
        this.panel_Proveedor.campo_Buscar.addKeyListener(this);
        this.panel_Proveedor.tabla_Proveedores.addMouseListener(this);
        this.panel_Proveedor.combo_Opciones.addActionListener(this);
        this.modelo_Tabla_Proveedor = (DefaultTableModel) this.panel_Proveedor.tabla_Proveedores.getModel();
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.removeAll();
        this.vista.Panel_Contenedor.add(panel_Proveedor);
        this.panel_Proveedor.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.cargar_Proveedores();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panel_Proveedor.boton_Modificar) {
            this.modelo_Proveedor = new Proveedor((String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 0), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 1), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 2), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 4), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 5), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 6), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 3));
            if (new Controlador_Dialogo_Proveedor(this.vista, this.conexion_Database, this.modelo_Proveedor, "Modificar").iniciar()) {
                this.panel_Proveedor.boton_Modificar.setEnabled(false);
                this.panel_Proveedor.boton_Eliminar.setEnabled(false);
                this.cargar_Proveedores();
            }
        }

        if (ae.getSource() == this.panel_Proveedor.boton_Eliminar) {
            try {
                if (new DAO_Proveedor_Implementacion(this.conexion_Database).eliminar((String) this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 0)) > 0) {
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    this.cargar_Proveedores();
                    this.panel_Proveedor.boton_Modificar.setEnabled(false);
                    this.panel_Proveedor.boton_Eliminar.setEnabled(false);
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar proveedor, debido a que mantiene documentos pendientes", "Proveedor", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
            }
        }

        if (ae.getSource() == this.panel_Proveedor.boton_Nuevo_Proveedor) {
            if (new Controlador_Dialogo_Proveedor(this.vista, this.conexion_Database, this.modelo_Proveedor, "Registrar").iniciar()) {
                this.cargar_Proveedores();
            }
        }

        if (ae.getSource() == panel_Proveedor.boton_Reporte_Proveedor) {
            if (this.panel_Proveedor.tabla_Proveedores.getRowCount() > 0) {

                ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();

                for (int i = 0; i < this.panel_Proveedor.tabla_Proveedores.getRowCount(); i++) {
                    proveedor.add(new Proveedor((String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 0), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 1), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 2), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 4), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 5), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 6), (String) this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 3)));
                }
                new Controlador_Reporte_Proveedor(proveedor).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Proveedor.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (this.panel_Proveedor.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Proveedor.campo_Buscar.setEditable(false);
        } else {
            this.panel_Proveedor.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Proveedor.campo_Buscar) {
                this.panel_Proveedor.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Proveedor.tabla_Proveedores.getModel());
                this.panel_Proveedor.tabla_Proveedores.setRowSorter(TRSFiltro);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == this.panel_Proveedor.tabla_Proveedores) {
            seleccion_Tabla(this.panel_Proveedor.tabla_Proveedores.getSelectedRow());
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

    public void filtro() {
        if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por RUC / CI") {
            filtrar_Tabla(2);
        } else if (this.panel_Proveedor.combo_Opciones.getSelectedItem() == "Por producto") {
            filtrar_Tabla(3);
        }
    }

    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Proveedor.boton_Modificar.setEnabled(true);
            this.panel_Proveedor.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Proveedor.boton_Modificar.setEnabled(false);
            this.panel_Proveedor.boton_Eliminar.setEnabled(false);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Proveedor.tabla_Proveedores.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Proveedor.campo_Buscar.getText(), valor));
        if (this.panel_Proveedor.tabla_Proveedores.getRowCount() > 0) {
            this.panel_Proveedor.boton_Reporte_Proveedor.setEnabled(true);
        } else {
            this.panel_Proveedor.boton_Reporte_Proveedor.setEnabled(false);
        }
    }
    
    public String numero_Proveedor() {
        String numero = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar_Numero_Proveedor();
        String valor = "";

        if (numero == null) {
            valor = convertirNumero(0);
        } else {
            valor = convertirNumero(Integer.parseInt(numero));
        }
        return valor;
    }

    public String convertirNumero(int numero) {
        DecimalFormat format = new DecimalFormat("00000000");
        return format.format(numero + 1);
    }

    public void set_Usuario() {
        this.panel_Proveedor.set_Usuario(this.usuario, this.rol);
    }

    public void cargar_Proveedores() {
        this.modelo_Tabla_Proveedor.setRowCount(0);
        ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar("");

        if (proveedor.size() > 0) {
            for (int i = 0; i < proveedor.size(); i++) {
                Object[] fila = {proveedor.get(i).getId_Proveedor(), proveedor.get(i).getProveedor(), proveedor.get(i).getRUC(), proveedor.get(i).getProductos(), proveedor.get(i).getDireccion(), proveedor.get(i).getCorreo(), proveedor.get(i).getTelefono()};
                this.modelo_Tabla_Proveedor.addRow(fila);
            }
        }
    }
}
