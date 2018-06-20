/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync_controller;

import java.sql.SQLException;
import controller.TransactionController;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 'Kasun Chamara'
 */
public class SyncController {

    private static SyncController instance;

    public static SyncController getInstance() throws SQLException {
        if (instance == null) {
            instance = new SyncController();
        }

        return instance;
    }

    public SyncController() throws SQLException {
    }

    public Integer checkLoginUser(String name, String pswd) throws SQLException {
        return TransactionController.getInstance().checkLoginUser(name,pswd);
    }

    public HashMap getDetailCount(String date, int[] terList) throws SQLException {
        return TransactionController.getInstance().getDetailCount(date,terList);
    }

    public ArrayList<Object[]> setTerminalDetail(int temId, String date, Integer loginUser) throws SQLException {
        return TransactionController.getInstance().setTerminalDetail(temId,date,loginUser);
        
    }


}
