package com.atm.entity;

import java.io.Serializable;

/**
 * ��־��Ϣʵ����
 */
public class LogInfo implements Serializable {
    private long logId;  //��־id
    private String logInfo;   //��־��Ϣ
    private String logType;   //��־����
    private String logTime;   //��־ʱ��
    private String operateUser;  //������
    private String status;    //״̬

    public LogInfo(long logId, String logInfo, String logType, String logTime, String operateUser, String status) {
        this.logId = logId;
        this.logInfo = logInfo;
        this.logType = logType;
        this.logTime = logTime;
        this.operateUser = operateUser;
        this.status = status;
    }

    public LogInfo(String logInfo, String logType, String logTime, String operateUser, String status) {
        this.logInfo = logInfo;
        this.logType = logType;
        this.logTime = logTime;
        this.operateUser = operateUser;
        this.status = status;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
