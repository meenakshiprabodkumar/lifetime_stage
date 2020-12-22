/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino_linearactuatorGUI;

import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meenakshi
 */
public class arduinoGUI extends javax.swing.JFrame {

    //Fields for communicating to arduino
    //Serial version for this class.
    private static final long serialVersionUID = 1L;
    //SerialPort object.
    private SerialPort comPort;
    //The comport that will be used.  Change this field if you're using a different port for your Arduino. */
    private String comPortName = "COM4";
    //Communication rate for the USB port.  If you're Arduino is using a different rate, change this value to match it. */
    private int baudRate = 9600;
    //PrintWriter used to write through the USB port. */
    static PrintWriter outPut;

    //fields for position control of linear actuator
    private int tarPos;    //userInput is set as target position
    private int curPos; //program is initialised assuming the motor is at zero position
    private int Steps; //compute which direction to move
    private String dir; //send direction info to arduino as a string - because I am not sure in what form the info is sent. Seems important that the info is sent as String

    /**
     * Creates new form arduinoGUI
     */
    public arduinoGUI() {
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

        jLabel1 = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        setButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSteps = new javax.swing.JTextField();
        goPosButton = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPosition = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GUI for stage control");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Connect to Arduino:");

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Initialise position of stage:");

        setButton.setText("Set zero");
        setButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Enter steps:");

        goPosButton.setText("Go");
        goPosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goPosButtonActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblStatus.setText("Status:");

        jLabel4.setText("1 step = 0.0025cm");

        jLabel5.setText("Enter position (in cm):");

        jLabel6.setText("1 cm = 400 steps");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Set position of stage:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConnect)
                        .addGap(39, 39, 39)
                        .addComponent(btnDisconnect))
                    .addComponent(setButton)
                    .addComponent(lblStatus)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goPosButton))
                    .addComponent(jLabel7))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect)
                    .addComponent(btnDisconnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goPosButton)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        //Set up the values for the USB port.
        comPort = SerialPort.getCommPort(comPortName);
        comPort.setBaudRate(baudRate);

        //If the port is not closed, open the USB port.
        if (comPort.isOpen() == false) {
            try {
                //Open the USB port and initialize the PrintWriter.
                comPort.openPort();
                Thread.sleep(1000);
                outPut = new PrintWriter(comPort.getOutputStream());
                //Update the console and status.
                System.out.println("Connection to Arduino successful.");
                lblStatus.setText("Status: Connected");
            } catch (Exception c) {
                System.out.println("Error connecting to Arduino.");
                System.out.println(c);
                lblStatus.setText("Status: Error");
            }
        } else if (comPort.isOpen() == true) {
            System.out.println("Port open.");
            lblStatus.setText("Status: Connected");
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        //Close the USB port if it's open.
        if (comPort.isOpen() == true) {
            try {
                //Close the port and update the console/status.
                comPort.closePort();
                System.out.println("Disconnected from Arduino.");
                lblStatus.setText("Status: Disconnected");
            } catch (Exception c) {
                System.out.println("Error disconnecting from Arduino.");
                System.out.println(c);
                lblStatus.setText("Status: Error");
            }
        } else if (comPort.isOpen() == false) {
            System.out.println("Port closed.");
            lblStatus.setText("Status: Disconnected");
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void setButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setButtonActionPerformed
        if (comPort.isOpen() == true) {
            try {
                curPos = 0;
                tarPos = 0;
                dir = String.valueOf(this.difference());
                //send instruction to arduino
                outPut.print(dir);
                outPut.flush();
                System.out.println("Status: Zero set.");
                lblStatus.setText("Status: Current position set as zero.");
            } catch (Exception c) {
                System.out.println("Error setting zero");
                System.out.println(c);
                lblStatus.setText("Status: Error");
            }
        } else {
            //Update the status/console if the Arduino hasn't been connected.
            System.out.println("Connect Arduino");
            lblStatus.setText("Status: Connect Arduino");
        }
    }//GEN-LAST:event_setButtonActionPerformed

    private void goPosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goPosButtonActionPerformed
        if (comPort.isOpen() == true) {
            try {
                if (!jTextFieldPosition.getText().isEmpty()) {
                    float pos = Float.parseFloat(jTextFieldPosition.getText());
                    tarPos = (int) (pos / 0.0025);
                    jTextFieldSteps.setText(String.valueOf(tarPos));
                } else {
                    tarPos = Integer.parseInt(jTextFieldSteps.getText());
                    jTextFieldPosition.setText(String.valueOf(tarPos*0.0025));
                }
                dir = String.valueOf(this.difference());
                //send instruction to arduino
                outPut.print(dir);
                outPut.flush();
                if (this.difference() > 0) {
                    System.out.println("Status: Moving in forward direction.");
                    lblStatus.setText("Status: Forward motion in progress");
                } else if (this.difference() < 0) {
                    System.out.println("Status: Moving in reverse direction.");
                    lblStatus.setText("Status: Reverse motion in progress");
                } else {
                    System.out.println("Status: Motor is set.");
                    lblStatus.setText("Status: Reached target position complete");
                }
            } catch (NumberFormatException c) {
                System.out.println("Issue with number entered.");
                lblStatus.setText("Status: Please enter a valid number");
            } catch (Exception c) {
                System.out.println("Error moving stage");
                System.out.println(c);
                lblStatus.setText("Status: Error");
            }
        } else {
            //Update the status/console if the Arduino hasn't been connected.
            System.out.println("Connect Arduino");
            lblStatus.setText("Status: Connect Arduino");
        }
    }//GEN-LAST:event_goPosButtonActionPerformed

    private int difference() {
        Steps = curPos - tarPos;
        curPos = tarPos;
        return Steps;
    }

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
            java.util.logging.Logger.getLogger(arduinoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(arduinoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(arduinoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(arduinoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new arduinoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton goPosButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextFieldPosition;
    private javax.swing.JTextField jTextFieldSteps;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JButton setButton;
    // End of variables declaration//GEN-END:variables
}