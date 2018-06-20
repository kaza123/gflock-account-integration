/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controller.TransactionController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sync_controller.SyncController;

/**
 *
 * @author 'Kasun Chamara'
 */
public class SystemIntegrationSyncGUI extends javax.swing.JFrame {

    Integer loginUser = -1;
    int[] terminalList = new int[5];
    DefaultTableModel model = new DefaultTableModel();
    private int selectedTerminal;
    private ArrayList<Object[]> terminalDetail;

    public SystemIntegrationSyncGUI() {
        this.terminalDetail = new ArrayList<>();
        this.selectedTerminal = -1;
        initComponents();
        model = (DefaultTableModel) tblData.getModel();

        initOthers();
        lblProcess.setText("");

        try {
            terminalList[0] = 1;
            terminalList[1] = 2;
            terminalList[2] = 3;
            terminalList[3] = 4;
            terminalList[4] = 5;

            setButtonValue(getDetailCount(txtDate.getText(), terminalList));

        } catch (SQLException ex) {
            System.out.println("get detail count function some error !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        setTitle("Account Integration System");
        setLocationRelativeTo(null);
        txtLog.setEditable(false);
        // String date = TransactionController.getInstance().getSyncDate();
        String date = "2018-06-19";
        txtDate.setText(date);

        //set company
        //String companyName = TransactionController.getInstance().getCompanyName();
        String companyName = "GFlock";
        lblCompanyName.setText(companyName);

        //table decoration
        tblData.getColumnModel().getColumn(0).setPreferredWidth(200);
        tblData.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblData.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblData.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblData.getColumnModel().getColumn(4).setWidth(0);
        tblData.getColumnModel().getColumn(4).setMinWidth(0);
        tblData.getColumnModel().getColumn(4).setMaxWidth(0);
        tblData.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblData.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        tblData.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tblData.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tblData.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(txtLog);
        System.setOut(new PrintStream(textAreaOutputStream));
    }

    public void setLoginUser(Integer loginUser) {
        this.loginUser = loginUser;
        System.out.println("login User -> " + loginUser);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDate = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnTremi1 = new javax.swing.JButton();
        btnTremi3 = new javax.swing.JButton();
        btnTremi4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblProcess = new javax.swing.JLabel();
        btnTremi2 = new javax.swing.JButton();
        btnDayComplete = new javax.swing.JButton();
        lblCompanyName = new javax.swing.JLabel();
        btnTremi5 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnSync = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 330));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtDate.setEnabled(false);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTremi1.setText("Terminal 01");
        btnTremi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTremi1ActionPerformed(evt);
            }
        });

        btnTremi3.setText("Terminal 03");
        btnTremi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTremi3ActionPerformed(evt);
            }
        });

        btnTremi4.setText("Terminal 04");
        btnTremi4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTremi4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date :");

        lblProcess.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        btnTremi2.setText("Terminal 02");
        btnTremi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTremi2ActionPerformed(evt);
            }
        });

        btnDayComplete.setText("Day Complete");
        btnDayComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDayCompleteActionPerformed(evt);
            }
        });

        lblCompanyName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnTremi5.setText("Terminal 05");
        btnTremi5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTremi5ActionPerformed(evt);
            }
        });

        jSplitPane1.setDividerLocation(470);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel2.setText("jLabel2");
        jSplitPane1.setTopComponent(jLabel2);

        txtLog.setBackground(new java.awt.Color(0, 0, 0));
        txtLog.setColumns(20);
        txtLog.setForeground(new java.awt.Color(0, 153, 0));
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        jSplitPane1.setRightComponent(jScrollPane2);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "Sub Amount", "Amount", "Amount", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblData.setRowHeight(20);
        jScrollPane1.setViewportView(tblData);

        jSplitPane1.setTopComponent(jScrollPane1);

        btnSync.setText("Sync");
        btnSync.setPreferredSize(new java.awt.Dimension(75, 23));
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDayComplete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnTremi5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTremi2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTremi1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTremi3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTremi4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSync, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSplitPane1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClear)
                        .addComponent(lblProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSync, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTremi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTremi2)
                        .addGap(8, 8, 8)
                        .addComponent(btnTremi3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTremi4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTremi5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDayComplete))
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(SystemIntegrationSyncGUI.EXIT_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtLog.setText("");
        lblProcess.setText("");
        model.setRowCount(0);
        try {
//            String date = TransactionController.getInstance().getSyncDate();
            String date = "2018-06-19";
            txtDate.setText(date);
            txtLog.setText("Data is cleared !");

            setButtonValue(getDetailCount(txtDate.getText(), terminalList));
        } catch (SQLException ex) {
            System.out.println("Clear buttom some error !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnTremi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTremi3ActionPerformed
        if (optionPain() == 0) {
            int temId = 3;
            setTerminalDetail(temId);
        }
    }//GEN-LAST:event_btnTremi3ActionPerformed

    private void btnTremi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTremi1ActionPerformed
        if (optionPain() == 0) {
            int temId = 1;
            setTerminalDetail(temId);
        }

    }//GEN-LAST:event_btnTremi1ActionPerformed

    private void btnTremi4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTremi4ActionPerformed
        if (optionPain() == 0) {
            int temId = 4;
            setTerminalDetail(temId);
        }
    }//GEN-LAST:event_btnTremi4ActionPerformed

    private void btnTremi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTremi2ActionPerformed
        if (optionPain() == 0) {
            int temId = 2;
            setTerminalDetail(temId);
        }
    }//GEN-LAST:event_btnTremi2ActionPerformed

    private void btnDayCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDayCompleteActionPerformed
        if (optionPain() == 0) {
            try {
                String date = TransactionController.getInstance().getNextDate(txtDate.getText());
                txtDate.setText(date);
//                btnClear.doClick();
            } catch (SQLException ex) {
                System.out.println("Can't find next date !");
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDayCompleteActionPerformed

    private void btnTremi5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTremi5ActionPerformed
        if (optionPain() == 0) {
            int temId = 5;
            setTerminalDetail(temId);
        }
    }//GEN-LAST:event_btnTremi5ActionPerformed

    private void btnSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSyncActionPerformed
        if (selectedTerminal == -1) {
            System.out.println("Select a terminal to sync data !");
            throw new RuntimeException("Select a terminal to sync data !");
        }
        System.out.println("processing terminal " + selectedTerminal + " data ");
        System.out.println("date " + txtDate.getText());
        try {
            boolean save = TransactionController.getInstance().saveAccount(selectedTerminal, loginUser, terminalDetail, txtDate.getText());
            if (save) {
                btnClear.doClick();
                System.out.println("terminal " + selectedTerminal + " sync success ");
            } else {
                JOptionPane.showMessageDialog(null, "terminal " + selectedTerminal + " sync fail !");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnDayComplete;
    private javax.swing.JButton btnSync;
    private javax.swing.JButton btnTremi1;
    private javax.swing.JButton btnTremi2;
    private javax.swing.JButton btnTremi3;
    private javax.swing.JButton btnTremi4;
    private javax.swing.JButton btnTremi5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblProcess;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

    private HashMap getDetailCount(String date, int[] terList) throws SQLException {
        return SyncController.getInstance().getDetailCount(date, terList);
    }

    private int optionPain() {
        return JOptionPane.showConfirmDialog(null, "Are you sure ?", "Warning", JOptionPane.YES_OPTION);
    }

    private void setButtonValue(HashMap hashMap) {
        btnTremi1.setText("Terminal 01 - " + hashMap.get("ter1"));
        btnTremi2.setText("Terminal 02 - " + hashMap.get("ter2"));
        btnTremi3.setText("Terminal 03 - " + hashMap.get("ter3"));
        btnTremi4.setText("Terminal 04 - " + hashMap.get("ter4"));
        btnTremi5.setText("Terminal 05 - " + hashMap.get("ter5"));
    }

    private void totalCalculater(ArrayList<Object[]> setTerminalDetail) {
        BigDecimal amount1 = new BigDecimal(0);
        BigDecimal amount2 = new BigDecimal(0);
        for (Object[] objects : setTerminalDetail) {
            amount1 = amount1.add(new BigDecimal(objects[2].toString()));
            amount2 = amount2.add(new BigDecimal(objects[3].toString()));
        }
        model.addRow(new Object[]{"", 0.00, amount1, amount2, ""});
    }

    private void setTerminalDetail(int temId) {
        selectedTerminal = temId;
        try {
            lblProcess.setText("terminal " + temId + " is selected !");
            System.out.println("terminal " + temId + " is selected !");
            terminalDetail = SyncController.getInstance().setTerminalDetail(temId, txtDate.getText(), loginUser);
            for (Object[] objects : terminalDetail) {
                model.addRow(objects);
            }
            totalCalculater(terminalDetail);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
