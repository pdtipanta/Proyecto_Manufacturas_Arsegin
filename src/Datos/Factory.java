package Datos;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public interface Factory {
    public Creador crear();
    public Creador consultar();
    public Creador editar();
    public Creador eliminar();
}
