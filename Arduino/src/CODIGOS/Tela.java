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
import java.util.Random;
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
    
    public static PanamaHitek_Arduino arduino;
    public static SerialPortEventListener listener;
    public static Timer timer;
    public static Robot bot;
    public static Random random;
    
    public static String porta = "";
    public static String porta_selecionada;
    public static String mensagem_da_serial;
    
    public static String frase_completa, frase;
    
    public static Uma_Frase f1;
    public static Duas_Frases f2;
    public static Tres_Frases f3;
    
    public static String[] palavras_separadas;
    
    public static int HORIZONTAL, VERTICAL, count;
    public static int na;

    /**
     * Creates new form Tela
     * @param comando
     */
    
    public static void robo(String comando){
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
    
    public static void play(String frase){
        try {
            Runtime.getRuntime().exec("cmd /c start /B C:\\GA_XLSX\\Player\\dist\\Player_2.exe "+frase);
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        
        f1 = new Uma_Frase();
        f2 = new Duas_Frases();
        f3 = new Tres_Frases();
        random = new Random();
        arduino = new PanamaHitek_Arduino();
        
        this.listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    if (arduino.isMessageAvailable()) {
                        
                        na = random.nextInt(4);//ESCOLHE UM NUMERO ALEATORIO ENTRE 0 E 3
                        
                        mensagem_da_serial = ""+arduino.printMessage();

                        frase_completa = mensagem_da_serial;
                        
                        frase = mensagem_da_serial;
                        
                        frase = frase.replace(" ",";");
                        String[] s = frase.split(";");
                        
                        palavras_separadas = new String[s.length];
                        
                        System.arraycopy(s, 0, palavras_separadas, 0, s.length);
                        
                        if(s.length == 1){                       
                            f1.Uma_Frase(palavras_separadas[0]);
                        }
                        else
                        if((s.length == 2)){
                            f2.Duas_Frases(palavras_separadas[0], palavras_separadas[1]);
                        }
                        else
                        if((s.length >= 3)){
                            f3.Tres_Frases(palavras_separadas[0], palavras_separadas[1], palavras_separadas[2]);
                        }
                        
                        System.out.println(mensagem_da_serial);
                        
                        TEXTO_CONVERSA.setText(mensagem_da_serial);

                    }
                } catch (SerialPortException | ArduinoException ex) {
//                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TEXTO_CONVERSA = new javax.swing.JTextArea();

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

        TEXTO_CONVERSA.setEditable(false);
        TEXTO_CONVERSA.setColumns(20);
        TEXTO_CONVERSA.setRows(5);
        jScrollPane1.setViewportView(TEXTO_CONVERSA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPortasDisponiveis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BTN_ON_OFF))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Conectar)
                            .addComponent(jButton_Sair, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPortasDisponiveis)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Conectar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
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
        
        Diretorio.pastaPrincipal();
        
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
        jButton_Conectar.setEnabled(false);
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
    public static javax.swing.JToggleButton BTN_ON_OFF;
    public static javax.swing.JTextArea TEXTO_CONVERSA;
    private javax.swing.JButton jButton_Conectar;
    private javax.swing.JButton jButton_Sair;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabelPortasDisponiveis;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
