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
 * @author kasun
 */
@Entity
@Table(name = "grn_detail")
public class GrnDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "grn")
    private Integer grn;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_unit")
    private String itemUnit;

    @Column(name = "item_barcode")
    private String itemBarcode;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "qty")
    private BigDecimal qty;

    @Column(name = "`value`")
    private BigDecimal value;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "discount_value")
    private BigDecimal discountValue;

    @Column(name = "net_value")
    private BigDecimal netValue;

    @Column(name = "reorder_max")
    private BigDecimal reorderMax;

    @Column(name = "reorder_min")
    private BigDecimal reorderMin;

    @Column(name = "sales_price")
    private BigDecimal salesPrice;

    public GrnDetail() {
    }

    public GrnDetail(Integer indexNo, Integer grn, String itemNo, String itemName, String itemType, String itemUnit, String itemBarcode, BigDecimal cost, BigDecimal qty, BigDecimal value, BigDecimal discount, BigDecimal discountValue, BigDecimal netValue, BigDecimal reorderMax, BigDecimal reorderMin, BigDecimal salesPrice) {
        this.indexNo = indexNo;
        this.grn = grn;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemUnit = itemUnit;
        this.itemBarcode = itemBarcode;
        this.cost = cost;
        this.qty = qty;
        this.value = value;
        this.discount = discount;
        this.discountValue = discountValue;
        this.netValue = netValue;
        this.reorderMax = reorderMax;
        this.reorderMin = reorderMin;
        this.salesPrice = salesPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public BigDecimal getReorderMax() {
        return reorderMax;
    }

    public void setReorderMax(BigDecimal reorderMax) {
        this.reorderMax = reorderMax;
    }

    public BigDecimal getReorderMin() {
        return reorderMin;
    }

    public void setReorderMin(BigDecimal reorderMin) {
        this.reorderMin = reorderMin;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getGrn() {
        return grn;
    }

    public void setGrn(Integer grn) {
        this.grn = grn;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

}
