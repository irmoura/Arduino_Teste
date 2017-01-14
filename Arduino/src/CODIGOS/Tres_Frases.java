/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import static CODIGOS.Tela.frase;
import static CODIGOS.Tela.frase_completa;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ibyte
 */
public class Tres_Frases {

public static void programa(String comando){
        try {
            Runtime.getRuntime().exec("cmd /c start "+comando+".exe");
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public static void type(int i){
    try {
        Robot robot = new Robot();
        robot.delay(40);
        robot.keyPress(i);
        robot.keyRelease(i);
    } catch (AWTException ex) {
        Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    
}
 
public static void type(String s){
      
    Robot robot;
    try {
        robot = new Robot();
        byte[] bytes = s.getBytes();
    for (byte b : bytes)
    {
      int code = b;
      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
      if (code > 96 && code < 123) code = code - 32;
      robot.delay(40);
      robot.keyPress(code);
      robot.keyRelease(code);
    }
    } catch (AWTException ex) {
        Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public static void click(int x, int y){
    Robot bot;
    try {
        bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    } catch (AWTException ex) {
        Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
    }
}    
    
public void Tres_Frases(String p1, String p2, String p3){
    if((p1.equals("mouse"))){
        
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bot.delay(1000);
        
        int X = Integer.parseInt(p2);
        int Y = Integer.parseInt(p3);
        
        bot.mouseMove(X, Y);
        
    }
    else
    if((p1.equals("clique"))){
        
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bot.delay(1000);
        
        int X = Integer.parseInt(p2);
        int Y = Integer.parseInt(p3);
        
        click(X, Y);
        
    }
    else
    if((p1.equals("digite")) && (p2.equals("isso"))){
        
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Tres_Frases.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bot.delay(1000);
        
        frase_completa = frase_completa.replace("digite isso ", "");
        
        type(frase_completa); 
        
    }
    else
    if((p1.equals("painel")) && (p2.equals("de"))&& (p3.equals("controle"))){
        programa("control");
    }
    else
    if((p1.equals("gerenciador")) && (p2.equals("de"))&& (p3.equals("tarefas"))){
        programa("Taskmgr");
    }
}
    
}