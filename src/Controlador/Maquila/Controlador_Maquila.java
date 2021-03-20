/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Maquila;

import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Datos.Maquila.DAO_Maquila_Implementacion;
import Modelo.Maquila;
import Modelo.Usuario;
import Vista.Maquilas.Panel_Maquilas;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Maquila implements ActionListener, KeyListener, MouseListener{
    private Vista_Principal    vista;
    private Connection  conexion_Database;
    private Usuario            usuario;
    private String                rol;
    private Maquila maquilas;
    private TableRowSorter                  TRSFiltro;
    private DefaultTableModel               modelo_Tabla_Maquilas;
    private Panel_Maquilas     panel_Maquilas = new Panel_Maquilas();
    private String             id_Maquila;
    
    public Controlador_Maquila(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
        //this.panel_Maquilas.boton_Guardar.addActionListener(this);
        this.panel_Maquilas.boton_Modificar.addActionListener(this);
        this.panel_Maquilas.campo_Buscar.addKeyListener(this);
        //this.panel_Maquilas.boton_Buscar.addActionListener(this);
        this.panel_Maquilas.tabla_Maquilas.addMouseListener(this);
        this.panel_Maquilas.boton_Eliminar.addActionListener(this);
        this.panel_Maquilas.boton_Cerrar_Sesion.addActionListener(this);
        this.panel_Maquilas.boton_Nuevo_Maquila.addActionListener(this);
        this.modelo_Tabla_Maquilas = ( DefaultTableModel ) this.panel_Maquilas.tabla_Maquilas.getModel();
    }

    public void iniciar() {
        this.cargar_Maquilas();
        this.vista.Panel_Contenedor.add(this.panel_Maquilas);
        this.panel_Maquilas.setVisible(true);
        this.vista.Panel_Contenedor.validate();
        this.set_Usuario();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
/*
        if (ae.getSource() == this.panel_Maquilas.boton_Guardar) {
            if (this.panel_Maquilas.etiquetas(true)) {
                numero_Maquila();
                try {
                    if (new DAO_Maquila_Implementacion(this.conexion_Database).crear(new Maquila(this.numero_Maquila(), this.panel_Maquilas.combo_Maquila.getText(), this.panel_Maquilas.campo_RUC.getText(), this.panel_Maquilas.campo_Direccion.getText(), this.panel_Maquilas.campo_Telefono.getText(), this.panel_Maquilas.caja_Servicios.getText()))) {
                        this.panel_Maquilas.limpiar_Campos();
                        this.panel_Maquilas.botones(true, true, false, false, true);
                        JOptionPane.showMessageDialog(null, "Maquila registrada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Maquilas.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }*/
/*
        if (ae.getSource() == this.panel_Maquilas.boton_Buscar) {
            ArrayList<Maquila> maquila = new Controlador_Dialogo_Buscar_Maquila(this.vista, this.conexion_Database).iniciar();

            if (maquila.size() == 1) {
                this.panel_Maquilas.setCampos(maquila.get(0).getMaquila(), maquila.get(0).getRUC(), maquila.get(0).getDireccion(), maquila.get(0).getTelefono(), maquila.get(0).getServicio());
                this.panel_Maquilas.campos_Busqueda();
                this.panel_Maquilas.etiquetas(true);
                this.id_Maquila = maquila.get(0).getId_Maquila();
            }
        }*/
/*
        if (ae.getSource() == this.panel_Maquilas.boton_Modificar) {
            if (this.panel_Maquilas.etiquetas(true)) {

                try {
                    if (new DAO_Maquila_Implementacion(this.conexion_Database).editar(new Maquila(this.id_Maquila, this.panel_Maquilas.combo_Maquila.getText(), this.panel_Maquilas.campo_RUC.getText(), this.panel_Maquilas.campo_Direccion.getText(), this.panel_Maquilas.campo_Telefono.getText(), this.panel_Maquilas.caja_Servicios.getText())) > 0) {
                        this.panel_Maquilas.limpiar_Campos();
                        this.panel_Maquilas.limpiar_Etiquetas_Campos();
                        this.panel_Maquilas.botones(true, true, false, false, true);
                        this.id_Maquila = null;
                        JOptionPane.showMessageDialog(null, "Maquila actualizada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLIntegrityConstraintViolationException e1) {
                    this.panel_Maquilas.correccion_Campos(e1.getCause().toString().split("'")[1]);
                } catch (SQLException ex) {
                }
            }
        }
*/
   /*     if (ae.getSource() == this.panel_Maquilas.boton_Eliminar) {
            try {
                if (new DAO_Maquila_Implementacion(this.conexion_Database).eliminar(this.id_Maquila) > 0) {
                    this.panel_Maquilas.limpiar_Campos();
                    this.panel_Maquilas.limpiar_Etiquetas_Campos();
                    this.panel_Maquilas.botones(true, true, false, false, true);
                    this.id_Maquila = null;
                    JOptionPane.showMessageDialog(null, "Maquila eliminada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar la maquila, debido a que mantiene documentos pendientes", "Maquilas", JOptionPane.WARNING_MESSAGE);
                this.panel_Maquilas.limpiar_Campos();
            } catch (SQLException ex) {
            }
        }

        if (ae.getSource() == this.panel_Maquilas.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }*/
/*
        if (ae.getSource() == this.panel_Maquilas.boton_Nuevo_Maquila) {
            this.panel_Maquilas.limpiar_Campos();
            this.panel_Maquilas.botones(true, true, false, false, true);
            this.id_Maquila = null;
            this.panel_Maquilas.limpiar_Etiquetas_Campos();
        }*/

if (ae.getSource() == this.panel_Maquilas.boton_Nuevo_Maquila) {
    
    
    if(new Controlador_Dialogo_Maquilas(this.vista, this.conexion_Database, this.maquilas, "Registrar").iniciar()){
        this.panel_Maquilas.boton_Modificar.setEnabled(false);
        this.panel_Maquilas.boton_Eliminar.setEnabled(false);
        this.cargar_Maquilas();
    }
}

if (ae.getSource() == this.panel_Maquilas.boton_Modificar) {
    ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar(this.panel_Maquilas.tabla_Maquilas.getValueAt(this.panel_Maquilas.tabla_Maquilas.getSelectedRow(), 0));
    
    if(maquila.size() == 1){
    if(new Controlador_Dialogo_Maquilas(this.vista, this.conexion_Database, maquila.get(0), "Modificar").iniciar()){
        this.panel_Maquilas.boton_Modificar.setEnabled(false);
        this.panel_Maquilas.boton_Eliminar.setEnabled(false);
        this.cargar_Maquilas();
    }
    }
}
if (ae.getSource() == this.panel_Maquilas.boton_Cerrar_Sesion) {
            vista.Panel_Contenedor.removeAll();
            this.vista.borrar_Menu();
            new Controlador_Panel_Ingreso(this.vista).iniciar();
        }

if (ae.getSource() == this.panel_Maquilas.boton_Eliminar) {
            try {
                if (new DAO_Maquila_Implementacion(this.conexion_Database).eliminar((String)this.panel_Maquilas.tabla_Maquilas.getValueAt(this.panel_Maquilas.tabla_Maquilas.getSelectedRow(), 0)) > 0) {
                    //this.panel_Maquilas.limpiar_Campos();
                    //this.panel_Maquilas.limpiar_Etiquetas_Campos();
                    //this.panel_Maquilas.botones(true, true, false, false, true);
                    //this.id_Maquila = null;
                    //JOptionPane.showMessageDialog(null, "Maquila eliminada", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    this.cargar_Maquilas();
                }
            } catch (SQLIntegrityConstraintViolationException e1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar la maquila, debido a que mantiene documentos pendientes", "Maquilas", JOptionPane.WARNING_MESSAGE);
                //this.panel_Maquilas.limpiar_Campos();
            } catch (SQLException ex) {
            }
        }
    }
/*
    public String numero_Maquila() {
        String numero = new DAO_Maquila_Implementacion(this.conexion_Database).consultar_Numero_Maquila();
        String valor = "";

        if (numero == null) {
            valor = convertirNumero(0);
        } else {
            valor = convertirNumero(Integer.parseInt(numero));
        }
        return valor;
    }*/
/*
    public String convertirNumero(int numero) {
        DecimalFormat format = new DecimalFormat("00000000");
        return format.format(Integer.valueOf(numero) + 1);
    }*/
    
    public void cargar_Maquilas(){
        this.modelo_Tabla_Maquilas.setRowCount(0);
    ArrayList<Maquila> maquila = new DAO_Maquila_Implementacion(this.conexion_Database).consultar("Todos");

            if (maquila.size() > 0) {
                 for (int i = 0; i < maquila.size(); i++) {
                Object[] fila = {maquila.get(i).getId_Maquila(), maquila.get(i).getMaquila(), maquila.get(i).getRUC(), maquila.get(i).getServicio(), maquila.get(i).getDireccion(), maquila.get(i).getTelefono()};
                 this.modelo_Tabla_Maquilas.addRow(fila);
                 
                 }
                
                /*
                this.panel_Maquilas.setCampos(maquila.get(0).getMaquila(), maquila.get(0).getRUC(), maquila.get(0).getDireccion(), maquila.get(0).getTelefono(), maquila.get(0).getServicio());
                this.panel_Maquilas.campos_Busqueda();
                this.panel_Maquilas.etiquetas(true);
                this.id_Maquila = maquila.get(0).getId_Maquila();*/
                
                
            }
    }
            
    public void set_Usuario(){
        this.panel_Maquilas.set_Usuario(this.usuario, this.rol);
    }
    
    public void habilitar_Rol() {
        this.panel_Maquilas.Roles(rol);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (this.panel_Maquilas.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.panel_Maquilas.campo_Buscar.setEditable(false);
        } else {
            this.panel_Maquilas.campo_Buscar.setEditable(true);
            if (ke.getSource() == this.panel_Maquilas.campo_Buscar) {
                this.panel_Maquilas.campo_Buscar.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }});

                TRSFiltro = new TableRowSorter(this.panel_Maquilas.tabla_Maquilas.getModel());
                this.panel_Maquilas.tabla_Maquilas.setRowSorter(TRSFiltro);
            }
        }
    }
    
    public void filtro(){
        if (this.panel_Maquilas.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.panel_Maquilas.combo_Opciones.getSelectedItem() == "Por RUC / CI") {
            filtrar_Tabla(2);
        } else if (this.panel_Maquilas.combo_Opciones.getSelectedItem() == "Por servicio") {
            filtrar_Tabla(3);
        }
    }
    
    public void filtrar_Tabla(int valor){
        seleccion_Tabla(this.panel_Maquilas.tabla_Maquilas.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.panel_Maquilas.campo_Buscar.getText(), valor ));
        if(this.panel_Maquilas.tabla_Maquilas.getRowCount() > 0 ){
            //this.panel_Maquilas.boton_Informe.setEnabled(true);
        }else{
            //this.panel_Inventarios.boton_Informe.setEnabled(false);
        }
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.panel_Maquilas.boton_Modificar.setEnabled(true);
            this.panel_Maquilas.boton_Eliminar.setEnabled(true);
        } else {
            this.panel_Maquilas.boton_Modificar.setEnabled(false);
            this.panel_Maquilas.boton_Eliminar.setEnabled(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(me.getSource() == this.panel_Maquilas.tabla_Maquilas){
            seleccion_Tabla(this.panel_Maquilas.tabla_Maquilas.getSelectedRow());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
