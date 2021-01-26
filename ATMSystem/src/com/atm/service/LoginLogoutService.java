package com.atm.service;

import com.atm.dao.LogDao;
import com.atm.dao.LoginDao;
import com.atm.entity.CommonResult;
import com.atm.entity.CustomerInfo;
import com.atm.entity.LogInfo;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;


/**
 * ATM登录(含修改密码)与退出服务
 */
public class LoginLogoutService {
    LoginDao loginDao = new LoginDao();
    LogDao logDao = new LogDao();
    CommonResult commonResult = new CommonResult();

    //登录ATM系统
    public CommonResult loginBank(String loginAccountInfo,String confirmPwd){
        commonResult = new CommonResult();
        int generateKey=-1;
        LogInfo logInfo=null;
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(loginAccountInfo);
        String loginPassword = customerInfo.getPassword();
        String cardNumber = customerInfo.getCardNumber();
        if (loginAccountInfo.equals(cardNumber)&&loginPassword.equals(confirmPwd)){
            commonResult.setMessage("登录成功!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("登录账户", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginAccountInfo,
                    StatusEnumEntity.getValue("STATUS_LOGIN"));
            //将登录信息更新到日志表
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("登录失败!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("登录账户",StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginAccountInfo,
                    StatusEnumEntity.getValue("STATUS_LOGINFAILED"));
            //将登录失败的信息更新到日志表
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //退出ATM系统
    public CommonResult logoutBank(String logoutAccountInfo){
        commonResult = new CommonResult();
        LogInfo logInfo=null;
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(logoutAccountInfo);
        int generateKey = Integer.parseInt(customerInfo.getCertifyNumber());
        commonResult.setMessage("退出成功!");
        commonResult.setStatus(true);
        commonResult.setId(generateKey);
        logInfo = new LogInfo("退出账户", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),logoutAccountInfo,
                    StatusEnumEntity.getValue("STATUS_EXIT"));
        //将退出信息更新到日志表
        logDao.recordLog(logInfo);
        return commonResult;
    }
    //修改密码
    public CommonResult changePwdBank(String loginCardNumber,String changedPassword){
        commonResult = new CommonResult();
        LogInfo logInfo=null;
        int generateKey=-1;
        CustomerInfo customerInfo = loginDao.changePassword(loginCardNumber, changedPassword);
        generateKey = Integer.parseInt(customerInfo.getCertifyNumber());
        commonResult.setMessage("修改密码成功!");
        commonResult.setStatus(true);
        commonResult.setId(generateKey);
        logInfo = new LogInfo("修改密码", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginCardNumber,
                    StatusEnumEntity.getValue("STATUS_CHANGESUCCESS"));//将修改密码的信息更新到日志表
        logDao.recordLog(logInfo);
        return commonResult;
    }
}
