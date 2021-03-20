/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Controlador.Numeracion_Documentos;
import Datos.Orden_Produccion.DAO_Orden_Produccion_Implementacion;
import Modelo.Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Dialogo_Orden_Produccion;
import Vista.Maquilas.Orden_De_Produccion.Panel_Orden_De_Produccion;
import Vista.Vista_Principal;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Orden_Produccion {
    private final Vista_Principal                     vista;
    private final Connection                          conexion;
    private final Dialogo_Orden_Produccion     dialogo_Orden_Produccion;
    private TableRowSorter                            TRSFiltro;
    private DefaultTableModel                         modelo_Tabla_Orden_Produccion;
    //private Panel_Orden_De_Produccion panel_Orden_De_Produccion;
    private ArrayList<Orden_Produccion>               orden_Produccion = new ArrayList<Orden_Produccion>();

    public Controlador_Dialogo_Orden_Produccion(Vista_Principal vista, Connection conexion) {
        this.vista = vista;
        this.conexion = conexion;
        this.dialogo_Orden_Produccion = new Dialogo_Orden_Produccion(this.vista, true);
        //this.dialogo_Buscar_Orden_Produccion.campo_Busqueda.addKeyListener(this);
        //this.dialogo_Buscar_Orden_Produccion.tabla_Consulta_Orden_Produccion.addMouseListener(this);
        //this.dialogo_Buscar_Orden_Produccion.combo_Opciones.addActionListener(this);
        //this.dialogo_Buscar_Orden_Produccion.boton_Fecha.addActionListener(this);
        this.modelo_Tabla_Orden_Produccion = (DefaultTableModel) this.dialogo_Orden_Produccion.tabla_Productos_Maquila.getModel();
    }
    
    public void iniciar() {
        this.numero_Orden_Produccion();
        this.dialogo_Orden_Produccion.setVisible(true);
    }
    
    public void numero_Orden_Produccion() {
        this.dialogo_Orden_Produccion.etiqueta_No_Orden.setText(new Numeracion_Documentos().convertir_Numero(new DAO_Orden_Produccion_Implementacion(this.conexion).consultar_Numero_Orden()));
    }
}
