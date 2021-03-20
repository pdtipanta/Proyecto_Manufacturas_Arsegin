/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Maquila;

import Datos.Creador;
import Modelo.Maquila;

/**
 *
 * @author David
 */
public interface DAO_Maquila extends Creador<Maquila> {
    public String consultar_Numero_Maquila();
}
