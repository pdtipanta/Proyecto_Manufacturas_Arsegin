package Controlador.Maquila;
import Modelo.Maquila;
import Modelo.Usuario;
import Vista.Maquilas.Dialogo_Maquilas;
import Vista.Maquilas.Panel_Maquilas;
import Vista.Vista_Principal;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Controlador_Dialogo_Maquilas{
private Vista_Principal    vista;
    private Connection  conexion_Database;
    private Usuario            usuario;
    private String                rol;
    private Maquila maquila;
    private String actividad;
    Dialogo_Maquilas dialogo_Maquilas;
    private TableRowSorter                  TRSFiltro;
    private DefaultTableModel               modelo_Tabla_Maquilas;
    private Panel_Maquilas     panel_Maquilas = new Panel_Maquilas();
    private String             id_Maquila;
    
    public Controlador_Dialogo_Maquilas(Vista_Principal vista, Connection conexion_Database, Maquila maquila, String actividad) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.maquila = maquila;
        this.actividad = actividad;
        this.dialogo_Maquilas = new Dialogo_Maquilas(this.vista, true);
        //this.usuario = usuario;
        //this.rol = rol;
        //this.panel_Maquilas.boton_Guardar.addActionListener(this);
       // this.panel_Maquilas.boton_Modificar.addActionListener(this);
       // this.panel_Maquilas.campo_Buscar.addKeyListener(this);
        //this.panel_Maquilas.boton_Buscar.addActionListener(this);
        //this.panel_Maquilas.tabla_Maquilas.addMouseListener(this);
        //this.panel_Maquilas.boton_Eliminar.addActionListener(this);
        //this.panel_Maquilas.boton_Cerrar_Sesion.addActionListener(this);
        //this.panel_Maquilas.boton_Nuevo_Maquila.addActionListener(this);
       // this.modelo_Tabla_Maquilas = ( DefaultTableModel ) this.panel_Maquilas.tabla_Maquilas.getModel();
    }

    public void iniciar() {
        this.dialogo_Maquilas.setVisible(true);
        //this.cargar_Maquilas();
        //this.vista.Panel_Contenedor.add(this.panel_Maquilas);
        //this.panel_Maquilas.setVisible(true);
        //this.vista.Panel_Contenedor.validate();
        //this.set_Usuario();
    }
}


/**
 *
 * @author David
 */
