/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account_model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author kasun
 */

public class TrSum implements Serializable {
   
    private Integer indexNo;
    private Integer terminalId;
    private Integer userId;
    private Boolean trStatus;
    private int maxTrNo;
    private Integer maxInvNo;
    private String trType;
    private String trNo;
    private String invoiceNo;
    private Date trDate;
    private String trDate1;
    private Date trStartTime;
    private Date trEndTime;
    private Integer customerId;
    private String cusMobNo;
    private String userName;
    private BigDecimal totalAmount;
    private BigDecimal discountP;
    private BigDecimal discountAmount;
    private BigDecimal roundupAmount;
    private BigDecimal trAmount;
    private boolean syncStatus;
    private BigDecimal loyaltyPoints;
    private boolean deleteStatus;
    private String balanceType;
    private boolean isSync;
    private Date syncDateTimt;

    public TrSum() {
    }

    public TrSum(Integer indexNo, Integer terminalId, Integer userId, Boolean trStatus, int maxTrNo, Integer maxInvNo, String trType, String trNo, String invoiceNo, Date trDate, String trDate1, Date trStartTime, Date trEndTime, Integer customerId, String cusMobNo, String userName, BigDecimal totalAmount, BigDecimal discountP, BigDecimal discountAmount, BigDecimal roundupAmount, BigDecimal trAmount, boolean syncStatus, BigDecimal loyaltyPoints, boolean deleteStatus, String balanceType, boolean isSync, Date syncDateTimt) {
        this.indexNo = indexNo;
        this.terminalId = terminalId;
        this.userId = userId;
        this.trStatus = trStatus;
        this.maxTrNo = maxTrNo;
        this.maxInvNo = maxInvNo;
        this.trType = trType;
        this.trNo = trNo;
        this.invoiceNo = invoiceNo;
        this.trDate = trDate;
        this.trDate1 = trDate1;
        this.trStartTime = trStartTime;
        this.trEndTime = trEndTime;
        this.customerId = customerId;
        this.cusMobNo = cusMobNo;
        this.userName = userName;
        this.totalAmount = totalAmount;
        this.discountP = discountP;
        this.discountAmount = discountAmount;
        this.roundupAmount = roundupAmount;
        this.trAmount = trAmount;
        this.syncStatus = syncStatus;
        this.loyaltyPoints = loyaltyPoints;
        this.deleteStatus = deleteStatus;
        this.balanceType = balanceType;
        this.isSync = isSync;
        this.syncDateTimt = syncDateTimt;
    }

   

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getTrStatus() {
        return trStatus;
    }

    public void setTrStatus(boolean trStatus) {
        this.trStatus = trStatus;
    }

    public int getMaxTrNo() {
        return maxTrNo;
    }

    public void setMaxTrNo(int maxTrNo) {
        this.maxTrNo = maxTrNo;
    }

    public Integer getMaxInvNo() {
        return maxInvNo;
    }

    public void setMaxInvNo(Integer maxInvNo) {
        this.maxInvNo = maxInvNo;
    }

    public String getTrType() {
        return trType;
    }

    public void setTrType(String trType) {
        this.trType = trType;
    }

    public String getTrNo() {
        return trNo;
    }

    public void setTrNo(String trNo) {
        this.trNo = trNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    public String getTrDate1() {
        return trDate1;
    }

    public void setTrDate1(String trDate1) {
        this.trDate1 = trDate1;
    }

    public Date getTrStartTime() {
        return trStartTime;
    }

    public void setTrStartTime(Date trStartTime) {
        this.trStartTime = trStartTime;
    }

    public Date getTrEndTime() {
        return trEndTime;
    }

    public void setTrEndTime(Date trEndTime) {
        this.trEndTime = trEndTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCusMobNo() {
        return cusMobNo;
    }

    public void setCusMobNo(String cusMobNo) {
        this.cusMobNo = cusMobNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscountP() {
        return discountP;
    }

    public void setDiscountP(BigDecimal discountP) {
        this.discountP = discountP;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getRoundupAmount() {
        return roundupAmount;
    }

    public void setRoundupAmount(BigDecimal roundupAmount) {
        this.roundupAmount = roundupAmount;
    }

    public BigDecimal getTrAmount() {
        return trAmount;
    }

    public void setTrAmount(BigDecimal trAmount) {
        this.trAmount = trAmount;
    }

    public boolean getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(boolean syncStatus) {
        this.syncStatus = syncStatus;
    }

    public BigDecimal getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(BigDecimal loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(boolean isSync) {
        this.isSync = isSync;
    }

    public Date getSyncDateTimt() {
        return syncDateTimt;
    }

    public void setSyncDateTimt(Date syncDateTimt) {
        this.syncDateTimt = syncDateTimt;
    }

}
