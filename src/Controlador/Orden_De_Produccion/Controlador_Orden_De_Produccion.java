/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Controlador.Maquila.Controlador_Dialogo_Buscar_Maquila;
import Controlador.Numeracion_Documentos;
import Controlador.Orden_De_Produccion.Reporte.Controlador_Reporte_Orden_Produccion;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Maquila;
import Modelo.Orden_Produccion;
import Modelo.Usuario;
import Vista.Maquilas.Orden_De_Produccion.Panel_Orden_De_Produccion;
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
public class Controlador_Orden_De_Produccion extends EventListenerList implements ActionListener{
    private Vista_Principal             vista;
    private Connection                  conexion_Database;
    private Usuario                     usuario;
    private String                      rol;
    private ArrayList<Maquila>          modelo_Maquila = new ArrayList<Maquila>(); 
    private Orden_Produccion            modelo_Orden_Produccion;
    private Panel_Orden_De_Produccion   panel_Orden_Produccion = new Panel_Orden_De_Produccion();
    private DefaultTableModel           modelo_Tabla_Maquila = ( DefaultTableModel )  panel_Orden_Produccion.tabla_Productos_Maquila.getModel();
    private ArrayList<Orden_Produccion>  lista_Orden = new ArrayList<Orden_Produccion>();
    private String id_Maquila;
    
    public Controlador_Orden_De_Produccion(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        this.panel_Orden_Produccion.boton_Guardar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Modificar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Agregar_Fila.addActionListener(this);
        this.panel_Orden_Produccion.boton_Nueva_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Buscar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Agregar_Maquila.addActionListener(this);
        this.panel_Orden_Produccion.boton_Generar_Orden.addActionListener(this);
        this.panel_Orden_Produccion.boton_Cerrar_Sesion.addActionListener(this);
    }

    public void iniciar() {
        this.vista.Panel_Contenedor.add(this.panel_Orden_Produccion);
        this.panel_Orden_Produccion.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.numero_Orden_Produccion();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Agregar_Maquila) {
            ArrayList<Maquila> maquila = new Controlador_Dialogo_Buscar_Maquila(this.vista, this.conexion_Database).iniciar();

            if (maquila.size() == 1) {
                this.panel_Orden_Produccion.valores_Maquila(maquila.get(0).getMaquila(), maquila.get(0).getDireccion(), maquila.get(0).getTelefono(), maquila.get(0).getRUC());
                this.panel_Orden_Produccion.botones(true, true, false, false, true, true);
                this.id_Maquila = maquila.get(0).getId_Maquila();
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Nueva_Orden) {
            this.panel_Orden_Produccion.limpiar_Valores();
            this.panel_Orden_Produccion.limpiar_Tabla();
            this.panel_Orden_Produccion.valores_Maquila("", "", "", "");
            this.panel_Orden_Produccion.limpiar_Etiquetas();
            this.panel_Orden_Produccion.botones(true, true, false, false, true, false);
            this.numero_Orden_Produccion();
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Guardar_Orden) {

            if (this.modelo_Tabla_Maquila.getRowCount() > 0 && this.panel_Orden_Produccion.etiquetas()) {
                this.numero_Orden_Produccion();
                String[] valores = this.panel_Orden_Produccion.evaluar_Tabla();

                this.modelo_Orden_Produccion = new Orden_Produccion(this.panel_Orden_Produccion.etiqueta_No_Orden.getText(), this.panel_Orden_Produccion.calendario(), Double.valueOf(this.panel_Orden_Produccion.campo_Total_Orden.getText()), (String) this.panel_Orden_Produccion.combo_Estado_Orden.getSelectedItem(), this.panel_Orden_Produccion.caja_Observaciones_Maquila.getText(), this.id_Maquila, valores[0], valores[1], valores[2], valores[3]);

                try {
                    if (new DAO_Orden_Produccion_Implementacion(this.conexion_Database).crear(this.modelo_Orden_Produccion)) {
                        this.panel_Orden_Produccion.valores_Maquila("", "", "", "");
                        this.panel_Orden_Produccion.limpiar_Valores();
                        this.panel_Orden_Produccion.limpiar_Tabla();
                        this.id_Maquila = null;
                        numero_Orden_Produccion();
                        JOptionPane.showMessageDialog(null, "Orden de produccion registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                this.panel_Orden_Produccion.etiquetas();
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Modificar_Orden) {

            if (this.modelo_Tabla_Maquila.getRowCount() > 0 && this.panel_Orden_Produccion.etiquetas()) {
                
                String[] valores = this.panel_Orden_Produccion.evaluar_Tabla();

                this.modelo_Orden_Produccion = new Orden_Produccion(this.panel_Orden_Produccion.etiqueta_No_Orden.getText(), this.panel_Orden_Produccion.calendario(), Double.valueOf(this.panel_Orden_Produccion.campo_Total_Orden.getText()), (String) this.panel_Orden_Produccion.combo_Estado_Orden.getSelectedItem(), this.panel_Orden_Produccion.caja_Observaciones_Maquila.getText(), (String) this.id_Maquila, valores[0], valores[1], valores[2], valores[3]);

                try {
                    if (new DAO_Orden_Produccion_Implementacion(this.conexion_Database).editar(this.modelo_Orden_Produccion) > 0) {
                        this.panel_Orden_Produccion.valores_Maquila("", "", "", "");
                        this.panel_Orden_Produccion.limpiar_Valores();
                        this.panel_Orden_Produccion.limpiar_Tabla();
                        this.id_Maquila = null;
                        this.panel_Orden_Produccion.botones(true, true, false, false, true, false);
                        this.numero_Orden_Produccion();
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede actualizar la orden", "Orden de Produccion", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Buscar_Orden) {
            ArrayList<Orden_Produccion> orden_Produccion = new Controlador_Dialogo_Buscar_Orden_Produccion(this.vista, this.conexion_Database).iniciar();
            boolean bandera = false;

            if (orden_Produccion.size() == 1) {
                if (orden_Produccion.get(0).getEstado().equals("Por pagar")) {
                    bandera = true;
                }

                this.panel_Orden_Produccion.botones(false, true, bandera, true, bandera, bandera);

                String valor_Maquila = orden_Produccion.get(0).getMaquila();
                ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar(valor_Maquila);

                if (maquila.size() == 1) {
                    this.panel_Orden_Produccion.valores_Tabla_Orden(orden_Produccion.get(0), maquila.get(0));
                }
            }

        }

        if (ae.getSource() == this.panel_Orden_Produccion.boton_Agregar_Fila) {
            Object[] trabajo = new Controlador_Dialogo_Agregar_Trabajo(this.vista).iniciar();

            if (trabajo != null) {
                this.modelo_Tabla_Maquila.addRow(trabajo);
                this.panel_Orden_Produccion.calculo_Valores();
            }
        }
        
        if (ae.getSource() == this.panel_Orden_Produccion.boton_Generar_Orden) {
            new Controlador_Reporte_Orden_Produccion(this.panel_Orden_Produccion).iniciar();
        }
        
        if (ae.getSource() == this.panel_Orden_Produccion.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }
    }

    public void numero_Orden_Produccion() {
        this.panel_Orden_Produccion.etiqueta_No_Orden.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Orden_Produccion_Implementacion(this.conexion_Database).consultar_Numero_Orden()));
    }
    
    public void set_Usuario(){
        this.panel_Orden_Produccion.set_Usuario(this.usuario, this.rol);
    }
    
    public void habilitar_Rol() {
        this.panel_Orden_Produccion.Roles(rol);
    }
}
