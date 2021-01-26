package com.atm.entity;

import java.io.Serializable;

/**
 * 客户端上传信息实体类,便于将信息进行封装
 */
public class CommonAtmVO implements Serializable {
    private CustomerInfo customerInfo; //客户信息
    private Double operatorMoney;      //操作的金额
    private String type;               //操作的类型(存款,取款,转账,登录,登出)
    private String sourceCardNumber;   //卡号
    private String targetCardNumber;   //目标卡号

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Double getOperatorMoney() {
        return operatorMoney;
    }

    public void setOperatorMoney(Double operatorMoney) {
        this.operatorMoney = operatorMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceCardNumber() {
        return sourceCardNumber;
    }

    public void setSourceCardNumber(String sourceCardNumber) {
        this.sourceCardNumber = sourceCardNumber;
    }

    public String getTargetCardNumber() {
        return targetCardNumber;
    }

    public void setTargetCardNumber(String targetCardNumber) {
        this.targetCardNumber = targetCardNumber;
    }
}
