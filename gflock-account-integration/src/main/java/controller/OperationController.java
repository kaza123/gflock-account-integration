/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_connections.DataSourceWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.log4j.Logger;
import service.ConnectionService;

/**
 *
 * @author 'Kasun Chamara'
 */
public class OperationController {

    private static OperationController instance;
    private final DataSourceWrapper operationDataSourceWrapper;
    private static final Logger LOGGER = Logger.getLogger(OperationController.class);

    public OperationController() throws SQLException {

        this.operationDataSourceWrapper = new ConnectionService().getInstance().getOperationDataSourceWrapper();
    }

    public static OperationController getInstance() throws SQLException {
        if (instance == null) {
            instance = new OperationController();
        }
        return instance;
    }

    public ArrayList<Grn> getNotCheckGrnList(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select grn.*\n"
                    + "from grn where grn.enter_date <= ? and grn.`check`=0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Grn> list = new ArrayList<>();
            while (resultSet.next()) {
                Grn grn = new Grn();
                grn.setIndexNo(resultSet.getInt(1));
                grn.setGrnNo(resultSet.getString(2));
                grn.setEnterDate(resultSet.getString(3));
                grn.setEnterTime(resultSet.getString(4));
                grn.setUpdatedDate(resultSet.getString(5));
                grn.setUpdatedTime(resultSet.getString(6));
                grn.setSupNo(resultSet.getString(7));
                grn.setSupName(resultSet.getString(8));
                grn.setRefNo(resultSet.getString(9));
                grn.setTotalValue(resultSet.getBigDecimal(10));
                grn.setNbt(resultSet.getBigDecimal(11));
                grn.setNbtValue(resultSet.getBigDecimal(12));
                grn.setVat(resultSet.getBigDecimal(13));
                grn.setVatValue(resultSet.getBigDecimal(14));
                grn.setFinalValue(resultSet.getBigDecimal(15));
                grn.setBranch(resultSet.getInt(16));
                grn.setCheck(resultSet.getBoolean(17));
                grn.setCreditPeriod(resultSet.getInt(18));

                list.add(grn);
            }
            return list;
        }
    }

    public List<GrnDetail> getGrnDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        String query = "select grn_detail.*\n"
                + "from grn_detail where grn_detail.grn=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<GrnDetail> list = new ArrayList<>();
        while (resultSet.next()) {
            GrnDetail detail = new GrnDetail();
            detail.setIndexNo(resultSet.getInt(1));
            detail.setGrn(resultSet.getInt(2));
            detail.setItemNo(resultSet.getString(3));
            detail.setItemName(resultSet.getString(4));
            detail.setItemType(resultSet.getString(5));
            detail.setItemUnit(resultSet.getString(6));
            detail.setItemBarcode(resultSet.getString(7));
            detail.setReorderMax(resultSet.getBigDecimal(8));
            detail.setReorderMin(resultSet.getBigDecimal(9));
            detail.setCost(resultSet.getBigDecimal(10));
            detail.setQty(resultSet.getBigDecimal(11));
            detail.setValue(resultSet.getBigDecimal(12));
            detail.setDiscount(resultSet.getBigDecimal(13));
            detail.setDiscountValue(resultSet.getBigDecimal(14));
            detail.setNetValue(resultSet.getBigDecimal(15));
            detail.setSalesPrice(resultSet.getBigDecimal(16));
            list.add(detail);
        }
        return list;

    }

    public Integer updateGrn(Grn grn, Connection connection) throws SQLException {
        String insertSql = "UPDATE grn set `check` = true ,updated_date=? , updated_time=?\n"
                + "WHERE index_no=?";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        preparedStatement.setString(2, new SimpleDateFormat("HH:mm:ss").format(new Date()));
        preparedStatement.setInt(3, grn.getIndexNo());

        return preparedStatement.executeUpdate();

    }

    public ArrayList<Invoice> getNotCheckInvoiceList(String date) throws SQLException {
        ArrayList<StockAdjustment> pendingAdjustmentList = getNotCheckStockAdjustmentList(date);
        if (!pendingAdjustmentList.isEmpty()) {
            System.out.println("There is " + pendingAdjustmentList.size() + " pending Stock Adjustment.first of all integrate Stock Adjustment to Account system !");
            throw new RuntimeException("There is " + pendingAdjustmentList.size() + " pending Stock Adjustment.first of all integrate Stock Adjustment to Account system !");
        }
        ArrayList<Grn> pendingGrnList = getNotCheckGrnList(date);
        if (!pendingGrnList.isEmpty()) {
            System.out.println("There is " + pendingGrnList.size() + " pending Grn.first of all integrate grn to Account system !");
            throw new RuntimeException("There is " + pendingGrnList.size() + " pending Grn.first of all integrate grn to Account system !");
        }
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select invoice.*\n"
                    + "from invoice where invoice.enter_date <= ? and invoice.`check`=0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Invoice> list = new ArrayList<>();
            while (rst.next()) {
                Invoice invoice = new Invoice();
                invoice.setIndexNo(rst.getInt(1));
                invoice.setClientNo(rst.getString(2));
                invoice.setClientName(rst.getString(3));
                invoice.setClientRecident(rst.getString(4));
                invoice.setVehicleNo(rst.getString(5));
                invoice.setVehicleType(rst.getString(6));
                invoice.setEnterDate(rst.getString(7));
                invoice.setEnterTime(rst.getString(8));
                invoice.setUpdatedDate(rst.getString(9));
                invoice.setUpdatedTime(rst.getString(10));
                invoice.setInvoiceNo(rst.getString(11));
                invoice.setAmount(rst.getBigDecimal(12));
                invoice.setDiscountRate(rst.getBigDecimal(13));
                invoice.setDiscountAmount(rst.getBigDecimal(14));
                invoice.setNetAmount(rst.getBigDecimal(15));
                invoice.setNbtRate(rst.getBigDecimal(16));
                invoice.setNbtValue(rst.getBigDecimal(17));
                invoice.setVatRate(rst.getBigDecimal(18));
                invoice.setVatValue(rst.getBigDecimal(19));
                invoice.setFinalValue(rst.getBigDecimal(20));
                invoice.setBranch(rst.getInt(21));
                invoice.setCheck(rst.getBoolean(22));
                list.add(invoice);
            }
            return list;
        }

    }

    public List<InvoiceDetail> getInvoiceDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        String query = "select invoice_detail.*\n"
                + "from invoice_detail where invoice_detail.invoice=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<InvoiceDetail> list = new ArrayList<>();
        while (resultSet.next()) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setIndexNo(resultSet.getInt(1));
            detail.setInvoice(resultSet.getInt(2));
            detail.setItemNo(resultSet.getString(3));
            detail.setItemName(resultSet.getString(4));
            detail.setItemType(resultSet.getString(5));
            detail.setItemUnit(resultSet.getString(6));
            detail.setItemBarcode(resultSet.getString(7));
            detail.setCost(resultSet.getBigDecimal(8));
            detail.setSalesPrice(resultSet.getBigDecimal(9));
            detail.setQty(resultSet.getBigDecimal(10));
            detail.setStockRemoveQty(resultSet.getBigDecimal(11));
            detail.setValue(resultSet.getBigDecimal(12));
            detail.setIsZeroItem(resultSet.getBoolean(13));
            list.add(detail);
        }
        return list;
    }

    public Integer updateInvoice(Invoice invoice, Connection operaConnection) throws SQLException {
        String insertSql = "UPDATE invoice set `check` = true ,updated_date=? , updated_time=?\n"
                + "WHERE index_no=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(insertSql);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        preparedStatement.setString(2, new SimpleDateFormat("HH:mm:ss").format(new Date()));
        preparedStatement.setInt(3, invoice.getIndexNo());

        return preparedStatement.executeUpdate();
    }

    public ArrayList<Payment> getNotCheckPaymentList(String date) throws SQLException {
        ArrayList<Invoice> pendingInvoiceList = getNotCheckInvoiceList(date);
        if (!pendingInvoiceList.isEmpty()) {
            System.out.println("There is " + pendingInvoiceList.size() + " pending Invoice.first of all integrate invoice to Account system !");
            throw new RuntimeException("There is " + pendingInvoiceList.size() + " pending Invoice.first of all integrate invoice to Account system !");
        }
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select payment.*\n"
                    + "from payment where payment.enter_date <= ? and payment.`check`=0\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<Payment> list = new ArrayList<>();
            while (rst.next()) {
                Payment paymemt = new Payment();
                paymemt.setIndexNo(rst.getInt(1));
                paymemt.setNumber(rst.getString(2));
                paymemt.setClientNo(rst.getString(3));
                paymemt.setClientName(rst.getString(4));
                paymemt.setEnterDate(rst.getString(5));
                paymemt.setEnterTime(rst.getString(6));
                paymemt.setUpdatedDate(rst.getString(7));
                paymemt.setUpdatedTime(rst.getString(8));
                paymemt.setTotalAmount(rst.getBigDecimal(9));
                paymemt.setCashAmount(rst.getBigDecimal(10));
                paymemt.setChequeAmount(rst.getBigDecimal(11));
                paymemt.setCardAmount(rst.getBigDecimal(12));
                paymemt.setOverPaymentAmount(rst.getBigDecimal(13));
                paymemt.setBranch(rst.getInt(14));
                paymemt.setCheck(rst.getBoolean(15));
                paymemt.setIsDownPayment(rst.getBoolean(16));
                list.add(paymemt);
            }
            return list;
        }
    }

    public Integer updatePayment(Integer payment, Connection operaConnection) throws SQLException {
        String insertSql = "UPDATE payment set `check` = true ,updated_date=? , updated_time=?\n"
                + "WHERE index_no=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(insertSql);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        preparedStatement.setString(2, new SimpleDateFormat("HH:mm:ss").format(new Date()));
        preparedStatement.setInt(3, payment);

        return preparedStatement.executeUpdate();
    }

    public List<PaymentDetail> getPaymentDetail(Integer indexNo, Connection operaConnection) throws SQLException {
        String query = "select payment_detail.*\n"
                + "from payment_detail\n"
                + "WHERE payment_detail.payment=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<PaymentDetail> list = new ArrayList<>();
        while (resultSet.next()) {
            PaymentDetail detail = new PaymentDetail();
            detail.setIndexNo(resultSet.getInt(1));
            detail.setInvoice(resultSet.getString(2));
            detail.setPayment(resultSet.getInt(3));
            detail.setAmount(resultSet.getBigDecimal(4));
            list.add(detail);
        }
        return list;
    }

    public List<PaymentInformation> getPaymentInformations(Integer payment, Connection operaConnection) throws SQLException {
        String query = "select payment_information.*\n"
                + "from payment_information\n"
                + "WHERE payment_information.payment=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setInt(1, payment);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<PaymentInformation> list = new ArrayList<>();
        while (resultSet.next()) {
            PaymentInformation detail = new PaymentInformation();
            detail.setIndexNo(resultSet.getInt(1));
            detail.setPayment(resultSet.getInt(2));
            detail.setType(resultSet.getString(3));
            detail.setChequeDate(resultSet.getString(4));
            detail.setAmount(resultSet.getBigDecimal(5));
            detail.setBank(resultSet.getString(6));
            detail.setBankBranch(resultSet.getString(7));
            detail.setCardType(resultSet.getString(8));
            detail.setCardReader(resultSet.getString(9));
            detail.setNumber(resultSet.getString(10));
            list.add(detail);
        }
        return list;
    }

    public Integer getNotCheckGrnCount(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select count(grn.index_no) as count\n"
                    + "from grn where grn.enter_date <= ? and grn.`check`=0";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 0;
        }
    }

    public Integer getNotCheckInvoiceCount(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select count(invoice.index_no) as count\n"
                    + "from invoice where invoice.enter_date <= ? and invoice.`check`=0";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 0;
        }
    }

    public Integer getNotCheckPaymentCount(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select count(payment.index_no) as count\n"
                    + "from payment where payment.enter_date <= ? and payment.`check`=0";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 0;
        }
    }

    public Integer getNotCheckStockAdjustmentCount(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select count(stock_adjustment.index_no) as count\n"
                    + "from stock_adjustment where stock_adjustment.enter_date <= ? and stock_adjustment.`check`=0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 0;
        }
    }

    public ArrayList<StockAdjustment> getNotCheckStockAdjustmentList(String date) throws SQLException {

        ArrayList<Grn> pendingGrnList = getNotCheckGrnList(date);
        if (!pendingGrnList.isEmpty()) {
            System.out.println("There is " + pendingGrnList.size() + " pending Grn.first of all integrate grn to Account system !");
            throw new RuntimeException("There is " + pendingGrnList.size() + " pending Grn.first of all integrate grn to Account system !");
        }
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select stock_adjustment.*\n"
                    + "from stock_adjustment where stock_adjustment.enter_date <= ? and stock_adjustment.`check`=0\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            ArrayList<StockAdjustment> list = new ArrayList<>();
            while (rst.next()) {
                StockAdjustment adjustment = new StockAdjustment();
                adjustment.setIndexNo(rst.getInt(1));
                adjustment.setEnterDate(rst.getString(2));
                adjustment.setEnterTime(rst.getString(3));
                adjustment.setUpdatedDate(rst.getString(4));
                adjustment.setUpdatedTime(rst.getString(5));
                adjustment.setBranch(rst.getInt(6));
                adjustment.setCheck(rst.getBoolean(7));
                adjustment.setRefNo(rst.getString(8));
                adjustment.setFormType(rst.getString(9));
                list.add(adjustment);
            }
            return list;
        }
    }

    public List<StockAdjustmentDetail> getAdjustmentDetail(Integer indexNo, Connection operaConnection) throws SQLException {

        String query = "select stock_adjustment_detail.*\n"
                + "from stock_adjustment_detail where stock_adjustment_detail.stock_adjustment=?\n"
                + "order by stock_adjustment_detail.qty asc";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<StockAdjustmentDetail> list = new ArrayList<>();
        while (resultSet.next()) {
            StockAdjustmentDetail detail = new StockAdjustmentDetail();
            detail.setIndexNo(resultSet.getInt(1));
            detail.setStockAdjustment(resultSet.getInt(2));
            detail.setItemNo(resultSet.getString(3));
            detail.setItemName(resultSet.getString(4));
            detail.setItemUnit(resultSet.getString(5));
            detail.setBarcode(resultSet.getString(6));
            detail.setCostPrice(resultSet.getBigDecimal(7));
            detail.setQty(resultSet.getBigDecimal(8));
            list.add(detail);
        }
        return list;
    }

    public Integer updateAdjustment(Integer indexNo, Connection operaConnection) throws SQLException {
        String insertSql = "UPDATE stock_adjustment set `check` = true ,updated_date=? , updated_time=?\n"
                + "WHERE index_no=?";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(insertSql);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        preparedStatement.setString(2, new SimpleDateFormat("HH:mm:ss").format(new Date()));
        preparedStatement.setInt(3, indexNo);

        return preparedStatement.executeUpdate();
    }

    public String getTransactionDate() throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String query = "select transaction_date.date from transaction_date where transaction_date.index_no=1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            throw new RuntimeException("Get Transaction Date Fail");
        }
    }

    public String getNextDate(String date) throws SQLException, ParseException {

        ArrayList<Invoice> pendingInvoiceList = getNotCheckInvoiceList(date);
        if (!pendingInvoiceList.isEmpty()) {
            System.out.println("There is " + pendingInvoiceList.size() + " pending Invoice.first of all integrate invoice to Account system !");
            throw new RuntimeException("There is " + pendingInvoiceList.size() + " pending Invoice.first of all integrate invoice to Account system !");
        }
        ArrayList<Payment> pendingPaymentList = getNotCheckPaymentList(date);
        if (!pendingPaymentList.isEmpty()) {
            System.out.println("There is " + pendingPaymentList.size() + " pending Payment.first of all integrate Payment to Account system !");
            throw new RuntimeException("There is " + pendingPaymentList.size() + " pending Payment.first of all integrate Payment to Account system !");
        }

        String dt = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 1);
        dt = sdf.format(c.getTime());

        return setNewDate(dt);
    }

    private String setNewDate(String date) throws SQLException {
        try (Connection connection = operationDataSourceWrapper.getConnection()) {
            String insertSql = "UPDATE transaction_date SET transaction_date.date=? WHERE  transaction_date.index_no=1;";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, date);
            preparedStatement.executeUpdate();
        }
        return date;

    }

    public String getCustomerNoByInvoice(String invoice, Connection operaConnection) throws SQLException {

        String query = "select invoice.client_no from invoice where invoice.invoice_no=?\n"
                + "limit 1";
        PreparedStatement preparedStatement = operaConnection.prepareStatement(query);
        preparedStatement.setString(1, invoice);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        throw new RuntimeException("Get Customer Number By Invoice is Fail !");
    }
}
