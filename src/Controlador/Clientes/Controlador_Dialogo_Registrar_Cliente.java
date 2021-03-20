/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Clientes;

import Datos.Cliente.DAO_Cliente_Implementacion;
import Modelo.Cliente;
import Modelo.Usuario;
import Vista.Cliente.Dialogo_Registrar_Cliente;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Registrar_Cliente implements ActionListener {

    private final Dialogo_Registrar_Cliente dialogo_Registrar_Cliente;
    private final Vista_Principal vista;
    private final Connection conexion_Database;
    private final Usuario usuario;
    private final String actividad;
    private final Cliente cliente;
    private boolean bandera = false;

    public Controlador_Dialogo_Registrar_Cliente(Vista_Principal vista, Connection conexion_Database, Usuario usuario, Cliente cliente, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.actividad = actividad;
        this.cliente = cliente;
        this.dialogo_Registrar_Cliente = new Dialogo_Registrar_Cliente(this.vista, true);
        this.dialogo_Registrar_Cliente.boton_Agregar.addActionListener(this);
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Registrar_Cliente.setVisible(true);
        return this.bandera;
    }

    public void numero_Cliente() {
        String numero = new DAO_Cliente_Implementacion(this.conexion_Database).consultar_Numero_Cliente();
        this.dialogo_Registrar_Cliente.numero_Cliente(numero);
    }

    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Cliente();
        } else if (this.actividad.equals("Modificar")) {
            this.dialogo_Registrar_Cliente.cargar_Datos_Cliente(this.cliente);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Registrar_Cliente.boton_Agregar) {
            if (this.dialogo_Registrar_Cliente.etiquetas()) {
                if (this.actividad.equals("Registrar")) {
                    try {
                        if (new DAO_Cliente_Implementacion(this.conexion_Database).crear(new Cliente(this.dialogo_Registrar_Cliente.campo_Cod_Cliente.getText(), this.dialogo_Registrar_Cliente.combo_Cliente.getText(), this.dialogo_Registrar_Cliente.campo_Direccion.getText(), String.valueOf(this.dialogo_Registrar_Cliente.campo_Ciudad.getSelectedItem()), this.dialogo_Registrar_Cliente.campo_Telefono.getText(), this.dialogo_Registrar_Cliente.campo_Celular.getText(), this.dialogo_Registrar_Cliente.campo_RUC.getText(), this.dialogo_Registrar_Cliente.campo_Correo.getText(), this.dialogo_Registrar_Cliente.campo_Contacto.getText(), this.usuario.getCedula()))) {
                            this.bandera = true;
                            this.dialogo_Registrar_Cliente.dispose();
                            JOptionPane.showMessageDialog(null, "Cliente registrado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Registrar_Cliente.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                } else if (this.actividad.equals("Modificar")) {

                    try {
                        if (new DAO_Cliente_Implementacion(this.conexion_Database).editar(new Cliente(this.dialogo_Registrar_Cliente.campo_Cod_Cliente.getText(), this.dialogo_Registrar_Cliente.combo_Cliente.getText(), this.dialogo_Registrar_Cliente.campo_Direccion.getText(), String.valueOf(this.dialogo_Registrar_Cliente.campo_Ciudad.getSelectedItem()), this.dialogo_Registrar_Cliente.campo_Telefono.getText(), this.dialogo_Registrar_Cliente.campo_Celular.getText(), this.dialogo_Registrar_Cliente.campo_RUC.getText(), this.dialogo_Registrar_Cliente.campo_Correo.getText(), this.dialogo_Registrar_Cliente.campo_Contacto.getText(), this.usuario.getCedula())) > 0) {
                            this.bandera = true;
                            this.dialogo_Registrar_Cliente.dispose();
                            JOptionPane.showMessageDialog(null, "Cliente actualizado", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLIntegrityConstraintViolationException e1) {
                        this.dialogo_Registrar_Cliente.correccion_Campos(e1.getCause().toString().split("'")[1]);
                    } catch (SQLException ex) {
                    }
                }
            }
        }
    }
}
