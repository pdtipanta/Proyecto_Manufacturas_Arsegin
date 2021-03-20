/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Numeracion_Documentos;
import Controlador.Orden_De_Compra.Reporte.Controlador_Reporte_Orden_Compra;
import Controlador.Proveedor.Controlador_Dialogo_Buscar_Proveedor;
import Datos.Orden_Compra.DAO_Orden_Compra_Implementacion;
import Datos.Proveedor.DAO_Proveedor_Implementacion;
import Modelo.Orden_Compra;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.Orden_De_Compra.Panel_Orden_Compra;
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
public class Controlador_Orden_De_Compra implements ActionListener {
    private final Vista_Principal       vista;
    private final Connection            conexion_Database;
    private Orden_Compra                modelo_Orden_Compra;
    private Proveedor modelo_Proveedor;
    private Panel_Orden_Compra          panel_Orden_Compra = new Panel_Orden_Compra();
   // private DefaultTableModel           modelo_tabla_Productos_Orden_Compra = ( DefaultTableModel ) panel_Orden_Compra.tabla_Productos_Orden_Compra.getModel();
    private ArrayList<Orden_Compra>     lista_Orden_Compra = new ArrayList<Orden_Compra>();
    private ArrayList<Proveedor>        proveedor = new ArrayList<Proveedor>();
    private final Usuario               usuario;
    private final String                rol;
    private String                      id;
    private String                      cod_Proveedor;
    
    public Controlador_Orden_De_Compra(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        //this.panel_Orden_Compra.boton_Guardar_Orden_Compra.addActionListener(this);
        this.panel_Orden_Compra.boton_Modificar_Orden_Compra.addActionListener(this);
        //this.panel_Orden_Compra.boton_Agregar_Fila.addActionListener(this);
        this.panel_Orden_Compra.boton_Nueva_Orden_Compra.addActionListener(this);
       // this.panel_Orden_Compra.boton_Buscar_Proveedor_Orden.addActionListener(this);
        //this.panel_Orden_Compra.boton_Buscar_Orden_Compra.addActionListener(this);
        this.panel_Orden_Compra.boton_Generar_Orden.addActionListener(this);
        this.panel_Orden_Compra.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        vista.Panel_Contenedor.add(panel_Orden_Compra);
        panel_Orden_Compra.setVisible(true);
        vista.Panel_Contenedor.validate();
        //this.numero_Orden_Compra();
        this.cargar_Ordenes();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
/*
        if (ae.getSource() == panel_Orden_Compra.boton_Buscar_Proveedor_Orden) {
            ArrayList<Proveedor> proveedor = new Controlador_Dialogo_Buscar_Proveedor(this.vista, this.conexion_Database).iniciar();

            if (proveedor.size() == 1) {
                this.panel_Orden_Compra.valores_Proveedores(proveedor.get(0).getProveedor(), proveedor.get(0).getDireccion(), proveedor.get(0).getTelefono(), proveedor.get(0).getCorreo(), proveedor.get(0).getRUC(), this.usuario.getNombre(), this.usuario.getApellido());
                this.cod_Proveedor = proveedor.get(0).getId_Proveedor();
                this.panel_Orden_Compra.botones(true, true, false, false, true, true, true);
            }
        }

        if (ae.getSource() == panel_Orden_Compra.boton_Guardar_Orden_Compra ) {
            String valor_Estado = "En revision";
            
            if (this.panel_Orden_Compra.combo_Estado_Orden.isVisible()) {
                valor_Estado = String.valueOf(this.panel_Orden_Compra.combo_Estado_Orden.getSelectedItem());
            }
            
            if (this.modelo_tabla_Productos_Orden_Compra.getRowCount() > 0 && this.panel_Orden_Compra.etiquetas() ) {
                this.numero_Orden_Compra();
                String[] valores = panel_Orden_Compra.evaluar_Tabla();
                
                this.modelo_Orden_Compra = new Orden_Compra(panel_Orden_Compra.etiqueta_No_Orden_Compra.getText(), panel_Orden_Compra.calendario_Fecha(), Double.valueOf(panel_Orden_Compra.campo_Subtotal_Orden_Compra.getText()), Double.valueOf(panel_Orden_Compra.campo_IVA_Orden_Compra.getText()), Double.valueOf(panel_Orden_Compra.campo_Total_Orden_Compra.getText()), valor_Estado, (String) panel_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.getSelectedItem(), this.usuario.getCedula(), this.cod_Proveedor, valores[0], valores[1], valores[2], valores[3], valores[4]);
                
                try {
                    if (new DAO_Orden_Compra_Implementacion(this.conexion_Database).crear(modelo_Orden_Compra)) {
                        panel_Orden_Compra.valores_Proveedores("", "", "", "", "", "", "");
                        panel_Orden_Compra.limpiar_Valores();
                        panel_Orden_Compra.limpiar_Tabla();
                        this.cod_Proveedor = null;
                        this.panel_Orden_Compra.botones(true, true, false, false, true, false, true);
                        numero_Orden_Compra();
                        JOptionPane.showMessageDialog(null, "Orden de compra registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {}
            } else {
                this.panel_Orden_Compra.etiquetas();
            }
        }

        if (ae.getSource() == panel_Orden_Compra.boton_Buscar_Orden_Compra) {
            ArrayList<Orden_Compra> orden_Compra = new Controlador_Dialogo_Buscar_Orden(this.vista, this.conexion_Database, this.usuario, this.rol).iniciar();

            if (orden_Compra.size() == 1) {

                if (orden_Compra.get(0).getEstado().equals("Aprobado")) {
                    this.panel_Orden_Compra.botones(false, true, false, true, false, false, true);
                } else {
                    if (orden_Compra.get(0).getTipo_Pago().equals("Anulado")) {
                        orden_Compra.get(0).setEstado("Anulado");
                        this.panel_Orden_Compra.botones(false, true, false, false, false, false, false);
                    } else {
                        this.panel_Orden_Compra.botones(false, true, true, false, true, true, true);
                    }
                }

                ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar(orden_Compra.get(0).getProveedor());

                if (proveedor.size() == 1) {
                    this.id = orden_Compra.get(0).getSolicitante();
                    this.cod_Proveedor = proveedor.get(0).getId_Proveedor();
                    this.panel_Orden_Compra.valores_Tabla_Orden(orden_Compra.get(0), proveedor.get(0));
                }
            }
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Nueva_Orden_Compra) {
            this.panel_Orden_Compra.limpiar_Valores();
            this.panel_Orden_Compra.limpiar_Tabla();
            this.panel_Orden_Compra.limpiar_Etiquetas();
            this.cod_Proveedor = null;
            this.panel_Orden_Compra.valores_Proveedores("", "", "", "", "", "", "");
            this.panel_Orden_Compra.botones(true, true, false, false, true, false, true);
            numero_Orden_Compra();
        }

        if (ae.getSource() == panel_Orden_Compra.boton_Modificar_Orden_Compra) {
            String valor_Estado = "En revision";

            if (this.panel_Orden_Compra.combo_Estado_Orden.isVisible()) {
                valor_Estado = String.valueOf(this.panel_Orden_Compra.combo_Estado_Orden.getSelectedItem());
            }

            if (this.modelo_tabla_Productos_Orden_Compra.getRowCount() > 0 && this.panel_Orden_Compra.etiquetas()) {
                String[] valores = panel_Orden_Compra.evaluar_Tabla();

                this.modelo_Orden_Compra = new Orden_Compra(panel_Orden_Compra.etiqueta_No_Orden_Compra.getText(), panel_Orden_Compra.calendario_Fecha(), Double.valueOf(panel_Orden_Compra.campo_Subtotal_Orden_Compra.getText()), Double.valueOf(panel_Orden_Compra.campo_IVA_Orden_Compra.getText()), Double.valueOf(panel_Orden_Compra.campo_Total_Orden_Compra.getText()), valor_Estado, (String) panel_Orden_Compra.combo_Modalidad_Pago_Orden_Compra.getSelectedItem(), this.id, this.cod_Proveedor, valores[0], valores[1], valores[2], valores[3], valores[4]);

                try {
                    if (new DAO_Orden_Compra_Implementacion(this.conexion_Database).editar(modelo_Orden_Compra) > 0) {
                        panel_Orden_Compra.valores_Proveedores("", "", "", "", "", "", "");
                        panel_Orden_Compra.limpiar_Valores();
                        panel_Orden_Compra.limpiar_Tabla();
                        this.cod_Proveedor = null;
                        this.id = null;
                        this.panel_Orden_Compra.botones(true, true, false, false, true, false, true);
                        this.numero_Orden_Compra();
                        JOptionPane.showMessageDialog(null, "Orden de compra actualizada", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {System.out.println(ex);
                }
            } else {
                this.panel_Orden_Compra.etiquetas();
                JOptionPane.showMessageDialog(null, "No se puede actualizar orden de compra", "Orden de compra", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Agregar_Fila) {

            Object[] inventario = new Controlador_Dialogo_Buscar_Producto_Orden_Compra(this.vista, this.conexion_Database, this.panel_Orden_Compra).iniciar();

            if (inventario != null) {
                if (inventario.length == 5) {
                    this.modelo_tabla_Productos_Orden_Compra.addRow(inventario);
                    this.panel_Orden_Compra.calculo_Valores();
                }

            }
        }
        
        if (ae.getSource() == this.panel_Orden_Compra.boton_Generar_Orden) {
            new Controlador_Reporte_Orden_Compra(this.panel_Orden_Compra).iniciar();
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }*/
        if (ae.getSource() == this.panel_Orden_Compra.boton_Nueva_Orden_Compra) {
           if(new Controlador_Dialogo_Orden_Compra(this.vista, this.conexion_Database, this.usuario, this.rol, this.modelo_Orden_Compra, this.modelo_Proveedor, "Registrar").iniciar()){
               this.cargar_Ordenes();
           }
        }
        
        if (ae.getSource() == panel_Orden_Compra.boton_Modificar_Orden_Compra) {
            //String valor = this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getValueAt(this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getValueAt(this.dialogo_Buscar_Compra.tabla_Consulta_Compra.getSelectedRow(), 5);

            ArrayList<Orden_Compra> orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 5));
            

                ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar(orden_Compra.get(0).getProveedor());

                if (proveedor.size() == 1 && orden_Compra.size() == 1) {
                    this.id = orden_Compra.get(0).getSolicitante();
                    this.cod_Proveedor = proveedor.get(0).getId_Proveedor();
                    //this.panel_Orden_Compra.valores_Tabla_Orden(orden_Compra.get(0), proveedor.get(0));
                    if(new Controlador_Dialogo_Orden_Compra(this.vista, this.conexion_Database, this.usuario, this.rol, orden_Compra.get(0), proveedor.get(0), "Modificar").iniciar()){
                        this.cargar_Ordenes();
                    }
                }
            }
        
        if (ae.getSource() == this.panel_Orden_Compra.boton_Generar_Orden) {
            ArrayList<Orden_Compra> orden_Compra = new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar(this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 0) + ";" + this.panel_Orden_Compra.tabla_Consulta_Compra.getValueAt(this.panel_Orden_Compra.tabla_Consulta_Compra.getSelectedRow(), 5));
            

                ArrayList<Proveedor> proveedor = new DAO_Proveedor_Implementacion(this.conexion_Database).consultar(orden_Compra.get(0).getProveedor());

                if (proveedor.size() == 1 && orden_Compra.size() == 1) {
                    //this.panel_Orden_Compra.valores_Tabla_Orden(orden_Compra.get(0), proveedor.get(0));
                    new Controlador_Reporte_Orden_Compra(orden_Compra.get(0), proveedor.get(0)).iniciar();
                }
            //new Controlador_Reporte_Orden_Compra(this.panel_Orden_Compra).iniciar();
        }

        if (ae.getSource() == this.panel_Orden_Compra.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
           // new Controlador_Dialogo_Orden_Compra(this.vista, this.conexion_Database, this.usuario, this.rol, "Modificar").iniciar();
        
    }
/*
    public void numero_Orden_Compra() {
        panel_Orden_Compra.etiqueta_No_Orden_Compra.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Orden_Compra_Implementacion(this.conexion_Database).consultar_Numero_Orden()));
    }*/
    
    public void cargar_Ordenes(){
        new Controlador_Dialogo_Buscar_Orden(this.panel_Orden_Compra, this.conexion_Database, this.usuario, this.rol).iniciar();
    }
    
    public void habilitar_Rol() {
        this.panel_Orden_Compra.Roles(rol);
    }
    
    public void set_Usuario(){
        this.panel_Orden_Compra.set_Usuario(this.usuario, this.rol);
    }
}
    
