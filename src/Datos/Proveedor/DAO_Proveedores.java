/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Proveedor;

import Datos.Creador;
import Modelo.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Proveedores extends Creador<Proveedor> {
    public ArrayList<Proveedor> consulta_Proveedor_Producto(String productos);
    public String consultar_Numero_Proveedor();
}
