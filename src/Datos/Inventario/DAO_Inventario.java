/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Inventario;

import Datos.Creador;
import Modelo.Inventario;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Inventario extends Creador<Inventario> {
    public ArrayList<Inventario> consultar_Inventario_Descripcion(String productos);
}
