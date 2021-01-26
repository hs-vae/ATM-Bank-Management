package com.atm.entity;

import java.io.Serializable;

/**
 * 客户信息实体类
 */
public class CustomerInfo implements Serializable {
    private String cardNumber; //卡号
    private String certifyNumber; //字段名
    private String customerName;  //客户名字
    private String password;   //银行卡密码
    private double remainMoney;  //卡余额
    private String createDate;  //开户时间
    private String createCardBank; //开户的银行
    private String status;     //银行卡状态

    public String getCardNumber() {
        return cardNumber;
    }

    public CustomerInfo(String cardNumber,  String password) {
        this.cardNumber = cardNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "cardNumber='" + cardNumber + '\'' +
                ", certifyNumber='" + certifyNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", remainMoney='" + remainMoney + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createCardBank='" + createCardBank + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCertifyNumber() {
        return certifyNumber;
    }

    public void setCertifyNumber(String certifyNumber) {
        this.certifyNumber = certifyNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateCardBank() {
        return createCardBank;
    }

    public void setCreateCardBank(String createCardBank) {
        this.createCardBank = createCardBank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerInfo() {
    }

    public CustomerInfo(String cardNumber, String certifyNumber, String customerName, String password, double remainMoney, String createDate, String createCardBank, String status) {
        this.cardNumber = cardNumber;
        this.certifyNumber = certifyNumber;
        this.customerName = customerName;
        this.password = password;
        this.remainMoney = remainMoney;
        this.createDate = createDate;
        this.createCardBank = createCardBank;
        this.status = status;
    }
}
