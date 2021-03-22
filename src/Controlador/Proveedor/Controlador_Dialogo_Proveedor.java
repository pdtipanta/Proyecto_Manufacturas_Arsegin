/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proveedor;

import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Proveedor;
import Vista.Proveedor.Dialogo_Proveedor;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Proveedor implements ActionListener {
    private final Dialogo_Proveedor       dialogo_Registrar_Proveedor;
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private final String                  actividad;
    private Proveedor                     proveedor;
    private String                        id_Proveedor;
    private boolean                       bandera = false;

    public Controlador_Dialogo_Proveedor(Vista_Principal vista, Connection conexion_Database, Proveedor proveedor, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.proveedor = proveedor;
        this.actividad = actividad;
        this.dialogo_Registrar_Proveedor = new Dialogo_Proveedor(this.vista, true);
        this.dialogo_Registrar_Proveedor.boton_Guardar.addActionListener(this);
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Registrar_Proveedor.setVisible(true);
        return this.bandera;
    }
 
    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Proveedor();
        } else if (this.actividad.equals("Modificar")) {
            this.dialogo_Registrar_Proveedor.setCampos(proveedor);
            this.dialogo_Registrar_Proveedor.campos_Busqueda();
            this.id_Proveedor = this.proveedor.getId_Proveedor();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Registrar_Proveedor.boton_Guardar) {

            if (this.actividad.equals("Registrar")) {
                if (this.dialogo_Registrar_Proveedor.etiquetas()) {
                    this.numero_Proveedor();
                    try {
                        if (new DAO_Proveedor_Implementacion(this.conexion_Database).crear(new Proveedor(this.numero_Proveedor(), this.dialogo_Registrar_Proveedor.combo_Proveedor.getText(), this.dialogo_Registrar_Proveedor.campo_RUC.getText(), this.dialogo_Registrar_Proveedor.campo_Direccion.getText(), this.dialogo_Registrar_Proveedor.campo_Correo.getText(), this.dialogo_Registrar_Proveedor.campo_Telefono.getText(), this.dialogo_Registrar_Proveedor.caja_Productos.getText()))) {
                            this.bandera = true;
                            this.dialogo_Registrar_Proveedor.dispose();
                            JOptionPane.showMessageDialog(null, "Proveedor registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Registrar_Proveedor.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }
            if (this.actividad.equals("Modificar")) {
                if (this.dialogo_Registrar_Proveedor.etiquetas()) {
                    this.proveedor = new Proveedor(this.id_Proveedor, this.dialogo_Registrar_Proveedor.combo_Proveedor.getText(), this.dialogo_Registrar_Proveedor.campo_RUC.getText(), this.dialogo_Registrar_Proveedor.campo_Direccion.getText(), this.dialogo_Registrar_Proveedor.campo_Correo.getText(), this.dialogo_Registrar_Proveedor.campo_Telefono.getText(), this.dialogo_Registrar_Proveedor.caja_Productos.getText());

                    try {
                        if (new DAO_Proveedor_Implementacion(this.conexion_Database).editar(this.proveedor) > 0) {
                            this.id_Proveedor = null;
                            this.bandera = true;
                            this.dialogo_Registrar_Proveedor.dispose();
                            JOptionPane.showMessageDialog(null, "Proveedor actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Registrar_Proveedor.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }

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
}
