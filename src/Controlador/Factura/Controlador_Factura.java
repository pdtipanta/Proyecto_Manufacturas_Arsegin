/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Controlador.Factura.Reporte.Controlador_Reporte_Factura;
import Controlador.Clientes.Controlador_Dialogo_Buscar_Cliente;
import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Inventario.Controlador_Dialogo_Buscar_Inventario;
import Controlador.Numeracion_Documentos;
import Datos.Cliente.DAO_Cliente_Implementacion;
import Datos.Factura.DAO_Factura_Implementacion;
import Datos.Inventario.DAO_Inventario_Implementacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Inventario;
import Modelo.Usuario;
import Vista.Factura.Panel_Factura;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Controlador_Factura extends EventListenerList implements ActionListener {
    private Vista_Principal         vista;
    private Connection              conexion_Database;
    private Factura                 modelo_Factura;
    private Panel_Factura           panel_Factura = new Panel_Factura(); 
    private ArrayList<Factura>      factura;
    private ArrayList<Cliente>      cliente;
    //private DefaultTableModel       modelo_Tabla_Factura = ( DefaultTableModel )  panel_Factura.tabla_Productos_Factura.getModel();
    private Inventario              modelo_Inventario;
    //private Factura                 respaldo_Factura;
    private Usuario                 usuario;
    private String                     rol;
    
    public Controlador_Factura(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        //this.panel_Factura.boton_Guardar_Factura.addActionListener(this);
        this.panel_Factura.boton_Modificar_Factura.addActionListener(this);
        //this.panel_Factura.boton_Agregar_Fila.addActionListener(this);
        //this.panel_Factura.boton_Quitar_Fila.addActionListener(this);
        this.panel_Factura.boton_Nueva_Factura.addActionListener(this);
        //this.panel_Factura.boton_Buscar_Factura.addActionListener(this);
        //this.panel_Factura.boton_Agregar_Cliente.addActionListener(this);
        this.panel_Factura.boton_Imprimir_Facturacion.addActionListener(this);
        this.panel_Factura.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(panel_Factura);
        this.panel_Factura.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        //this.numero_Factura();
        this.cargar_Facturas();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == this.panel_Factura.boton_Nueva_Factura) {
            new Controlador_Dialogo_Factura(this.vista, this.conexion_Database, factura, cliente, this.usuario, this.rol, "Registrar").iniciar();
            //limpiar_Valores();
            //this.panel_Factura.limpiar_Etiquetas();
            //this.panel_Factura.botones(true, true, false, true, false);
            //this.numero_Factura();
        }
        /*
        if (ae.getSource() == this.panel_Factura.boton_Agregar_Cliente) {
            ArrayList<Cliente> cliente = new Controlador_Dialogo_Buscar_Cliente(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (cliente.size() == 1) {
                this.panel_Factura.valores_Clientes(cliente.get(0).getCodigo_Cliente(), cliente.get(0).getCliente(), cliente.get(0).getDireccion(), cliente.get(0).getTelefono(), cliente.get(0).getRUC(), cliente.get(0).getCiudad(), this.usuario.getNombre(), this.usuario.getApellido());
                this.panel_Factura.botones(true, true, false, true, true);
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Nueva_Factura) {
            limpiar_Valores();
            this.panel_Factura.limpiar_Etiquetas();
            this.panel_Factura.botones(true, true, false, true, false);
            this.numero_Factura();
        }

        if (ae.getSource() == this.panel_Factura.boton_Guardar_Factura) {

            if (this.modelo_Tabla_Factura.getRowCount() > 0 && this.panel_Factura.etiquetas()) {
                this.numero_Factura();
                
                String[] valores = this.panel_Factura.evaluar_Tabla();

                this.modelo_Factura = new Factura(this.panel_Factura.etiqueta_No_Factura.getText(), this.panel_Factura.calendario(), Double.parseDouble(this.panel_Factura.campo_Subtotal_Factura.getText()), Double.valueOf(this.panel_Factura.campo_IVA_Factura.getText()), Double.valueOf(this.panel_Factura.campo_Total_Factura.getText()), (String) this.panel_Factura.combo_Estado_Factura.getSelectedItem(), this.panel_Factura.campo_Codigo.getText(), valores[0], valores[1], valores[2], valores[3], valores[4], this.usuario.getCedula(), this.panel_Factura.caja_Observaciones_Factura.getText());

                try {
                    if (new DAO_Factura_Implementacion(this.conexion_Database).crear(this.modelo_Factura)) {

                        for (int i = 0; i < this.panel_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                            this.debitar_Inventario(i);
                        }

                        limpiar_Valores();
                        this.numero_Factura();
                        this.panel_Factura.botones(true, true, false, true, false);
                        JOptionPane.showMessageDialog(null, "Registro exitoso", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                this.panel_Factura.etiquetas();
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Modificar_Factura) {

            String[] tamaño = this.respaldo_Factura.getCodigo().split(";");

            if (this.modelo_Tabla_Factura.getRowCount() > 0 && this.panel_Factura.etiquetas() && this.estado_Factura()) {
                String[] valores = this.panel_Factura.evaluar_Tabla();

                try {
                    if (new DAO_Factura_Implementacion(this.conexion_Database).editar(new Factura(this.panel_Factura.etiqueta_No_Factura.getText(), this.panel_Factura.calendario(), Double.valueOf(this.panel_Factura.campo_Subtotal_Factura.getText()), Double.valueOf(this.panel_Factura.campo_IVA_Factura.getText()), Double.valueOf(this.panel_Factura.campo_Total_Factura.getText()), (String) this.panel_Factura.combo_Estado_Factura.getSelectedItem(), this.panel_Factura.campo_Codigo.getText(), valores[0], valores[1], valores[2], valores[3], valores[4], this.usuario.getCedula(), this.panel_Factura.caja_Observaciones_Factura.getText())) > 0) {
                        for (int i = 0; i < tamaño.length - 1; i++) {
                            this.reponer_Inventario(i);
                        }

                        for (int i = 0; i < this.panel_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                            this.debitar_Inventario(i);
                        }

                        this.respaldo_Factura = null;
                        limpiar_Valores();
                        this.panel_Factura.botones(true, true, false, true, false);
                        this.numero_Factura();
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la Factura", "Factura", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else if (this.panel_Factura.combo_Estado_Factura.getSelectedItem().equals("Anular")) {
                try {
                    this.respaldo_Factura.setEstado("Anular");

                    if (new DAO_Factura_Implementacion(this.conexion_Database).editar(this.respaldo_Factura) > 0) {

                        for (int i = 0; i < this.panel_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                            this.reponer_Inventario(i);
                        }
                        this.respaldo_Factura = null;

                        limpiar_Valores();
                        this.panel_Factura.botones(true, true, false, true, true);
                        this.numero_Factura();
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar la Factura", "Factura", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Buscar_Factura) {
            ArrayList<Factura> factura = new Controlador_Dialogo_Buscar_Factura(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (factura.size() == 1) {
                this.respaldo_Factura = factura.get(0);
                boolean bandera = false;

                if (factura.get(0).getEstado().equals("Credito")) {
                    bandera = true;
                }

                this.panel_Factura.botones(false, true, bandera, bandera, bandera);

                String valor_Cliente = factura.get(0).getCliente() + ";" + factura.get(0).getVendedor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    this.panel_Factura.valores_Clientes(modelo_Cliente.get(0).getCodigo_Cliente(), modelo_Cliente.get(0).getCliente(), modelo_Cliente.get(0).getDireccion(), modelo_Cliente.get(0).getTelefono(), modelo_Cliente.get(0).getRUC(), modelo_Cliente.get(0).getCiudad(), this.usuario.getNombre(), this.usuario.getApellido());
                    this.panel_Factura.valores_Tabla_Factura(factura);
                }
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Agregar_Fila) {
            ArrayList<Inventario> inventario = new Controlador_Dialogo_Buscar_Inventario(this.vista, this.conexion_Database, false).iniciar();
            boolean bandera = true;

            if (inventario.size() == 1) {

                for (int i = 0; i < this.panel_Factura.tabla_Productos_Factura.getRowCount(); i++) {
                    if (inventario.get(0).getCodigo().equals(this.panel_Factura.tabla_Productos_Factura.getValueAt(i, 1))) {
                        bandera = false;
                    }
                }
                if (bandera) {
                    Object[] fila = new Controlador_Dialogo_Agregar_Factura(this.vista, inventario.get(0)).iniciar();

                    if (fila != null) {
                        this.modelo_Tabla_Factura.addRow(fila);
                        this.panel_Factura.calculo_Valores();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya esta agregado a la lista", "Inventario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (ae.getSource() == panel_Factura.boton_Imprimir_Facturacion) {

            ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(this.respaldo_Factura.getCliente() + ";" + "Todos");

            if (this.respaldo_Factura != null && cliente.size() == 1) {
                new Controlador_Reporte_Factura(this.respaldo_Factura, cliente.get(0)).iniciar();
            }
        }

        if (ae.getSource() == this.panel_Factura.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }*/
        if (ae.getSource() == this.panel_Factura.boton_Modificar_Factura) {
           // ArrayList<Factura> factura = new Controlador_Dialogo_Buscar_Factura(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();
           ArrayList<Factura> factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar(this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 0) + ";" + this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 6));
           
           
           if (factura.size() == 1) {
               String valor_Cliente = factura.get(0).getCliente() + ";" + factura.get(0).getVendedor();
                ArrayList<Cliente> modelo_Cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(valor_Cliente);

                if (modelo_Cliente.size() == 1) {
                    if(new Controlador_Dialogo_Factura(this.vista, this.conexion_Database, factura, modelo_Cliente, this.usuario, this.rol, "Modificar").iniciar()){
                        this.panel_Factura.boton_Modificar_Factura.setEnabled(false);
                        this.panel_Factura.boton_Imprimir_Facturacion.setEnabled(false);
                        this.cargar_Facturas();
                    }
                }
        }
        }
        
        if (ae.getSource() == panel_Factura.boton_Imprimir_Facturacion) {
            ArrayList<Factura> factura = new DAO_Factura_Implementacion(this.conexion_Database).consultar(this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 0) + ";" + this.panel_Factura.tabla_Consulta_Factura.getValueAt(this.panel_Factura.tabla_Consulta_Factura.getSelectedRow(), 6));
            //ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(this.respaldo_Factura.getCliente() + ";" + "Todos");

            if (factura.size() == 1) {
            //System.out.println(factura.get(0).getCliente() + "/" + factura.get(0).getVendedor());
            ArrayList<Cliente> cliente = new DAO_Cliente_Implementacion(this.conexion_Database).consultar(factura.get(0).getCliente() + ";" + factura.get(0).getVendedor());
                new Controlador_Reporte_Factura(factura.get(0), cliente.get(0)).iniciar();
            }
        }
        
        if (ae.getSource() == this.panel_Factura.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }
/*
    public void numero_Factura() {
        this.panel_Factura.etiqueta_No_Factura.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Factura_Implementacion(this.conexion_Database).consultar_Numero_Factura()));
    }
*//*
    public void limpiar_Valores() {
        this.panel_Factura.valores_Clientes("", "", "", "", "", "", "", "");
        this.panel_Factura.limpiar_Valores();
        this.panel_Factura.limpiar_Tabla();
    }
*//*
    public void debitar_Inventario(int i) {

        ArrayList< Inventario> inventario = new ArrayList<Inventario>();

        inventario = new DAO_Inventario_Implementacion(this.conexion_Database).consultar((String) this.panel_Factura.tabla_Productos_Factura.getValueAt(i, 1));

        int cantidad = inventario.get(0).getCantidad_Disponible() - Integer.parseInt(String.valueOf(this.panel_Factura.tabla_Productos_Factura.getValueAt(i, 0)));

        this.modelo_Inventario = new Inventario((String) this.panel_Factura.tabla_Productos_Factura.getValueAt(i, 1), inventario.get(0).getDescripcion(), cantidad, inventario.get(0).getPrecio_Compra(), inventario.get(0).getPrecio_Venta(), inventario.get(0).getProveedor());
        try {
            new DAO_Inventario_Implementacion(this.conexion_Database).editar(this.modelo_Inventario);
        } catch (SQLException ex) {
        }
    }*/
/*
    public void reponer_Inventario(int i) {

        String[] cantidad = this.respaldo_Factura.getCantidad().split(";");
        String[] codigo = this.respaldo_Factura.getCodigo().split(";");
        String[] descripcion = this.respaldo_Factura.getDescripcion().split(";");

        ArrayList< Inventario> inventarios = new ArrayList<Inventario>();

        inventarios = new DAO_Inventario_Implementacion(this.conexion_Database).consultar(codigo[i]);

        int total = inventarios.get(0).getCantidad_Disponible() + Integer.parseInt(String.valueOf(cantidad[i]));

        Inventario inventario = new Inventario(codigo[i], descripcion[i], total, inventarios.get(0).getPrecio_Compra(), inventarios.get(0).getPrecio_Venta(), inventarios.get(0).getProveedor());

        try {
            new DAO_Inventario_Implementacion(this.conexion_Database).editar(inventario);
        } catch (SQLException ex) {
        }
    }*/
/*
    public boolean estado_Factura() {
        boolean bandera = false;
        if (this.panel_Factura.combo_Estado_Factura.getSelectedItem().equals("Credito")) {
            bandera = true;
        } else if (this.panel_Factura.combo_Estado_Factura.getSelectedItem().equals("Pagado")) {
            bandera = true;
        }
        return bandera;
    }*/
    
    public void cargar_Facturas(){
        new Controlador_Dialogo_Buscar_Factura(this.panel_Factura, this.conexion_Database, this.usuario, this.rol).iniciar();
    }

    public void habilitar_Rol() {
        this.panel_Factura.Roles(rol);
    }

    public void set_Usuario() {
        this.panel_Factura.set_Usuario(this.usuario, this.rol);
    }
}
