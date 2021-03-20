/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Cliente;

import Datos.Creador;
import Modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface DAO_Clientes extends Creador<Cliente> {
    public String[] consultar_Lista_Ciudades(String valor);
    public ArrayList<Cliente> consultar_Reporte_Cliente(String valor, String usuario);
    public String consultar_Numero_Cliente();
}
