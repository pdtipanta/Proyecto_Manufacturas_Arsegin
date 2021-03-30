/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Controlador.Clientes.Controlador_Dialogo_Buscar_Cliente;
import Controlador.Inventario.Controlador_Dialogo_Buscar_Inventario;
import Controlador.Numeracion_Documentos;
import Datos.Factura.DAO_Factura_Implementacion;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Factura.Dialogo_Factura;
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
public class Controlador_Dialogo_Factura implements ActionListener{
    private final Vista_Principal         vista;
    private final Connection              conexion_Database;
    private Factura                       modelo_Factura;
    private final Dialogo_Factura         dialogo_Factura;
    private final DefaultTableModel       modelo_Tabla_Factura;
    private Inventario                    modelo_Inventario;
    private Factura                       respaldo_Factura;
    private final ArrayList<Cliente>      modelo_Cliente;
    private final ArrayList<Factura>      factura;
    private final String                  actividad;
    private boolean                       bandera = false;
    private final Usuario                 usuario;
    private final String                  rol;
    
    public Controlador_Dialogo_Factura(Vista_Principal vista, Connection conexion_Database, ArrayList<Factura> factura, ArrayList<Cliente> cliente, Usuario usuario, String rol, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.factura = factura;
        this.modelo_Cliente = cliente;
        this.usuario = usuario;
        this.rol = rol;
        this.actividad = actividad;
        this.dialogo_Factura = new Dialogo_Factura(this.vista, true);
        this.dialogo_Factura.boton_Guardar_Factura.addActionListener(this);
        this.dialogo_Factura.boton_Agregar_Fila.addActionListener(this);
        this.dialogo_Factura.boton_Quitar_Fila.addActionListener(this);
        this.dialogo_Factura.boton_Agregar_Cliente.addActionListener(this);
        modelo_Tabla_Factura = (DefaultTableModel) this.dialogo_Factura.tabla_Productos_Factura.getModel();
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Factura.setVisible(true);
        return this.bandera;
    }
    
    public void tipo_Actividad() {
        if (this.actividad.equals("Registrar")) {
            this.numero_Factura();
            this.dialogo_Factura.valor_IVA.setEnabled(true);
        } else if (this.actividad.equals("Modificar")) {
            this.dialogo_Factura.boton_Agregar_Fila.setEnabled(true);
            this.dialogo_Factura.boton_Quitar_Fila.setEnabled(true);
            this.dialogo_Factura.valor_IVA.setEnabled(true);
            this.dialogo_Factura.boton_Agregar_Cliente.setEnabled(false);

            if (factura.size() == 1) {
                this.respaldo_Factura = factura.get(0);
                boolean bandera = false;

                if (factura.get(0).getEstado().equals("Credito")) {
                    bandera = true;
                }

                this.dialogo_Factura.boton_Agregar_Fila.setEnabled(bandera);
                this.dialogo_Factura.boton_Quitar_Fila.setEnabled(bandera);
                this.dialogo_Factura.combo_Estado_Factura.setEnabled(bandera);
                this.dialogo_Factura.boton_Guardar_Factura.setEnabled(bandera);
                this.dialogo_Factura.caja_Observaciones_Factura.setEditable(bandera);
                this.dialogo_Factura.valor_IVA.setEnabled(bandera);

                this.dialogo_Factura.valores_Clientes(this.modelo_Cliente.get(0), this.usuario);
                this.dialogo_Factura.valores_Tabla_Factura(factura);
            }
        }
    }

    public void numero_Factura() {
        this.dialogo_Factura.etiqueta_No_Factura.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Factura_Implementacion(this.conexion_Database).consultar_Numero_Factura()));
    }

    public void debitar_Inventario(int i) {

        ArrayList< Inventario> inventario = new ArrayList<Inventario>();

        inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar((String) this.dialogo_Factura.tabla_Productos_Factura.getValueAt(i, 1));

        if (inventario.size() == 1) {
            int cantidad = inventario.get(0).getCantidad_Disponible() - Integer.parseInt(String.valueOf(this.dialogo_Factura.tabla_Productos_Factura.getValueAt(i, 0)));

            this.modelo_Inventario = new Inventario((String) this.dialogo_Factura.tabla_Productos_Factura.getValueAt(i, 1), inventario.get(0).getDescripcion(), cantidad, inventario.get(0).getPrecio_Compra(), inventario.get(0).getPrecio_Venta(), inventario.get(0).getProveedor(), inventario.get(0).getImagen());
        }
        try {
            new DAO_Inventario_Implementacion(this.conexion_Database).editar(this.modelo_Inventario);
        } catch (SQLException ex) {
        }
    }

    public boolean estado_Factura() {
        boolean bandera = false;
        if (this.dialogo_Factura.combo_Estado_Factura.getSelectedItem().equals("Credito")) {
            bandera = true;
        } else if (this.dialogo_Factura.combo_Estado_Factura.getSelectedItem().equals("Pagado")) {
            bandera = true;
        }
        return bandera;
    }
    
    public void reponer_Inventario(int i) {

        String[] cantidad = this.respaldo_Factura.getCantidad().split(";");
        String[] codigo = this.respaldo_Factura.getCodigo().split(";");
        String[] descripcion = this.respaldo_Factura.getDescripcion().split(";");

        ArrayList< Inventario> inventarios = new ArrayList<Inventario>();

        inventarios = new DAO_Inventario_Implementacion(this.conexion_Database).consultar(codigo[i]);

        int total = inventarios.get(0).getCantidad_Disponible() + Integer.parseInt(String.valueOf(cantidad[i]));

        Inventario inventario = new Inventario(codigo[i], descripcion[i], total, inventarios.get(0).getPrecio_Compra(), inventarios.get(0).getPrecio_Venta(), inventarios.get(0).getProveedor(), inventarios.get(0).getImagen());

        try {
            new DAO_Inventario_Implementacion(this.conexion_Database).editar(inventario);
        } catch (SQLException ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Factura.boton_Agregar_Cliente) {
            ArrayList<Cliente> cliente = new Controlador_Dialogo_Buscar_Cliente(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cliente.size() == 1) {
                this.dialogo_Factura.valores_Clientes(cliente.get(0), this.usuario);
                this.dialogo_Factura.boton_Agregar_Fila.setEnabled(true);
                this.dialogo_Factura.boton_Quitar_Fila.setEnabled(true);
                this.dialogo_Factura.valor_IVA.setEnabled(true);
            }
        }
        
        if (ae.getSource() == this.dialogo_Factura.boton_Agregar_Fila) {
            ArrayList<Inventario> inventario = new Controlador_Dialogo_Buscar_Inventario(this.vista, this.conexion_Database, false).iniciar();
            boolean bandera = true;

            if (inventario.size() == 1) {

                for (int i = 0; i < this.dialogo_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                    if (inventario.get(0).getCodigo().equals(this.dialogo_Factura.tabla_Productos_Factura.getValueAt(i, 1))) {
                        bandera = false;
                    }
                }
                if (bandera) {
                    Object[] fila = new Controlador_Dialogo_Agregar_Factura(this.vista, inventario.get(0)).iniciar();

                    if (fila != null) {
                        this.modelo_Tabla_Factura.addRow(fila);
                        this.dialogo_Factura.calculo_Valores();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if (ae.getSource() == this.dialogo_Factura.boton_Guardar_Factura) {

            if (this.modelo_Tabla_Factura.getRowCount() > 0 && this.dialogo_Factura.etiquetas()) {
                if (this.actividad.equals("Registrar")) {
                    this.numero_Factura();

                    String[] valores = this.dialogo_Factura.evaluar_Tabla();

                    this.modelo_Factura = new Factura(this.dialogo_Factura.etiqueta_No_Factura.getText(), this.dialogo_Factura.calendario(), Double.parseDouble(this.dialogo_Factura.campo_Subtotal_Factura.getText()), Double.valueOf(this.dialogo_Factura.campo_IVA_Factura.getText()), Double.valueOf(this.dialogo_Factura.campo_Total_Factura.getText()), (String) this.dialogo_Factura.combo_Estado_Factura.getSelectedItem(), this.dialogo_Factura.campo_Codigo.getText(), valores[0], valores[1], valores[2], valores[3], valores[4], this.usuario.getCedula(), this.dialogo_Factura.caja_Observaciones_Factura.getText());

                    try {
                        if (new DAO_Factura_Implementacion(this.conexion_Database).crear(this.modelo_Factura)) {

                            for (int i = 0; i < this.dialogo_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                                this.debitar_Inventario(i);
                            }
                            this.bandera = true;
                            this.dialogo_Factura.dispose();
                            JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    this.dialogo_Factura.etiquetas();
                }
            }
            
            if (this.actividad.equals("Modificar")) {
                String[] tamaño = this.respaldo_Factura.getCodigo().split(";");

                if (this.modelo_Tabla_Factura.getRowCount() > 0 && this.dialogo_Factura.etiquetas() && this.estado_Factura()) {
                    String[] valores = this.dialogo_Factura.evaluar_Tabla();

                    try {
                        if (new DAO_Factura_Implementacion(this.conexion_Database).editar(new Factura(this.dialogo_Factura.etiqueta_No_Factura.getText(), this.dialogo_Factura.calendario(), Double.valueOf(this.dialogo_Factura.campo_Subtotal_Factura.getText()), Double.valueOf(this.dialogo_Factura.campo_IVA_Factura.getText()), Double.valueOf(this.dialogo_Factura.campo_Total_Factura.getText()), (String) this.dialogo_Factura.combo_Estado_Factura.getSelectedItem(), this.dialogo_Factura.campo_Codigo.getText(), valores[0], valores[1], valores[2], valores[3], valores[4], this.usuario.getCedula(), this.dialogo_Factura.caja_Observaciones_Factura.getText())) > 0) {
                            for (int i = 0; i < tamaño.length - 1; i++) {
                                this.reponer_Inventario(i);
                            }

                            for (int i = 0; i < this.dialogo_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                                this.debitar_Inventario(i);
                            }
                            this.respaldo_Factura = null;
                            this.bandera = true;
                            this.dialogo_Factura.dispose();
                            JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar la Factura", "Factura", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                } else if (this.dialogo_Factura.combo_Estado_Factura.getSelectedItem().equals("Anular")) {
                    try {
                        this.respaldo_Factura.setEstado("Anular");

                        if (new DAO_Factura_Implementacion(this.conexion_Database).editar(this.respaldo_Factura) > 0) {

                            for (int i = 0; i < this.dialogo_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                                this.reponer_Inventario(i);
                            }
                            this.respaldo_Factura = null;
                            this.bandera = true;
                            this.dialogo_Factura.dispose();
                            JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al actualizar la Factura", "Factura", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException ex) {
                    }
                }
            }
        }

    }
}
