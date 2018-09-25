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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
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
//    JDialog dialog = new JDialog(this, true);
    JSplitPane pain;
    public JLabel lblMain=new JLabel("Select a terminal to view details !", JLabel.CENTER);
        
    JSplitPane splitPane;

    public SystemIntegrationSyncGUI() {
        this.terminalDetail = new ArrayList<>();
        this.selectedTerminal = -1;
        initComponents();
        model = (DefaultTableModel) tblData.getModel();
        initOthers();
        pain = splitePain;
        lblProcess.setText("");
        lblMain.setBackground(Color.white);
        lblMain.setOpaque(true);
        txtLog.setBackground(Color.white);
        txtLog.setForeground(Color.BLACK);
        txtLog.setFont(new java.awt.Font("Bodoni MT", 1, 12));
        
        try {
            terminalList[0] = 1;
            terminalList[1] = 2;
            terminalList[2] = 3;
            terminalList[3] = 4;
            terminalList[4] = 5;

            setButtonValue(getDetailCount(lblDate.getText(), terminalList));

        } catch (SQLException ex) {
            System.out.println("get detail count function some error !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        try {
            setTitle("Account Integration System");
            setLocationRelativeTo(null);
            txtLog.setEditable(false);
            String date = getSyncDate();
            lblDate.setText(date);

            //set company
            String companyName = TransactionController.getInstance().getCompanyName();
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

            setJSplitPane("main");

        } catch (SQLException ex) {
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLoginUser(Integer loginUser) {
        this.loginUser = loginUser;
        System.out.println("login User -> " + loginUser);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitePain = new javax.swing.JSplitPane();
        jLabel2 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTem2 = new javax.swing.JLabel();
        lblTem1 = new javax.swing.JLabel();
        lblTem3 = new javax.swing.JLabel();
        lblTem4 = new javax.swing.JLabel();
        lblTem5 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblDateCompleted = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        lblProcess = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        lblTem6 = new javax.swing.JLabel();
        lblTem7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(550, 330));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        splitePain.setDividerLocation(470);
        splitePain.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel2.setText("jLabel2");
        splitePain.setTopComponent(jLabel2);

        txtLog.setBackground(new java.awt.Color(0, 0, 0));
        txtLog.setColumns(20);
        txtLog.setForeground(new java.awt.Color(0, 153, 0));
        txtLog.setRows(5);
        scrollPane.setViewportView(txtLog);

        splitePain.setRightComponent(scrollPane);

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

        splitePain.setTopComponent(jScrollPane1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bodoni MT", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Date :");

        lblTem2.setBackground(new java.awt.Color(50, 102, 153));
        lblTem2.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem2.setText("Terminal 02");
        lblTem2.setOpaque(true);
        lblTem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem2MouseClicked(evt);
            }
        });

        lblTem1.setBackground(new java.awt.Color(0, 102, 153));
        lblTem1.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem1.setText("Terminal 01");
        lblTem1.setOpaque(true);
        lblTem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem1MouseClicked(evt);
            }
        });

        lblTem3.setBackground(new java.awt.Color(100, 102, 153));
        lblTem3.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem3.setText("Terminal 03");
        lblTem3.setOpaque(true);
        lblTem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem3MouseClicked(evt);
            }
        });

        lblTem4.setBackground(new java.awt.Color(150, 102, 153));
        lblTem4.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem4.setText("Terminal 04");
        lblTem4.setOpaque(true);
        lblTem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem4MouseClicked(evt);
            }
        });

        lblTem5.setBackground(new java.awt.Color(150, 80, 133));
        lblTem5.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem5.setText("Terminal 05");
        lblTem5.setOpaque(true);
        lblTem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem5MouseClicked(evt);
            }
        });

        lblDate.setFont(new java.awt.Font("Bodoni MT", 1, 12)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("Date goes here ");

        lblDateCompleted.setBackground(new java.awt.Color(0, 153, 153));
        lblDateCompleted.setFont(new java.awt.Font("Bodoni MT", 1, 16)); // NOI18N
        lblDateCompleted.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateCompleted.setText("Next Date");
        lblDateCompleted.setOpaque(true);
        lblDateCompleted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDateCompletedMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTem2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTem3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTem5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(lblDateCompleted, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDateCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new java.awt.BorderLayout());

        lblProcess.setFont(new java.awt.Font("Bodoni MT", 1, 14)); // NOI18N
        lblProcess.setText("Selected terminal goes here ");

        lblCompanyName.setFont(new java.awt.Font("Bodoni MT", 1, 14)); // NOI18N
        lblCompanyName.setText("Branch name goes here");

        lblTem6.setBackground(new java.awt.Color(0, 153, 153));
        lblTem6.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem6.setText("SYNC");
        lblTem6.setOpaque(true);
        lblTem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem6MouseClicked(evt);
            }
        });

        lblTem7.setBackground(new java.awt.Color(252, 65, 64));
        lblTem7.setFont(new java.awt.Font("Bodoni MT", 1, 13)); // NOI18N
        lblTem7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTem7.setText("Clear");
        lblTem7.setOpaque(true);
        lblTem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTem7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTem7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTem7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTem6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCompanyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(splitePain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(splitePain, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(SystemIntegrationSyncGUI.EXIT_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing

    private void lblTem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem1MouseClicked
        if (optionPain() == 0) {
            setJSplitPane("loding");
            new SwingWorker<Void, String>() {
                Integer dataSize;

                @Override
                protected Void doInBackground() throws Exception {
                    // Worken hard or hardly worken...
                    dataSize = setTerminalDetail(1);
//                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    if (dataSize <= 0) {
                        setJSplitPane("main");
                    } else {
                        setJSplitPane("data");
                    }

                }
            }.execute();
        }
    }//GEN-LAST:event_lblTem1MouseClicked

    private void lblTem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem2MouseClicked
        if (optionPain() == 0) {
            setJSplitPane("loding");
            new SwingWorker<Void, String>() {
                Integer dataSize;

                @Override
                protected Void doInBackground() throws Exception {
                    // Worken hard or hardly worken...
                    dataSize = setTerminalDetail(2);
//                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    if (dataSize <= 0) {
                        setJSplitPane("main");
                    } else {
                        setJSplitPane("data");
                    }

                }
            }.execute();
        }
    }//GEN-LAST:event_lblTem2MouseClicked

    private void lblTem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem3MouseClicked
        if (optionPain() == 0) {
            setJSplitPane("loding");
            new SwingWorker<Void, String>() {
                Integer dataSize;

                @Override
                protected Void doInBackground() throws Exception {
                    // Worken hard or hardly worken...
                    dataSize = setTerminalDetail(3);
//                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    if (dataSize <= 0) {
                        setJSplitPane("main");
                    } else {
                        setJSplitPane("data");
                    }

                }
            }.execute();
        }
    }//GEN-LAST:event_lblTem3MouseClicked

    private void lblTem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem4MouseClicked
        if (optionPain() == 0) {
            setJSplitPane("loding");
            new SwingWorker<Void, String>() {
                Integer dataSize;

                @Override
                protected Void doInBackground() throws Exception {
                    // Worken hard or hardly worken...
                    dataSize = setTerminalDetail(4);
//                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    if (dataSize <= 0) {
                        setJSplitPane("main");
                    } else {
                        setJSplitPane("data");
                    }

                }
            }.execute();
        }
    }//GEN-LAST:event_lblTem4MouseClicked

    private void lblTem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem5MouseClicked
        if (optionPain() == 0) {
            setJSplitPane("loding");
            new SwingWorker<Void, String>() {
                Integer dataSize;

                @Override
                protected Void doInBackground() throws Exception {
                    // Worken hard or hardly worken...
                    dataSize = setTerminalDetail(5);
//                    Thread.sleep(2000);
                    return null;
                }

                @Override
                protected void done() {
                    if (dataSize <= 0) {
                        setJSplitPane("main");
                    } else {
                        setJSplitPane("data");
                    }

                }
            }.execute();
        }
    }//GEN-LAST:event_lblTem5MouseClicked

    private void lblDateCompletedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDateCompletedMouseClicked
        if (optionPain() == 0) {
            try {
                Integer count = TransactionController.getInstance().getNotCheckDataCount(lblDate.getText());
                if (count > 0) {
                    System.out.println("There are " + count + " detail(s) to sync ! please sync all !");
                } else {
                    String date = TransactionController.getInstance().getNextDate(lblDate.getText());
                    lblDate.setText(date);
                    clear();
                    selectedTerminal = -1;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                System.out.println("Can't find next date !");
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblDateCompletedMouseClicked

    private void lblTem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem6MouseClicked
        if (optionPain() == 0) {
            if (selectedTerminal == -1) {
                System.out.println("Select a terminal to sync data !");
                throw new RuntimeException("Select a terminal to sync data !");
            }
            System.out.println("processing terminal " + selectedTerminal + " data ");
            try {
                Object[] saveObject = TransactionController.getInstance().saveAccount(selectedTerminal, loginUser, terminalDetail, lblDate.getText());
                if (saveObject.length > 0) {
                    clear();
                    selectedTerminal = -1;
                    System.out.println("terminal " + selectedTerminal + " sync success ");
                    System.out.println("Total Debit : " + saveObject[0] + " Total Credit : " + saveObject[1]);
                    System.out.println("Search code is " + saveObject[2]);

                } else {
                    JOptionPane.showMessageDialog(null, "terminal " + selectedTerminal + " sync fail !");

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblTem6MouseClicked

    private void lblTem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTem7MouseClicked
        clear();
        selectedTerminal = -1;
    }//GEN-LAST:event_lblTem7MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDateCompleted;
    private javax.swing.JLabel lblProcess;
    private javax.swing.JLabel lblTem1;
    private javax.swing.JLabel lblTem2;
    private javax.swing.JLabel lblTem3;
    private javax.swing.JLabel lblTem4;
    private javax.swing.JLabel lblTem5;
    private javax.swing.JLabel lblTem6;
    private javax.swing.JLabel lblTem7;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSplitPane splitePain;
    private javax.swing.JTable tblData;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

    private HashMap getDetailCount(String date, int[] terList) throws SQLException {
        return SyncController.getInstance().getDetailCount(date, terList);
    }

    private int optionPain() {
        return JOptionPane.showConfirmDialog(null, "Are you sure ?", "Warning", JOptionPane.YES_OPTION);
    }

    private void setButtonValue(HashMap hashMap) {
        lblTem1.setText("Terminal 01 - " + hashMap.get("ter1"));
        lblTem2.setText("Terminal 02 - " + hashMap.get("ter2"));
        lblTem3.setText("Terminal 03 - " + hashMap.get("ter3"));
        lblTem4.setText("Terminal 04 - " + hashMap.get("ter4"));
        lblTem5.setText("Terminal 05 - " + hashMap.get("ter5"));
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

    private Integer setTerminalDetail(int temId) {
        selectedTerminal = temId;
        model.setRowCount(0);
        try {
            lblProcess.setText("Terminal " + temId + " is Selected !");
            System.out.println("Terminal " + temId + " is Selected !");
            terminalDetail = SyncController.getInstance().setTerminalDetail(temId, lblDate.getText(), loginUser);
            for (Object[] objects : terminalDetail) {
                model.addRow(objects);
            }
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            totalCalculater(terminalDetail);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return terminalDetail.size();
    }

    private String getSyncDate() throws SQLException {
        return TransactionController.getInstance().getSyncDate();
    }

    private Integer setJSplitPane(String label) {
        JScrollPane scrollPanelLeft = new JScrollPane(lblMain);
        JScrollPane scrollPanelRight = new JScrollPane(txtLog);
        panel.removeAll();

        if ("main".equals(label)) {
            scrollPanelLeft = new JScrollPane(lblMain);
        } else if ("loding".equals(label)) {
            ImageIcon loading2 = new ImageIcon("./images/loder.gif");
            scrollPanelLeft = new JScrollPane(new JLabel("", loading2, JLabel.CENTER));
        } else if ("data".equals(label)) {
            scrollPanelLeft = new JScrollPane(tblData);
        }

        // put two JScrollPane into SplitPane 
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                scrollPanelLeft, scrollPanelRight);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(470);
        panel.add(splitPane, BorderLayout.CENTER);
        revalidate();
        repaint();
        return 1;
    }

    private void clear() {
        txtLog.setText("");
        lblProcess.setText("");
        model.setRowCount(0);
        try {
            String date = getSyncDate();
            lblDate.setText(date);
            System.out.println("Data is cleared !");

            setButtonValue(getDetailCount(lblDate.getText(), terminalList));
        } catch (SQLException ex) {
            System.out.println("Clear buttom some error !");
            Logger.getLogger(SystemIntegrationSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
