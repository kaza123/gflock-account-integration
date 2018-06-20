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
public class TrDet implements Serializable {

    private Integer indexNo;
    private String trDetType;
    private String barCode;
    private String description;
    private BigDecimal itemQty;
    private BigDecimal itemPrice;
    private BigDecimal itemValue;
    private BigDecimal lineDisP1;
    private BigDecimal lineDisP2;
    private BigDecimal lineDisAmt1;
    private BigDecimal lineDisAmt2;
    private BigDecimal finalValue;
    private Integer trIndexNo;

    public TrDet() {
    }

    public TrDet(Integer indexNo, String trDetType, String barCode, String description, BigDecimal itemQty, BigDecimal itemPrice, BigDecimal itemValue, BigDecimal lineDisP1, BigDecimal lineDisP2, BigDecimal lineDisAmt1, BigDecimal lineDisAmt2, BigDecimal finalValue, Integer trIndexNo) {
        this.indexNo = indexNo;
        this.trDetType = trDetType;
        this.barCode = barCode;
        this.description = description;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
        this.itemValue = itemValue;
        this.lineDisP1 = lineDisP1;
        this.lineDisP2 = lineDisP2;
        this.lineDisAmt1 = lineDisAmt1;
        this.lineDisAmt2 = lineDisAmt2;
        this.finalValue = finalValue;
        this.trIndexNo = trIndexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getTrDetType() {
        return trDetType;
    }

    public void setTrDetType(String trDetType) {
        this.trDetType = trDetType;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getItemQty() {
        return itemQty;
    }

    public void setItemQty(BigDecimal itemQty) {
        this.itemQty = itemQty;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    public BigDecimal getLineDisP1() {
        return lineDisP1;
    }

    public void setLineDisP1(BigDecimal lineDisP1) {
        this.lineDisP1 = lineDisP1;
    }

    public BigDecimal getLineDisP2() {
        return lineDisP2;
    }

    public void setLineDisP2(BigDecimal lineDisP2) {
        this.lineDisP2 = lineDisP2;
    }

    public BigDecimal getLineDisAmt1() {
        return lineDisAmt1;
    }

    public void setLineDisAmt1(BigDecimal lineDisAmt1) {
        this.lineDisAmt1 = lineDisAmt1;
    }

    public BigDecimal getLineDisAmt2() {
        return lineDisAmt2;
    }

    public void setLineDisAmt2(BigDecimal lineDisAmt2) {
        this.lineDisAmt2 = lineDisAmt2;
    }

    public BigDecimal getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(BigDecimal finalValue) {
        this.finalValue = finalValue;
    }

    public Integer getTrIndexNo() {
        return trIndexNo;
    }

    public void setTrIndexNo(Integer trIndexNo) {
        this.trIndexNo = trIndexNo;
    }

}
