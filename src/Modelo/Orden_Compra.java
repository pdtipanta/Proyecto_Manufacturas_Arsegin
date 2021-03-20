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
public class Orden_Compra {
    private String no_Orden;
    private String fecha;//
    private double v_Subtotal;//
    private double IVA;//
    private double valor_Total;//
    private String tipo_Pago;
    private String estado;
    private String solicitante;//
    private String proveedor; //  
    private String cantidad;//
    private String codigo;//
    private String descripcion;//
    private String v_Unitario;//
    private String v_Total;//


    public Orden_Compra(String no_Orden, String fecha, double v_Subtotal, double IVA, double valor_Total, String estado, String tipo_Pago, String solicitante, String proveedor, String cantidad, String codigo, String descripcion, String v_Unitario, String v_Total) {
        this.no_Orden = no_Orden;
        this.fecha = fecha;
        this.v_Subtotal = v_Subtotal;
        this.IVA = IVA;
        this.valor_Total = valor_Total;
        this.tipo_Pago = tipo_Pago;
        this.estado = estado;
        this.solicitante = solicitante;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.v_Unitario = v_Unitario;
        this.v_Total = v_Total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Orden_Compra() {
    }

    public String getNo_Orden() {
        return no_Orden;
    }

    public void setNo_Orden(String no_Orden) {
        this.no_Orden = no_Orden;
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

    public String getTipo_Pago() {
        return tipo_Pago;
    }

    public void setTipo_Pago(String tipo_Pago) {
        this.tipo_Pago = tipo_Pago;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    
}
