/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Inventario.Reporte.Controlador_Reporte_Inventario;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Inventario.Panel_Inventarios;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Inventarios implements ActionListener, MouseListener,KeyListener{
    private Vista_Principal     vista;
    private Connection          conexion_Database;
    private Panel_Inventarios   panel_Inventarios = new Panel_Inventarios();
    private DefaultTableModel               modelo_Tabla_Productos;
    private TableRowSorter                  TRSFiltro;
    private Inventario inventario;
    private String              rol;
    private Usuario             usuario;
    
    public Controlador_Inventarios(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        
        //this.panel_Inventarios.boton_Guardar.addActionListener(this);
        this.panel_Inventarios.boton_Modificar.addActionListener(this);
        this.panel_Inventarios.boton_Nuevo_Producto.addActionListener(this);
        //this.panel_Inventarios.boton_Buscar.addActionListener(this);
        this.panel_Inventarios.boton_Eliminar.addActionListener(this);
        this.panel_Inventarios.boton_Informe.addActionListener(this);
        //this.panel_Inventarios.boton_Proveedor.addActionListener(this);
        this.panel_Inventarios.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Inventarios.boton_Devoluciones.addActionListener(this);
        this.panel_Inventarios.tabla_Inventario.addMouseListener(this);
        this.panel_Inventarios.campo_Buscar.addKeyListener(this);
        this.modelo_Tabla_Productos = (DefaultTableModel) this.panel_Inventarios.tabla_Inventario.getModel();
    }

    public void iniciar() {
        this.consultar_Datos_Inventario();
        this.vista.Panel_Contenedor.add(panel_Inventarios);
        this.panel_Inventarios.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        
        this.set_Usuario();
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
/*
        if (ae.getSource() == this.panel_Inventarios.boton_Guardar) {
            if (this.panel_Inventarios.etiquetas(true)) {

                try {
                    if (new DAO_Inventario_Implementacion(this.conexion_Database).crear(new Inventario((String) this.panel_Inventarios.campo_Codigo.getText(), this.panel_Inventarios.campo_Descripcion.getText(), Integer.parseInt(this.panel_Inventarios.campo_Cantidad.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Compra.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Venta.getText()), this.panel_Inventarios.combo_Proveedor.getText()))) {
                        this.panel_Inventarios.limpiar_Campos();
                        this.panel_Inventarios.botones(true, true, false, false, true, true);
                        JOptionPane.showMessageDialog(null, "Producto registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Inventarios.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }*/
/*
        if (ae.getSource() == this.panel_Inventarios.boton_Modificar) {
            if (this.panel_Inventarios.etiquetas(true)) {

                try {
                    if (new DAO_Inventario_Implementacion(this.conexion_Database).editar(new Inventario(this.panel_Inventarios.campo_Codigo.getText(), this.panel_Inventarios.campo_Descripcion.getText(), Integer.parseInt(this.panel_Inventarios.campo_Cantidad.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Compra.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Venta.getText()), this.panel_Inventarios.combo_Proveedor.getText())) > 0) {
                        this.panel_Inventarios.limpiar_Campos();
                        this.panel_Inventarios.botones(true, true, false, false, true, true);
                        JOptionPane.showMessageDialog(null, "Inventario actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Inventarios.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }*/
/*
        if (ae.getSource() == this.panel_Inventarios.boton_Buscar) {
            ArrayList<Inventario> inventario = new Controlador_Dialogo_Buscar_Inventario(this.vista, this.conexion_Database, false).iniciar();

            if (inventario.size() == 1) {
                this.panel_Inventarios.setCampos(inventario.get(0).getCodigo(), inventario.get(0).getDescripcion(), String.valueOf(inventario.get(0).getCantidad_Disponible()), String.valueOf(inventario.get(0).getPrecio_Compra()), String.valueOf(inventario.get(0).getPrecio_Venta()), inventario.get(0).getProveedor());
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Eliminar) {

            try {
                if (new DAO_Inventario_Implementacion(this.conexion_Database).eliminar(this.panel_Inventarios.campo_Codigo.getText()) > 0) {
                    this.panel_Inventarios.limpiar_Campos();
                    this.panel_Inventarios.botones(true, true, false, false, true, true);
                    JOptionPane.showMessageDialog(null, "Inventario eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el producto, debido a que se encuentra en documentos", "Inventario", JOptionPane.WARNING_MESSAGE);
                    this.panel_Inventarios.limpiar_Campos();
                }
            } catch (SQLException ex) {
            }
        }*/
/*
        if (ae.getSource() == this.panel_Inventarios.boton_Proveedor) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();

            if (proveedor.size() == 1) {
                this.panel_Inventarios.combo_Proveedor.setText(proveedor.get(0).getProveedor());
            }
        }*/
        if (ae.getSource() == this.panel_Inventarios.boton_Modificar) {
            ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar(this.panel_Inventarios.tabla_Inventario.getValueAt(this.panel_Inventarios.tabla_Inventario.getSelectedRow(), 0));

            if (inventario.size() == 1) {
                if(new Controlador_Dialogo_Inventario(this.vista, this.conexion_Database, inventario.get(0), "Modificar").iniciar()){
                    this.panel_Inventarios.boton_Modificar.setEnabled(false);
                    this.panel_Inventarios.boton_Eliminar.setEnabled(false);
                    this.consultar_Datos_Inventario();
                }
                //this.consultar_Datos_Inventario();
            }
                //this..setCampos(inventario.get(0).getCodigo(), inventario.get(0).getDescripcion(), String.valueOf(inventario.get(0).getCantidad_Disponible()), String.valueOf(inventario.get(0).getPrecio_Compra()), String.valueOf(inventario.get(0).getPrecio_Venta()), inventario.get(0).getProveedor());
            
            }
        
        if (ae.getSource() == this.panel_Inventarios.boton_Eliminar) {

            try {
                if (new DAO_Inventario_Implementacion(this.conexion_Database).eliminar((String)this.panel_Inventarios.tabla_Inventario.getValueAt(this.panel_Inventarios.tabla_Inventario.getSelectedRow(), 0)) > 0) {
                    //this.panel_Inventarios.limpiar_Campos();
                    //this.panel_Inventarios.botones(true, true, false, false, true, true);
                    this.panel_Inventarios.boton_Modificar.setEnabled(false);
                    this.panel_Inventarios.boton_Eliminar.setEnabled(false);
                    this.consultar_Datos_Inventario();
                    JOptionPane.showMessageDialog(null, "Inventario eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el producto, debido a que se encuentra en documentos", "Inventario", JOptionPane.WARNING_MESSAGE);
                   // this.panel_Inventarios.limpiar_Campos();
                }
            } catch (SQLException ex) {
            }
        }
            /*
            if (this.panel_Inventarios.etiquetas(true)) {

                try {
                    if (new DAO_Inventario_Implementacion(this.conexion_Database).editar(new Inventario(this.panel_Inventarios.campo_Codigo.getText(), this.panel_Inventarios.campo_Descripcion.getText(), Integer.parseInt(this.panel_Inventarios.campo_Cantidad.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Compra.getText()), Double.parseDouble(this.panel_Inventarios.campo_Precio_Venta.getText()), this.panel_Inventarios.combo_Proveedor.getText())) > 0) {
                        this.panel_Inventarios.limpiar_Campos();
                        this.panel_Inventarios.botones(true, true, false, false, true, true);
                        JOptionPane.showMessageDialog(null, "Inventario actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Inventarios.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }*/
        

        
        if (ae.getSource() == this.panel_Inventarios.boton_Nuevo_Producto) {
            if(new Controlador_Dialogo_Inventario(this.vista, this.conexion_Database, inventario, "Registrar").iniciar()){
                this.consultar_Datos_Inventario();
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Informe) {
            //new Controlador_Reporte_Inventario(this.vista, this.conexion_Database).iniciar();
            if(this.panel_Inventarios.tabla_Inventario.getRowCount() > 0){
                ArrayList<Inventario> inventario = new ArrayList<Inventario>();
                
                for(int i = 0; i<this.panel_Inventarios.tabla_Inventario.getRowCount(); i++){
                    inventario.add( new Inventario((String)this.panel_Inventarios.tabla_Inventario.getValueAt(i, 0), (String)this.panel_Inventarios.tabla_Inventario.getValueAt(i, 1), (int) this.panel_Inventarios.tabla_Inventario.getValueAt(i, 3), (Double)this.panel_Inventarios.tabla_Inventario.getValueAt(i, 4), (Double)this.panel_Inventarios.tabla_Inventario.getValueAt(i, 5), (String)this.panel_Inventarios.tabla_Inventario.getValueAt(i, 2)));
                }
                new Controlador_Reporte_Inventario(inventario).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }

        if (ae.getSource() == this.panel_Inventarios.boton_Devoluciones) {
            new Controlador_Dialogo_Devoluciones(this.vista).iniciar();
        }
    }
    
    public void consultar_Datos_Inventario() {
        this.modelo_Tabla_Productos.setRowCount(0);
        ArrayList<Inventario> inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar("");

        if (inventario.size() > 0) {
            
            for (int i = 0; i < inventario.size(); i++) {
                Object[] fila = {inventario.get(i).getCodigo(), inventario.get(i).getDescripcion(), inventario.get(i).getProveedor(), inventario.get(i).getCantidad_Disponible(), inventario.get(i).getPrecio_Compra(), inventario.get(i).getPrecio_Venta()};
                this.modelo_Tabla_Productos.addRow(fila);
            }
        }
    }

    public void habilitar_Rol() {
        this.panel_Inventarios.Roles(rol);
    }
    
    public void set_Usuario(){
        this.panel_Inventarios.set_Usuario(this.usuario, this.rol);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(me.getSource() == this.panel_Inventarios.tabla_Inventario){
            seleccion_Tabla(this.panel_Inventarios.tabla_Inventario.getSelectedRow());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if (this.panel_Inventarios.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Inventarios.campo_Buscar.setEditable(false);
        } else {
            this.panel_Inventarios.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Inventarios.campo_Buscar) {
                this.panel_Inventarios.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.panel_Inventarios.tabla_Inventario.getModel());
                this.panel_Inventarios.tabla_Inventario.setRowSorter(TRSFiltro);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void filtro() {
        if (this.panel_Inventarios.combo_Opciones.getSelectedItem() == "Por Codigo") {
            filtrar_Tabla(0);
        } else if (this.panel_Inventarios.combo_Opciones.getSelectedItem() == "Por Descripcion") {
            filtrar_Tabla(1);
        } else if (this.panel_Inventarios.combo_Opciones.getSelectedItem() == "Por Proveedor") {
            filtrar_Tabla(2);
        }
    }
    
    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.panel_Inventarios.tabla_Inventario.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Inventarios.campo_Buscar.getText(), valor));
        if(this.panel_Inventarios.tabla_Inventario.getRowCount() > 0 ){
            this.panel_Inventarios.boton_Informe.setEnabled(true);
        }else{
            this.panel_Inventarios.boton_Informe.setEnabled(false);
        }
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Inventarios.boton_Modificar.setEnabled(true);
            this.panel_Inventarios.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Inventarios.boton_Modificar.setEnabled(false);
            this.panel_Inventarios.boton_Eliminar.setEnabled(false);
        }
    }
}