package com.atm.entity;

import java.io.Serializable;

/**
 * �ͻ����ϴ���Ϣʵ����,���ڽ���Ϣ���з�װ
 */
public class CommonAtmVO implements Serializable {
    private CustomerInfo customerInfo; //�ͻ���Ϣ
    private Double operatorMoney;      //�����Ľ��
    private String type;               //����������(���,ȡ��,ת��,��¼,�ǳ�)
    private String sourceCardNumber;   //����
    private String targetCardNumber;   //Ŀ�꿨��

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
