package com.atm.entity;

import java.io.Serializable;

/**
 * 交易记录实体类
 */
public class ChargeRecorder implements Serializable {
    private int chargeId;  //交易流水号
    private String chargeTime;  //交易时间
    private double chargeMoney; //交易金额
    private String customerName; //户名
    private String cardNumber;  //卡号
    private String chargeType;  //交易类型

    public ChargeRecorder() {
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public ChargeRecorder(int chargeId, String chargeTime, double chargeMoney, String customerName, String cardNumber, String chargeType) {
        this.chargeId = chargeId;
        this.chargeTime = chargeTime;
        this.chargeMoney = chargeMoney;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.chargeType = chargeType;
    }
}
