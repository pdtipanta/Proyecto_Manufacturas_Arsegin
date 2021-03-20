/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Cliente;

import Modelo.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Cliente_Implementacion implements DAO_Clientes {
    private Cliente modelo_Cliente;
    private Connection conexion;
    
    public DAO_Cliente_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }

    @Override
    public boolean crear(Cliente clase) throws SQLException {
        boolean bandera = true;
        try {
            CallableStatement procedimiento_Crear_Cliente = this.conexion.prepareCall("{CALL crear_Cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Crear_Cliente.setString(1, clase.getCodigo_Cliente());
            procedimiento_Crear_Cliente.setString(2, clase.getCliente());
            procedimiento_Crear_Cliente.setString(3, clase.getDireccion());
            procedimiento_Crear_Cliente.setString(4, clase.getCiudad());
            procedimiento_Crear_Cliente.setString(5, clase.getTelefono());
            procedimiento_Crear_Cliente.setString(6, clase.getCelular());
            procedimiento_Crear_Cliente.setString(7, clase.getRUC());
            procedimiento_Crear_Cliente.setString(8, clase.getCorreo());
            procedimiento_Crear_Cliente.setString(9, clase.getPersona_Contacto());
            procedimiento_Crear_Cliente.setString(10, clase.getEmpleado());
            procedimiento_Crear_Cliente.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e2) {
            bandera = false;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int editar(Cliente clase) throws SQLException{
        int bandera = 0;
        try {
            CallableStatement procedimiento_Editar_Cliente = this.conexion.prepareCall("{CALL editar_Clientes(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Editar_Cliente.setString(1, clase.getCodigo_Cliente());
            procedimiento_Editar_Cliente.setString(2, clase.getCliente());
            procedimiento_Editar_Cliente.setString(3, clase.getDireccion());
            procedimiento_Editar_Cliente.setString(4, clase.getCiudad());
            procedimiento_Editar_Cliente.setString(5, clase.getTelefono());
            procedimiento_Editar_Cliente.setString(6, clase.getCelular());
            procedimiento_Editar_Cliente.setString(7, clase.getRUC());
            procedimiento_Editar_Cliente.setString(8, clase.getCorreo());
            procedimiento_Editar_Cliente.setString(9, clase.getPersona_Contacto());
            procedimiento_Editar_Cliente.setString(10, clase.getEmpleado());
            bandera = procedimiento_Editar_Cliente.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int eliminar(String valor) throws SQLException {
        int bandera = 0;
        try {
            CallableStatement procedimiento_eliminarCliente = this.conexion.prepareCall("{CALL eliminar_Clientes(?)}");
            procedimiento_eliminarCliente.setString(1, valor);
            bandera = procedimiento_eliminarCliente.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public ArrayList<Cliente> consultar(Object valor) {
        ArrayList<Cliente> cliente = new ArrayList<Cliente>();
        
        String cadena = String.valueOf(valor);
        String valores[] =  cadena.split(";");

        try {
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall("{CALL consulta_Clientes(?, ?)}");
            procedimiento_Usuario.setObject(1, valores[0]);
            procedimiento_Usuario.setObject(2, valores[1]);
            ResultSet consulta = procedimiento_Usuario.executeQuery();

            while (consulta.next()) {
                modelo_Cliente = new Cliente(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getString(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(10));
                cliente.add(modelo_Cliente);
            }
            consulta.close();
        } catch (SQLException e1) {
        }finally{}
        return cliente;
    }

    @Override
    public String[] consultar_Lista_Ciudades(String valor) {
        ArrayList<String> ciudades = new ArrayList<String>();
        String[] arreglo_Ciudades = null;

        try {
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall("{CALL consulta_Ciudades(?)}");
            procedimiento_Usuario.setString(1, valor);
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            ciudades.add("Seleccione......");
            ciudades.add("Todos");

            while (consulta.next()) {
                ciudades.add(consulta.getString(1));
            }

            arreglo_Ciudades = new String[ciudades.size()];
            arreglo_Ciudades = ciudades.toArray(arreglo_Ciudades);

            consulta.close();
        } catch (SQLException e1) {
        } finally {
        }
        return arreglo_Ciudades;
    }
    
    

    @Override
    public String consultar_Numero_Cliente() {
        String numero_Cliente = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Cliente = this.conexion.prepareCall( "{CALL numero_Codigo_Cliente()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Cliente.executeQuery();
            while(consulta.next() ){
                numero_Cliente = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Cliente;
    }

    @Override
    public ArrayList<Cliente> consultar_Reporte_Cliente(String valor, String usuario) {
        ArrayList<Cliente> cliente = new ArrayList<Cliente>();

        try {
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall("{CALL consulta_Reporte_Cliente(?, ?)}");
            procedimiento_Usuario.setObject(1, valor);
            procedimiento_Usuario.setObject(2, usuario);
            ResultSet consulta = procedimiento_Usuario.executeQuery();

            while (consulta.next()) {
                modelo_Cliente = new Cliente(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getString(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(10));
                cliente.add(modelo_Cliente);
            }
            consulta.close();
        } catch (SQLException e1) {
        }finally{}
        return cliente;
    }
}
