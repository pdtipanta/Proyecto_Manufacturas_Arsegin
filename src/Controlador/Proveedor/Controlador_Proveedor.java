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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Proveedor implements ActionListener {

    private Vista_Principal         vista;
    private Connection              conexion_Database;
    private Usuario                 usuario;
    private String                  rol;
    private Panel_Proveedor         panel_Proveedor = new Panel_Proveedor();
    private Proveedor               modelo_Proveedor;
    private String                  id_Proveedor;

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
/*
        if (ae.getSource() == this.panel_Proveedor.boton_Guardar) {
            if (this.panel_Proveedor.etiquetas(true)) {
                this.numero_Proveedor();
                try {
                    if (new DAO_Proveedor_Implementacion(this.conexion_Database).crear(new Proveedor(this.numero_Proveedor(), this.panel_Proveedor.combo_Proveedor.getText(), this.panel_Proveedor.campo_RUC.getText(), this.panel_Proveedor.campo_Direccion.getText(), this.panel_Proveedor.campo_Correo.getText(), this.panel_Proveedor.campo_Telefono.getText(), this.panel_Proveedor.caja_Productos.getText()))) {
                        this.panel_Proveedor.limpiar_Campos();
                        this.panel_Proveedor.botones(true, true, false, false, true, true);
                        JOptionPane.showMessageDialog(null, "Proveedor registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Proveedor.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }*/
/*
        if (ae.getSource() == this.panel_Proveedor.boton_Buscar) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();

            if (proveedor.size() == 1) {
                this.panel_Proveedor.setCampos(proveedor.get(0).getProveedor(), proveedor.get(0).getDireccion(), proveedor.get(0).getRUC(), proveedor.get(0).getTelefono(), proveedor.get(0).getCorreo(), proveedor.get(0).getProductos());
                this.panel_Proveedor.campos_Busqueda();
                this.panel_Proveedor.etiquetas(true);
                this.id_Proveedor = proveedor.get(0).getId_Proveedor();
            }
        }*/

        if (ae.getSource() == this.panel_Proveedor.boton_Modificar) {
            this.modelo_Proveedor = new Proveedor((String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 0), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 1), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 2), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 4), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 5), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 6), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 3));
            if(new Controlador_Dialogo_Registrar_Proveedor(this.vista, this.conexion_Database, this.usuario, this.rol, this.modelo_Proveedor, "Modificar").iniciar()){
                this.panel_Proveedor.boton_Modificar.setEnabled(false);
                this.panel_Proveedor.boton_Eliminar.setEnabled(false);
                this.cargar_Proveedores();
            }
            
            /*
            if (this.panel_Proveedor.etiquetas(true)) {
                this.modelo_Proveedor = new Proveedor(this.id_Proveedor, this.panel_Proveedor.combo_Proveedor.getText(), this.panel_Proveedor.campo_RUC.getText(), this.panel_Proveedor.campo_Direccion.getText(), this.panel_Proveedor.campo_Correo.getText(), this.panel_Proveedor.campo_Telefono.getText(), this.panel_Proveedor.caja_Productos.getText());

                try {
                    if (new DAO_Proveedor_Implementacion(this.conexion_Database).editar(this.modelo_Proveedor) > 0) {
                        this.panel_Proveedor.limpiar_Campos();
                        this.panel_Proveedor.botones(true, true, false, false, true, true);
                        this.id_Proveedor = null;
                        JOptionPane.showMessageDialog(null, "Proveedor actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    } 
                }catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Proveedor.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }*/
        }

        if (ae.getSource() == this.panel_Proveedor.boton_Eliminar) {
            try {
            if (new DAO_Proveedor_Implementacion(this.conexion_Database).eliminar((String)this.panel_Proveedor.tabla_Proveedores.getValueAt(this.panel_Proveedor.tabla_Proveedores.getSelectedRow(), 0))>0) {
                JOptionPane.showMessageDialog(null, "Proveedor eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                this.cargar_Proveedores();
                this.panel_Proveedor.boton_Modificar.setEnabled(false);
                    this.panel_Proveedor.boton_Eliminar.setEnabled(false);
            }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar proveedor, debido a que mantiene documentos pendientes", "Proveedor", JOptionPane.WARNING_MESSAGE);
                //this.panel_Proveedor.limpiar_Campos();
                this.id_Proveedor = null;
                //this.panel_Proveedor.botones(true, true, false, false, true, true);

            } catch (SQLException ex) {
            }
            /*
            try {
                if (new DAO_Proveedor_Implementacion(this.conexion_Database).eliminar(this.id_Proveedor) > 0) {
                    this.panel_Proveedor.limpiar_Campos();
                    this.panel_Proveedor.botones(true, true, false, false, true, true);
                    this.id_Proveedor = null;
                    JOptionPane.showMessageDialog(null, "Proveedor eliminado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar proveedor, debido a que mantiene documentos pendientes", "Proveedor", JOptionPane.WARNING_MESSAGE);
                this.panel_Proveedor.limpiar_Campos();
                this.id_Proveedor = null;
                this.panel_Proveedor.botones(true, true, false, false, true, true);

            } catch (SQLException ex) {
            }*/
        }
        
        if (ae.getSource() == this.panel_Proveedor.boton_Nuevo_Proveedor) {
            if(new Controlador_Dialogo_Registrar_Proveedor(this.vista, this.conexion_Database, this.usuario, this.rol, this.modelo_Proveedor, "Registrar").iniciar()){
                this.cargar_Proveedores();
            }
        }
/*
        if (ae.getSource() == panel_Proveedor.boton_Reporte_Proveedor) {
            new Controlador_Reporte_Proveedor(this.vista, this.conexion_Database).iniciar();
        }
*/
        if (ae.getSource() == panel_Proveedor.boton_Reporte_Proveedor) {
            if(this.panel_Proveedor.tabla_Proveedores.getRowCount() > 0){
               
                ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
                
                for(int i = 0; i<this.panel_Proveedor.tabla_Proveedores.getRowCount(); i++){
                proveedor.add(new Proveedor((String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 0), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 1), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 2), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 4), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 5), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 6), (String)this.panel_Proveedor.tabla_Proveedores.getValueAt(i, 3)));
                }
                new Controlador_Reporte_Proveedor(this.vista, this.conexion_Database, proveedor).iniciar();
            }
            //new Controlador_Reporte_Proveedor(this.vista, this.conexion_Database).iniciar();
        }
        
        if (ae.getSource() == this.panel_Proveedor.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
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
        return format.format(Integer.valueOf(numero) + 1);
    }
    
    public void set_Usuario(){
        this.panel_Proveedor.set_Usuario(this.usuario, this.rol);
    }
    
    public void cargar_Proveedores(){
        new Controlador_Dialogo_Buscar_Proveedores(this.panel_Proveedor, this.conexion_Database).iniciar();
    }
}
