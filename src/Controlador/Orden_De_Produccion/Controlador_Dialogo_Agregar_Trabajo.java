/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Orden_De_Produccion;

import Vista.Maquilas.Orden_De_Produccion.Dialogo_Agregar_Trabajo;
import Vista.Vista_Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author David
 */
public class Controlador_Dialogo_Agregar_Trabajo implements ActionListener{
    private final Vista_Principal vista;
    private Object[] item;
    private final Dialogo_Agregar_Trabajo dialogo_Agregar_Trabajo;

    public Controlador_Dialogo_Agregar_Trabajo(Vista_Principal vista) {
        this.vista = vista;
        this.dialogo_Agregar_Trabajo = new Dialogo_Agregar_Trabajo(this.vista, true);
        this.dialogo_Agregar_Trabajo.boton_Agregar.addActionListener(this);
    }
    
    public Object[] iniciar(){
        this.dialogo_Agregar_Trabajo.setVisible(true);
        return this.item;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.dialogo_Agregar_Trabajo.boton_Agregar){
            if(this.dialogo_Agregar_Trabajo.verificar_Campos()){
                Object[] trabajo = {Integer.valueOf(this.dialogo_Agregar_Trabajo.campo_Cantidad.getText()), this.dialogo_Agregar_Trabajo.campo_Descripcion.getText(), Double.valueOf(this.dialogo_Agregar_Trabajo.campo_Unitario.getText()), Double.valueOf(this.dialogo_Agregar_Trabajo.campo_Total.getText())};
                this.item = trabajo;
                this.dialogo_Agregar_Trabajo.dispose();
            }
        }
    }
}
