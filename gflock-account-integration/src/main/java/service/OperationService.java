/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.OperationController;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.operation_model.Grn;
import model.operation_model.GrnDetail;
import model.operation_model.Invoice;
import model.operation_model.InvoiceDetail;
import model.operation_model.Payment;
import model.operation_model.PaymentDetail;
import model.operation_model.PaymentInformation;
import model.operation_model.StockAdjustment;
import model.operation_model.StockAdjustmentDetail;

/**
 *
 * @author chama
 */
public class OperationService {

    public static List<GrnDetail> getGrnDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().getGrnDetail(indexNo, operaConnection);
    }

    public static List<InvoiceDetail> getInvoiceDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().getInvoiceDetail(indexNo, operaConnection);
    }

    public static Integer updateInvoice(Invoice invoice, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().updateInvoice(invoice,operaConnection);
    }

    public static List<PaymentDetail> getPaymentDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().getPaymentDetail(indexNo, operaConnection);
    }

    public static List<PaymentInformation> getPaymentInformations(Integer indexNo, Connection operaConnection)throws SQLException {
        return OperationController.getInstance().getPaymentInformations(indexNo, operaConnection);
    }

    public static List<StockAdjustmentDetail> getAdjustmentDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().getAdjustmentDetail(indexNo,operaConnection);
    }

    public static Integer updateAdjustment(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().updateAdjustment(indexNo,operaConnection);
    }

    public static String getCustomerNoByInvoice(String invoice, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().getCustomerNoByInvoice(invoice,operaConnection);
    }

    public ArrayList<Grn> getNotCheckGrnList(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckGrnList(date);
    }

    public static Integer updateGrn(Grn grn,Connection connection) throws SQLException {
        return OperationController.getInstance().updateGrn(grn,connection);
    }

    public ArrayList<Invoice> getNotCheckInvoiceList(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckInvoiceList(date);
    }

    public ArrayList<Payment> getNotCheckPaymentList(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckPaymentList(date);
    }

    public static Integer updatePayment(Integer indexNo, Connection operaConnection) throws SQLException {
        return OperationController.getInstance().updatePayment(indexNo,operaConnection);
    }

    public Integer getNotGrnCount(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckGrnCount(date);
    }

    public Integer getNotCheckInvoiceCount(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckInvoiceCount(date);
    }

    public Integer getNotCheckPaymentCount(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckPaymentCount(date);
    }

    public Integer getNotStockAdjustmentCount(String date) throws SQLException {
        return OperationController.getInstance().getNotCheckStockAdjustmentCount(date);
    }

    public ArrayList<StockAdjustment> getNotCheckStockAdjustmentList(String date) throws SQLException {
         return OperationController.getInstance().getNotCheckStockAdjustmentList(date);
    }

    public String getTransactionDate() throws SQLException {
        return OperationController.getInstance().getTransactionDate();
    }

    public String getNextDate(String date) throws SQLException, ParseException {
        return OperationController.getInstance().getNextDate(date);
    }
}
