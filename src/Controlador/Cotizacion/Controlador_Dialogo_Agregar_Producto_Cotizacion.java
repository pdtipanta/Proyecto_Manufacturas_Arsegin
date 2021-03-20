/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Cotizacion;

import Modelo.Inventario;
import Vista.Factura.Dialogo_Cantidad_Producto;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Agregar_Producto_Cotizacion implements ActionListener, KeyListener {

    private final Vista_Principal vista;
    private final Inventario inventario;
    private Object[] item;
    private final Dialogo_Cantidad_Producto dialogo_Cantidad_Producto;

    public Controlador_Dialogo_Agregar_Producto_Cotizacion(Vista_Principal vista, Inventario inventario) {
        this.vista = vista;
        this.inventario = inventario;
        this.dialogo_Cantidad_Producto = new Dialogo_Cantidad_Producto(this.vista, true);
        this.dialogo_Cantidad_Producto.boton_Agregar_Producto.addActionListener(this);
        this.dialogo_Cantidad_Producto.campo_Cantidad.addKeyListener(this);
    }

    public Object[] iniciar() {
        this.dialogo_Cantidad_Producto.setValores(this.inventario.getDescripcion(), this.inventario.getCodigo(), this.inventario.getCantidad_Disponible(), this.inventario.getPrecio_Venta());
        this.dialogo_Cantidad_Producto.setVisible(true);
        return this.item;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.dialogo_Cantidad_Producto.boton_Agregar_Producto) {
            if (this.dialogo_Cantidad_Producto.campo_Cantidad.getText().equals("0")) {
            } else {
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
                this.dialogo_Cantidad_Producto.calcular_Valores();
                this.dialogo_Cantidad_Producto.boton_Agregar_Producto.setEnabled(true);
            } else {
                this.dialogo_Cantidad_Producto.etiqueta_Total.setText("0.0");
                this.dialogo_Cantidad_Producto.boton_Agregar_Producto.setEnabled(false);
            }
        }
    }
}
