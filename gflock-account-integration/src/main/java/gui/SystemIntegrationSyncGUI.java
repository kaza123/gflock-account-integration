/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sync_service.SyncService;

/**
 *
 * @author 'Kasun Chamara'
 */
public class SystemIntegrationSyncGUI extends javax.swing.JFrame {

    Integer loginUser = -1;

    public SystemIntegrationSyncGUI() {
        initComponents();

        initOthers();
        lblProcess.setText("");

        try {
            getDetailCount(txtDate.getText());
        } catch (SQLException ex) {
            System.out.println("get detail count function not support !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void executeGrn(String date, Integer loginUser1) throws SQLException {
        SyncService.getInstance().executeGrn(date, loginUser1);
        getDetailCount(date);
    }

    private void executeInvoice(String date, Integer loginUser1) throws SQLException {
        SyncService.getInstance().executeInvoice(date, loginUser1);
        getDetailCount(date);
    }

    private void executePayment(String date, Integer loginUser1) throws SQLException {
        SyncService.getInstance().executePayment(date, loginUser1);
        getDetailCount(date);
    }

    private void executeStockAdjustment(String date, Integer loginUser) throws SQLException {
        SyncService.getInstance().executeStockAdjustment(date, loginUser);
        getDetailCount(date);

    }

    private void getDetailCount(String date) throws SQLException {
        Integer stockAdjustmentCount = SyncService.getInstance().getStockAdjustmentCount(date);
        btnStockAdjust.setText("Stock Adjustment" + " - " + stockAdjustmentCount);

        Integer grnCount = SyncService.getInstance().getGrnCount(date);
        btnGrn.setText("GRN" + " - " + grnCount);

        Integer invoiceCount = SyncService.getInstance().getInvoiceCount(date);
        btnInvoice.setText("Invoice" + " - " + invoiceCount);

        Integer paymentCount = SyncService.getInstance().getPaymentCount(date);
        btnPayment.setText("Payment" + " - " + paymentCount);

    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        try {
            setTitle("Account Integration System");
            setLocationRelativeTo(null);
            txtLog.setEditable(false);
            String date = SyncService.getInstance().getTransactionDate();
            txtDate.setText(date);

            TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(txtLog);
            System.setOut(new PrintStream(textAreaOutputStream));
        } catch (SQLException ex) {
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLoginUser(Integer loginUser) {
        this.loginUser = loginUser;
        System.out.println("login User - " + loginUser);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        txtDate = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnGrn = new javax.swing.JButton();
        btnInvoice = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblProcess = new javax.swing.JLabel();
        btnStockAdjust = new javax.swing.JButton();
        btnSync = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 330));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        txtDate.setEnabled(false);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnGrn.setText("GRN");
        btnGrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrnActionPerformed(evt);
            }
        });

        btnInvoice.setText("Invoice");
        btnInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvoiceActionPerformed(evt);
            }
        });

        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date :");

        lblProcess.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblProcess.setForeground(new java.awt.Color(0, 0, 0));
        lblProcess.setText("processing . . .");

        btnStockAdjust.setText("Stock Adjustment");
        btnStockAdjust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockAdjustActionPerformed(evt);
            }
        });

        btnSync.setText("Sync");
        btnSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSyncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnStockAdjust, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGrn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnSync, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lblProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClear)
                        .addComponent(lblProcess)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStockAdjust)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInvoice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPayment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSync))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(SystemIntegrationSyncGUI.EXIT_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtLog.setText("");
        try {
            getDetailCount(txtDate.getText());
        } catch (SQLException ex) {
            System.out.println("get detail count function not support !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvoiceActionPerformed
        if (optionPain() == 0) {
            try {
                lblProcess.setText("processing . . .");
                executeInvoice(txtDate.getText(), loginUser);
                lblProcess.setText("");
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnInvoiceActionPerformed

    private void btnGrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrnActionPerformed
        if (optionPain() == 0) {
            try {
                lblProcess.setText("processing . . .");
                executeGrn(txtDate.getText(), loginUser);
                lblProcess.setText("");
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnGrnActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        if (optionPain() == 0) {
            try {
                lblProcess.setText("processing . . .");
                executePayment(txtDate.getText(), loginUser);
                lblProcess.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnStockAdjustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockAdjustActionPerformed
        if (optionPain() == 0) {
            try {
                lblProcess.setText("processing . . .");
                executeStockAdjustment(txtDate.getText(), loginUser);
                lblProcess.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnStockAdjustActionPerformed

    private void btnSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyncActionPerformed
        if (optionPain() == 0) {
            try {
                String date = SyncService.getInstance().getNextDate(txtDate.getText());
                txtDate.setText(date);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSyncActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnGrn;
    private javax.swing.JButton btnInvoice;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnStockAdjust;
    private javax.swing.JButton btnSync;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblProcess;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

    private int optionPain() {
        return JOptionPane.showConfirmDialog(null, "Are you sure ?", "Warning", JOptionPane.YES_OPTION);
    }

}
