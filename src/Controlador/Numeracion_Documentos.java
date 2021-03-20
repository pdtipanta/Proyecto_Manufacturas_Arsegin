/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.text.DecimalFormat;

/**
 *
 * @author David
 */
public class Numeracion_Documentos {

    DecimalFormat format1 = new DecimalFormat("000");
    DecimalFormat format2 = new DecimalFormat("000");
    DecimalFormat format3 = new DecimalFormat("0000000");

    public String convertir_Numero(String numero) {
        
        String[] arreglo = verificar_Numero(numero).split("-");
        if (Integer.parseInt(arreglo[2]) == 999 && Integer.parseInt(arreglo[2]) != 9999999) {
            arreglo[1] = String.valueOf(Integer.parseInt(arreglo[1]) + 1);
            arreglo[2] = String.valueOf(0);
        }

        if (Integer.parseInt(arreglo[1]) == 999 && Integer.parseInt(arreglo[2]) == 9999999) {
            arreglo[0] = String.valueOf(Integer.parseInt(arreglo[0]) + 1);
            arreglo[1] = String.valueOf(0);
            arreglo[2] = String.valueOf(0);
        }
        return format1.format(Integer.valueOf(arreglo[0])) + "-" + format2.format(Integer.valueOf(arreglo[1])) + "-" + format3.format(Integer.valueOf(arreglo[2]) + 1);
    }
    
    private String verificar_Numero(String numero) {
        if (numero == null) {
            numero = "000-000-0000000";
        }
        return numero;
    }
}
