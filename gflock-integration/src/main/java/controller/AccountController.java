/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Constant;
import java.math.BigDecimal;
import java.math.BigInteger;
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

        BigDecimal sales ;
        BigDecimal salesGroupByCategory ;
        BigDecimal itemLineDiscount ;
        BigDecimal itemReturn ;
        BigDecimal returnNoteIssue ;
        BigDecimal giftVoucherSales ;
        BigDecimal invoiceDiscount ;
        BigDecimal creditNoteIssue ;
        BigDecimal ignoreAmount ;
        BigDecimal cashReceived ;
        BigDecimal cashReturn ;
        BigDecimal visa ;
        BigDecimal master;
        BigDecimal amex;
        BigDecimal frimi;
        BigDecimal giftVoucher ;
        BigDecimal returnExchange ;
        BigDecimal creditNote ;
        BigDecimal cheque ;

//        BigDecimal loyeltyPoints = new BigDecimal(0);
        sales = getSales(temId, date, connection);
        ArrayList<Object[]> categoryData = getSalesGroupByCategory(temId, date, connection);
        System.out.println(sales);
        itemLineDiscount = getItemLineDiscount(temId, date, connection);
        System.out.println(itemLineDiscount);
        itemReturn = getItemReturn(temId, date, connection);
        System.out.println(itemReturn);
        returnNoteIssue = getReturnNoteIssue(temId, date, connection);
        System.out.println(returnNoteIssue);
        giftVoucherSales = getGiftVoucherSales(temId, date, connection);
        System.out.println(giftVoucherSales);
        invoiceDiscount = getInvoiceDiscount(temId, date, connection);
        System.out.println(invoiceDiscount);
        creditNoteIssue = getCreditNoteIssue(temId, date, connection);
        System.out.println(creditNoteIssue);
        ignoreAmount = getIgnoreAmount(temId, date, connection);
        System.out.println(ignoreAmount);
        cashReceived = getCashReceived(temId, date, connection);
        System.out.println(cashReceived);
//        cashReturn = getCashReturn(temId, date, connection);
        cashReturn = new BigDecimal(BigInteger.ZERO);
        System.out.println(cashReturn);
        visa = getVisa(temId, date, connection);
        System.out.println(visa);
        master = getMaster(temId, date, connection);
        System.out.println(master);
        amex = getAmex(temId, date, connection);
        System.out.println(amex);
        frimi = getFrimi(temId, date, connection);
        System.out.println(frimi);
        giftVoucher = getGiftVoucher(temId, date, connection);
        System.out.println(giftVoucher);
        returnExchange = getReturnExchange(temId, date, connection);
        System.out.println(returnExchange);
        creditNote = getCreditNote(temId, date, connection);
        System.out.println(creditNote);
        cheque = getCheque(temId, date, connection);
        System.out.println(cheque);

        ArrayList<Object[]> returnList = new ArrayList<>();
        returnList.add(new Object[]{"Item Sales", 0.00, sales.abs(), 0.00,"1"});
        for (Object[] objects : categoryData) {
            returnList.add(new Object[]{objects[0], new BigDecimal(objects[1].toString()).abs(),0.00, 0.00,"111"});
        }
        returnList.add(new Object[]{"Item Line Discount", 0.00, 0.00, itemLineDiscount.abs(),"2"});
        returnList.add(new Object[]{"Item Return", 0.00, 0.00, itemReturn.abs(),"3"});
        returnList.add(new Object[]{"Return Note Issue", 0.00, returnNoteIssue.abs(), 0.00,"4"});
        returnList.add(new Object[]{"GiftVoucher Sales", 0.00, giftVoucherSales.abs(), 0.00,"5"});
        returnList.add(new Object[]{"Invoice Discount", 0.00, 0.00, invoiceDiscount.abs(),"6"});
        returnList.add(new Object[]{"Credit Note Issue", 0.00, creditNoteIssue.abs(), 0.00,"7"});
        returnList.add(new Object[]{"Ignore Amount", 0.00, ignoreAmount.abs(), 0.00,"8"});
        returnList.add(new Object[]{"Cash Received", 0.00, 0.00, cashReceived.abs(),"9"});
        returnList.add(new Object[]{"Cash Return", 0.00, cashReturn.abs(), 0.00,"10"});
        returnList.add(new Object[]{"VISA", 0.00, 0.00, visa.abs(),"11"});
        returnList.add(new Object[]{"MASTER", 0.00, 0.00, master.abs(),"12"});
        returnList.add(new Object[]{"AMEX", 0.00, 0.00, amex.abs(),"13"});
        returnList.add(new Object[]{"FRIMI", 0.00, 0.00, frimi.abs(),"14"});
        returnList.add(new Object[]{"GiftVoucher Redeam", 0.00, 0.00, giftVoucher.abs(),"15"});
        returnList.add(new Object[]{"Return Exchange", 0.00, 0.00, returnExchange.abs(),"16"});
        returnList.add(new Object[]{"Credit Note", 0.00, 0.00, creditNote.abs(),"17"});
        returnList.add(new Object[]{"Cheque", 0.00, 0.00, cheque.abs(),"18"});

        return returnList;
    }
    static boolean saveAccount(int selectedTerminal, String date, Integer loginUser, ArrayList<Object[]> terminalDetail, Connection accConnection) throws SQLException {
         //get branch from login user
        MBranch branch = getBranch(loginUser, accConnection);
        String integrationCode = Constant.INTEGRATION_CODE;
        String formName = Constant.FORM_NAME;
        Integer number = getNxtLedgerNoByType(formName, accConnection);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String currentTime = new SimpleDateFormat("HH:ss:mm").format(new Date());
        
        for (Object[] objects : terminalDetail) {
            if ("1".equals(objects[4].toString())) {
                // get_sales m_acc_setting
                
            }
            if ("111".equals(objects[4].toString())) {
                // get_sales cost department
                String costCenterName = objects[1].toString();
            }
            if ("2".equals(objects[4].toString())) {
                // get_Item Line Discount
                
            }
            if ("3".equals(objects[4].toString())) {
                // get_Item return
                
            }
            if ("4".equals(objects[4].toString())) {
                // get Return Note Issue
                
            }
            if ("5".equals(objects[4].toString())) {
                // get GiftVoucher Sales
                
            }
            if ("6".equals(objects[4].toString())) {
                // get Invoice Discount
                
            }
            if ("7".equals(objects[4].toString())) {
                // get Credit Note Issue
                
            }
            if ("8".equals(objects[4].toString())) {
                // get Ignore Amount
                
            }
            if ("9".equals(objects[4].toString())) {
                // get Cash ReceivedCash Received	0.0	0.0	406885.0000	9
                
            }
            if ("10".equals(objects[4].toString())) {
                // get Cash Return
                
            }
            if ("11".equals(objects[4].toString())) {
                // get VISA
                
            }
            if ("12".equals(objects[4].toString())) {
                // get master
                
            }
            if ("13".equals(objects[4].toString())) {
                // get Amex
                
            }
            if ("14".equals(objects[4].toString())) {
                // get FRIMI
                
            }
            if ("15".equals(objects[4].toString())) {
                // get GiftVoucher Redeam
                
            }
            if ("16".equals(objects[4].toString())) {
                // get Return Exchange
                
            }
            if ("17".equals(objects[4].toString())) {
                // get Credit Note
                
            }
            if ("18".equals(objects[4].toString())) {
                // get cheque
                
            }
        }
        
        return true;
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
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_details.tr_det_type='item' and pos_t_transaction_summary.tr_type='invoice' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getItemLineDiscount(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.line_dis_amt1)+sum(pos_t_transaction_details.line_dis_amt2),0.00)  as val\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getItemReturn(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_details.tr_det_type='item' and pos_t_transaction_summary.tr_type='return' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getReturnNoteIssue(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_summary.tr_type='return' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getGiftVoucherSales(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_transaction_details.final_value),0.00) as sales\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "where pos_t_transaction_details.tr_det_type='gift' and pos_t_transaction_summary.tr_type='invoice' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getInvoiceDiscount(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.discount_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCreditNoteIssue(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.credit_note_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getIgnoreAmount(int temId, String date, Connection connection) throws SQLException {
        String query = "select ifnull(sum(pos_t_payment_summary.ignore_amount),0.00)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCashReceived(int temId, String date, Connection connection) throws SQLException {
        String query = "select (Select sum(pmt_amount) as Cash_value from temp_payment_details Where pmt_type='CASH' \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')\n"
                + "and terminal_id=?)-\n"
                + "(select sum(pos_t_payment_summary.balance_return)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?) as val";
        return AccountService.getInstance().proseedQuery2(temId, date, query, connection);
    }

    private static BigDecimal getCashReturn(int temId, String date, Connection connection) throws SQLException {
        String query = "select (Select sum(pmt_amount) as Cash_value from temp_payment_details Where pmt_type='CASH' \n"
                + "and terminal_id=?)-\n"
                + "and tr_date1= DATE_FORMAT(?,'%Y%m%d')\n"
                + "(select sum(pos_t_payment_summary.balance_return)  as val\n"
                + "from pos_t_payment_summary\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_payment_summary.tr_index_no\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=?) as val";
        return AccountService.getInstance().proseedQuery2(temId, date, query, connection);
    }

    private static BigDecimal getVisa(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='VISA' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getMaster(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='MASTER' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getAmex(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='AMEX' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getFrimi(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='FRIMI' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getGiftVoucher(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='GV' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getReturnExchange(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(tr_final_value),0.00) as return_exchange_value from temp_tr_details \n"
                + " Where tr_status=2 and tr_type='invoice' and tr_det_type='Return' \n"
                + " and terminal_id=? \n"
                + " and tr_date1=DATE_FORMAT(?,'%Y%m%d') \n";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCreditNote(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='CN' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static BigDecimal getCheque(int temId, String date, Connection connection) throws SQLException {
        String query = "Select ifnull(sum(pmt_amount),0.00) as visa from temp_payment_details Where pmt_type='CHEQUE' \n"
                + "and terminal_id=? \n"
                + "and tr_date1=DATE_FORMAT(?,'%Y%m%d')";
        return AccountService.getInstance().proseedQuery(temId, date, query, connection);
    }

    private static ArrayList<Object[]> getSalesGroupByCategory(int temId, String date, Connection connection) throws SQLException {
        return AccountService.getInstance().getSalesGroupByCategory(temId, date, connection);
    }

    

}
