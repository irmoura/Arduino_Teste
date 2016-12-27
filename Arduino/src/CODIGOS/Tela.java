/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
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
    
    public String porta;
    public boolean clique = false;

    /**
     * Creates new form Tela
     */
    
    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (arduino.isMessageAvailable()) {
//                    System.out.println("Comando via Bluetooth ...");
//                        if(!(BTN_ON_OFF.isSelected())){
//                        BTN_ON_OFF.setSelected(true);
//                        BTN_ON_OFF.setText("ON");
//                        BTN_ON_OFF.setBackground(Color.green);
//                    }else{
//                        BTN_ON_OFF.setSelected(false);
//                        BTN_ON_OFF.setText("OFF");
//                        BTN_ON_OFF.setBackground(Color.red);
//                    }
                }
            } catch (SerialPortException | ArduinoException ex) {
//                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public void on(){
        try {
            arduino.sendData("a");
        } catch (ArduinoException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTN_ON_OFF.setSelected(true);
        BTN_ON_OFF.setBackground(Color.green);
        BTN_ON_OFF.setText("ON");
    }
    public void off(){
        try {
            arduino.sendData("a");
        } catch (ArduinoException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
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
            //COM5 - BLUETOOTH
            arduino.arduinoRXTX("COM5", 9600, listener);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(BTN_ON_OFF)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(BTN_ON_OFF)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_ON_OFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ON_OFFActionPerformed
        // TODO add your handling code here:
        clique = true;
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
        if(Integer.parseInt(""+arduino.getPortsAvailable()) == 0){
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                + "\nNenhuma Porta Disponível.");
        }else if(Integer.parseInt(""+arduino.getPortsAvailable()) == 1){
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                + "\nPorta Disponível: "+arduino.getPortsAvailable()
                + "\nNome da Porta: "+porta);
        }else{
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
                + "\nPortas Disponíveis: "+arduino.getPortsAvailable()
                + "\nNome das Portas: "+porta);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BTN_ON_OFF;
    // End of variables declaration//GEN-END:variables
}
