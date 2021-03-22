/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Controlador.Maquila.Controlador_Dialogo_Buscar_Maquila;
import Controlador.Numeracion_Documentos;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Maquila;
import Modelo.Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Dialogo_Orden_Produccion;
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
public class Controlador_Dialogo_Orden_Produccion implements ActionListener{
    private final Vista_Principal                     vista;
    private final Connection                          conexion;
    private final Dialogo_Orden_Produccion            dialogo_Orden_Produccion;
    private final DefaultTableModel                   modelo_Tabla_Orden_Produccion;
    private final Orden_Produccion                    orden_Produccion;
    private final String                              actividad;
    private String                                    id_Maquila;
    private boolean                                   bandera = false;

    public Controlador_Dialogo_Orden_Produccion(Vista_Principal vista, Connection conexion, Orden_Produccion orden_Produccion, String actividad) {
        this.vista = vista;
        this.conexion = conexion;
        this.orden_Produccion = orden_Produccion;
        this.actividad = actividad;
        this.dialogo_Orden_Produccion = new Dialogo_Orden_Produccion(this.vista, true);
        this.dialogo_Orden_Produccion.boton_Guardar_Orden.addActionListener(this);
        this.dialogo_Orden_Produccion.boton_Agregar_Maquila.addActionListener(this);
        this.dialogo_Orden_Produccion.boton_Agregar_Fila.addActionListener(this);
        this.modelo_Tabla_Orden_Produccion = (DefaultTableModel) this.dialogo_Orden_Produccion.tabla_Productos_Maquila.getModel();
    }
    
    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Orden_Produccion.setVisible(true);
        return this.bandera;
    }

    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Orden_Produccion();
        } else if (this.actividad.equals("Modificar")) {
            boolean bandera = false;

            if (orden_Produccion != null) {
                if (orden_Produccion.getEstado().equals("Por pagar")) {
                    bandera = true;
                }
                this.dialogo_Orden_Produccion.boton_Agregar_Fila.setEnabled(bandera);
                this.dialogo_Orden_Produccion.boton_Quitar_Fila.setEnabled(bandera);
                this.dialogo_Orden_Produccion.combo_Estado_Orden.setEnabled(bandera);
                this.dialogo_Orden_Produccion.boton_Agregar_Maquila.setEnabled(!bandera);

                String valor_Maquila = orden_Produccion.getMaquila();
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion).consultar(valor_Maquila);

                if (maquila.size() == 1) {
                    this.dialogo_Orden_Produccion.valores_Tabla_Orden(orden_Produccion, maquila.get(0));
                }
            }
        }
    }

    public void numero_Orden_Produccion() {
        this.dialogo_Orden_Produccion.etiqueta_No_Orden.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Orden_Produccion_Implementacion(this.conexion).consultar_Numero_Orden()));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.dialogo_Orden_Produccion.boton_Agregar_Maquila) {
            ArrayList<Maquila> maquila = new Controlador_Dialogo_Buscar_Maquila(this.vista, this.conexion).iniciar();

            if (maquila.size() == 1) {
                this.dialogo_Orden_Produccion.valores_Maquila(maquila.get(0));
                this.dialogo_Orden_Produccion.boton_Agregar_Fila.setEnabled(true);
                this.dialogo_Orden_Produccion.boton_Quitar_Fila.setEnabled(true);
                this.id_Maquila = maquila.get(0).getId_Maquila();
            }
        }

        if (ae.getSource() == this.dialogo_Orden_Produccion.boton_Agregar_Fila) {
            Object[] trabajo = new Controlador_Dialogo_Agregar_Trabajo(this.vista).iniciar();

            if (trabajo != null) {
                this.modelo_Tabla_Orden_Produccion.addRow(trabajo);
                this.dialogo_Orden_Produccion.calculo_Valores();
            }
        }

        if (ae.getSource() == this.dialogo_Orden_Produccion.boton_Guardar_Orden) {
            if (this.actividad.equals("Registrar")) {
                if (this.modelo_Tabla_Orden_Produccion.getRowCount() > 0 && this.dialogo_Orden_Produccion.etiquetas()) {
                    this.numero_Orden_Produccion();
                    String[] valores = this.dialogo_Orden_Produccion.evaluar_Tabla();

                    Orden_Produccion modelo_Orden_Produccion = new Orden_Produccion(this.dialogo_Orden_Produccion.etiqueta_No_Orden.getText(), this.dialogo_Orden_Produccion.calendario(), Double.valueOf(this.dialogo_Orden_Produccion.campo_Total_Orden.getText()), (String) this.dialogo_Orden_Produccion.combo_Estado_Orden.getSelectedItem(), this.dialogo_Orden_Produccion.caja_Observaciones_Maquila.getText(), this.id_Maquila, valores[0], valores[1], valores[2], valores[3]);

                    try {
                        if (new DAO_Orden_Produccion_Implementacion(this.conexion).crear(modelo_Orden_Produccion)) {
                            this.id_Maquila = null;
                            this.bandera = true;
                            this.dialogo_Orden_Produccion.dispose();
                            JOptionPane.showMessageDialog(null, "Orden de produccion registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    this.dialogo_Orden_Produccion.etiquetas();
                }
            }

            if (this.actividad.equals("Modificar")) {
                if (this.modelo_Tabla_Orden_Produccion.getRowCount() > 0 && this.dialogo_Orden_Produccion.etiquetas()) {

                    String[] valores = this.dialogo_Orden_Produccion.evaluar_Tabla();

                    Orden_Produccion modelo_Orden_Produccion = new Orden_Produccion(this.dialogo_Orden_Produccion.etiqueta_No_Orden.getText(), this.dialogo_Orden_Produccion.calendario(), Double.valueOf(this.dialogo_Orden_Produccion.campo_Total_Orden.getText()), (String) this.dialogo_Orden_Produccion.combo_Estado_Orden.getSelectedItem(), this.dialogo_Orden_Produccion.caja_Observaciones_Maquila.getText(), this.orden_Produccion.getMaquila(), valores[0], valores[1], valores[2], valores[3]);

                    try {
                        if (new DAO_Orden_Produccion_Implementacion(this.conexion).editar(modelo_Orden_Produccion) > 0) {
                            this.bandera = true;
                            this.dialogo_Orden_Produccion.dispose();
                            JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar la orden", "Orden de Produccion", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
