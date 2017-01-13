/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.AWTException;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Mael
 */
public class Tela extends javax.swing.JFrame {
    
    public PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    public Timer timer;
    public Robot bot;
    
    public String porta = "";
    public String porta_selecionada;
    public String mensagem_da_serial;
    
    public int HORIZONTAL, VERTICAL, count;

    /**
     * Creates new form Tela
     * @param comando
     */
    
    public void robo(String comando){
        timer = new Timer(80, (ActionEvent e) ->{
            try {
                bot = new Robot();
                HORIZONTAL = Integer.parseInt(""+MouseInfo.getPointerInfo().getLocation().x);
                VERTICAL = Integer.parseInt(""+MouseInfo.getPointerInfo().getLocation().y);
                if(comando.equals("mouse para cima")){
                    VERTICAL--;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("mouse para baixo")){
                    VERTICAL++;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("mouse para esquerda")){
                    HORIZONTAL--;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("mouse para direita")){
                    HORIZONTAL++;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("Mouse superior esquerda")){
                    HORIZONTAL--;
                    VERTICAL--;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("Mouse superior direita")){
                    HORIZONTAL++;
                    VERTICAL--;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("Mouse inferior esquerda")){
                    HORIZONTAL--;
                    VERTICAL++;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
                else
                if(comando.equals("Mouse inferior direita")){
                    HORIZONTAL++;
                    VERTICAL++;
                    bot.mouseMove(HORIZONTAL, VERTICAL);
                }
            } catch (AWTException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        timer.start();
    }
    
    public void program(String comando){
        try {
            Runtime.getRuntime().exec("cmd /c start "+comando+".exe");
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void define_Portas_Disponiveis(){
        String[] s = porta.split(", ");
        for (String item : s) {
            jComboBox1.addItem(item);
        }
    }
    
    public void inicia_comunicao(String porta_selecionada){
        try {
            arduino.arduinoRXTX(this.porta_selecionada, 9600, listener);
        } catch (ArduinoException ex) {
            JOptionPane.showMessageDialog(null,"O Arduino não está conectado ou a porta selecionada não é a correta.");
            System.exit(0);
        }
    }
    
    public void play(String nomeDoAudio){
        try {
            Runtime.getRuntime().exec("cmd /c start /B C:\\GA_XLSX\\Player\\dist\\Player.jar "+nomeDoAudio);
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (arduino.isMessageAvailable()) {
                    mensagem_da_serial = ""+arduino.printMessage();
/******************************************************************************/
                     if(mensagem_da_serial.equals("ligar")){
                        play("ligado");
                        BTN_ON_OFF.setSelected(true);
                        BTN_ON_OFF.setText("ON");
                        BTN_ON_OFF.setBackground(Color.green);
                    }else
                        if(mensagem_da_serial.equals("desligar")){
                        play("desligado");
                        BTN_ON_OFF.setSelected(false);
                        BTN_ON_OFF.setText("OFF");
                        BTN_ON_OFF.setBackground(Color.red);
                    }else
                        if(mensagem_da_serial.equals("Abrir Google Chrome")){
                            program("chrome");
                        }
                        else
                        if(mensagem_da_serial.equals("Abrir Paint")){
                            program("mspaint");
                        }
                        else
                        if(mensagem_da_serial.equals("mouse para cima")){
                            timer.stop();
                            robo("mouse para cima");
                        }
                        else
                        if(mensagem_da_serial.equals("Mouse superior esquerda")){
                            timer.stop();
                            robo("Mouse superior esquerda");
                        }
                        else
                        if(mensagem_da_serial.equals("Mouse inferior esquerda")){
                            timer.stop();
                            robo("Mouse inferior esquerda");
                        }
                        else
                        if(mensagem_da_serial.equals("Mouse superior direita")){
                            timer.stop();
                            robo("Mouse superior direita");
                        }
                        else
                        if(mensagem_da_serial.equals("Mouse inferior direita")){
                            timer.stop();
                            robo("Mouse inferior direita");
                        }
                        else
                        if(mensagem_da_serial.equals("mouse para baixo")){
                            timer.stop();
                            robo("mouse para baixo");
                        }
                        else
                        if(mensagem_da_serial.equals("mouse para esquerda")){
                            timer.stop();
                            robo("mouse para esquerda");
                        }
                        else
                        if(mensagem_da_serial.equals("mouse para direita")){
                            timer.stop();
                            robo("mouse para direita");
                        }
                        else
                        if(mensagem_da_serial.equals("parar Mouse")){
                            timer.stop();
                        }
                        else
                        if(mensagem_da_serial.equals("Abrir AD")){
                            try {
                            Runtime.getRuntime().exec("cmd /c start %windir%\\explorer.exe shell:Appsfolder\\Microsoft.MicrosoftEdge_8wekyb3d8bbwe!MicrosoftEdge");
                            } catch (IOException ex) {
                            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else
                        if(mensagem_da_serial.equals("Abrir calculadora")){
                            program("calc");
                        }
                        else{
                            System.out.println(mensagem_da_serial);
                        }
/******************************************************************************/
                }
            } catch (SerialPortException | ArduinoException ex) {
//                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public void on(){
        try {
            arduino.sendData("ligar");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTN_ON_OFF.setSelected(true);
        BTN_ON_OFF.setBackground(Color.green);
        BTN_ON_OFF.setText("ON");
    }
    public void off(){
        try {
            arduino.sendData("desligar");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTN_ON_OFF.setSelected(false);
        BTN_ON_OFF.setBackground(Color.red);
        BTN_ON_OFF.setText("OFF");
    }
    
    public Tela() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTN_ON_OFF = new javax.swing.JToggleButton();
        jLabelPortasDisponiveis = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton_Conectar = new javax.swing.JButton();
        jButton_Sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        BTN_ON_OFF.setText("OFF");
        BTN_ON_OFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ON_OFFActionPerformed(evt);
            }
        });

        jLabelPortasDisponiveis.setText("Portas Disponíveis :");

        jButton_Conectar.setText("CONECTAR");
        jButton_Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConectarActionPerformed(evt);
            }
        });

        jButton_Sair.setText("SAIR");
        jButton_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPortasDisponiveis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTN_ON_OFF))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Sair)
                    .addComponent(jButton_Conectar))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPortasDisponiveis)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Conectar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_ON_OFF)
                    .addComponent(jButton_Sair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_ON_OFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ON_OFFActionPerformed
        // TODO add your handling code here:
        if(BTN_ON_OFF.isSelected()){
                on();
        }else{
            off();
        }
    }//GEN-LAST:event_BTN_ON_OFFActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        /*APENAS INICIALIZANDO O TIMER*/
        timer = new Timer(1000, (ActionEvent e) ->{
        });
        
        porta = ""+arduino.getSerialPorts();
        porta = porta.replace("[", "");
        porta = porta.replace("]", "");
        
        BTN_ON_OFF.setBackground(Color.red);
        BTN_ON_OFF.setEnabled(false);
        
/******************************************************************************/
        switch (Integer.parseInt(""+arduino.getPortsAvailable())) {
            case 0:
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                        + "\nNenhuma Porta Disponível.");
                break;
            case 1:
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                        + "\nPorta Disponível: "+arduino.getPortsAvailable()
                        + "\nNome da Porta: "+porta);
                define_Portas_Disponiveis();
                break;
            default:
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                        + "\nPortas Disponíveis: "+arduino.getPortsAvailable()
                        + "\nNome das Portas: "+porta);
                define_Portas_Disponiveis();
                break;
        }
/******************************************************************************/
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton_ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConectarActionPerformed
        // TODO add your handling code here:
        porta_selecionada = ""+jComboBox1.getSelectedItem();
        inicia_comunicao(porta_selecionada);
        BTN_ON_OFF.setEnabled(true);
    }//GEN-LAST:event_jButton_ConectarActionPerformed

    private void jButton_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton_SairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Tela().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BTN_ON_OFF;
    private javax.swing.JButton jButton_Conectar;
    private javax.swing.JButton jButton_Sair;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelPortasDisponiveis;
    // End of variables declaration//GEN-END:variables
}
