/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import common.Constant;
import db_connections.DataSourceWrapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import model.operation_model.Grn;
import model.operation_model.GrnDetail;
import model.account_model.TTypeIndexDetail;
import model.operation_model.Invoice;
import model.operation_model.InvoiceDetail;
import model.operation_model.Payment;
import model.operation_model.PaymentDetail;
import model.operation_model.PaymentInformation;
import model.operation_model.StockAdjustment;

/**
 *
 * @author chama
 */
public class TransactionService {

    private static TransactionService instance;
    private final DataSourceWrapper operationDataSourceWrapper;
    private final DataSourceWrapper accountDataSourceWrapper;
//    private static final Logger LOGGER = Logger.getLogger(TransactionService.class);

    public TransactionService() throws SQLException {

//        
        this.operationDataSourceWrapper = ConnectionService.getInstance().getOperationDataSourceWrapper();
        this.accountDataSourceWrapper = ConnectionService.getInstance().getAccuntDataSourceWrapper();

    }

    public static TransactionService getInstance() throws SQLException {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    public Integer saveGrn(Grn grn, Integer user) {

        Connection operaConnection = null;
        Connection accConnection = null;
        try {
            //Open a connection
            operaConnection = operationDataSourceWrapper.getConnection();
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            operaConnection.setAutoCommit(false);
            accConnection.setAutoCommit(false);
            // Execute a query to create statment
            List<GrnDetail> grnDetail = OperationService.getGrnDetail(grn.getIndexNo(), operaConnection);
            if (grnDetail.isEmpty()) {
                throw new RuntimeException("Grn Detail was Empty !");
            }

            HashMap<Integer, Integer> supplierMap = new HashMap<>();
            TTypeIndexDetail typeIndexDetail;
            typeIndexDetail = AccountService.CheckTypeIndexDetail(Constant.SUPPLIER, grn.getSupNo(), accConnection);
            if (typeIndexDetail.getType() == null) {
                //type index detail save with supplier
                supplierMap = AccountService.saveSupplier(grn, user, accConnection);

                Integer typeIndexId = AccountService.saveTypeIndexDetail(grn.getSupNo(), Constant.SUPPLIER, supplierMap.get(1), supplierMap.get(2), accConnection);

                if (typeIndexId < 0) {
                    throw new RuntimeException("Type Index detail save fail !");
                }
                System.out.println("New Supplier ( " + grn.getSupName() + " ) Save Success !");

            } else {
                supplierMap.put(1, typeIndexDetail.getAccountRefId());
                supplierMap.put(2, typeIndexDetail.getAccountIndex());
                AccountService.updateSupplier(grn, supplierMap, accConnection);

            }
//          save grn
            Integer grnIndex = AccountService.saveGrn(grn, supplierMap, accConnection);

//          save acc ledger    
            HashMap<Integer, Object> ledgerMap = AccountService.saveAccountLedgerWithSupplierNbtVat(grn, supplierMap, grnIndex, user, accConnection);

//          save item
            HashMap<Integer, Integer> map = new HashMap<>();
            for (GrnDetail detail : grnDetail) {
//                type index check from item
                TTypeIndexDetail typeIndexDetailItem = AccountService.CheckTypeIndexDetail(Constant.ITEM, detail.getItemNo(), accConnection);
                if (typeIndexDetailItem.getType() == null) {

                    map = AccountService.saveItem(detail, accConnection);
                    //type index detail save with item
                    Integer typeIndexId = AccountService.saveTypeIndexDetail(detail.getItemNo(), Constant.ITEM, map.get(1), map.get(2), accConnection);

                    if (typeIndexId < 0) {
                        throw new RuntimeException("Type Index detail save fail !");
                    }
                    System.out.println("New Item( " + detail.getItemName() + " ) Save Success !");

                } else {
                    map.put(1, typeIndexDetailItem.getAccountRefId());
                    map.put(2, typeIndexDetailItem.getAccountIndex());
                    AccountService.updateItem(detail, map, accConnection);
                }
                detail.setGrn(grnIndex);

                String grnNo = AccountService.saveGrnDetail(detail, grn, map, user, accConnection);
                if (null == grnNo) {
                    throw new RuntimeException("Grn Number was empty or Grn save failed !");
                }

                //save acc ledger with item
                AccountService.saveAccLedgerWithItem(detail, grn.getBranch(), map, ledgerMap, user, accConnection);

            }
            Integer saveSupplierLedger = AccountService.saveSupplierLedger(grn, grnIndex, supplierMap.get(2), accConnection);
            if (saveSupplierLedger <= 0) {
                throw new RuntimeException("Supplier Ledger Save fail !");
            }

            Integer masterId = OperationService.updateGrn(grn, operaConnection);

            if (masterId > 0) {
                System.out.println(grn.getGrnNo() + " - " + grn.getFinalValue() + " - " + grn.getSupName() + " Grn Save Success !");
            } else {
                throw new RuntimeException("Grn Update fail !");
            }

            System.out.println(" ");
            //commit
            operaConnection.commit();
            accConnection.commit();

            //Clean-up environment
            operaConnection.close();
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);
                if (operaConnection != null) {
                    operaConnection.rollback();
                }
                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked success !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }

        return grn.getIndexNo();
    }

    public Integer saveInvoice(Invoice invoice, Integer user) {

        Connection operaConnection = null;
        Connection accConnection = null;
        try {
            //Open a connection
            operaConnection = operationDataSourceWrapper.getConnection();
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            operaConnection.setAutoCommit(false);
            accConnection.setAutoCommit(false);

//             Execute a query to create statment
            List<InvoiceDetail> invoiceDetail = OperationService.getInvoiceDetail(invoice.getIndexNo(), operaConnection);
            if (invoiceDetail.isEmpty()) {
                throw new RuntimeException("Invoice Detail was Empty !");
            }

            HashMap<Integer, Integer> customerMap = new HashMap<>();
            TTypeIndexDetail typeIndexDetail;
            typeIndexDetail = AccountService.CheckTypeIndexDetail(Constant.CUSTOMER, invoice.getClientNo(), accConnection);
            if (typeIndexDetail.getType() == null) {
                //type index detail save with customer
                customerMap = AccountService.saveCustomer(invoice, user, accConnection);

                Integer typeIndexId = AccountService.saveTypeIndexDetail(invoice.getClientNo(), Constant.CUSTOMER, customerMap.get(1), customerMap.get(2), accConnection);

                if (typeIndexId < 0) {
                    throw new RuntimeException("Type Index detail save fail !");
                }
                System.out.println("New Customer ( " + invoice.getClientName() + " ) Save Success !");

            } else {
                customerMap.put(1, typeIndexDetail.getAccountRefId());
                customerMap.put(2, typeIndexDetail.getAccountIndex());
                AccountService.updateCustomer(invoice, customerMap, accConnection);

            }
            Integer vehicleIndex = -1;
            TTypeIndexDetail typeIndexDetailVehicle;
            if (invoice.getVehicleNo() != null) {

                typeIndexDetailVehicle = AccountService.CheckTypeIndexDetail(Constant.VEHICLE, invoice.getVehicleNo(), accConnection);
                if (typeIndexDetailVehicle.getType() == null) {
                    //type index detail save with customer
                    vehicleIndex = AccountService.saveVehicle(invoice, customerMap.get(2), accConnection);
                    if (vehicleIndex <= 0) {
                        throw new RuntimeException("New Vehicle save Fail !");
                    }

                    Integer typeIndexId = AccountService.saveTypeIndexDetail(invoice.getVehicleNo(), Constant.VEHICLE, 0, vehicleIndex, accConnection);

                    if (typeIndexId < 0) {
                        throw new RuntimeException("Type Index detail save fail !");
                    }
                    System.out.println("New Vehicle ( " + invoice.getVehicleNo() + " ) Save Success !");

                } else {
                    vehicleIndex = typeIndexDetailVehicle.getAccountIndex();
                }
            } else {
                vehicleIndex = null;
            }
//          save invoice
            HashMap<Integer, Integer> invoiceMap = AccountService.saveInvoice(invoice, invoiceDetail, customerMap, vehicleIndex, user, accConnection);
//
//          save item
            HashMap<Integer, Integer> itemMap = new HashMap<>();
            for (InvoiceDetail detail : invoiceDetail) {
////                type index check from item
                TTypeIndexDetail typeIndexDetailItem = AccountService.CheckTypeIndexDetail(Constant.ITEM, detail.getItemNo(), accConnection);
                if (typeIndexDetailItem.getType() == null) {
                    if (!detail.getItemType().equals(Constant.ITEM_SERVICE)) {
                        throw new RuntimeException("Can't find Item (" + detail.getItemName() + ") from account system !");
                    } else {
                        itemMap = AccountService.saveItem(detail, accConnection);
                        //type index detail save with item
                        Integer typeIndexId = AccountService.saveTypeIndexDetail(detail.getItemNo(), Constant.ITEM, itemMap.get(1), itemMap.get(2), accConnection);

                        if (typeIndexId < 0) {
                            throw new RuntimeException("Type Index detail save fail !");
                        }
                        System.out.println("New Item( " + detail.getItemName() + " ) Save Success !");

                    }
//
                } else {
                    itemMap.put(1, typeIndexDetailItem.getAccountRefId());
                    itemMap.put(2, typeIndexDetailItem.getAccountIndex());
                    if (!detail.getIsZeroItem()) {
                        AccountService.updateItemFromInvoice(detail, itemMap, accConnection);
                    }
                }
                detail.setInvoice(invoiceMap.get(1));
                String invoiceNo = AccountService.saveInvoiceDetail(detail, invoice, itemMap, invoiceMap, accConnection);
                if (null == invoiceNo) {
                    throw new RuntimeException("Invoice save failed !");
                }

            }
//            Integer saveSupplierLedger = AccountService.saveSupplierLedger(grn, grnIndex, supplierMap.get(2), accConnection);
//            if (saveSupplierLedger <= 0) {
//                throw new RuntimeException("Supplier Ledger Save fail !");
//            }
            Integer typeIndexId = AccountService.saveTypeIndexDetail(invoice.getInvoiceNo(), Constant.INVOICE, invoiceMap.get(1), invoiceMap.get(2), accConnection);
            if (typeIndexId <= 0) {
                throw new RuntimeException("Type index detail Invoice save fail !");
            }
            Integer masterId = OperationService.updateInvoice(invoice, operaConnection);
//
            if (masterId > 0) {
                System.out.println(invoice.getInvoiceNo() + " - " + invoice.getNetAmount() + " - " + invoice.getClientName() + " Invoice Save Success !");
            } else {
                throw new RuntimeException("Invoice Update fail !");
            }

            System.out.println(" ");
            //commit
            operaConnection.commit();
            accConnection.commit();

            //Clean-up environment
            operaConnection.close();
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);
                if (operaConnection != null) {
                    operaConnection.rollback();
                }
                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }

        return invoice.getIndexNo();
    }

    public Integer savePayment(Payment payment, Integer user) {
        Connection operaConnection = null;
        Connection accConnection = null;
        try {
            //Open a connection
            operaConnection = operationDataSourceWrapper.getConnection();
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            operaConnection.setAutoCommit(false);
            accConnection.setAutoCommit(false);

            //Execute a query to create statment
            TTypeIndexDetail customerTypeIndexDetail;
            customerTypeIndexDetail = AccountService.CheckTypeIndexDetail(Constant.CUSTOMER, payment.getClientNo(), accConnection);
            HashMap<Integer, Integer> customerMap = new HashMap<>();
            if (customerTypeIndexDetail.getType() == null) {
//                throw new RuntimeException("Customer Not found !");
                // save change customer
                customerMap = AccountService.saveCustomer(payment, user, accConnection);

                Integer typeIndexId = AccountService.saveTypeIndexDetail(payment.getClientNo(), Constant.CUSTOMER, customerMap.get(1), customerMap.get(2), accConnection);

                if (typeIndexId < 0) {
                    throw new RuntimeException("Type Index detail save fail !");
                }
                System.out.println("New Customer ( " + payment.getClientNo() + "-" + payment.getClientName() + " ) Save Success !");
                customerTypeIndexDetail = AccountService.CheckTypeIndexDetail(Constant.CUSTOMER, payment.getClientNo(), accConnection);
            }
            Integer paymentIndex = AccountService.savePayment(payment, accConnection);
            if (paymentIndex <= 0) {
                throw new RuntimeException("Payment Save Fail !");
            }

            List<PaymentDetail> paymentDetail = OperationService.getPaymentDetail(payment.getIndexNo(), operaConnection);
            if (paymentDetail.isEmpty()) {
                throw new RuntimeException("Payment Detail was Empty !");
            }
            HashMap<Integer, Object> numberMap = AccountService.getAccLedgerNumber(payment.getBranch(), accConnection);

            for (PaymentDetail paymentDetail1 : paymentDetail) {
                String invCustomerNo = OperationService.getCustomerNoByInvoice(paymentDetail1.getInvoice(), operaConnection);
                if (!invCustomerNo.equals(payment.getClientNo())) {
                    System.out.println("change customer form " + invCustomerNo + " to " + payment.getClientNo());
                    TTypeIndexDetail typeDetail = AccountService.CheckTypeIndexDetail(Constant.INVOICE, paymentDetail1.getInvoice(), accConnection);
                    Integer save = AccountService.tAccLedgerByCustomer(typeDetail, customerTypeIndexDetail.getType() == null ? customerMap.get(1) : customerTypeIndexDetail.getAccountRefId(), accConnection);
                    if (save <= 0) {
                        throw new RuntimeException("tAccLedger update by customer is fail !");
                    }
                }
                AccountService.saveCustomerLedger(paymentDetail1, paymentIndex, payment, customerTypeIndexDetail, numberMap, user, accConnection);
            }

            List<PaymentInformation> paymentInformationList = OperationService.getPaymentInformations(payment.getIndexNo(), operaConnection);
            if (paymentInformationList.isEmpty()) {
                throw new RuntimeException("Payment Informations was Empty !");
            }
            for (PaymentInformation paymentInformation : paymentInformationList) {
                AccountService.savePaymentInformation(paymentInformation, paymentIndex, payment, customerTypeIndexDetail, numberMap, user, accConnection);
            }

            Integer masterId = OperationService.updatePayment(payment.getIndexNo(), operaConnection);

            if (masterId > 0) {
                System.out.println(payment.getNumber() + " - " + payment.getTotalAmount() + " Payment Save Success !");
            } else {
                throw new RuntimeException("Payment Update fail !");
            }

            System.out.println(" ");
            //commit
            operaConnection.commit();
            accConnection.commit();

            //Clean-up environment
            operaConnection.close();
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);
                if (operaConnection != null) {
                    operaConnection.rollback();
                }
                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }

        return 1;
    }

    public Integer checkLoginUser(String name, String pswd) {
        Connection accConnection = null;
        Integer loginUser = -1;
        try {
            //Open a connection
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            accConnection.setAutoCommit(false);
            // Execute a query to create statment
            loginUser = AccountService.checkLoginUser(name, pswd, accConnection);

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

    public void saveStockAdjustment(StockAdjustment adjustment, Integer user) {
        Connection operaConnection = null;
        Connection accConnection = null;
        try {
            //Open a connection
            operaConnection = operationDataSourceWrapper.getConnection();
            accConnection = accountDataSourceWrapper.getConnection();

            //Set auto commit as false.
            operaConnection.setAutoCommit(false);
            accConnection.setAutoCommit(false);

//             Execute a query to create statment
            int saveStockAdjustment = AccountService.saveStockAdjustment(adjustment, accConnection);

            Integer saveIndex = AccountService.saveStockAdjustmentToLedger(adjustment, user, saveStockAdjustment, accConnection, operaConnection);
            if (saveIndex <= 0) {
                throw new RuntimeException("Stock Ledger Save Fail !");
            }

            Integer masterId = OperationService.updateAdjustment(adjustment.getIndexNo(), operaConnection);
            if (masterId <= 0) {
                throw new RuntimeException("Stock Adjustment Status update fail !");
            }
            System.out.println("Stock Adjustment Save Success ! ");
            System.out.println(" ");
            //commit
            operaConnection.commit();
            accConnection.commit();

            //Clean-up environment
            operaConnection.close();
            accConnection.close();

        } catch (Exception e) {
            try {
                System.out.println("COMPILE ERROR ! , check the data and try again !");
                System.out.println(e);
                if (operaConnection != null) {
                    operaConnection.rollback();
                }
                if (accConnection != null) {
                    accConnection.rollback();
                }
                System.out.println("Transactions Rollbacked !");
            } catch (SQLException se2) {
                System.out.println("Can't find database Connections !");

            }
        }
    }
}
