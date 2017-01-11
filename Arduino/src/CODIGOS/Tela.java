/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Mael
 */
public class Tela extends javax.swing.JFrame {
    
    private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    
    public String porta = "";
    public String mensagem_da_serial;

    /**
     * Creates new form Tela
     * @param nomeDoAudio
     */
    
    public void define_Portas_Disponiveis(){
        String[] s = porta.split(", ");
        for(int i = 0; i < s.length; i++){
            jComboBox1.addItem(s[i]);
        }
    }
    
    public void play(String nomeDoAudio){
        try {
            Runtime.getRuntime().exec("cmd /c start /B C:\\GA_XLSX\\Player\\dist\\Player.jar "+nomeDoAudio);
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (arduino.isMessageAvailable()) {
                    mensagem_da_serial = ""+arduino.printMessage();
/******************************************************************************/
                     if(mensagem_da_serial.equals("ligado")){
                        play("ligado");
                        BTN_ON_OFF.setSelected(true);
                        BTN_ON_OFF.setText("ON");
                        BTN_ON_OFF.setBackground(Color.green);
                    }else
                        if(mensagem_da_serial.equals("desligado")){
                        BTN_ON_OFF.setSelected(false);
                        BTN_ON_OFF.setText("OFF");
                        BTN_ON_OFF.setBackground(Color.red);
                        play("desligado");
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
            arduino.sendData("a");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTN_ON_OFF.setSelected(true);
        BTN_ON_OFF.setBackground(Color.green);
        BTN_ON_OFF.setText("ON");
    }
    public void off(){
        try {
            arduino.sendData("a");
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTN_ON_OFF.setSelected(false);
        BTN_ON_OFF.setBackground(Color.red);
        BTN_ON_OFF.setText("OFF");
    }
    
    public Tela() {
        initComponents();
        porta = ""+arduino.getSerialPorts();
        porta = porta.replace("[", "");
        porta = porta.replace("]", "");
        try {
            arduino.arduinoRXTX("COM6", 9600, listener);
        } catch (ArduinoException ex) {
            System.out.println("O Arduino não está conectado.");
        }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPortasDisponiveis)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(BTN_ON_OFF)
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
        
        BTN_ON_OFF.setBackground(Color.red);
        
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelPortasDisponiveis;
    // End of variables declaration//GEN-END:variables
}
