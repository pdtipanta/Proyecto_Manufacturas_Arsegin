/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Compras;

import Controlador.Compras.Reportes.Controlador_Reporte_Compras;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Proveedor.Controlador_Dialogo_Buscar_Proveedor;
import Datos.Compras.DAO_Compras_Implementacion;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Modelo.Compras;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Compras.Panel_Compras;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Compras implements ActionListener, MouseListener, KeyListener {
    private final Vista_Principal              vista;
    private final Connection                   conexion_Database;
    private final Usuario                      usuario;
    private final String                       rol;
    private final Panel_Compras                panel_Compras = new Panel_Compras();
    private Compras                            modelo_Compras;
    private Proveedor                          proveedor; 
    private ArrayList< Compras >               lista_Compras = new ArrayList<Compras>();
    private Orden_Compra                       modelo_Orden_Compra;
    private ArrayList< Orden_Compra >          lista_Orden_Compra = new ArrayList<Orden_Compra>();
    
    public Controlador_Compras(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Compras.boton_Buscar.addActionListener(this);
        this.panel_Compras.combo_Opcion.addActionListener(this);
        this.panel_Compras.combo_Filtrar.addActionListener(this);
        this.panel_Compras.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Compras.tabla_Consulta_Orden_Compra.addMouseListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(this.panel_Compras);
        panel_Compras.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Compras.combo_Filtrar) {
            if (this.panel_Compras.combo_Filtrar.getSelectedItem().equals("Seleccionar.....")) {
                this.panel_Compras.campo_Busqueda.setText("");
                this.panel_Compras.campo_Busqueda.setEditable(false);
            }
        }
        if (ae.getSource() == this.panel_Compras.combo_Opcion) {
            if (this.panel_Compras.combo_Opcion.getSelectedItem().equals("Seleccionar.....")) {
                this.panel_Compras.campo_Busqueda.setEditable(false);
                this.panel_Compras.combo_Filtrar.setEnabled(false);
                DefaultTableModel modelo_Tabla_Datos_Compras = (DefaultTableModel) this.panel_Compras.tabla_Consulta_Orden_Compra.getModel();
                modelo_Tabla_Datos_Compras.setRowCount(0);
            }

            if (this.panel_Compras.combo_Opcion.getSelectedItem().equals("Compras no ingresadas")) {
                new Tabla_PDF_Compras_No_Ingresadas().construir_TablaPDF(panel_Compras.tabla_Consulta_Orden_Compra, new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar_Compras_Vacias(this.proveedor.getId_Proveedor()), new Orden_Compra());
                this.panel_Compras.campo_Busqueda.setEditable(true);
                if (this.panel_Compras.tabla_Consulta_Orden_Compra.getRowCount() > 0) {
                    this.panel_Compras.combo_Filtrar.setEnabled(true);
                } else {
                    this.panel_Compras.combo_Filtrar.setEnabled(false);
                    this.panel_Compras.combo_Filtrar.setSelectedItem("Seleccionar.....");
                }

            }

            if (this.panel_Compras.combo_Opcion.getSelectedItem().equals("Compras ingresadas")) {
                this.panel_Compras.campo_Busqueda.setEditable(true);

                this.tabla_Compras_Ingresadas();
                if (this.panel_Compras.tabla_Consulta_Orden_Compra.getRowCount() > 0) {
                    this.panel_Compras.combo_Filtrar.setEnabled(true);
                } else {
                    this.panel_Compras.combo_Filtrar.setEnabled(false);
                    this.panel_Compras.combo_Filtrar.setSelectedItem("Seleccionar.....");
                }
            }
        }

        if (ae.getSource() == this.panel_Compras.boton_Buscar) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();
            DefaultTableModel modelo_Tabla_Consulta_Orden_Compra = (DefaultTableModel) panel_Compras.tabla_Consulta_Orden_Compra.getModel();

            if (proveedor.size() == 1) {
                this.panel_Compras.grupo_Botones.clearSelection();
                modelo_Tabla_Consulta_Orden_Compra.setRowCount(0);
                this.panel_Compras.combo_Opcion.setEnabled(true);
                this.panel_Compras.combo_Opcion.setSelectedItem("Seleccionar.....");
                this.proveedor = proveedor.get(0);
                this.panel_Compras.valores_Proveedor(proveedor.get(0));
            }
        }

        if (ae.getSource() == this.panel_Compras.boton_Cerrar_Sesion) {
            this.vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getSource() == this.panel_Compras.tabla_Consulta_Orden_Compra) {
            int column = this.panel_Compras.tabla_Consulta_Orden_Compra.getColumnModel().getColumnIndexAtX(me.getX());
            int row = me.getY() / this.panel_Compras.tabla_Consulta_Orden_Compra.getRowHeight();

            if (column == 5) {
                Object ubicacion_BotonTabla = this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, column);

                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();
                    ArrayList<Orden_Compra> lista_Productos = new ArrayList<Orden_Compra>();

                    String[] cantidad = this.lista_Orden_Compra.get(row).getCantidad().split(";");
                    String[] codigo = this.lista_Orden_Compra.get(row).getCodigo().split(";");
                    String[] descripcion = this.lista_Orden_Compra.get(row).getDescripcion().split(";");
                    String[] v_Unitario = this.lista_Orden_Compra.get(row).getV_Unitario().split(";");
                    String[] v_Total = this.lista_Orden_Compra.get(row).getV_Total().split(";");

                    for (int i = 0; i < cantidad.length - 1; i++) {
                        this.modelo_Orden_Compra = new Orden_Compra(this.lista_Orden_Compra.get(row).getNo_Orden(), this.lista_Orden_Compra.get(row).getFecha(), this.lista_Orden_Compra.get(row).getV_Subtotal(), this.lista_Orden_Compra.get(row).getIVA(), this.lista_Orden_Compra.get(row).getValor_Total(), this.lista_Orden_Compra.get(row).getEstado(), this.lista_Orden_Compra.get(row).getTipo_Pago(), this.lista_Orden_Compra.get(row).getSolicitante(), this.lista_Orden_Compra.get(row).getProveedor(),
                                cantidad[i], codigo[i], descripcion[i], v_Unitario[i], v_Total[i]);
                        lista_Productos.add(modelo_Orden_Compra);
                    }

                    new Controlador_Reporte_Compras(proveedor, lista_Productos).iniciar();
                }
            }

            if (column == 8 && this.panel_Compras.combo_Opcion.getSelectedItem().equals("Compras ingresadas")) {
                Object ubicacion_BotonTabla = this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, column);

                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();
                    this.panel_Compras.ejecutar_archivoPDF(this.lista_Compras.get(row).getFactura());
                }
            }

            if (column == 10 && this.panel_Compras.combo_Opcion.getSelectedItem().equals("Compras no ingresadas")) {
                Object ubicacion_BotonTabla = this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, column);
                DefaultTableModel modelo_Tabla_Consulta_Orden_Compra = (DefaultTableModel) this.panel_Compras.tabla_Consulta_Orden_Compra.getModel();

                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();
                    JButton boton = (JButton) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 8);

                    if (this.panel_Compras.etiquetas(true, row, boton)) {

                        String no_Factura = String.valueOf(modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 6));
                        double valor_Factura = Double.valueOf(String.valueOf(modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 4)));

                        this.modelo_Compras = new Compras((String) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 0), no_Factura, (String) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 1), this.panel_Compras.calendario_Fecha(row), this.panel_Compras.digitalizar_PDF(boton.getText()), valor_Factura, (String) this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, 9));

                        try {
                            if (new DAO_Compras_Implementacion(this.conexion_Database).crear(this.modelo_Compras)) {
                                if (new DAO_Orden_Compra_Implementacion(this.conexion_Database).editar_Factura_Orden_Compra((String) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 0)) > 0) {
                                    JOptionPane.showMessageDialog(null, "Registro exitoso", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                                    modelo_Tabla_Consulta_Orden_Compra.removeRow(row);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al registrar", "Compras", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        } catch (SQLException ex) {
                        }
                    }
                }
            }

            if (column == 10 && this.panel_Compras.combo_Opcion.getSelectedItem().equals("Compras ingresadas")) {
                Object ubicacion_BotonTabla = this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, column);
                DefaultTableModel modelo_Tabla_Consulta_Orden_Compra = (DefaultTableModel) this.panel_Compras.tabla_Consulta_Orden_Compra.getModel();

                if (ubicacion_BotonTabla instanceof JButton) {
                    ((JButton) ubicacion_BotonTabla).doClick();

                    String no_Factura = String.valueOf(modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 6));
                    double valor_Factura = Double.valueOf(String.valueOf(modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 4)));

                    this.modelo_Compras = new Compras((String) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 0), no_Factura, (String) modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 1), String.valueOf(modelo_Tabla_Consulta_Orden_Compra.getValueAt(row, 7)), new byte[20], valor_Factura, (String) this.panel_Compras.tabla_Consulta_Orden_Compra.getValueAt(row, 9));

                    try {
                        if (new DAO_Compras_Implementacion(this.conexion_Database).editar(this.modelo_Compras) > 0) {
                            this.tabla_Compras_Ingresadas();
                            JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar", "Actualizar", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
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

    public void tabla_Compras_Ingresadas() {
        this.lista_Orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar_Compras_Ingresadas(this.proveedor.getId_Proveedor());
        this.lista_Compras = new DAO_Compras_Implementacion(this.conexion_Database).consultar(this.proveedor.getId_Proveedor());
        new Tabla_PDF_Compras_Ingresadas().construir_TablaPDF(this.panel_Compras.tabla_Consulta_Orden_Compra, this.lista_Orden_Compra, this.lista_Compras, new Orden_Compra(), new Compras());
    }

    public void set_Usuario() {
        this.panel_Compras.set_Usuario(this.usuario, this.rol);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
