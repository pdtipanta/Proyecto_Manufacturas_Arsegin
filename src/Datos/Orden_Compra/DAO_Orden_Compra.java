/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Orden_Compra;

import Datos.Creador;
import Modelo.Orden_Compra;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Orden_Compra extends Creador<Orden_Compra>{
    public String consultar_Numero_Orden();
    public int editar_Factura_Orden_Compra( String no_Orden )throws SQLException;
    public ArrayList<Orden_Compra> consultar_Compras_Vacias( String proveedor );
    public ArrayList<Orden_Compra> consultar_Compras_Ingresadas( String proveedor );
    public ArrayList<Orden_Compra> consultar_Ordenes_Fechas(String valor);
}
