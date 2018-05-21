/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync_service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import model.operation_model.Grn;
import model.operation_model.Invoice;
import model.operation_model.Payment;
import model.operation_model.StockAdjustment;
import service.OperationService;
import service.TransactionService;

/**
 *
 * @author 'Kasun Chamara'
 */
public class SyncService {

    private final OperationService operationService;

    private static SyncService instance;

    public static SyncService getInstance() throws SQLException {
        if (instance == null) {
            instance = new SyncService();
        }

        return instance;
    }

    public SyncService() throws SQLException {
        this.operationService = new OperationService();
    }

    public void executeGrn(String date,Integer user) throws SQLException {

        ArrayList<Grn> grnList = operationService.getNotCheckGrnList(date);
        if (grnList.isEmpty()) {
            System.out.println("Integration Grn is empty!");
        } else {
            System.out.println("Finded " + grnList.size() + " Grn to Integrate with account System !");
        }
        for (Grn grn : grnList) {
            TransactionService.getInstance().saveGrn(grn,user);
        }
    }

    public void executeInvoice(String date,Integer user) throws SQLException {
        ArrayList<Invoice> invoiceList = operationService.getNotCheckInvoiceList(date);
        if (invoiceList.isEmpty()) {
            System.out.println("Integration Invoice is empty!");
        } else {
            System.out.println("Finded " + invoiceList.size() + " Invoice to Integrate with account System !");
        }
        for (Invoice invoice : invoiceList) {
            TransactionService.getInstance().saveInvoice(invoice,user);
        }

    }

    public void executePayment(String date,Integer user) throws SQLException {
        ArrayList<Payment> paymentList = operationService.getNotCheckPaymentList(date);
         if (paymentList.isEmpty()) {
            System.out.println("Integration Payment is empty!");
        } else {
            System.out.println("Finded " + paymentList.size() + " Payment to Integrate with account System !");
        }
        for (Payment payment : paymentList) {
            TransactionService.getInstance().savePayment(payment,user);
        }
    }

    public Integer getGrnCount(String date) throws SQLException {
        return operationService.getNotGrnCount(date);
    }

    public Integer getInvoiceCount(String date) throws SQLException {
        return operationService.getNotCheckInvoiceCount(date);
    }

    public Integer getPaymentCount(String date) throws SQLException {
        return operationService.getNotCheckPaymentCount(date);
    }

    public Integer checkLoginUser(String name, String pswd) throws SQLException {
        return TransactionService.getInstance().checkLoginUser(name,pswd);
    }

    public Integer getStockAdjustmentCount(String date) throws SQLException {
        return operationService.getNotStockAdjustmentCount(date);
    }

    public void executeStockAdjustment(String date, Integer user) throws SQLException {
        ArrayList<StockAdjustment> stockAdjustmentList = operationService.getNotCheckStockAdjustmentList(date);
        if (stockAdjustmentList.isEmpty()) {
            System.out.println("Integration Stock Adjustment is empty!");
        } else {
            System.out.println("Finded " + stockAdjustmentList.size() + " StockAdjustment to Integrate with account System !");
        }
        for (StockAdjustment adjustment : stockAdjustmentList) {
            TransactionService.getInstance().saveStockAdjustment(adjustment,user);
        }
    }

    public String getTransactionDate() throws SQLException {
        return operationService.getTransactionDate();
    }

    public String getNextDate(String date) throws SQLException, ParseException {
        return operationService.getNextDate(date);
    }

}
