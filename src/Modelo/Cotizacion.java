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
public class Cotizacion {
    private String    no_Cotizacion;
    private String    fecha;
    private String    tipo_Pago;
    private double    v_Subtotal;
    private double    IVA;
    private double    valor_Total;
    private String    cliente;
    private String    cantidad;
    private String    codigo;
    private String    descripcion;
    private String    v_Unitario;
    private String    v_Total;
    private String    emisor;

    public Cotizacion( String no_Cotizacion, String fecha, String tipo_Pago, double v_Subtotal, double IVA, double valor_Total, String emisor, String cliente, String cantidad, String codigo, String descripcion, String v_Unitario, String v_Total ) {
        this.no_Cotizacion = no_Cotizacion;
        this.fecha = fecha;
        this.tipo_Pago = tipo_Pago;
        this.v_Subtotal = v_Subtotal;
        this.IVA = IVA;
        this.valor_Total = valor_Total;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.v_Unitario = v_Unitario;
        this.v_Total = v_Total;
        this.emisor = emisor;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public Cotizacion() {
    }

    public String getNo_Cotizacion() {
        return no_Cotizacion;
    }

    public void setNo_Cotizacion(String no_Cotizacion) {
        this.no_Cotizacion = no_Cotizacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_Pago() {
        return tipo_Pago;
    }

    public void setTipo_Pago(String tipo_Pago) {
        this.tipo_Pago = tipo_Pago;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
