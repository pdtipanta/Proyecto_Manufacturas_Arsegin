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
public class Orden_Produccion {
    private String numero_Orden;//
    private String fecha;//
    private double v_Pagar;//
    private String estado;//
    private String observaciones;//
    private String maquila;//
    private String cantidad;//
    private String descripcion;//
    private String v_Unitario;
    private String v_Total;

    public Orden_Produccion( String numero_Orden, String fecha, double v_Pagar, String estado, String observaciones, String maquila, String cantidad, String descripcion, String v_Unitario, String v_Total) {
        this.numero_Orden = numero_Orden;
        this.fecha = fecha;
        this.v_Pagar = v_Pagar;
        this.estado = estado;
        this.observaciones = observaciones;
        this.maquila = maquila;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.v_Unitario = v_Unitario;
        this.v_Total = v_Total;
    }

    public Orden_Produccion() {
    }
    
    public String getNumero_Orden() {
        return numero_Orden;
    }

    public void setNumero_Orden(String numero_Orden) {
        this.numero_Orden = numero_Orden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getV_Pagar() {
        return v_Pagar;
    }

    public void setV_Pagar(double v_Pagar) {
        this.v_Pagar = v_Pagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMaquila() {
        return maquila;
    }

    public void setMaquila(String maquila) {
        this.maquila = maquila;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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
