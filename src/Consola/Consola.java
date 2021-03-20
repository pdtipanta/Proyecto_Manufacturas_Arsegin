package Consola;


import Controlador.Gestion_Usuarios.Controlador_Panel_Ingreso;
import Vista.Vista_Principal;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class Consola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new AeroLookAndFeel());
        new Controlador_Panel_Ingreso(new Vista_Principal()).iniciar();
    }
}
