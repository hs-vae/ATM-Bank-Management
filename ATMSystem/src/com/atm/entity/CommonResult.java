package com.atm.entity;

import java.io.Serializable;

/**
 *  ����˷�����Ϣʵ����
 */
public class CommonResult implements Serializable {

    private CustomerInfo customerInfo; //�û���Ϣ
    private int id;                    //�����ֶε�id(���ڱ���)
    private String message;            //�����������󷵻ص���Ϣ
    private boolean status;            //�������Ƿ�ɹ���״̬

    public CommonResult() {
    }

    public CommonResult(CustomerInfo customerInfo, int id, String message, boolean status) {
        this.customerInfo = customerInfo;
        this.id = id;
        this.message = message;
        this.status = status;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
