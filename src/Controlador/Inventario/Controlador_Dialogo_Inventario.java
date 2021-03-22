/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Inventario;

import Controlador.Proveedor.Controlador_Dialogo_Buscar_Proveedor;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Inventario;
import Modelo.Proveedor;
import Vista.Inventario.Dialogo_Inventario;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Inventario implements ActionListener{
    private final Vista_Principal           vista;
    private final Connection                conexion;
    private final Dialogo_Inventario        dialogo_Inventario;
    private final String                    actividad;
    private final Inventario                inventario;
    private boolean                         bandera = false;

    public Controlador_Dialogo_Inventario(Vista_Principal vista, Connection conexion, Inventario inventario, String actividad) {
        this.vista = vista;
        this.conexion = conexion;
        this.actividad = actividad;
        this.inventario = inventario;
        this.dialogo_Inventario = new Dialogo_Inventario(this.vista, true);
        this.dialogo_Inventario.boton_Proveedor.addActionListener(this);
        this.dialogo_Inventario.boton_Guardar.addActionListener(this);
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Inventario.setVisible(true);
        return this.bandera;
    }
    
    public void tipo_Actividad() {
       if (this.actividad.equals("Modificar")) {
            this.dialogo_Inventario.campo_Codigo.setEditable(false);
            this.dialogo_Inventario.setCampos(inventario);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Inventario.boton_Guardar) {
            if (this.actividad.equals("Registrar")) {
                if (this.dialogo_Inventario.etiquetas()) {

                    try {
                        if (new DAO_Inventario_Implementacion(this.conexion).crear(new Inventario((String) this.dialogo_Inventario.campo_Codigo.getText(), this.dialogo_Inventario.campo_Descripcion.getText(), Integer.parseInt(this.dialogo_Inventario.campo_Cantidad.getText()), Double.parseDouble(this.dialogo_Inventario.campo_Precio_Compra.getText()), Double.parseDouble(this.dialogo_Inventario.campo_Precio_Venta.getText()), this.dialogo_Inventario.combo_Proveedor.getText()))) {
                            this.bandera = true;
                            this.dialogo_Inventario.dispose();
                            JOptionPane.showMessageDialog(null, "Producto registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Inventario.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            } else if (this.actividad.equals("Modificar")) {
                if (this.dialogo_Inventario.etiquetas()) {
                    try {
                        if (new DAO_Inventario_Implementacion(this.conexion).editar(new Inventario(this.dialogo_Inventario.campo_Codigo.getText(), this.dialogo_Inventario.campo_Descripcion.getText(), Integer.parseInt(this.dialogo_Inventario.campo_Cantidad.getText()), Double.parseDouble(this.dialogo_Inventario.campo_Precio_Compra.getText()), Double.parseDouble(this.dialogo_Inventario.campo_Precio_Venta.getText()), this.dialogo_Inventario.combo_Proveedor.getText())) > 0) {
                            this.bandera = true;
                            this.dialogo_Inventario.dispose();
                            JOptionPane.showMessageDialog(null, "Inventario actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Inventario.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }
        }

        if (ae.getSource() == this.dialogo_Inventario.boton_Proveedor) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion).iniciar();
            if (proveedor.size() == 1) {
                this.dialogo_Inventario.combo_Proveedor.setText(proveedor.get(0).getProveedor());
            }
        }
    }
}
