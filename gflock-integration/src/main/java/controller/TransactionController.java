/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_connections.DataSourceWrapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JScrollBar;

/**
 *
 * @author chama
 */
public class TransactionController {

    private static TransactionController instance;
    private final DataSourceWrapper accountDataSourceWrapper;
    private final DataSourceWrapper operaDataSourceWrapper;

    public TransactionController() throws SQLException {

//        
        this.accountDataSourceWrapper = ConnectionController.getInstance().getAccuntDataSourceWrapper();
        this.operaDataSourceWrapper = ConnectionController.getInstance().getOperationDataSourceWrapper();

    }

    public static TransactionController getInstance() throws SQLException {
        if (instance == null) {
            instance = new TransactionController();
        }
        return instance;
    }

    public Integer checkLoginUser(String name, String pswd) {
        Connection accConnection = null;
        Integer loginUser = -1;
        try {
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            // Execute a query to create statment
            loginUser = AccountController.checkLoginUser(name, pswd, accConnection);

            //commit
            accConnection.commit();

            //Clean-up environment
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);
                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked success !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return loginUser;

    }

    public String getCompanyName() {
        Connection accConnection = null;
        String companyName = "";
        try {
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            companyName = AccountController.getCompanyName(accConnection);

            //commit
            accConnection.commit();

            //Clean-up environment
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return companyName;
    }

    public HashMap getDetailCount(String date, int[] terList) {
        Connection accConnection = null;
        HashMap map = new HashMap();
        try {
            accConnection = operaDataSourceWrapper.getConnection();
            map = AccountController.getDetailCount(date, terList, accConnection);
            accConnection.close();
            return map;
        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return map;
    }

    public String getSyncDate() {
        Connection accConnection = null;
        String date = String.valueOf(new Date());
        try {
            accConnection = accountDataSourceWrapper.getConnection();
            date = AccountController.getSyncDate(accConnection);
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return date;
    }

    public String getNextDate(String text) {
        Connection accConnection = null;
        String date = "";
        try {
            accConnection = accountDataSourceWrapper.getConnection();
            date = AccountController.getNextDate(text, accConnection);
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR get next date ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return date;
    }

    public ArrayList<Object[]> setTerminalDetail(int temId, String date, Integer loginUser) {
        Connection accConnection = null;
        ArrayList<Object[]> setTerminalDetail = null;

        try {
            accConnection = operaDataSourceWrapper.getConnection();
            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            setTerminalDetail = AccountController.setTerminalDetail(temId, date, loginUser, accConnection);

            //commit
            accConnection.commit();

            //Clean-up environment
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return setTerminalDetail;
    }

    public Object[] saveAccount(int selectedTerminal, Integer loginUser, ArrayList<Object[]> terminalDetail, String date) {
        Connection accConnection = null;
        Connection operaConnection = null;
        Object[] saveAccount=new Object[]{};

        try {
            accConnection = accountDataSourceWrapper.getConnection();
            operaConnection = operaDataSourceWrapper.getConnection();
            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            saveAccount = AccountController.saveAccount(selectedTerminal, date, loginUser, terminalDetail, accConnection,operaConnection);

            //commit
            accConnection.commit();

            //Clean-up environment
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return saveAccount;
    }

    public Integer getNotCheckDataCount(String date) {
        Connection accConnection = null;
        Integer count=0;

        try {
            accConnection = operaDataSourceWrapper.getConnection();
            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            count = AccountController.getNotCheckDataCount(date,accConnection);

            //commit
            accConnection.commit();

            //Clean-up environment
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);

                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
        return count;
    }

}
