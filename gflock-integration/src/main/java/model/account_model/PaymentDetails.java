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
public class PaymentDetails implements Serializable {

    private Integer pmtDetIndexNo;
    private String pmtType;
    private String pmtRefNo;
    private BigDecimal pmtAmount;
    private Integer machineNo;
    private Integer pmtIndexNo;

    public PaymentDetails() {
    }

    public PaymentDetails(Integer pmtDetIndexNo) {
        this.pmtDetIndexNo = pmtDetIndexNo;
    }

    public Integer getPmtDetIndexNo() {
        return pmtDetIndexNo;
    }

    public void setPmtDetIndexNo(Integer pmtDetIndexNo) {
        this.pmtDetIndexNo = pmtDetIndexNo;
    }

    public String getPmtType() {
        return pmtType;
    }

    public void setPmtType(String pmtType) {
        this.pmtType = pmtType;
    }

    public String getPmtRefNo() {
        return pmtRefNo;
    }

    public void setPmtRefNo(String pmtRefNo) {
        this.pmtRefNo = pmtRefNo;
    }

    public BigDecimal getPmtAmount() {
        return pmtAmount;
    }

    public void setPmtAmount(BigDecimal pmtAmount) {
        this.pmtAmount = pmtAmount;
    }

    public Integer getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(Integer machineNo) {
        this.machineNo = machineNo;
    }

    public Integer getPmtIndexNo() {
        return pmtIndexNo;
    }

    public void setPmtIndexNo(Integer pmtIndexNo) {
        this.pmtIndexNo = pmtIndexNo;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
