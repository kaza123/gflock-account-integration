/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import model.account_model.MBranch;
import model.account_model.PaymentDetails;
import model.account_model.PaymentSummary;
import model.account_model.TrDet;
import model.account_model.TrSum;

/**
 *
 * @author 'Kasun Chamara'
 */
public class AccountService {

    private static AccountService instance;

    public AccountService() throws SQLException {
    }

    public static AccountService getInstance() throws SQLException {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public String getCompantName(Connection connection) throws SQLException {
        String query = "select m_company.name from m_company\n"
                + "where m_company.index_no=1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getString(1);
        }
        return "";
    }

    public Integer checkLoginUser(String name, String pswd, Connection accConnection) throws SQLException {
        String query = "select  m_user.index_no as login_user\n"
                + "from m_user where m_user.username=? and m_user.password=?";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pswd);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        return -1;
    }

    public HashMap getDetailCount(String date, int[] terList, Connection accConnection) throws SQLException {

        HashMap hashMap = new HashMap();
        for (int ter : terList) {

            String query = "select CONCAT( (CONVERT ((select count(pos_t_transaction_summary.index_no) as size\n"
                    + "from pos_t_transaction_summary \n"
                    + "where pos_t_transaction_summary.tr_date=? \n"
                    + "and pos_t_transaction_summary.terminal_id=? \n"
                    + "and pos_t_transaction_summary.is_sync=0 \n"
                    + "and pos_t_transaction_summary.tr_type='invoice'),char) ),\" - \",\n"
                    + "\n"
                    + "(CONVERT((select count(pos_t_transaction_summary.index_no) \n"
                    + "from pos_t_transaction_summary \n"
                    + "where pos_t_transaction_summary.tr_date=? \n"
                    + "and pos_t_transaction_summary.terminal_id=? \n"
                    + "and pos_t_transaction_summary.is_sync=0 \n"
                    + "and pos_t_transaction_summary.tr_type='return'),char))) as val";
            PreparedStatement preparedStatement = accConnection.prepareStatement(query);
            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, ter);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, ter);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                hashMap.put("ter" + ter, rst.getString(1));
            }
        }

        return hashMap;
    }

    public String getSyncDate(Connection accConnection) throws SQLException {
        String query = "select m_sync_date.date as sync_date\n"
                + "from m_sync_date where m_sync_date.index_no=1";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getString(1);
        }
        return String.valueOf(new Date());
    }

    public String getSyncDate(String date, Connection accConnection) throws ParseException, SQLException {
        String dt = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 1);
        dt = sdf.format(c.getTime());
        System.out.println("date : " + dt);
        return setNewDate(dt, accConnection);
    }

    private String setNewDate(String date, Connection connection) throws SQLException {
        String insertSql = "update m_sync_date SET m_sync_date.date=? WHERE  m_sync_date.index_no=1;";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, date);
        preparedStatement.executeUpdate();
        return date;
    }

    public List<TrSum> getNotCheckInvoiceList(int temId, String date, Connection accConnection) throws SQLException {
        String query = "select *\n"
                + "from pos_t_transaction_summary \n"
                + "where pos_t_transaction_summary.tr_date<=? and pos_t_transaction_summary.terminal_id=? and pos_t_transaction_summary.is_sync=0";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setString(1, date);
        preparedStatement.setInt(2, temId);
        ResultSet rst = preparedStatement.executeQuery();
        List<TrSum> list = new ArrayList<>();
        while (rst.next()) {
            TrSum trSum = new TrSum();
            trSum.setIndexNo(rst.getInt(1));
            trSum.setTerminalId(rst.getInt(2));
            trSum.setUserId(rst.getInt(3));
            trSum.setTrStatus(rst.getBoolean(4));
            trSum.setMaxTrNo(rst.getInt(5));
            trSum.setMaxInvNo(rst.getInt(6));
            trSum.setTrType(rst.getString(7));
            trSum.setTrNo(rst.getString(8));
            trSum.setInvoiceNo(rst.getString(9));
            trSum.setTrDate(rst.getDate(10));
            trSum.setTrDate1(rst.getString(11));
            trSum.setTrStartTime(rst.getTime(12));
            trSum.setTrEndTime(rst.getTime(13));
            trSum.setCustomerId(rst.getInt(14));
            trSum.setCusMobNo(rst.getString(15));
            trSum.setUserName(rst.getString(16));
            trSum.setTotalAmount(rst.getBigDecimal(17));
            trSum.setDiscountP(rst.getBigDecimal(18));
            trSum.setDiscountAmount(rst.getBigDecimal(19));
            trSum.setRoundupAmount(rst.getBigDecimal(20));
            trSum.setTrAmount(rst.getBigDecimal(21));
            trSum.setSyncStatus(rst.getBoolean(22));
            trSum.setLoyaltyPoints(rst.getBigDecimal(23));
            trSum.setDeleteStatus(rst.getBoolean(24));
            trSum.setBalanceType(rst.getString(25));
            trSum.setIsSync(rst.getBoolean(26));
            trSum.setSyncDateTimt(rst.getTimestamp(27));

            list.add(trSum);
        }
        return list;
    }

    public List<TrDet> getInvoiceDetails(Integer indexNo, Connection accConnection) throws SQLException {
        String query = "SELECT * FROM pos_t_transaction_details WHERE pos_t_transaction_details.tr_index_no=?";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet rst = preparedStatement.executeQuery();
        List<TrDet> list = new ArrayList<>();
        while (rst.next()) {
            TrDet trDet = new TrDet();
            trDet.setIndexNo(rst.getInt(1));
            trDet.setTrIndexNo(rst.getInt(2));
            trDet.setTrDetType(rst.getString(3));
            trDet.setBarCode(rst.getString(4));
            trDet.setDescription(rst.getString(5));
            trDet.setItemQty(rst.getBigDecimal(6));
            trDet.setItemPrice(rst.getBigDecimal(7));
            trDet.setItemValue(rst.getBigDecimal(8));
            trDet.setLineDisP1(rst.getBigDecimal(9));
            trDet.setLineDisP2(rst.getBigDecimal(10));
            trDet.setLineDisAmt1(rst.getBigDecimal(11));
            trDet.setLineDisAmt2(rst.getBigDecimal(12));
            trDet.setFinalValue(rst.getBigDecimal(13));

            list.add(trDet);
        }
        return list;
    }

    public List<PaymentSummary> getPaymentSummary(Integer indexNo, Connection accConnection) throws SQLException {
        String query = "SELECT * FROM pos_t_payment_summary WHERE pos_t_payment_summary.tr_index_no=?";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet rst = preparedStatement.executeQuery();
        List<PaymentSummary> list = new ArrayList<>();
        while (rst.next()) {
            PaymentSummary ps = new PaymentSummary();
            ps.setIndexNo(rst.getInt(1));
            ps.setTrIndexNo(rst.getInt(2));
            ps.setInvoiceAmount(rst.getBigDecimal(3));
            ps.setDiscountType(rst.getString(4));
            ps.setPromoNo(rst.getString(5));
            ps.setDiscountRate(rst.getBigDecimal(6));
            ps.setDiscountAmount(rst.getBigDecimal(7));
            ps.setFinalValue(rst.getBigDecimal(8));
            ps.setTotalPaidAmount(rst.getBigDecimal(9));
            ps.setBalanceReturn(rst.getBigDecimal(10));
            ps.setCashReturn(rst.getBigDecimal(11));
            ps.setCreditNoteNo(rst.getString(12));
            ps.setCreditNoteAmount(rst.getBigDecimal(13));
            ps.setIgnoreAmount(rst.getBigDecimal(14));

            list.add(ps);
        }
        return list;
    }

    public List<PaymentDetails> getPaymentDetails(Integer indexNo, Connection accConnection) throws SQLException {
        String query = "SELECT * FROM pos_t_payment_details WHERE pos_t_payment_details.pmt_index_no=?";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setInt(1, indexNo);
        ResultSet rst = preparedStatement.executeQuery();
        List<PaymentDetails> list = new ArrayList<>();
        while (rst.next()) {
            PaymentDetails pd = new PaymentDetails();
            pd.setPmtDetIndexNo(rst.getInt(1));
            pd.setPmtIndexNo(rst.getInt(2));
            pd.setPmtType(rst.getString(3));
            pd.setPmtRefNo(rst.getString(4));
            pd.setPmtAmount(rst.getBigDecimal(5));
            pd.setMachineNo(rst.getInt(6));

            list.add(pd);
        }
        return list;
    }

    public MBranch getBranch(Integer loginUser, Connection connection) throws SQLException {
        String query = "select m_branch.* from m_branch where m_branch.index_no=(\n"
                + "select m_user.branch from m_user where m_user.index_no=?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, loginUser);
        ResultSet rst = preparedStatement.executeQuery();
        MBranch branchModel = new MBranch();
        if (rst.next()) {
            branchModel.setIndexNo(rst.getInt(1));
            branchModel.setBranchCode(rst.getString(2));
            branchModel.setRegNumber(rst.getString(3));
            branchModel.setName(rst.getString(4));
            branchModel.setAddressLine1(rst.getString(5));
            branchModel.setAddressLine2(rst.getString(6));
            branchModel.setAddressLine3(rst.getString(7));
            branchModel.setTelephoneNumber(rst.getString(8));
            branchModel.setColor(rst.getString(9));
            branchModel.setType(rst.getString(10));
        }
        return branchModel;
    }

    public Integer getNxtLedgerNoByType(String formName, Connection connection) throws SQLException {
        String query = "SELECT ifnull(MAX(t_acc_ledger.number)+1,1) as nxt_no \n"
                + "FROM t_acc_ledger\n"
                + "where t_acc_ledger.`type`=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, formName);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        return -1;
    }

    public BigDecimal proseedQuery(int temId, String date, String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, temId);
        preparedStatement.setString(2, date);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getBigDecimal(1);
        }
        return new BigDecimal(0);
    }

    public BigDecimal proseedQuery2(int temId, String date, String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, date);
        preparedStatement.setInt(2, temId);
        preparedStatement.setInt(3, temId);
        preparedStatement.setString(4, date);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getBigDecimal(1);
        }
        return new BigDecimal(0);
    }

    public ArrayList<Object[]> getSalesGroupByCategory(int temId, String date, Connection connection) throws SQLException {
        String query = "Select pos_m_item.cost_center, ifnull(sum(item_value),0.00) as total_item_value\n"
                + "   from temp_tr_details \n"
                + "   left join pos_m_item on pos_m_item.barcode=temp_tr_details.bar_code \n"
                + "   Where tr_status=2 \n"
                + "   and tr_type='invoice' \n"
                + "   and tr_det_type='Item' \n"
                + "   and terminal_id=?\n"
                + "   and tr_date1=DATE_FORMAT(?,'%Y%m%d') and temp_tr_details.is_sync=0 \n"
                + "   group by pos_m_item.cost_center";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, temId);
        preparedStatement.setString(2, date);
        ResultSet rst = preparedStatement.executeQuery();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new Object[]{"    " + rst.getString(1), rst.getBigDecimal(2)});
        }
        return arrayList;
    }

    public Integer getDeleteFefNo(Connection accConnection) throws SQLException {
        String query = "select ifnull( max(t_acc_ledger.delete_ref_no)+1,1) as number\n"
                + "from t_acc_ledger";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        throw new RuntimeException("Can't find delete ref number !");
    }

    public Integer getCurrentFinancialYear(Connection accConnection) throws SQLException {
        String query = "select m_financial_year.index_no\n"
                + "from m_financial_year\n"
                + "where m_financial_year.is_current=1";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        throw new RuntimeException("Can't find current financial year !");
    }

    public Integer updateTrSum(int selectedTerminal, String date, Connection accConnection) throws SQLException {
        String insertSql = "UPDATE pos_t_transaction_summary \n"
                + "SET `is_sync`='1', `sync_date_time`=now()\n"
                + "WHERE pos_t_transaction_summary.terminal_id=? \n"
                + "and pos_t_transaction_summary.tr_date=? ";
        PreparedStatement preparedStatement = accConnection.prepareStatement(insertSql);
        preparedStatement.setInt(1, selectedTerminal);
        preparedStatement.setString(2, date);

        return preparedStatement.executeUpdate();
    }

    public Integer getAccSettingByName(String settingName, Connection accConnection) throws SQLException {
        String query = "SELECT  `acc_account` FROM `m_acc_setting` WHERE name=? limit 1";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setString(1, settingName);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        throw new RuntimeException("Can't find Acc Setting from " + settingName);

    }

    public Integer getCostCenterByName(String costCenterName, Connection accConnection) throws SQLException {
        String query = "select m_cost_center.index_no\n"
                + "from m_cost_center\n"
                + "where m_cost_center.name=trim(?) limit 1";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setString(1, costCenterName);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        throw new RuntimeException("Can't find cost center from " + costCenterName);
    }

    public ArrayList<Object[]> getLineDiscountByCategory(int temId, String date, Connection connection) throws SQLException {
        String query = "select trim(pos_m_item.cost_center) as category,\n"
                + "	ifnull(sum(pos_t_transaction_details.line_dis_amt1)+sum(pos_t_transaction_details.line_dis_amt2),0.00)  as val\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "left join pos_m_item on pos_m_item.barcode=pos_t_transaction_details.bar_code\n"
                + "where (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0\n"
                + "and pos_t_transaction_summary.tr_type='invoice'\n"
                + "group by pos_m_item.cost_center\n"
                + "HAVING val!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, temId);
        preparedStatement.setString(2, date);
        ResultSet rst = preparedStatement.executeQuery();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new Object[]{"    " + rst.getString(1), rst.getBigDecimal(2)});
        }
        return arrayList;
    }

    public ArrayList<Object[]> getItemReturnByCategory(int temId, String date, Connection connection) throws SQLException {
        String query = "select trim(pos_m_item.cost_center) as category,\n"
                + "	ifnull(sum(pos_t_transaction_details.final_value),0.00) as val\n"
                + "from pos_t_transaction_details\n"
                + "left join pos_t_transaction_summary on pos_t_transaction_summary.index_no=pos_t_transaction_details.tr_index_no\n"
                + "left join pos_m_item on pos_m_item.barcode=pos_t_transaction_details.bar_code\n"
                + "where pos_t_transaction_details.tr_det_type='item' and pos_t_transaction_summary.tr_type='return' \n"
                + "and (2 is null or pos_t_transaction_summary.terminal_id=?)\n"
                + "and pos_t_transaction_summary.tr_date=? and pos_t_transaction_summary.is_sync=0\n"
                + "group by pos_m_item.cost_center\n"
                + "HAVING val!=0";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, temId);
        preparedStatement.setString(2, date);
        ResultSet rst = preparedStatement.executeQuery();
        ArrayList<Object[]> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new Object[]{"    " + rst.getString(1), rst.getBigDecimal(2)});
        }
        return arrayList;
    }

    public void saveAccLedger(HashMap<Integer, Object> det, Connection accConnection) throws SQLException {
        String insertSql = "insert into t_acc_ledger (\n"
                + "number,\n"
                + "search_code,\n"
                + "transaction_date,\n"
                + "`current_date`,\n"
                + "`time`,\n"
                + "`branch`,\n"
                + "current_branch,\n"
                + "`user`,\n"
                + "debit,\n"
                + "credit,\n"
                + "acc_account,\n"
                + "form_name,\n"
                + "`type`,\n"
                + "delete_ref_no,\n"
                + "description,\n"
                + "financial_year,\n"
                + "cost_center)\n"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = accConnection.prepareStatement(insertSql);
        preparedStatement.setInt(1, Integer.valueOf(det.get(1).toString()));
        preparedStatement.setString(2, (det.get(2).toString()));
        preparedStatement.setString(3, (det.get(3).toString()));
        preparedStatement.setString(4, (det.get(4).toString()));
        preparedStatement.setString(5, (det.get(5).toString()));
        preparedStatement.setInt(6, Integer.valueOf(det.get(6).toString()));
        preparedStatement.setInt(7, Integer.valueOf(det.get(7).toString()));
        preparedStatement.setInt(8, Integer.valueOf(det.get(8).toString()));
        preparedStatement.setBigDecimal(9, new BigDecimal(det.get(9).toString()));
        preparedStatement.setBigDecimal(10, new BigDecimal(det.get(10).toString()));
        preparedStatement.setInt(11, Integer.valueOf(det.get(11).toString()));
        preparedStatement.setString(12, String.valueOf(det.get(12).toString()));
        preparedStatement.setString(13, String.valueOf(det.get(13).toString()));
        preparedStatement.setInt(14, Integer.valueOf(det.get(14).toString()));
        preparedStatement.setString(15, String.valueOf(det.get(15).toString()));
        preparedStatement.setInt(16, Integer.valueOf(det.get(16).toString()));
        preparedStatement.setInt(17, Integer.valueOf(det.get(17).toString()));

        preparedStatement.executeUpdate();
    }

    public Integer getNotCheckDataCount(String date, Connection accConnection) throws SQLException {
        String query = "select count(pos_t_transaction_summary.index_no) as count\n"
                + "from pos_t_transaction_summary\n"
                + "where pos_t_transaction_summary.is_sync=0 and pos_t_transaction_summary.tr_date=?";
        PreparedStatement preparedStatement = accConnection.prepareStatement(query);
        preparedStatement.setString(1, date);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        throw new RuntimeException("Can't find Not Check Data");
    }

}
