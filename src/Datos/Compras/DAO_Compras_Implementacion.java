/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Compras;

import Modelo.Compras;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Compras_Implementacion implements DAO_Compras {
    private Compras modelo_Compras;
    private Connection conexion;
    
    public DAO_Compras_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }

    @Override
    public boolean crear(Compras modelo_Compras) throws SQLException {
        boolean bandera = true;

        try {
            CallableStatement procedimiento_Crear_Compra = this.conexion.prepareCall("{CALL crear_Compras( ?, ?, ?, ?, ?, ?, ? )}");
            procedimiento_Crear_Compra.setString(1, modelo_Compras.getId_Compras());
            procedimiento_Crear_Compra.setString(2, modelo_Compras.getNo_Factura());
            procedimiento_Crear_Compra.setString(3, modelo_Compras.getProveedor());
            procedimiento_Crear_Compra.setString(4, modelo_Compras.getFecha());
            procedimiento_Crear_Compra.setBytes(5, modelo_Compras.getFactura());
            procedimiento_Crear_Compra.setDouble(6, modelo_Compras.getValor());
            procedimiento_Crear_Compra.setString(7, modelo_Compras.getEstado());
            procedimiento_Crear_Compra.executeUpdate();
            this.conexion.commit();
        } catch (SQLException e1) {System.out.println(e1);
            bandera = false;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int editar(Compras clase) throws SQLException {
        int bandera = 0;
        try {
            CallableStatement procedimiento_Editar_Compra = this.conexion.prepareCall("{CALL Editar_Compras(?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Editar_Compra.setString(1, clase.getId_Compras());
            procedimiento_Editar_Compra.setString(2, clase.getNo_Factura());
            procedimiento_Editar_Compra.setString(3, clase.getProveedor());
            procedimiento_Editar_Compra.setString(4, clase.getFecha());
            procedimiento_Editar_Compra.setBytes(5, clase.getFactura());
            procedimiento_Editar_Compra.setDouble(6, clase.getValor());
            procedimiento_Editar_Compra.setString(7, clase.getEstado());
            bandera = procedimiento_Editar_Compra.executeUpdate();
            this.conexion.commit();
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int eliminar(String valor) throws SQLException{
        return 0;
    }

    @Override
    public ArrayList<Compras> consultar(Object valor) {
        ArrayList<Compras> Compras = new ArrayList<Compras>();

        try {
            CallableStatement procedimiento_Compra = this.conexion.prepareCall("{CALL consultar_Compras_Ingresadas(?)}");
            procedimiento_Compra.setObject(1, valor);
            ResultSet consulta = procedimiento_Compra.executeQuery();

            while (consulta.next()) {
                modelo_Compras = new Compras(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getBytes(5), consulta.getDouble(6), consulta.getString(7));
                Compras.add(modelo_Compras);
            }
            consulta.close();
        } catch (SQLException e1) {
        }finally{}
        return Compras;
    }

    @Override
    public ArrayList<Compras> consultar_Pagos_Adeudados(String proveedor, String estado) {
        ArrayList<Compras> Compras = new ArrayList<Compras>();

        try {
            CallableStatement procedimiento_Compra = this.conexion.prepareCall("{CALL consultar_Pagos_Adeudados(?, ?)}");
            procedimiento_Compra.setString(1, proveedor);
            procedimiento_Compra.setString(2, estado);
            ResultSet consulta = procedimiento_Compra.executeQuery();

            while (consulta.next()) {
                modelo_Compras = new Compras(consulta.getString(1), consulta.getString(2), consulta.getString(3), consulta.getString(4), consulta.getBytes(5), consulta.getDouble(6), consulta.getString(7));
                Compras.add(modelo_Compras);
            }
            consulta.close();
        } catch (SQLException e1) {
        }finally{}
        return Compras;
    }

    @Override
    public String consultar_Numero_Compra() {
        String numero_Compra = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Compra = this.conexion.prepareCall( "{CALL numero_Compra()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Compra.executeQuery();
            while(consulta.next() ){
                numero_Compra = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Compra;
    }
}
