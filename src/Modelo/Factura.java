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
public class Factura {
    private String no_Factura;
    private String fecha;
    private double v_Subtotal;
    private double IVA;
    private double valor_Total;
    private String estado;
    private String cliente;   
    private String cantidad;
    private String codigo;
    private String descripcion;
    private String v_Unitario;
    private String v_Total;
    private String vendedor;
    private String observaciones;

    public Factura(String no_Factura, String fecha, double v_Subtotal, double IVA, double valor_Total, String estado, String cliente, String cantidad, String codigo, String descripcion, String v_Unitario, String v_Total, String vendedor, String observaciones) {
        this.no_Factura = no_Factura;
        this.fecha = fecha;
        this.v_Subtotal = v_Subtotal;
        this.IVA = IVA;
        this.valor_Total = valor_Total;
        this.estado = estado;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.v_Unitario = v_Unitario;
        this.v_Total = v_Total;
        this.vendedor = vendedor;
        this.observaciones = observaciones;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Factura() {
    }
/*
    public Factura( String cantidad, String descripcion, String v_Unitario, String v_Total ) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.v_Unitario = v_Unitario;
        this.v_Total = v_Total;
    }*/

    public String getNo_Factura() {
        return no_Factura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getV_Subtotal() {
        return v_Subtotal;
    }

    public void setV_Subtotal(double v_Subtotal) {
        this.v_Subtotal = v_Subtotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public String getV_Unitario() {
        return v_Unitario;
    }

    public void setV_Unitario(String v_Unitario) {
        this.v_Unitario = v_Unitario;
    }

    public String getV_Total() {
        return v_Total;
    }

    public void setV_Total(String v_Total) {
        this.v_Total = v_Total;
    }

    public void setNo_Factura(String no_Factura) {
        this.no_Factura = no_Factura;
    }
}
