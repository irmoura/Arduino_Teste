/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Ismael Ribeiro
 */
public class Diretorio {
    
    public static File dir;
    
    public static String letra;
    public static String pasta_do_programa = "AI_3_Master";
    
    public static void mostraMensagem(String mensagem, String titulo){
        JOptionPane.showMessageDialog(null,mensagem,titulo,JOptionPane.WARNING_MESSAGE);
    }
    
    public static void pastaPrincipal(){
        String unidades_possiveis[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
        dir = new File(letra+":\\"+pasta_do_programa);
        
        if(!dir.exists()){
            for(int i=0; i < unidades_possiveis.length; i++){
                letra = unidades_possiveis[i];
                dir = new File(letra+":\\"+pasta_do_programa);
            if(!dir.exists()){
                //mostraMensagem("O diretório "+pasta_do_programa+" não existe na unidade "+letra+":", arquivo);
            }
            if(dir.exists()){
            i = unidades_possiveis.length;
            }
            if(letra.equals("Z")){
                mostraMensagem("O diretório "+pasta_do_programa+" não existe em lugar nenhum.","Aviso");
                System.exit(0);
        }
    }
}
        
    }
}