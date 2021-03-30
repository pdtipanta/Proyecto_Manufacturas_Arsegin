/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;

import Controlador.Numeracion_Documentos;
import Controlador.Proveedor.Controlador_Dialogo_Buscar_Proveedor;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Orden_De_Compra.Dialogo_Orden_Compra;
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
public class Controlador_Dialogo_Orden_Compra implements ActionListener{
    private final Vista_Principal       vista;
    private final Connection            conexion_Database;
    private Orden_Compra                modelo_Orden_Compra;
    private final Dialogo_Orden_Compra  dialogo_Orden_Compra;
    private final DefaultTableModel     modelo_tabla_Productos_Orden_Compra;
    private final Proveedor             modelo_Proveedor;
    private final Usuario               usuario;
    private final String                rol;
    private String                      cod_Proveedor;
    private final String                actividad;
    private boolean                     bandera = false;
    
    public Controlador_Dialogo_Orden_Compra(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol, Orden_Compra orden_Compra, Proveedor proveedor, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.actividad = actividad;
        this.modelo_Orden_Compra = orden_Compra;
        this.modelo_Proveedor = proveedor;
        this.dialogo_Orden_Compra = new Dialogo_Orden_Compra(this.vista, true);
        this.modelo_tabla_Productos_Orden_Compra = (DefaultTableModel) this.dialogo_Orden_Compra.tabla_Productos_Orden_Compra.getModel();
        this.dialogo_Orden_Compra.boton_Guardar_Orden_Compra.addActionListener(this);
        this.dialogo_Orden_Compra.boton_Agregar_Fila.addActionListener(this);
        this.dialogo_Orden_Compra.boton_Buscar_Proveedor_Orden.addActionListener(this);
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Orden_Compra.Roles(this.rol);
        this.dialogo_Orden_Compra.setVisible(true);
        return this.bandera;
    }

    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Orden_Compra();
        } else if (this.actividad.equals("Modificar")) {
            if (this.modelo_Orden_Compra != null) {

                if (this.modelo_Orden_Compra.getEstado().equals("Aprobado")) {
                    this.dialogo_Orden_Compra.boton_Buscar_Proveedor_Orden.setEnabled(false);
                    this.dialogo_Orden_Compra.boton_Guardar_Orden_Compra.setEnabled(false);
                    this.dialogo_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.setEnabled(false);
                } else {
                    if (this.modelo_Orden_Compra.getTipo_Pago().equals("Anulado")) {
                        this.modelo_Orden_Compra.setEstado("Anulado");
                        this.dialogo_Orden_Compra.boton_Buscar_Proveedor_Orden.setEnabled(false);
                        this.dialogo_Orden_Compra.boton_Guardar_Orden_Compra.setEnabled(false);
                        this.dialogo_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.setEnabled(false);
                    } else {
                        this.dialogo_Orden_Compra.boton_Agregar_Fila.setEnabled(true);
                        this.dialogo_Orden_Compra.boton_Quitar_Fila.setEnabled(true);
                        this.dialogo_Orden_Compra.valor_IVA.setEnabled(true);
                        this.dialogo_Orden_Compra.boton_Buscar_Proveedor_Orden.setEnabled(false);
                    }
                }
                this.dialogo_Orden_Compra.valores_Tabla_Orden(this.modelo_Orden_Compra, this.modelo_Proveedor, this.usuario);
            }
        }
    }

    public void numero_Orden_Compra() {
        this.dialogo_Orden_Compra.etiqueta_No_Orden_Compra.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar_Numero_Orden()));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Orden_Compra.boton_Buscar_Proveedor_Orden) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();

            if (proveedor.size() == 1) {
                this.dialogo_Orden_Compra.valores_Proveedores(proveedor.get(0), this.usuario);
                this.cod_Proveedor = proveedor.get(0).getId_Proveedor();
                this.dialogo_Orden_Compra.boton_Agregar_Fila.setEnabled(true);
                this.dialogo_Orden_Compra.boton_Quitar_Fila.setEnabled(true);
                this.dialogo_Orden_Compra.valor_IVA.setEnabled(true);
            }
        }

        if (ae.getSource() == this.dialogo_Orden_Compra.boton_Agregar_Fila) {

            Object[] inventario = new Controlador_Dialogo_Producto_Orden_Compra(this.vista, this.conexion_Database, this.dialogo_Orden_Compra).iniciar();
            if (inventario != null) {
                if (inventario.length == 5) {
                    this.modelo_tabla_Productos_Orden_Compra.addRow(inventario);
                    this.dialogo_Orden_Compra.calculo_Valores();
                }

            }
        }

        if (ae.getSource() == this.dialogo_Orden_Compra.boton_Guardar_Orden_Compra) {

            if (this.actividad.equals("Registrar")) {
                String valor_Estado = "En revision";

                if (this.dialogo_Orden_Compra.combo_Estado_Orden.isVisible()) {
                    valor_Estado = String.valueOf(this.dialogo_Orden_Compra.combo_Estado_Orden.getSelectedItem());
                }

                if (this.modelo_tabla_Productos_Orden_Compra.getRowCount() > 0 && this.dialogo_Orden_Compra.etiquetas()) {
                    this.numero_Orden_Compra();
                    String[] valores = this.dialogo_Orden_Compra.evaluar_Tabla();

                    this.modelo_Orden_Compra = new Orden_Compra(this.dialogo_Orden_Compra.etiqueta_No_Orden_Compra.getText(), this.dialogo_Orden_Compra.calendario_Fecha(), Double.valueOf(this.dialogo_Orden_Compra.campo_Subtotal_Orden_Compra.getText()), Double.valueOf(this.dialogo_Orden_Compra.campo_IVA_Orden_Compra.getText()), Double.valueOf(this.dialogo_Orden_Compra.campo_Total_Orden_Compra.getText()), valor_Estado, (String) this.dialogo_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.getSelectedItem(), this.usuario.getCedula(), this.cod_Proveedor, valores[0], valores[1], valores[2], valores[3], valores[4]);

                    try {
                        if (new DAO_Orden_Compra_Implementacion(this.conexion_Database).crear(modelo_Orden_Compra)) {
                            this.cod_Proveedor = null;
                            this.bandera = true;
                            this.dialogo_Orden_Compra.dispose();
                            JOptionPane.showMessageDialog(null, "Orden de compra registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    this.dialogo_Orden_Compra.etiquetas();
                }
            }
            if (this.actividad.equals("Modificar")) {

                String valor_Estado = "En revision";

                if (this.dialogo_Orden_Compra.combo_Estado_Orden.isVisible()) {
                    valor_Estado = String.valueOf(this.dialogo_Orden_Compra.combo_Estado_Orden.getSelectedItem());
                }

                if (this.modelo_tabla_Productos_Orden_Compra.getRowCount() > 0 && this.dialogo_Orden_Compra.etiquetas()) {

                    String[] valores = this.dialogo_Orden_Compra.evaluar_Tabla();

                    Orden_Compra modelo_Orden_Compra = new Orden_Compra(this.dialogo_Orden_Compra.etiqueta_No_Orden_Compra.getText(), this.dialogo_Orden_Compra.calendario_Fecha(), Double.valueOf(this.dialogo_Orden_Compra.campo_Subtotal_Orden_Compra.getText()), Double.valueOf(this.dialogo_Orden_Compra.campo_IVA_Orden_Compra.getText()), Double.valueOf(this.dialogo_Orden_Compra.campo_Total_Orden_Compra.getText()), valor_Estado, (String) this.dialogo_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.getSelectedItem(), this.modelo_Orden_Compra.getSolicitante(), this.modelo_Proveedor.getId_Proveedor(), valores[0], valores[1], valores[2], valores[3], valores[4]);

                    try {
                        if (new DAO_Orden_Compra_Implementacion(this.conexion_Database).editar(modelo_Orden_Compra) > 0) {;
                            this.bandera = true;
                            this.dialogo_Orden_Compra.dispose();
                            JOptionPane.showMessageDialog(null, "Orden de compra actualizada", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    this.dialogo_Orden_Compra.etiquetas();
                    JOptionPane.showMessageDialog(null, "No se puede actualizar orden de compra", "Orden de compra", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
