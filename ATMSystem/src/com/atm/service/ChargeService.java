package com.atm.service;

import com.atm.dao.LogDao;
import com.atm.dao.ChargeDao;
import com.atm.entity.CommonResult;
import com.atm.entity.LogInfo;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;

import java.util.Vector;

/**
 * ATM交易(存款,取款,转账)服务
 */
public class ChargeService {
    ChargeDao chargeDao = new ChargeDao();
    LogDao  logDao = new LogDao();
    CommonResult commonResult = new CommonResult();
    //行内转账的方法
    public CommonResult transferInnerBank(String fromAccountInfo,String toAccountInfo,
                                          double transferMoney,String confirmPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.transferInnerBank(fromAccountInfo, toAccountInfo, transferMoney, confirmPwd);
        System.out.println(generateKey);
        if (generateKey>0){
            commonResult.setMessage("行内转账成功!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("行内转账,金额:"+transferMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_INTRANSFER")+"成功");
            //将行内转账成功信息更新到日志表
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("行内转账失败!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("行内转账,金额:"+transferMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_INTRANSFER")+"失败");
            //将行内转账异常信息更新到日志表
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }

    //跨行转账的方法
    public CommonResult transferOuterBank(String fromAccountInfo, double transferMoney , String confirmPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.transferOuterBank(fromAccountInfo, transferMoney ,confirmPwd);
        if (generateKey>0){
            commonResult.setMessage("跨行转账成功!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("跨行转账,金额:"+transferMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_OUTTRANSFER")+"成功");
            //将跨行转账成功信息更新到日志表
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("跨行转账失败!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("行内转账,金额:"+transferMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_OUTTRANSFER")+"失败");
            //将行内转账成功信息更新到日志表
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //存款的方法
    public CommonResult depositBank(String accountInfo,double depositMoney){
        commonResult = new CommonResult();
        int generateKey=-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.depositBank(accountInfo,depositMoney);
        if (generateKey>0){
            commonResult.setMessage("存款成功!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("存款,金额:"+depositMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("STATUS_SAVE"));
            //将存款成功信息更新到日志表
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //取款的方法
    public CommonResult withdrawBank(String accountInfo,double withdrawMoney,String withdrawPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.withdrawBank(accountInfo,withdrawMoney,withdrawPwd);
        if (generateKey>0){
            commonResult.setMessage("取款成功!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("取款,金额:"+withdrawMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("STATUS_FETCHSUCCESS"));
            //将取款成功信息更新到日志表
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("取款失败!余额不足或密码错误");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("取款,金额:"+withdrawMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("FAILED"));
            //将取款失败信息更新到日志表
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }

    //查询交易记录
    public void queryChargeRecord(String cardNumber){
        LogInfo logInfo =new LogInfo("查询交易记录",StatusEnumEntity.getValue("SUCCESS_LOG"),
                DateGenerateUtil.dateStringGenerate(),cardNumber,StatusEnumEntity.getValue("STATUS_QUERYSUCCESS"));
        logDao.recordLog(logInfo);
    }
}
