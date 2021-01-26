package com.atm.entity;

import java.io.Serializable;

/**
 * 日志信息实体类
 */
public class LogInfo implements Serializable {
    private long logId;  //日志id
    private String logInfo;   //日志信息
    private String logType;   //日志类型
    private String logTime;   //日志时间
    private String operateUser;  //操作者
    private String status;    //状态

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
