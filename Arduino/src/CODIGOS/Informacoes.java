/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import static CODIGOS.Tela.play;
import java.util.Random;

/**
 *
 * @author irmoura
 */
public class Informacoes {
    
    static public Random random;
    
    static String vogais[] = {"a","e","i","o","u"};
    static String consoantes[] = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};
    static String alfabeto[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    public static void main(String[] args) throws InterruptedException {
//        for(int i = 0; i < 2; i++){
            while(true){
            random = new Random();
            int n1 = random.nextInt(consoantes.length);
            int n2 = random.nextInt(vogais.length);
            int n3 = random.nextInt(consoantes.length);
            int n4 = random.nextInt(vogais.length);
            System.out.println(consoantes[n1]+vogais[n2]+consoantes[n3]+vogais[n4]);
            play(""+consoantes[n1]+vogais[n2]+consoantes[n3]+vogais[n4]);
            Thread.sleep(1000);
            }
//        }
    }
}