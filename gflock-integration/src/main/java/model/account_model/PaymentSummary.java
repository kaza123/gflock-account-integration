/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account_model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author kasun
 */
public class PaymentSummary implements Serializable {

    private Integer indexNo;
    private BigDecimal invoiceAmount;
    private String discountType;
    private String promoNo;
    private BigDecimal discountRate;
    private BigDecimal discountAmount;
    private BigDecimal finalValue;
    private BigDecimal totalPaidAmount;
    private BigDecimal balanceReturn;
    private BigDecimal cashReturn;
    private String creditNoteNo;
    private BigDecimal creditNoteAmount;
    private BigDecimal ignoreAmount;
    private Integer trIndexNo;

    public PaymentSummary() {
    }

    public PaymentSummary(Integer indexNo, BigDecimal invoiceAmount, String discountType, String promoNo, BigDecimal discountRate, BigDecimal discountAmount, BigDecimal finalValue, BigDecimal totalPaidAmount, BigDecimal balanceReturn, BigDecimal cashReturn, String creditNoteNo, BigDecimal creditNoteAmount, BigDecimal ignoreAmount, Integer trIndexNo) {
        this.indexNo = indexNo;
        this.invoiceAmount = invoiceAmount;
        this.discountType = discountType;
        this.promoNo = promoNo;
        this.discountRate = discountRate;
        this.discountAmount = discountAmount;
        this.finalValue = finalValue;
        this.totalPaidAmount = totalPaidAmount;
        this.balanceReturn = balanceReturn;
        this.cashReturn = cashReturn;
        this.creditNoteNo = creditNoteNo;
        this.creditNoteAmount = creditNoteAmount;
        this.ignoreAmount = ignoreAmount;
        this.trIndexNo = trIndexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getPromoNo() {
        return promoNo;
    }

    public void setPromoNo(String promoNo) {
        this.promoNo = promoNo;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(BigDecimal finalValue) {
        this.finalValue = finalValue;
    }

    public BigDecimal getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(BigDecimal totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public BigDecimal getBalanceReturn() {
        return balanceReturn;
    }

    public void setBalanceReturn(BigDecimal balanceReturn) {
        this.balanceReturn = balanceReturn;
    }

    public BigDecimal getCashReturn() {
        return cashReturn;
    }

    public void setCashReturn(BigDecimal cashReturn) {
        this.cashReturn = cashReturn;
    }

    public String getCreditNoteNo() {
        return creditNoteNo;
    }

    public void setCreditNoteNo(String creditNoteNo) {
        this.creditNoteNo = creditNoteNo;
    }

    public BigDecimal getCreditNoteAmount() {
        return creditNoteAmount;
    }

    public void setCreditNoteAmount(BigDecimal creditNoteAmount) {
        this.creditNoteAmount = creditNoteAmount;
    }

    public BigDecimal getIgnoreAmount() {
        return ignoreAmount;
    }

    public void setIgnoreAmount(BigDecimal ignoreAmount) {
        this.ignoreAmount = ignoreAmount;
    }

    public Integer getTrIndexNo() {
        return trIndexNo;
    }

    public void setTrIndexNo(Integer trIndexNo) {
        this.trIndexNo = trIndexNo;
    }

}
