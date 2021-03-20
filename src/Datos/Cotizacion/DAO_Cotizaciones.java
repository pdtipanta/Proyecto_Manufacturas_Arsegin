/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Cotizacion;

import Datos.Creador;
import Modelo.Cotizacion;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Cotizaciones extends Creador<Cotizacion>{
    public String consultar_Numero_Cotizacion();
    public ArrayList<Cotizacion> consultar_Cotizacion_Fechas(String valor);
}
