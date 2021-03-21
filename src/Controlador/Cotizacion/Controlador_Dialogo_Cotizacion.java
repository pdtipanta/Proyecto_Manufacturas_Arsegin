/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Controlador.Clientes.Controlador_Dialogo_Buscar_Cliente;
import Controlador.Inventario.Controlador_Dialogo_Buscar_Inventario;
import Controlador.Numeracion_Documentos;
import Datos.Cotizacion.DAO_Cotizacion_Implementacion;
import Modelo.Cliente;
import Modelo.Cotizacion;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Cotizacion.Dialogo_Cotizacion;
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
public class Controlador_Dialogo_Cotizacion implements ActionListener{
    private final Vista_Principal         vista;
    private Cotizacion                    modelo_Cotizacion;
    private final Connection              conexion_Database;
    private final Dialogo_Cotizacion      dialogo_Cotizacion;
    private final DefaultTableModel       modelo_Tabla_Cotizacion;
    private final Cliente                 modelo_Cliente;
    private final Cotizacion              cotizacion;
    private final Usuario                 usuario;
    private final String                  rol;
    private final String                  actividad;
    private boolean                       bandera = false;
    public Controlador_Dialogo_Cotizacion(Vista_Principal vista, Connection conexion_Database, Cliente cliente, Cotizacion cotizacion, Usuario usuario, String rol, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.cotizacion = cotizacion;
        this.modelo_Cliente = cliente;
        this.actividad = actividad;
        this.usuario = usuario;
        this.rol = rol;
        dialogo_Cotizacion = new Dialogo_Cotizacion(this.vista, true);
        this.dialogo_Cotizacion.boton_Guardar_Cotizacion.addActionListener(this);
        this.dialogo_Cotizacion.boton_Agregar_Fila.addActionListener(this);
        this.dialogo_Cotizacion.boton_Agregar_Cliente.addActionListener(this);
        modelo_Tabla_Cotizacion = (DefaultTableModel) dialogo_Cotizacion.tabla_Productos_Cotizacion.getModel();
    }
    
    public boolean iniciar() {
        this.tipo_Actividad();   
        this.dialogo_Cotizacion.setVisible(true);
        return this.bandera;
    }
    
    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Cotizacion();
        } else if (this.actividad.equals("Modificar")) {
            this.dialogo_Cotizacion.boton_Agregar_Fila.setEnabled(true);
            this.dialogo_Cotizacion.boton_Quitar_Fila.setEnabled(true);
            this.dialogo_Cotizacion.boton_Agregar_Cliente.setEnabled(false);
            this.dialogo_Cotizacion.valores_Tabla_Cotizacion(this.cotizacion, this.modelo_Cliente, this.usuario);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Cotizacion.boton_Agregar_Cliente) {
            ArrayList<Cliente> cliente = new Controlador_Dialogo_Buscar_Cliente(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cliente.size() == 1) {
                this.dialogo_Cotizacion.valores_Clientes(cliente.get(0), this.usuario);
                this.dialogo_Cotizacion.boton_Agregar_Fila.setEnabled(true);
                this.dialogo_Cotizacion.boton_Quitar_Fila.setEnabled(true);
            }
        }
        
        if (ae.getSource() == this.dialogo_Cotizacion.boton_Agregar_Fila) {
            ArrayList<Inventario> inventario = new Controlador_Dialogo_Buscar_Inventario(this.vista, this.conexion_Database, false).iniciar();
            boolean bandera = true;

            if (inventario.size() == 1) {

                for (int i = 0; i < this.dialogo_Cotizacion.tabla_Productos_Cotizacion.getRowCount(); i++) {
                    if (inventario.get(0).getCodigo().equals(this.dialogo_Cotizacion.tabla_Productos_Cotizacion.getValueAt(i, 1))) {
                        bandera = false;
                    }
                }

                if (bandera) {
                    Object[] inventarios = new Controlador_Dialogo_Agregar_Producto_Cotizacion(this.vista, inventario.get(0)).iniciar();

                    if (inventarios != null) {
                        if (inventarios.length == 5) {
                            Object[] fila = inventarios;
                            this.modelo_Tabla_Cotizacion.addRow(fila);
                            this.dialogo_Cotizacion.calculo_Valores();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if (ae.getSource() == this.dialogo_Cotizacion.boton_Guardar_Cotizacion) {

            if (this.modelo_Tabla_Cotizacion.getRowCount() > 0 && this.dialogo_Cotizacion.etiquetas()) {
                if (this.actividad.equals("Registrar")) {
                    this.numero_Cotizacion();
                    String[] valores = this.dialogo_Cotizacion.evaluar_Tabla();
                    this.modelo_Cotizacion = new Cotizacion(this.dialogo_Cotizacion.etiqueta_No_Cotizacion.getText(), this.dialogo_Cotizacion.calendario_Fecha(), this.dialogo_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.dialogo_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(this.dialogo_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.dialogo_Cotizacion.campo_Total_Cotizacion.getText()), this.usuario.getCedula(), this.dialogo_Cotizacion.campo_Codigo_Cliente.getText(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                    try {
                        if (new DAO_Cotizacion_Implementacion(this.conexion_Database).crear(this.modelo_Cotizacion)) {
                            this.bandera = true;
                            this.dialogo_Cotizacion.dispose();
                            JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                }
            } else {
                this.dialogo_Cotizacion.etiquetas();
            }

            if (this.modelo_Tabla_Cotizacion.getRowCount() > 0 && this.dialogo_Cotizacion.etiquetas()) {
                if (this.actividad.equals("Modificar")) {
                    String[] valores = this.dialogo_Cotizacion.evaluar_Tabla();

                    this.modelo_Cotizacion = new Cotizacion(this.dialogo_Cotizacion.etiqueta_No_Cotizacion.getText(), this.dialogo_Cotizacion.calendario_Fecha(), this.dialogo_Cotizacion.campo_Modalidad_Cotizacion.getText(), Double.valueOf(this.dialogo_Cotizacion.campo_Subtotal_Cotizacion.getText()), Double.valueOf(this.dialogo_Cotizacion.campo_IVA_Cotizacion.getText()), Double.valueOf(this.dialogo_Cotizacion.campo_Total_Cotizacion.getText()), this.usuario.getCedula(), this.dialogo_Cotizacion.combo_Cliente_Cotizacion.getText(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                    try {
                        if (new DAO_Cotizacion_Implementacion(this.conexion_Database).editar(this.modelo_Cotizacion) > 0) {
                            this.bandera = true;
                            this.dialogo_Cotizacion.dispose();
                            JOptionPane.showMessageDialog(null, "Cotizacion actualizada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede actualizar cotizacion", "Cotizacion", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void numero_Cotizacion() {;
        this.dialogo_Cotizacion.etiqueta_No_Cotizacion.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Cotizacion_Implementacion(this.conexion_Database).consultar_Numero_Cotizacion()));
    }
}
