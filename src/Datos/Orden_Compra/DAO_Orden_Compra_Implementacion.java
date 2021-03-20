/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Orden_Compra;

import Modelo.Orden_Compra;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DAO_Orden_Compra_Implementacion implements DAO_Orden_Compra{
    private Orden_Compra modelo_Orden_Compra;
    
    private Connection conexion;
    
    public DAO_Orden_Compra_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }

    @Override
    public boolean crear(Orden_Compra orden) throws SQLException {
        boolean bandera = true;
        try {
            CallableStatement procedimiento_Crear_Orden = this.conexion.prepareCall("{CALL crear_Orden_Compra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )}");
            procedimiento_Crear_Orden.setString(1, orden.getNo_Orden());
            procedimiento_Crear_Orden.setString(2, orden.getFecha());
            procedimiento_Crear_Orden.setDouble(3, orden.getV_Subtotal());
            procedimiento_Crear_Orden.setDouble(4, orden.getIVA());
            procedimiento_Crear_Orden.setDouble(5, orden.getValor_Total());
            procedimiento_Crear_Orden.setString(6, orden.getEstado());
            procedimiento_Crear_Orden.setString(7, orden.getTipo_Pago());
            procedimiento_Crear_Orden.setString(8, orden.getSolicitante());
            procedimiento_Crear_Orden.setString(9, orden.getProveedor());
            procedimiento_Crear_Orden.setString(10, orden.getCantidad());
            procedimiento_Crear_Orden.setString(11, orden.getCodigo());
            procedimiento_Crear_Orden.setString(12, orden.getDescripcion());
            procedimiento_Crear_Orden.setString(13, orden.getV_Unitario());
            procedimiento_Crear_Orden.setString(14, orden.getV_Total());
            procedimiento_Crear_Orden.executeUpdate();
            this.conexion.commit();
        } catch (SQLException e1) {
            bandera = false;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public ArrayList<Orden_Compra> consultar(Object proveedor) {
        ArrayList<Orden_Compra> lista_Ordenes = new ArrayList<Orden_Compra>();

        String cadena = String.valueOf(proveedor);
        String valor[] = cadena.split(";");

        try {
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall("{CALL consultar_Orden_Compra(?, ?)}");
            procedimiento_consulta_Orden.setObject(1, valor[0]);
            procedimiento_consulta_Orden.setObject(2, valor[1]);

            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();

            while (consulta.next()) {
                modelo_Orden_Compra = new Orden_Compra(consulta.getString(1), consulta.getString(2), consulta.getDouble(3), consulta.getDouble(4), consulta.getDouble(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(12), consulta.getString(13), consulta.getString(14), consulta.getString(15), consulta.getString(16));
                lista_Ordenes.add(modelo_Orden_Compra);
            }
            consulta.close();
        } catch (SQLException ex) {
        } finally {
        }
        return lista_Ordenes;
    }

    @Override
    public int editar(Orden_Compra orden) throws SQLException {
        int bandera = 0;

        try {
            CallableStatement procedimiento_Editar_Orden = this.conexion.prepareCall("{CALL editar_Orden_Compra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            procedimiento_Editar_Orden.setString(1, orden.getNo_Orden());
            procedimiento_Editar_Orden.setString(2, orden.getFecha());
            procedimiento_Editar_Orden.setDouble(3, orden.getV_Subtotal());
            procedimiento_Editar_Orden.setDouble(4, orden.getIVA());
            procedimiento_Editar_Orden.setDouble(5, orden.getValor_Total());
            procedimiento_Editar_Orden.setString(6, orden.getEstado());
            procedimiento_Editar_Orden.setString(7, orden.getTipo_Pago());
            procedimiento_Editar_Orden.setString(8, orden.getSolicitante());
            procedimiento_Editar_Orden.setString(9, orden.getProveedor());
            procedimiento_Editar_Orden.setString(10, orden.getCantidad());
            procedimiento_Editar_Orden.setString(11, orden.getCodigo());
            procedimiento_Editar_Orden.setString(12, orden.getDescripcion());
            procedimiento_Editar_Orden.setString(13, orden.getV_Unitario());
            procedimiento_Editar_Orden.setString(14, orden.getV_Total());
            bandera = procedimiento_Editar_Orden.executeUpdate();
            this.conexion.commit();
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int eliminar(String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consultar_Numero_Orden() {
        String numero_Factura = "";
        try {
            CallableStatement procedimiento_Consultar_Numero_Orden = this.conexion.prepareCall("{CALL numero_Orden_Compra()}");
            ResultSet consulta = procedimiento_Consultar_Numero_Orden.executeQuery();
            while (consulta.next()) {
                numero_Factura = consulta.getString(1);
            }
            consulta.close();
        } catch (SQLException e1) {
        } finally {
        }
        return numero_Factura;
    }

    @Override
    public int editar_Factura_Orden_Compra(String no_Orden) throws SQLException {
        int bandera = 0;

        try {
            CallableStatement procedimiento_Editar_Orden = this.conexion.prepareCall("{CALL editar_Factura_Orden_Compra(?)}");
            procedimiento_Editar_Orden.setString(1, no_Orden);
            bandera = procedimiento_Editar_Orden.executeUpdate();
            this.conexion.commit();
        } catch (SQLException e1) {
            bandera = 0;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public ArrayList<Orden_Compra> consultar_Compras_Vacias(String proveedor) {
        ArrayList<Orden_Compra> lista_Ordenes = new ArrayList<Orden_Compra>();

        try {
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall("{CALL consultar_Orden_Compras(?)}");
            procedimiento_consulta_Orden.setString(1, proveedor);
            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();

            while (consulta.next()) {
                modelo_Orden_Compra = new Orden_Compra(consulta.getString(1), consulta.getString(2), consulta.getDouble(3), consulta.getDouble(4), consulta.getDouble(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(12), consulta.getString(13), consulta.getString(14), consulta.getString(15), consulta.getString(16));
                lista_Ordenes.add(modelo_Orden_Compra);
            }
            consulta.close();
        } catch (SQLException ex) {System.out.println(ex);
        } finally {
        }
        return lista_Ordenes;
    }

    @Override
    public ArrayList<Orden_Compra> consultar_Compras_Ingresadas(String proveedor) {
        ArrayList<Orden_Compra> lista_Ordenes = new ArrayList<Orden_Compra>();

        try {
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall("{CALL consultar_Orden_Compra_Ingresadas(?)}");
            procedimiento_consulta_Orden.setString(1, proveedor);
            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();

            while (consulta.next()) {
                modelo_Orden_Compra = new Orden_Compra(consulta.getString(1), consulta.getString(2), consulta.getDouble(3), consulta.getDouble(4), consulta.getDouble(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(12), consulta.getString(13), consulta.getString(14), consulta.getString(15), consulta.getString(16));
                lista_Ordenes.add(modelo_Orden_Compra);
            }
            consulta.close();
        } catch (SQLException ex) {
        } finally {
        }
        return lista_Ordenes;
    }
    
    public ArrayList<Orden_Compra> consultar_Ordenes_Fechas(String valor){
        ArrayList<Orden_Compra> lista_Ordenes = new ArrayList<Orden_Compra>();

        String cadena = String.valueOf(valor);
        String valores[] = cadena.split(";");

        try{
            CallableStatement procedimiento_consulta_Orden = this.conexion.prepareCall( "{CALL consultar_Ordenes_Fechas(?,?,?,?)}" );
            procedimiento_consulta_Orden.setString( 1, valores[0] );
            procedimiento_consulta_Orden.setString( 2, valores[1] );
            procedimiento_consulta_Orden.setString( 3, valores[2] );
            procedimiento_consulta_Orden.setString( 4, valores[3] );
            ResultSet consulta = procedimiento_consulta_Orden.executeQuery();
            
            while( consulta.next() ){
                modelo_Orden_Compra = new Orden_Compra(consulta.getString(1), consulta.getString(2), consulta.getDouble(3), consulta.getDouble(4), consulta.getDouble(5), consulta.getString(6), consulta.getString(7), consulta.getString(8), consulta.getString(9), consulta.getString(12), consulta.getString(13), consulta.getString(14), consulta.getString(15), consulta.getString(16));
                lista_Ordenes.add(modelo_Orden_Compra);
            }
            consulta.close();
        } catch ( SQLException ex ) { 
        }finally{}
        return lista_Ordenes;
    }
}
