/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Compra;

import Modelo.Inventario;
import Vista.Inventario.Dialogo_Nuevo_Producto;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Nuevo_Producto implements ActionListener, KeyListener{
    private final Vista_Principal vista;
    private final Dialogo_Nuevo_Producto dialogo_Nuevo_Producto;
    private DecimalFormat           formato_Numero = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));
    private Object[] inventario;
    private Inventario producto;

    public Controlador_Dialogo_Nuevo_Producto(Vista_Principal vista) {
        this.vista = vista;
        this.dialogo_Nuevo_Producto = new Dialogo_Nuevo_Producto(this.vista, true);
        this.dialogo_Nuevo_Producto.boton_Agregar.addActionListener(this);
        this.dialogo_Nuevo_Producto.campo_Cantidad.addKeyListener(this);
        this.dialogo_Nuevo_Producto.campo_Unitario.addKeyListener(this);
    }
    
    public Object[] iniciar(){
        this.dialogo_Nuevo_Producto.setVisible(true);
        return this.inventario;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.dialogo_Nuevo_Producto.boton_Agregar){
            if(this.dialogo_Nuevo_Producto.verificar_Campos()){
                
                Object[] inventario = {Integer.valueOf(this.dialogo_Nuevo_Producto.campo_Cantidad.getText()), this.dialogo_Nuevo_Producto.campo_Codigo.getText(), this.dialogo_Nuevo_Producto.campo_Descripcion.getText(), Double.valueOf(this.dialogo_Nuevo_Producto.campo_Unitario.getText()), Double.valueOf(this.dialogo_Nuevo_Producto.campo_Total.getText())};
                this.inventario = inventario;
                this.dialogo_Nuevo_Producto.dispose();   
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == this.dialogo_Nuevo_Producto.campo_Cantidad) {
            if (this.dialogo_Nuevo_Producto.campo_Cantidad.getText().matches("^[1-9][0-9]*$")) {
                this.dialogo_Nuevo_Producto.calcular_Valor();
                this.dialogo_Nuevo_Producto.boton_Agregar.setEnabled(true);
            } else {
                this.dialogo_Nuevo_Producto.campo_Total.setText("0.0");
                this.dialogo_Nuevo_Producto.boton_Agregar.setEnabled(false);
            }
        }
        
        if (ke.getSource() == this.dialogo_Nuevo_Producto.campo_Unitario) {
            if (this.dialogo_Nuevo_Producto.campo_Unitario.getText().matches("^[1-9][0-9]*$")) {
                this.dialogo_Nuevo_Producto.calcular_Valor();
                this.dialogo_Nuevo_Producto.boton_Agregar.setEnabled(true);
            } else {
                this.dialogo_Nuevo_Producto.campo_Total.setText("0.0");
                this.dialogo_Nuevo_Producto.boton_Agregar.setEnabled(false);
            }
        }
    }
}
