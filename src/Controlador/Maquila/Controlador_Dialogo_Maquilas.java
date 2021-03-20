package Controlador.Maquila;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Modelo.Maquila;
import Modelo.Usuario;
import Vista.Maquilas.Dialogo_Maquilas;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Controlador_Dialogo_Maquilas implements ActionListener{
private Vista_Principal    vista;
    private Connection  conexion_Database;
    private Usuario            usuario;
    private String                rol;
    private Maquila maquila;
    private String actividad;
    Dialogo_Maquilas dialogo_Maquilas;
    private TableRowSorter                  TRSFiltro;
    private DefaultTableModel               modelo_Tabla_Maquilas;
    private String             id_Maquila;
    private boolean bandera = false;
    
    public Controlador_Dialogo_Maquilas(Vista_Principal vista, Connection conexion_Database, Maquila maquila, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.maquila = maquila;
        this.actividad = actividad;
        this.dialogo_Maquilas = new Dialogo_Maquilas(this.vista, true);
        //this.usuario = usuario;
        //this.rol = rol;
       this.dialogo_Maquilas.boton_Guardar.addActionListener(this);
       // this.panel_Maquilas.boton_Modificar.addActionListener(this);
       // this.panel_Maquilas.campo_Buscar.addKeyListener(this);
        //this.panel_Maquilas.boton_Buscar.addActionListener(this);
        //this.panel_Maquilas.tabla_Maquilas.addMouseListener(this);
        //this.panel_Maquilas.boton_Eliminar.addActionListener(this);
        //this.panel_Maquilas.boton_Cerrar_Sesion.addActionListener(this);
        //this.panel_Maquilas.boton_Nuevo_Maquila.addActionListener(this);
       // this.modelo_Tabla_Maquilas = ( DefaultTableModel ) this.panel_Maquilas.tabla_Maquilas.getModel();
    }

    public boolean iniciar() {
        this.tipo_Actividad();
        this.dialogo_Maquilas.setVisible(true);
        //this.cargar_Maquilas();
        //this.vista.Panel_Contenedor.add(this.panel_Maquilas);
        //this.panel_Maquilas.setVisible(true);
        //this.vista.Panel_Contenedor.validate();
        //this.set_Usuario();
        return this.bandera;
    }
    
    public void tipo_Actividad() {
         if (this.actividad.equals("Modificar")) {
            //this.dialogo_Registrar_Cliente.cargar_Datos_Cliente(this.cliente);
      this.dialogo_Maquilas.setCampos(maquila.getMaquila(), maquila.getRUC(), maquila.getDireccion(), maquila.getTelefono(), maquila.getServicio());
      this.dialogo_Maquilas.campos_Busqueda();
        
         }
    }
         


    
    
    public String numero_Maquila() {
        String numero = new DAO_Maquila_Implementacion(this.conexion_Database).consultar_Numero_Maquila();
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
        return format.format(Integer.valueOf(numero) + 1);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Maquilas.boton_Guardar) {
            if(this.dialogo_Maquilas.etiquetas()){
            if (this.actividad.equals("Registrar")) {
                try {
                    if (new DAO_Maquila_Implementacion(this.conexion_Database).crear(new Maquila(this.numero_Maquila(), this.dialogo_Maquilas.combo_Maquila.getText(), this.dialogo_Maquilas.campo_RUC.getText(), this.dialogo_Maquilas.campo_Direccion.getText(), this.dialogo_Maquilas.campo_Telefono.getText(), this.dialogo_Maquilas.caja_Servicios.getText()))) {
                        //this.panel_Maquilas.limpiar_Campos();
                        //this.panel_Maquilas.botones(true, true, false, false, true);
                        this.bandera = true;
                        this.dialogo_Maquilas.dispose();
                        JOptionPane.showMessageDialog(null, "Maquila registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.dialogo_Maquilas.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }
            
            if (this.dialogo_Maquilas.etiquetas()) {
if (this.actividad.equals("Modificar")) {
                try {
                    if (new DAO_Maquila_Implementacion(this.conexion_Database).editar(new Maquila(this.maquila.getId_Maquila(), this.dialogo_Maquilas.combo_Maquila.getText(), this.dialogo_Maquilas.campo_RUC.getText(), this.dialogo_Maquilas.campo_Direccion.getText(), this.dialogo_Maquilas.campo_Telefono.getText(), this.dialogo_Maquilas.caja_Servicios.getText())) > 0) {
                        //this.panel_Maquilas.limpiar_Campos();
                        //this.panel_Maquilas.limpiar_Etiquetas_Campos();
                        //this.panel_Maquilas.botones(true, true, false, false, true);
                        //this.id_Maquila = null;
                        this.bandera = true;
                        this.dialogo_Maquilas.dispose();
                        JOptionPane.showMessageDialog(null, "Maquila actualizada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) { 
                    this.dialogo_Maquilas.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }
        }
    }
}


/**
 *
 * @author David
 */
