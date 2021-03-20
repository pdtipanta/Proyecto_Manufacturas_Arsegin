/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Factura;

import Modelo.Inventario;
import Vista.Factura.Dialogo_Cantidad_Producto;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Agregar_Factura implements ActionListener, KeyListener {

    private final Vista_Principal vista;
    private final Inventario inventario;
    private Object[] item;
    private final Dialogo_Cantidad_Producto dialogo_Cantidad_Producto;

    public Controlador_Dialogo_Agregar_Factura(Vista_Principal vista, Inventario inventario) {
        this.vista = vista;
        this.inventario = inventario;
        this.dialogo_Cantidad_Producto = new Dialogo_Cantidad_Producto(this.vista, true);
        this.dialogo_Cantidad_Producto.boton_Agregar_Producto.addActionListener(this);
        this.dialogo_Cantidad_Producto.campo_Cantidad.addKeyListener(this);
    }

    public Object[] iniciar() {

        if (this.inventario.getCantidad_Disponible() == 0) {
            JOptionPane.showMessageDialog(null, "La cantidad del producto en inventario es 0, no disponible", "Inventario", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.dialogo_Cantidad_Producto.setValores(this.inventario.getDescripcion(), this.inventario.getCodigo(), this.inventario.getCantidad_Disponible(), this.inventario.getPrecio_Venta());
            this.dialogo_Cantidad_Producto.setVisible(true);
        }
        
        return this.item;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Cantidad_Producto.boton_Agregar_Producto) {
            if (this.dialogo_Cantidad_Producto.campo_Cantidad.getText().equals( "0")) {   
            }else{
                double total = Double.valueOf(this.dialogo_Cantidad_Producto.campo_Cantidad.getText()) * this.inventario.getPrecio_Venta();
                Object[] valores = {Integer.valueOf(this.dialogo_Cantidad_Producto.campo_Cantidad.getText()), this.inventario.getCodigo(), this.inventario.getDescripcion(), this.inventario.getPrecio_Venta(), total};

                this.item = valores;
                this.dialogo_Cantidad_Producto.dispose();
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
        if (ke.getSource() == this.dialogo_Cantidad_Producto.campo_Cantidad) {
            if (this.dialogo_Cantidad_Producto.campo_Cantidad.getText().matches("^[1-9][0-9]*$")) {
                if (Integer.valueOf(this.dialogo_Cantidad_Producto.campo_Cantidad.getText()) <= Integer.valueOf(this.dialogo_Cantidad_Producto.etiqueta_Stock.getText())) {
                    this.dialogo_Cantidad_Producto.etiqueta_Advertencia.setVisible(false);
                    this.dialogo_Cantidad_Producto.calcular_Valores();
                    this.dialogo_Cantidad_Producto.boton_Agregar_Producto.setEnabled(true);
                } else {
                    this.dialogo_Cantidad_Producto.campo_Cantidad.setText("0");
                    this.dialogo_Cantidad_Producto.etiqueta_Total.setText("0.0");
                    this.dialogo_Cantidad_Producto.etiqueta_Advertencia.setVisible(true);
                    this.dialogo_Cantidad_Producto.boton_Agregar_Producto.setEnabled(false);
                }
            } else {
                this.dialogo_Cantidad_Producto.etiqueta_Total.setText("0.0");
                this.dialogo_Cantidad_Producto.boton_Agregar_Producto.setEnabled(false);
            }
        }
    }
}
