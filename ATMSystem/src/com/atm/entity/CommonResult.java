package com.atm.entity;

import java.io.Serializable;

/**
 *  服务端返回信息实体类
 */
public class CommonResult implements Serializable {

    private CustomerInfo customerInfo; //用户信息
    private int id;                    //操作字段的id(用于报表)
    private String message;            //服务器操作后返回的消息
    private boolean status;            //操作后是否成功的状态

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
