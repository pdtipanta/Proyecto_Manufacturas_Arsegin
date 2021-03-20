/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author David
 */
public class Producto {
    private String    codigo;
    private String    descripcion;
    private   int     cantidad_Disponible;
    private double    precio_Compra;
    private String    proveedor;

    public Producto( String codigo, String descripcion, int cantidad_Disponible, double precio_Compra, String proveedor ) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad_Disponible = cantidad_Disponible;
        this.precio_Compra = precio_Compra;
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_Disponible() {
        return cantidad_Disponible;
    }

    public void setCantidad_Disponible(int cantidad_Disponible) {
        this.cantidad_Disponible = cantidad_Disponible;
    }

    public double getPrecio_Compra() {
        return precio_Compra;
    }

    public void setPrecio_Compra(double precio_Compra) {
        this.precio_Compra = precio_Compra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
