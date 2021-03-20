/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.Proveedor;

import Modelo.Proveedor;
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
public class DAO_Proveedor_Implementacion implements DAO_Proveedores {
    private Proveedor modelo_Proveedor;
    private Connection conexion;
    
    public DAO_Proveedor_Implementacion(Connection conexion_Database) {
        this.conexion = conexion_Database;
    }
    
    @Override
    public boolean crear( Proveedor clase ) throws SQLException{
        boolean bandera = true;
        try{
            CallableStatement procedimiento_Crear_Proveedor = this.conexion.prepareCall( "{CALL crear_Proveedor(?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Crear_Proveedor.setString( 1, clase.getId_Proveedor() );
            procedimiento_Crear_Proveedor.setString( 2, clase.getProveedor() );
            procedimiento_Crear_Proveedor.setString( 3, clase.getRUC() );
            procedimiento_Crear_Proveedor.setString( 4, clase.getDireccion() );
            procedimiento_Crear_Proveedor.setString( 5, clase.getCorreo() );
            procedimiento_Crear_Proveedor.setString( 6, clase.getTelefono() );
            procedimiento_Crear_Proveedor.setString( 7, clase.getProductos() );
            procedimiento_Crear_Proveedor.executeUpdate();
            this.conexion.commit();
        } catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        } catch (SQLException e1) {
            bandera = false;
            this.conexion.rollback();
        } finally {
        }
        return bandera;
    }

    @Override
    public int editar( Proveedor clase ) throws SQLException{
        int bandera = 0;
        try{
            CallableStatement procedimiento_Editar_Proveedores = this.conexion.prepareCall( "{CALL editar_Proveedor(?, ?, ?, ?, ?, ?, ?)}" );
            procedimiento_Editar_Proveedores.setString( 1, clase.getId_Proveedor() );
            procedimiento_Editar_Proveedores.setString( 2, clase.getProveedor() );
            procedimiento_Editar_Proveedores.setString( 3, clase.getRUC() );
            procedimiento_Editar_Proveedores.setString( 4, clase.getDireccion() );
            procedimiento_Editar_Proveedores.setString( 5, clase.getCorreo() );
            procedimiento_Editar_Proveedores.setString( 6, clase.getTelefono() );
            procedimiento_Editar_Proveedores.setString( 7, clase.getProductos() );
            bandera = procedimiento_Editar_Proveedores.executeUpdate();
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
    public int eliminar( String valor ) throws SQLException{
       int bandera = 0;
        try{
            CallableStatement procedimiento_eliminar_Proveedor  = this.conexion.prepareCall( "{CALL eliminar_Proveedor(?)}" );
            procedimiento_eliminar_Proveedor.setString( 1, valor );
            bandera = procedimiento_eliminar_Proveedor.executeUpdate();
            this.conexion.commit();
        }catch (SQLIntegrityConstraintViolationException e1) {
            this.conexion.rollback();
            throw new SQLIntegrityConstraintViolationException(e1);
        }
        catch( SQLException e1 ){
            bandera = 0;
            this.conexion.rollback();
        }finally{}
        return bandera;
    }

    @Override
    public ArrayList<Proveedor> consultar(Object valor) {
       ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
        
        try{
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall( "{CALL consulta_Proveedor(?)}" );
            procedimiento_Usuario.setObject( 1, valor );
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            
            while( consulta.next() ){
                modelo_Proveedor = new Proveedor( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getString( 3 ), consulta.getString( 4 ), consulta.getString( 5 ), consulta.getString( 6 ), consulta.getString( 7 ) );
                proveedor.add( modelo_Proveedor );
            }
            consulta.close();
        }catch( SQLException e1 ){
        }finally{}
        return proveedor;
    }

    @Override
    public ArrayList<Proveedor> consulta_Proveedor_Producto(String productos) {
        ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
        
        try{
            CallableStatement procedimiento_Usuario = this.conexion.prepareCall( "{CALL consulta_Proveedor_Producto(?)}" );
            procedimiento_Usuario.setString(1, productos);
            ResultSet consulta = procedimiento_Usuario.executeQuery();
            
            while( consulta.next() ){
                modelo_Proveedor = new Proveedor( consulta.getString( 1 ), consulta.getString( 2 ), consulta.getString( 3 ), consulta.getString( 4 ), consulta.getString( 5 ), consulta.getString( 6 ), consulta.getString( 7 ));
                proveedor.add( modelo_Proveedor );
            }
            consulta.close();
        }catch( SQLException e1 ){ 
        }finally{}
        return proveedor;
    }
    
     @Override
    public String consultar_Numero_Proveedor() {
        String numero_Proveedor = "";
        try{
            CallableStatement procedimiento_Consultar_Numero_Proveedor = this.conexion.prepareCall( "{CALL numero_Codigo_Proveedor()}" );
            ResultSet consulta = procedimiento_Consultar_Numero_Proveedor.executeQuery();
            while(consulta.next() ){
                numero_Proveedor = consulta.getString( 1 );
            }
            consulta.close();
        }catch( SQLException e1 ){    
        }finally{}
        return numero_Proveedor;
    }
}
