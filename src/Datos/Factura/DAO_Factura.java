/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Factura;

import Datos.Creador;
import Modelo.Factura;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Factura extends Creador<Factura>{
    public ArrayList<Factura> consultar_Facturas_Adeudadas( String cliente, String tipo, String id );
    public String consultar_Numero_Factura();
    public ArrayList<Factura> consultar_Facturas_Fechas(String valor);
}
