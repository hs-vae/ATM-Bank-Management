package com.atm.service;

import com.atm.dao.LogDao;
import com.atm.dao.LoginDao;
import com.atm.entity.CommonResult;
import com.atm.entity.CustomerInfo;
import com.atm.entity.LogInfo;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;


/**
 * ATM��¼(���޸�����)���˳�����
 */
public class LoginLogoutService {
    LoginDao loginDao = new LoginDao();
    LogDao logDao = new LogDao();
    CommonResult commonResult = new CommonResult();

    //��¼ATMϵͳ
    public CommonResult loginBank(String loginAccountInfo,String confirmPwd){
        commonResult = new CommonResult();
        int generateKey=-1;
        LogInfo logInfo=null;
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(loginAccountInfo);
        String loginPassword = customerInfo.getPassword();
        String cardNumber = customerInfo.getCardNumber();
        if (loginAccountInfo.equals(cardNumber)&&loginPassword.equals(confirmPwd)){
            commonResult.setMessage("��¼�ɹ�!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("��¼�˻�", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginAccountInfo,
                    StatusEnumEntity.getValue("STATUS_LOGIN"));
            //����¼��Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("��¼ʧ��!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("��¼�˻�",StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginAccountInfo,
                    StatusEnumEntity.getValue("STATUS_LOGINFAILED"));
            //����¼ʧ�ܵ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //�˳�ATMϵͳ
    public CommonResult logoutBank(String logoutAccountInfo){
        commonResult = new CommonResult();
        LogInfo logInfo=null;
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(logoutAccountInfo);
        int generateKey = Integer.parseInt(customerInfo.getCertifyNumber());
        commonResult.setMessage("�˳��ɹ�!");
        commonResult.setStatus(true);
        commonResult.setId(generateKey);
        logInfo = new LogInfo("�˳��˻�", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),logoutAccountInfo,
                    StatusEnumEntity.getValue("STATUS_EXIT"));
        //���˳���Ϣ���µ���־��
        logDao.recordLog(logInfo);
        return commonResult;
    }
    //�޸�����
    public CommonResult changePwdBank(String loginCardNumber,String changedPassword){
        commonResult = new CommonResult();
        LogInfo logInfo=null;
        int generateKey=-1;
        CustomerInfo customerInfo = loginDao.changePassword(loginCardNumber, changedPassword);
        generateKey = Integer.parseInt(customerInfo.getCertifyNumber());
        commonResult.setMessage("�޸�����ɹ�!");
        commonResult.setStatus(true);
        commonResult.setId(generateKey);
        logInfo = new LogInfo("�޸�����", StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),loginCardNumber,
                    StatusEnumEntity.getValue("STATUS_CHANGESUCCESS"));//���޸��������Ϣ���µ���־��
        logDao.recordLog(logInfo);
        return commonResult;
    }
}
