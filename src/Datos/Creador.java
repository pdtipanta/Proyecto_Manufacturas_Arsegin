package Datos;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public interface Creador<T> {
   
    public boolean crear( T clase ) throws SQLException;
    public ArrayList<T> consultar(Object valor );
    public int editar( T clase) throws SQLException;
    public int eliminar( String valor ) throws SQLException;
}
