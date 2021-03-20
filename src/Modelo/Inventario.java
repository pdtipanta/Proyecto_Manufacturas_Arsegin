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
public class Inventario {
    private String    codigo;
    private String    descripcion;
    private   int     cantidad_Disponible;
    private double    precio_Compra;
    private double    precio_Venta;
    private String    proveedor;

    public Inventario( String codigo, String descripcion, int cantidad_Disponible, double precio_Compra, double precio_Venta, String proveedor ) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad_Disponible = cantidad_Disponible;
        this.precio_Compra = precio_Compra;
        this.precio_Venta = precio_Venta;
        this.proveedor = proveedor;
    }

    public Inventario(String codigo, String descripcion, int cantidad_Disponible, double precio_Compra, double precio_Venta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad_Disponible = cantidad_Disponible;
        this.precio_Compra = precio_Compra;
        this.precio_Venta = precio_Venta;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio_Venta() {
        return precio_Venta;
    }

    public void setPrecio_Venta(double precio_Venta) {
        this.precio_Venta = precio_Venta;
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
