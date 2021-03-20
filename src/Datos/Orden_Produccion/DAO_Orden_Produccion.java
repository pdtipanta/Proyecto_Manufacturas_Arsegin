/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Orden_Produccion;

import Datos.Creador;
import Modelo.Orden_Produccion;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Orden_Produccion extends Creador<Orden_Produccion>{
    public String consultar_Numero_Orden();
    public ArrayList<Orden_Produccion> consultar_Ordenes_Produccion_Fechas(String valor);
}
