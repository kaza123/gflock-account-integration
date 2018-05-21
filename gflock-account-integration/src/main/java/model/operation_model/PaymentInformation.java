/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.operation_model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author chama
 */
@Entity
@Table(name = "payment_information")
public class PaymentInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "type")
    private String type;

    @Column(name = "cheque_date")
    private String chequeDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_reader")
    private String cardReader;

    @Column(name = "munber")
    private String number;

    public PaymentInformation() {
    }

    public PaymentInformation(Integer indexNo, Integer payment, String type, String chequeDate, BigDecimal amount, String bank, String bankBranch, String cardType, String cardReader, String number) {
        this.indexNo = indexNo;
        this.payment = payment;
        this.type = type;
        this.chequeDate = chequeDate;
        this.amount = amount;
        this.bank = bank;
        this.bankBranch = bankBranch;
        this.cardType = cardType;
        this.cardReader = cardReader;
        this.number = number;
    }


    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getCardReader() {
        return cardReader;
    }

    public void setCardReader(String cardReader) {
        this.cardReader = cardReader;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
