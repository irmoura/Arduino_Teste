/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import javax.swing.JOptionPane;

/**
 *
 * @author Mael
 */
public class Calculos_Diretos {
    
    static String operador_procurado, Depois, Antes;
    static int antes_do_operador, depois_do_operador, depois, antes;
    
//    public static void main(String args[]){
//        
//        for(int i = 0; i < 4;i++){
//        
//        String calculo = JOptionPane.showInputDialog(null,"");//"200+1990";
//        
//        Calculo(calculo);
//        
//        }
//        
//    }
    
    public static void Calculo(String calculo){
        
        if(calculo.contains("+")){
            operador_procurado = "+";
            depois = calculo.indexOf(operador_procurado);
            antes = calculo.indexOf(operador_procurado);
            Depois = calculo.substring(depois);
            Antes = calculo.substring(0, antes);
            antes_do_operador = Integer.parseInt(Antes);
            depois_do_operador = Integer.parseInt(Depois);
        }
        else
        if(calculo.contains("-")){
            operador_procurado = "-";
            depois = calculo.indexOf(operador_procurado);
            antes = calculo.indexOf(operador_procurado);
            Depois = calculo.substring(depois);
            Depois = Depois.replace("-","");
            Antes = calculo.substring(0, antes);
            antes_do_operador = Integer.parseInt(Antes);
            depois_do_operador = Integer.parseInt(Depois);
        }
        else
        if(calculo.contains("*")){
            operador_procurado = "*";
            depois = calculo.indexOf(operador_procurado);
            antes = calculo.indexOf(operador_procurado);
            Depois = calculo.substring(depois);
            Depois = Depois.replace("*","");
            Antes = calculo.substring(0, antes);
            antes_do_operador = Integer.parseInt(Antes);
            depois_do_operador = Integer.parseInt(Depois);
        }
        else
        if(calculo.contains("/")){
            operador_procurado = "/";
            depois = calculo.indexOf(operador_procurado);
            antes = calculo.indexOf(operador_procurado);
            Depois = calculo.substring(depois);
            Depois = Depois.replace("/","");
            Antes = calculo.substring(0, antes);
            antes_do_operador = Integer.parseInt(Antes);
            depois_do_operador = Integer.parseInt(Depois);
        }
        
        if(calculo.contains("+")){
            JOptionPane.showMessageDialog(null,"A soma é: "+(antes_do_operador+depois_do_operador));
        }
        else
        if(operador_procurado.contains("-")){
            JOptionPane.showMessageDialog(null,"A subtração é: "+(antes_do_operador-depois_do_operador));
        }
        else
        if(operador_procurado.contains("*")){
            JOptionPane.showMessageDialog(null,"A multiplicação é: "+(antes_do_operador*depois_do_operador));
        }
        else
        if(operador_procurado.contains("/")){
            JOptionPane.showMessageDialog(null,"A divisão é: "+(antes_do_operador/depois_do_operador));
        }
        
}
    
}