/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Compras;

import Datos.Creador;
import Modelo.Compras;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Compras extends Creador<Compras> {
    public ArrayList<Compras> consultar_Pagos_Adeudados( String proveedor, String estado );
    public String consultar_Numero_Compra();
}
