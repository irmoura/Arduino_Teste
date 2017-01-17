/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import static CODIGOS.Tela.f1;
import static CODIGOS.Tela.timer;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ismael Ribeiro
 */
public class Duas_Frases {
    
    public Robot bot;
    
    public void Duas_Frases(String p1, String p2){
        
        try {
            bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Duas_Frases.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(p1.equals("selecionar") && p2.equals("tudo")){
        bot.keyPress(KeyEvent.VK_CONTROL);
        bot.keyPress(KeyEvent.VK_A);
        bot.keyRelease(KeyEvent.VK_A);
        bot.keyRelease(KeyEvent.VK_CONTROL);
        }
        else
        if(p1.equals("sem") && p2.equals("som") || p1.equals("com") && p2.equals("som")){
        bot.keyPress(KeyEvent.VK_F7);
        bot.keyRelease(KeyEvent.VK_F7);
        }
        else
        if(p1.equals("Desligar") && p2.equals("computador")){
        try {
            Runtime.getRuntime().exec("cmd /c start shutdown -p");
        } catch (IOException ex) {
            Logger.getLogger(Duas_Frases.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        if(p1.equals("delay")){
        int delay = Integer.parseInt(p2);
        bot.delay(delay);
        }
        else
        if((p1.equals("menu")) && (p2.equals("iniciar"))){
        bot.keyPress(KeyEvent.VK_WINDOWS);
        bot.keyRelease(KeyEvent.VK_WINDOWS);
        }
        else
        if((p1.equals("parar")) && (p2.equals("Mouse"))){
        timer.stop();
        }
        else
        if((p1.equals("que")) && (p2.equals("horas"))){
        f1.Uma_Frase(p2);
        }
        else
        if((p1.equals("Abrir")) && (p2.equals("AD"))){
            try {
                Runtime.getRuntime().exec("cmd /c start %windir%\\explorer.exe shell:Appsfolder\\Microsoft.MicrosoftEdge_8wekyb3d8bbwe!MicrosoftEdge");
            } catch (IOException ex) {
                Logger.getLogger(Duas_Frases.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}