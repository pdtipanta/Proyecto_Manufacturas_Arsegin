/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Proveedor;

import Datos.Creador;
import Modelo.Proveedor;

/**
 *
 * @author David
 */
public interface DAO_Proveedores extends Creador<Proveedor> {

    public String consultar_Numero_Proveedor();
}
