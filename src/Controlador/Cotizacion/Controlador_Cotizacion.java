/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Controlador.Clientes.Controlador_Dialogo_Buscar_Cliente;
import Controlador.Cotizacion.Reporte.Controlador_Reporte_Cotizacion;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Inventario.Controlador_Dialogo_Buscar_Inventario;
import Controlador.Numeracion_Documentos;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Cotizacion.DAO_Cotizacion_Implementacion;
import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Cotizacion.Panel_Cotizacion;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Cotizacion implements ActionListener{
    private Vista_Principal         vista;
    private Cotizacion              modelo_Cotizacion;
    private Connection              conexion_Database;
    private Panel_Cotizacion        panel_Cotizacion = new Panel_Cotizacion();
    //private DefaultTableModel       modelo_Tabla_Cotizacion = ( DefaultTableModel ) panel_Cotizacion.tabla_Productos_Cotizacion.getModel();
    private ArrayList<Cotizacion>   lista_Cotizacion = new ArrayList<Cotizacion>();
    private ArrayList<Cliente>      cliente = new ArrayList<Cliente>();
    private Cliente                 modelo_Cliente;
    private Usuario                 usuario;
    private String                  rol;
    
    public Controlador_Cotizacion(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        //this.panel_Cotizacion.boton_Guardar_Cotizacion.addActionListener(this);
        this.panel_Cotizacion.boton_Modificar_Cotizacion.addActionListener(this);
        //this.panel_Cotizacion.boton_Agregar_Fila.addActionListener(this);
        //this.panel_Cotizacion.boton_Quitar_Fila.addActionListener(this);
        this.panel_Cotizacion.boton_Nueva_Cotizacion.addActionListener(this);
        //this.panel_Cotizacion.boton_Buscar_Cotizacion.addActionListener(this);
        //this.panel_Cotizacion.boton_Agregar_Cliente.addActionListener(this);
        this.panel_Cotizacion.boton_Generar_Cotizacion.addActionListener(this);
        this.panel_Cotizacion.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(panel_Cotizacion);
        this.panel_Cotizacion.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        //this.numero_Cotizacion();
        this.cargar_Cotizaciones();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
/*
        if (ae.getSource() == this.panel_Cotizacion.boton_Agregar_Cliente) {
            ArrayList<Cliente> cliente = new Controlador_Dialogo_Buscar_Cliente(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cliente.size() == 1) {
                this.panel_Cotizacion.valores_Clientes(cliente.get(0).getCodigo_Cliente(), cliente.get(0).getCliente(), cliente.get(0).getDireccion(), cliente.get(0).getTelefono(), cliente.get(0).getCorreo(), cliente.get(0).getRUC(), cliente.get(0).getCiudad(), cliente.get(0).getPersona_Contacto(), this.usuario.getNombre(), this.usuario.getApellido());
                this.panel_Cotizacion.botones(true, true, false, false, true, true);
            }
        }

        if (ae.getSource() == panel_Cotizacion.boton_Nueva_Cotizacion) {
            this.panel_Cotizacion.valores_Clientes("", "", "", "", "", "", "", "", "", "");
            this.panel_Cotizacion.limpiar_Valores();
            this.panel_Cotizacion.limpiar_Tabla();
            this.panel_Cotizacion.limpiar_Etiquetas();
            this.panel_Cotizacion.botones(true, true, false, false, true, false);
            this.numero_Cotizacion();
        }

        if (ae.getSource() == panel_Cotizacion.boton_Guardar_Cotizacion) {

            if (this.modelo_Tabla_Cotizacion.getRowCount() > 0 && this.panel_Cotizacion.etiquetas()) {
                this.numero_Cotizacion();
                String[] valores = panel_Cotizacion.evaluar_Tabla();

                this.modelo_Cotizacion = new Cotizacion(this.panel_Cotizacion.etiqueta_No_Cotizacion.getText(), this.panel_Cotizacion.calendario_Fecha(), this.panel_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.panel_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_Total_Cotizacion.getText()), this.usuario.getCedula(), this.panel_Cotizacion.campo_Codigo_Cliente.getText(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                try {
                    if (new DAO_Cotizacion_Implementacion(this.conexion_Database).crear(this.modelo_Cotizacion)) {
                        this.panel_Cotizacion.valores_Clientes("", "", "", "", "", "", "", "", "", "");
                        this.panel_Cotizacion.limpiar_Valores();
                        this.panel_Cotizacion.limpiar_Tabla();
                        this.panel_Cotizacion.limpiar_Etiquetas();
                        this.panel_Cotizacion.botones(true, true, false, false, true, false);
                        this.numero_Cotizacion();
                        JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                this.panel_Cotizacion.etiquetas();
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Modificar_Cotizacion) {
            if (this.modelo_Tabla_Cotizacion.getRowCount() > 0 && this.panel_Cotizacion.etiquetas()) {

                String[] valores = this.panel_Cotizacion.evaluar_Tabla();

                this.modelo_Cotizacion = new Cotizacion(panel_Cotizacion.etiqueta_No_Cotizacion.getText(), this.panel_Cotizacion.calendario_Fecha(), this.panel_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.panel_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(panel_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_Total_Cotizacion.getText()), this.usuario.getCedula(), this.panel_Cotizacion.combo_Cliente_Cotizacion.getText(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                try {
                    if (new DAO_Cotizacion_Implementacion(this.conexion_Database).editar(this.modelo_Cotizacion) > 0) {
                        this.panel_Cotizacion.valores_Clientes("", "", "", "", "", "", "", "", "", "");
                        this.panel_Cotizacion.limpiar_Valores();
                        this.panel_Cotizacion.limpiar_Tabla();
                        this.panel_Cotizacion.botones(true, true, false, false, true, false);
                        this.numero_Cotizacion();
                        JOptionPane.showMessageDialog(null, "Cotizacion actualizada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede actualizar cotizacion", "Cotizacion", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Buscar_Cotizacion) {
            ArrayList<Cotizacion> cotizacion = new Controlador_Dialogo_Buscar_Cotizacion(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cotizacion.size() == 1) {

                String valor_Cliente = cotizacion.get(0).getCliente() + ";" + cotizacion.get(0).getEmisor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    this.panel_Cotizacion.botones(false, true, true, true, false, true);
                    this.panel_Cotizacion.valores_Tabla_Cotizacion(cotizacion.get(0), modelo_Cliente.get(0));
                }
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Agregar_Fila) {
            ArrayList<Inventario> inventario = new Controlador_Dialogo_Buscar_Inventario(this.vista, this.conexion_Database, false).iniciar();
            boolean bandera = true;

            if (inventario.size() == 1) {

                for (int i = 0; i < this.panel_Cotizacion.tabla_Productos_Cotizacion.getRowCount(); i++) {
                    if (inventario.get(0).getCodigo().equals(this.panel_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 1))) {
                        bandera = false;
                    }
                }

                if (bandera) {
                    Object[] inventarios = new Controlador_Dialogo_Agregar_Producto_Cotizacion(this.vista, inventario.get(0)).iniciar();

                    if (inventarios != null) {
                        if (inventarios.length == 5) {
                            Object[] fila = inventarios;
                            this.modelo_Tabla_Cotizacion.addRow(fila);
                            this.panel_Cotizacion.calculo_Valores();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Generar_Cotizacion) {
            new Controlador_Reporte_Cotizacion(this.panel_Cotizacion, this.usuario).iniciar();
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }*/

        if (ae.getSource() == panel_Cotizacion.boton_Nueva_Cotizacion) {
            if(new Controlador_Dialogo_Cotizacion(this.vista, this.conexion_Database, this.modelo_Cliente, this.modelo_Cotizacion, this.usuario, this.rol, "Registrar").iniciar()){
                this.cargar_Cotizaciones();
            }
            /*
            this.panel_Cotizacion.valores_Clientes("", "", "", "", "", "", "", "", "", "");
            this.panel_Cotizacion.limpiar_Valores();
            this.panel_Cotizacion.limpiar_Tabla();
            this.panel_Cotizacion.limpiar_Etiquetas();
            this.panel_Cotizacion.botones(true, true, false, false, true, false);
            this.numero_Cotizacion();*/
            
        }
        
        if (ae.getSource() == this.panel_Cotizacion.boton_Modificar_Cotizacion) {
            //String valor_Cliente = this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0);
             //ArrayList<Cotizacion> cotizacion = new Controlador_Dialogo_Buscar_Cotizacion(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();
            ArrayList<Cotizacion> cotizacion = new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 3));
             if (cotizacion.size() == 1) {

                String valor_Cliente = cotizacion.get(0).getCliente() + ";" + cotizacion.get(0).getEmisor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    //this.panel_Cotizacion.botones(false, true, true, true, false, true);
                    //this.panel_Cotizacion.valores_Tabla_Cotizacion(cotizacion.get(0), modelo_Cliente.get(0));
                    if(new Controlador_Dialogo_Cotizacion(this.vista, this.conexion_Database, modelo_Cliente.get(0), cotizacion.get(0), this.usuario, this.rol, "Modificar").iniciar()){
                        this.panel_Cotizacion.boton_Modificar_Cotizacion.setEnabled(false);
                        this.panel_Cotizacion.boton_Generar_Cotizacion.setEnabled(false);
                        this.cargar_Cotizaciones();
                    };
                    
                }
            }
             /*

                String[] valores = this.panel_Cotizacion.evaluar_Tabla();

                this.modelo_Cotizacion = new Cotizacion(panel_Cotizacion.etiqueta_No_Cotizacion.getText(), this.panel_Cotizacion.calendario_Fecha(), this.panel_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.panel_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(panel_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.panel_Cotizacion.campo_Total_Cotizacion.getText()), this.usuario.getCedula(), this.panel_Cotizacion.combo_Cliente_Cotizacion.getText(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                try {
                    if (new DAO_Cotizacion_Implementacion(this.conexion_Database).editar(this.modelo_Cotizacion) > 0) {
                       // this.panel_Cotizacion.valores_Clientes("", "", "", "", "", "", "", "", "", "");
                       // this.panel_Cotizacion.limpiar_Valores();
                       // this.panel_Cotizacion.limpiar_Tabla();
                       // this.panel_Cotizacion.botones(true, true, false, false, true, false);
                        //this.numero_Cotizacion();
                        JOptionPane.showMessageDialog(null, "Cotizacion actualizada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede actualizar cotizacion", "Cotizacion", JOptionPane.WARNING_MESSAGE);
            }*/
        
    }
        
        if (ae.getSource() == this.panel_Cotizacion.boton_Generar_Cotizacion) {
            //new Controlador_Reporte_Cotizacion(this.panel_Cotizacion, this.usuario).iniciar();
            
            ArrayList<Cotizacion> cotizacion = new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 0) + ";" + this.panel_Cotizacion.tabla_Consulta_Cotizacion.getValueAt(this.panel_Cotizacion.tabla_Consulta_Cotizacion.getSelectedRow(), 3));
             if (cotizacion.size() == 1) {

                String valor_Cliente = cotizacion.get(0).getCliente() + ";" + cotizacion.get(0).getEmisor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    new Controlador_Reporte_Cotizacion(modelo_Cliente.get(0), cotizacion.get(0), this.usuario).iniciar();
                    
                }
        }

        if (ae.getSource() == this.panel_Cotizacion.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
        
        

   // public void numero_Cotizacion() {
       // this.panel_Cotizacion.etiqueta_No_Cotizacion.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar_Numero_Cotizacion()));
    }
    }
    public void habilitar_Rol() {
        this.panel_Cotizacion.Roles(rol);
    }
    
    public void set_Usuario(){
        this.panel_Cotizacion.set_Usuario(this.usuario, this.rol);
    }
    
    public void cargar_Cotizaciones() {
        new Controlador_Dialogo_Buscar_Cotizacion(this.panel_Cotizacion, this.conexion_Database, this.usuario, this.rol).iniciar();
    }
}
