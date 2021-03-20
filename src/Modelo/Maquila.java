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
public class Maquila {
    private String id_Maquila;
    private String maquila;
    private String RUC;
    private String direccion;
    private String telefono;
    private String servicio;

    public Maquila(String id_Maquila, String maquila, String RUC, String direccion, String telefono, String servicio) {
        this.id_Maquila = id_Maquila;
        this.maquila = maquila;
        this.RUC = RUC;
        this.direccion = direccion;
        this.telefono = telefono;
        this.servicio = servicio;
    }

    public String getId_Maquila() {
        return id_Maquila;
    }

    public void setId_Maquila(String id_Maquila) {
        this.id_Maquila = id_Maquila;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMaquila() {
        return maquila;
    }

    public void setMaquila(String maquila) {
        this.maquila = maquila;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } 
}
