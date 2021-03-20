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
public class Compras {
    private String id_Compras;
    private String no_Factura;
    private String proveedor;
    private String fecha;
    private byte factura[];
    private Double valor;
    private String estado;

    public Compras(String id_Compras, String no_Factura, String proveedor, String fecha, byte[] factura, Double valor, String estado) {
        this.id_Compras = id_Compras;
        this.no_Factura = no_Factura;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.factura = factura;
        this.valor = valor;
        this.estado = estado;
    }

    
    public String getId_Compras() {
        return id_Compras;
    }

    public void setId_Compras(String id_Compras) {
        this.id_Compras = id_Compras;
    }

    public Compras() {
    }

    public String getNo_Factura() {
        return no_Factura;
    }

    public void setNo_Factura(String no_Factura) {
        this.no_Factura = no_Factura;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public byte[] getFactura() {
        return factura;
    }

    public void setFactura(byte[] factura) {
        this.factura = factura;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
