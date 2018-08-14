/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import java.math.BigDecimal;
import service.AccountService;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.account_model.MBranch;

/**
 *
 * @author chama
 */
public class AccountController {

    private static BigDecimal totalCredit = new BigDecimal(0);
    private static BigDecimal totalDebit = new BigDecimal(0);

    public static Integer checkLoginUser(String name, String pswd, Connection accConnection) throws SQLException {
        return AccountService.getInstance().checkLoginUser(name, pswd, accConnection);
    }

    public static String getCompanyName(Connection accConnection) throws SQLException {
        return AccountService.getInstance().getCompantName(accConnection);
    }

    static HashMap getDetailCount(String date, int[] terList, Connection accConnection) throws SQLException {
        return AccountService.getInstance().getDetailCount(date, terList, accConnection);
    }

    static String getSyncDate(Connection accConnection) throws SQLException {
        return AccountService.getInstance().getSyncDate(accConnection);
    }

    static String getNextDate(String date, Connection accConnection) throws SQLException, ParseException {
        return AccountService.getInstance().getSyncDate(date, accConnection);
    }

    static ArrayList<Object[]> setTerminalDetail(int temId, String date, Integer loginUser, Connection connection) throws SQLException {

        BigDecimal sales;
        BigDecimal itemLineDiscount;
        BigDecimal itemReturn;
        BigDecimal returnNoteIssue;
        BigDecimal giftVoucherSales;
        BigDecimal invoiceDiscount;
        BigDecimal creditNoteIssue;
        BigDecimal ignoreAmount;
        BigDecimal cashReceived;
        BigDecimal cashReturn;
        BigDecimal visa;
        BigDecimal master;
        BigDecimal amex;
        BigDecimal frimi;
        BigDecimal giftVoucher;
        BigDecimal returnExchange;
        BigDecimal creditNote;
        BigDecimal cheque;

//        BigDecimal loyeltyPoints = new BigDecimal(0);
        sales = getSales(temId, date, connection);
        ArrayList<Object[]> categoryData = getSalesGroupByCategory(temId, date, connection);
        System.out.println("Sales :" + sales);
        itemLineDiscount = getItemLineDiscount(temId, date, connection);
        ArrayList<Object[]> lineDiscountByCategory = getLineDiscountByCategory(temId, date, connection);
        System.out.println("Line Discount :" + itemLineDiscount);
        itemReturn = getItemReturn(temId, date, connection);
        ArrayList<Object[]> itemReturnByCategory = getItemReturnByCategory(temId, date, connection);
        System.out.println("Item Return :" + itemReturn);
        returnNoteIssue = getReturnNoteIssue(temId, date, connection);
        System.out.println("return Note Issue :" + returnNoteIssue);
        giftVoucherSales = getGiftVoucherSales(temId, date, connection);
        System.out.println("Vift Voucher Sales :" + giftVoucherSales);
        invoiceDiscount = getInvoiceDiscount(temId, date, connection);
        System.out.println("Invoice Discount :" + invoiceDiscount);
        creditNoteIssue = getCreditNoteIssue(temId, date, connection);
        System.out.println("Credit Note Issue :" + creditNoteIssue);
        ignoreAmount = getIgnoreAmount(temId, date, connection);
        System.out.println("Ignore Amount :" + ignoreAmount);
        cashReceived = getCashReceived(temId, date, connection);
        System.out.println("Cash Received :" + cashReceived);
        cashReturn = getCashReturn(temId, date, connection);
        System.out.println("Cash Return :" + cashReturn);
        visa = getVisa(temId, date, connection);
        System.out.println("Visa :" + visa);
        master = getMaster(temId, date, connection);
        System.out.println("Master :" + master);
        amex = getAmex(temId, date, connection);
        System.out.println("Amex :" + amex);
        frimi = getFrimi(temId, date, connection);
        System.out.println("Frimi :" + frimi);
        giftVoucher = getGiftVoucher(temId, date, connection);
        System.out.println("Gift Voucher Redeam :" + giftVoucher);
        returnExchange = getReturnExchange(temId, date, connection);
        System.out.println("Return Exchange :" + returnExchange);
        creditNote = getCreditNote(temId, date, connection);
        System.out.println("Credit Note Recevied :" + creditNote);
        cheque = getCheque(temId, date, connection);
        System.out.println("Cheque :" + cheque);

        ArrayList<Object[]> returnList = new ArrayList<>();
        returnList.add(new Object[]{"Item Sales", 0.00, sales.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "1"});
        for (Object[] objects : categoryData) {
            returnList.add(new Object[]{objects[0], new BigDecimal(objects[1].toString()).abs().setScale(2, BigDecimal.ROUND_UP), 0.00, 0.00, "111"});
        }
        returnList.add(new Object[]{"Item Line Discount", 0.00, 0.00, itemLineDiscount.abs().setScale(2, BigDecimal.ROUND_UP), "2"});
        for (Object[] objects : lineDiscountByCategory) {
            returnList.add(new Object[]{objects[0], new BigDecimal(objects[1].toString()).abs().setScale(2, BigDecimal.ROUND_UP), 0.00, 0.00, "222"});
        }
        returnList.add(new Object[]{"Item Return", 0.00, 0.00, itemReturn.abs().setScale(2, BigDecimal.ROUND_UP), "3"});
        for (Object[] objects : itemReturnByCategory) {
            returnList.add(new Object[]{objects[0], new BigDecimal(objects[1].toString()).abs().setScale(2, BigDecimal.ROUND_UP), 0.00, 0.00, "333"});
        }
        returnList.add(new Object[]{"Return Note Issue", 0.00, returnNoteIssue.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "4"});
        returnList.add(new Object[]{"GiftVoucher Sales", 0.00, giftVoucherSales.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "5"});
        returnList.add(new Object[]{"Invoice Discount", 0.00, 0.00, invoiceDiscount.abs().setScale(2, BigDecimal.ROUND_UP), "6"});
        returnList.add(new Object[]{"Credit Note Issue", 0.00, creditNoteIssue.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "7"});
        returnList.add(new Object[]{"Ignore Amount", 0.00, ignoreAmount.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "8"});
        returnList.add(new Object[]{"Cash Received", 0.00, 0.00, cashReceived.abs().setScale(2, BigDecimal.ROUND_UP), "9"});
        returnList.add(new Object[]{"Cash Return", 0.00, cashReturn.abs().setScale(2, BigDecimal.ROUND_UP), 0.00, "10"});
        returnList.add(new Object[]{"VISA", 0.00, 0.00, visa.abs().setScale(2, BigDecimal.ROUND_UP), "11"});
        returnList.add(new Object[]{"MASTER", 0.00, 0.00, master.abs().setScale(2, BigDecimal.ROUND_UP), "12"});
        returnList.add(new Object[]{"AMEX", 0.00, 0.00, amex.abs().setScale(2, BigDecimal.ROUND_UP), "13"});
        returnList.add(new Object[]{"FRIMI", 0.00, 0.00, frimi.abs().setScale(2, BigDecimal.ROUND_UP), "14"});
        returnList.add(new Object[]{"GiftVoucher Redeam", 0.00, 0.00, giftVoucher.abs().setScale(2, BigDecimal.ROUND_UP), "15"});
        returnList.add(new Object[]{"Return Exchange", 0.00, 0.00, returnExchange.abs().setScale(2, BigDecimal.ROUND_UP), "16"});
        returnList.add(new Object[]{"Credit Note", 0.00, 0.00, creditNote.abs().setScale(2, BigDecimal.ROUND_UP), "17"});
        returnList.add(new Object[]{"Cheque", 0.00, 0.00, cheque.abs().setScale(2, BigDecimal.ROUND_UP), "18"});

        return returnList;
    }

    static Object[] saveAccount(int selectedTerminal, String date, Integer loginUser, ArrayList<Object[]> terminalDetail, Connection accConnection) throws SQLException {
        //get branch from login user
        totalDebit = new BigDecimal(0);
        totalCredit = new BigDecimal(0);

        MBranch branch = getBranch(loginUser, accConnection);
        String integrationCode = Constant.INTEGRATION_CODE;

        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String currentTime = new SimpleDateFormat("HH:ss:mm").format(new Date());
        String formName = Constant.FORM_NAME;
        Integer number = getNxtLedgerNoByType(formName, accConnection);
        Integer deleteFrfNo = getDeleteFefNo(accConnection);
        Integer financialYear = getCurrentFinancialYear(accConnection);
        String searchCode = integrationCode + "/" + branch.getBranchCode() + "/" + number;
        String description = date + " integration from terminal " + selectedTerminal;

        Integer salesAccountNo = -1;
        Integer lineDiscount = -1;
        Integer itemReturn = -1;
        ArrayList<Object[]> salesCostCenters = new ArrayList<>();
        ArrayList<Object[]> lineDiscountCostCenters = new ArrayList<>();
        ArrayList<Object[]> itemReturnCostCenters = new ArrayList<>();

        HashMap<Integer, Object> commonDetail = new HashMap<>();
        commonDetail.put(1, number);
        commonDetail.put(2, searchCode);
        commonDetail.put(3, date);
        commonDetail.put(4, currentDate);
        commonDetail.put(5, currentTime);
        commonDetail.put(6, branch.getIndexNo());
        commonDetail.put(7, branch.getIndexNo());
        commonDetail.put(8, loginUser);
        commonDetail.put(9, 0);
        commonDetail.put(10, 0);
        commonDetail.put(12, formName);
        commonDetail.put(13, formName);
        commonDetail.put(14, deleteFrfNo);
        commonDetail.put(15, description);
        commonDetail.put(16, financialYear);
        commonDetail.put(17, 0);

        for (Object[] objects : terminalDetail) {
            if ("1".equals(objects[4].toString())) {
                // get_sales m_acc_setting
                salesAccountNo = getAccSettingByName("item_sales", accConnection);
            }
            if ("111".equals(objects[4].toString())) {
                // get_sales cost center
                Integer costCenter = getCostCenterByName(objects[0].toString().toString(), accConnection);
                salesCostCenters.add(new Object[]{costCenter, objects[1]});
            }
            if ("2".equals(objects[4].toString())) {
                // get_Item Line Discount
                lineDiscount = getAccSettingByName("line_discount", accConnection);

            }
            if ("222".equals(objects[4].toString())) {
                // get_line_discount cost center
                Integer costCenter = getCostCenterByName(objects[0].toString().toString(), accConnection);
                lineDiscountCostCenters.add(new Object[]{costCenter, objects[1]});
            }
            if ("3".equals(objects[4].toString())) {
                // get_Item return
                itemReturn = getAccSettingByName("item_return", accConnection);

            }
            if ("333".equals(objects[4].toString())) {
                // get_item_return cost center
                Integer costCenter = getCostCenterByName(objects[0].toString().toString(), accConnection);
                itemReturnCostCenters.add(new Object[]{costCenter, objects[1]});
            }
            if ("4".equals(objects[4].toString())) {
                // get Return Note Issue
                setupSave("return_note", new BigDecimal(0), new BigDecimal(objects[2].toString()), commonDetail, accConnection);
            }
            if ("5".equals(objects[4].toString())) {
                // get GiftVoucher Sales
                setupSave("gift_voucher", new BigDecimal(0), new BigDecimal(objects[2].toString()), commonDetail, accConnection);
            }
            if ("6".equals(objects[4].toString())) {
                // get Invoice Discount
                setupSave("invoice_discount", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("7".equals(objects[4].toString())) {
                // get Credit Note Issue
                setupSave("credit_note", new BigDecimal(0), new BigDecimal(objects[2].toString()), commonDetail, accConnection);

            }
            if ("8".equals(objects[4].toString())) {
                // get Ignore Amount
                setupSave("ignore_amount", new BigDecimal(0), new BigDecimal(objects[2].toString()), commonDetail, accConnection);

            }
            if ("9".equals(objects[4].toString())) {
                // get Cash ReceivedCash
                setupSave("item_sales_cash_in", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("10".equals(objects[4].toString())) {
                // get Cash Return
                setupSave("item_sales_cash_in", new BigDecimal(0), new BigDecimal(objects[2].toString()), commonDetail, accConnection);

            }
            if ("11".equals(objects[4].toString())) {
                // get VISA
                setupSave("visa_contol_account", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("12".equals(objects[4].toString())) {
                // get master
                setupSave("master_contol_account", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("13".equals(objects[4].toString())) {
                // get Amex
                setupSave("amex_contol_account", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("14".equals(objects[4].toString())) {
                // get FRIMI
                setupSave("frimi_contol_account", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("15".equals(objects[4].toString())) {
                // get GiftVoucher Redeam
                setupSave("gift_voucher", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);
//
            }
            if ("16".equals(objects[4].toString())) {
                // get Return Exchange
                setupSave("return_note", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
            if ("17".equals(objects[4].toString())) {
                // get Credit Note
                setupSave("credit_note", new BigDecimal(objects[3].toString()), new BigDecimal(0), commonDetail, accConnection);
//
            }
            if ("18".equals(objects[4].toString())) {
                // get cheque
                setupSave("cheque_in_hand", new BigDecimal(objects[2].toString()), new BigDecimal(0), commonDetail, accConnection);

            }
        }
//        sales Save from cost center
        HashMap<Integer, Object> salesTransaction = commonDetail;
        salesTransaction.put(11, salesAccountNo);
        salesTransaction.put(9, new BigDecimal(0));
        Integer saveCount = 0;
        for (Object[] salesCostCenter : salesCostCenters) {
            salesTransaction.put(10, salesCostCenter[1]);
            salesTransaction.put(17, salesCostCenter[0]);
            if (Double.valueOf(salesCostCenter[1].toString()) > 0) {
                totalCredit = totalCredit.add(new BigDecimal(salesCostCenter[1].toString()));
                saveAccLedger(salesTransaction, accConnection);
            }
            saveCount++;
        }
        if (salesCostCenters.size() != saveCount) {
            throw new RuntimeException("sales cost center save fail !");
        }

//        sales return Save from cost center
        HashMap<Integer, Object> salesReturnTransaction = commonDetail;
        salesReturnTransaction.put(11, itemReturn);
        salesReturnTransaction.put(10, new BigDecimal(0));
        Integer saveReturnCount = 0;
        for (Object[] salesCostCenter : itemReturnCostCenters) {
            salesReturnTransaction.put(9, salesCostCenter[1]);
            salesReturnTransaction.put(17, salesCostCenter[0]);
            if (Double.valueOf(salesCostCenter[1].toString()) > 0) {
                System.out.println("**** " + salesCostCenter[1].toString());
                totalDebit = totalDebit.add(new BigDecimal(salesCostCenter[1].toString()));
                saveAccLedger(salesReturnTransaction, accConnection);
            }
            saveReturnCount++;
        }
        if (itemReturnCostCenters.size() != saveReturnCount) {
            throw new RuntimeException("sales return cost center save fail !");
        }

//        sales return Save from cost center
        HashMap<Integer, Object> lineDiscountTransaction = commonDetail;
        lineDiscountTransaction.put(11, lineDiscount);
        lineDiscountTransaction.put(10, new BigDecimal(0));
        Integer saveLineDisacount = 0;
        for (Object[] salesCostCenter : lineDiscountCostCenters) {
            lineDiscountTransaction.put(9, salesCostCenter[1]);
            lineDiscountTransaction.put(17, salesCostCenter[0]);
            if (Double.valueOf(salesCostCenter[1].toString()) > 0) {
                System.out.println("**** " + salesCostCenter[1].toString());
                totalDebit = totalDebit.add(new BigDecimal(salesCostCenter[1].toString()));
                saveAccLedger(lineDiscountTransaction, accConnection);
            }
            saveLineDisacount++;
        }
        if (lineDiscountCostCenters.size() != saveLineDisacount) {
            throw new RuntimeException("line discount cost center save fail !");
        }
        if (totalDebit.compareTo(totalCredit) != 0) {
            throw new RuntimeException("Total debit and Credit doesn't match ! TotalDebit : " + totalDebit + " TotalCredit : " + totalCredit);
        }

        Object[] retData = {totalDebit.setScale(2, BigDecimal.ROUND_UP), totalCredit.setScale(2, BigDecimal.ROUND_UP), searchCode};

        Integer updateTrSum = updateTrSum(selectedTerminal, date, accConnection);
        if (updateTrSum <= 0) {
            throw new RuntimeException("Tr_summary update failed !");
        }
        return retData;
    }

    private static MBranch getBranch(Integer loginUser, Connection connection) throws SQLException {
        MBranch branch = AccountService.getInstance().getBranch(loginUser, connection);
        if (branch == null || branch.getBranchCode() == null) {
            throw new RuntimeException("Can't find Branch ! ");
        }
        return branch;
    }

    private static Integer getNxtLedgerNoByType(String formName, Connection connection) throws SQLException {
        Integer number = AccountService.getInstance().getNxtLedgerNoByType(formName, connection);
        if (number <= 0) {
            throw new RuntimeException("Can't find next ledger number !");
        }
        return number;
    }

    private static BigDecimal getSales(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(item_value),0.00) as total_item_value\n"
                + "from temp_tr_details \n"
                + "Where tr_status=2 \n"
                + "and tr_type='invoice' \n"
                + "and tr_det_type='Item' \n"
                + "and terminal_id=?\n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and temp_tr_details.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getItemLineDiscount(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(line_dis_amt1),0.00) as total_item_value\n"
                + "from temp_tr_details \n"
                + "Where tr_status=2 \n"
                + "and tr_type='invoice' \n"
                + "and tr_det_type='Item' \n"
                + "and terminal_id=?\n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and temp_tr_details.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getItemReturn(int temId, String date, Connection connection) throws SQLException {
        String query = "Select sum(tr_final_value) as return_item_value \n"
                + "from temp_tr_details \n"
                + "Where tr_status=2 \n"
                + "and tr_type='return' \n"
                + "and tr_det_type='Item' \n"
                + "and terminal_id=?\n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and temp_tr_details.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getReturnNoteIssue(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_summary.tr_type='return' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getGiftVoucherSales(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_details.tr_det_type='gift' and pos_t_transaction_summary.tr_type='invoice' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getInvoiceDiscount(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.discount_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCreditNoteIssue(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.credit_note_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getIgnoreAmount(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.ignore_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCashReceived(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull((Select ifnull(sum(pmt_amount),0.00) as Cash_value from temp_payment_details Where pmt_type='CASH' \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')\n"
                + "and terminal_id=? and is_sync=0)-\n"
                + "(select sum(pos_t_payment_summary.balance_return)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0 ),0.00) as val";
        return AccountService.getInstance().proseedQuery2(temId, date, query, connection);
    }

    private static BigDecimal getCashReturn(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.cash_return),0.00) as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getVisa(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='VISA' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getMaster(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='MASTER' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getAmex(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='AMEX' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getFrimi(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='FRIMI' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getGiftVoucher(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='GV' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getReturnExchange(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(tr_final_value),0.00) as return_exchange_value from temp_tr_details \n"
                + " Where tr_status=2 and tr_type='invoice' and tr_det_type='Return' \n"
                + " and terminal_id=? \n"
                + " and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCreditNote(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='CN' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCheque(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='CHEQUE' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d') and is_sync=0";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static ArrayList<Object[]> getSalesGroupByCategory(int temId, String date, Connection connection) throws SQLException {
        return AccountService.getInstance().getSalesGroupByCategory(temId, date, connection);
    }

    private static Integer getDeleteFefNo(Connection accConnection) throws SQLException {
        return AccountService.getInstance().getDeleteFefNo(accConnection);
    }

    private static Integer getCurrentFinancialYear(Connection accConnection) throws SQLException {
        return AccountService.getInstance().getCurrentFinancialYear(accConnection);
    }

    private static Integer updateTrSum(int selectedTerminal, String date, Connection accConnection) throws SQLException {
        Integer updateTrSum = AccountService.getInstance().updateTrSum(selectedTerminal, date, accConnection);
        if (updateTrSum <= 0) {
            throw new RuntimeException("Operation final update fail !");
        }
        return updateTrSum;
    }

    private static Integer getAccSettingByName(String settingName, Connection accConnection) throws SQLException {
        Integer settingAccount = AccountService.getInstance().getAccSettingByName(settingName, accConnection);
        if (settingAccount <= 0) {
            throw new RuntimeException("Can't find setting from " + settingName);
        }
        return settingAccount;
    }

    private static Integer getCostCenterByName(String costCenterName, Connection accConnection) throws SQLException {
        Integer costCenter = AccountService.getInstance().getCostCenterByName(costCenterName.trim(), accConnection);
        if (costCenter <= 0) {
            throw new RuntimeException("Can't find cost center from " + costCenterName.trim());
        }
        return costCenter;
    }

    private static ArrayList<Object[]> getLineDiscountByCategory(int temId, String date, Connection connection) throws SQLException {
        return AccountService.getInstance().getLineDiscountByCategory(temId, date, connection);
    }

    private static ArrayList<Object[]> getItemReturnByCategory(int temId, String date, Connection connection) throws SQLException {
        return AccountService.getInstance().getItemReturnByCategory(temId, date, connection);
    }

    private static void saveAccLedger(HashMap<Integer, Object> editedDetail1, Connection accConnection) throws SQLException {
        AccountService.getInstance().saveAccLedger(editedDetail1, accConnection);

    }

    private static void setupSave(String settingName, BigDecimal debit, BigDecimal credit, HashMap<Integer, Object> commonDetail, Connection accConnection) throws SQLException {
        Integer creditNote = getAccSettingByName(settingName, accConnection);
        HashMap<Integer, Object> cashTransaction = commonDetail;
        cashTransaction.put(11, creditNote);
        cashTransaction.put(9, debit);
        cashTransaction.put(10, credit);
        if (debit.doubleValue() > 0 || credit.doubleValue() > 0) {
            System.out.println("**** " + debit);
            totalDebit = totalDebit.add(debit);
            totalCredit = totalCredit.add(credit);
            saveAccLedger(cashTransaction, accConnection);
        }
    }

    public static Integer getNotCheckDataCount(String date, Connection accConnection) throws SQLException {
        return AccountService.getInstance().getNotCheckDataCount(date, accConnection);
    }

}
