package com.atm.service;

import com.atm.dao.LogDao;
import com.atm.dao.ChargeDao;
import com.atm.entity.CommonResult;
import com.atm.entity.LogInfo;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;

import java.util.Vector;

/**
 * ATM����(���,ȡ��,ת��)����
 */
public class ChargeService {
    ChargeDao chargeDao = new ChargeDao();
    LogDao  logDao = new LogDao();
    CommonResult commonResult = new CommonResult();
    //����ת�˵ķ���
    public CommonResult transferInnerBank(String fromAccountInfo,String toAccountInfo,
                                          double transferMoney,String confirmPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.transferInnerBank(fromAccountInfo, toAccountInfo, transferMoney, confirmPwd);
        System.out.println(generateKey);
        if (generateKey>0){
            commonResult.setMessage("����ת�˳ɹ�!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("����ת��,���:"+transferMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_INTRANSFER")+"�ɹ�");
            //������ת�˳ɹ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("����ת��ʧ��!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("����ת��,���:"+transferMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_INTRANSFER")+"ʧ��");
            //������ת���쳣��Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }

    //����ת�˵ķ���
    public CommonResult transferOuterBank(String fromAccountInfo, double transferMoney , String confirmPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.transferOuterBank(fromAccountInfo, transferMoney ,confirmPwd);
        if (generateKey>0){
            commonResult.setMessage("����ת�˳ɹ�!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("����ת��,���:"+transferMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_OUTTRANSFER")+"�ɹ�");
            //������ת�˳ɹ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("����ת��ʧ��!");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("����ת��,���:"+transferMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    fromAccountInfo,
                    StatusEnumEntity.getValue("STATUS_OUTTRANSFER")+"ʧ��");
            //������ת�˳ɹ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //���ķ���
    public CommonResult depositBank(String accountInfo,double depositMoney){
        commonResult = new CommonResult();
        int generateKey=-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.depositBank(accountInfo,depositMoney);
        if (generateKey>0){
            commonResult.setMessage("���ɹ�!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("���,���:"+depositMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("STATUS_SAVE"));
            //�����ɹ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }
    //ȡ��ķ���
    public CommonResult withdrawBank(String accountInfo,double withdrawMoney,String withdrawPwd){
        commonResult = new CommonResult();
        int generateKey =-1;
        LogInfo logInfo = null;
        generateKey = chargeDao.withdrawBank(accountInfo,withdrawMoney,withdrawPwd);
        if (generateKey>0){
            commonResult.setMessage("ȡ��ɹ�!");
            commonResult.setStatus(true);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("ȡ��,���:"+withdrawMoney,
                    StatusEnumEntity.getValue("SUCCESS_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("STATUS_FETCHSUCCESS"));
            //��ȡ��ɹ���Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }else {
            commonResult.setMessage("ȡ��ʧ��!������������");
            commonResult.setStatus(false);
            commonResult.setId(generateKey);
            logInfo = new LogInfo("ȡ��,���:"+withdrawMoney,
                    StatusEnumEntity.getValue("EXCEPTION_LOG"),
                    DateGenerateUtil.dateStringGenerate(),
                    accountInfo,
                    StatusEnumEntity.getValue("FAILED"));
            //��ȡ��ʧ����Ϣ���µ���־��
            logDao.recordLog(logInfo);
        }
        return commonResult;
    }

    //��ѯ���׼�¼
    public void queryChargeRecord(String cardNumber){
        LogInfo logInfo =new LogInfo("��ѯ���׼�¼",StatusEnumEntity.getValue("SUCCESS_LOG"),
                DateGenerateUtil.dateStringGenerate(),cardNumber,StatusEnumEntity.getValue("STATUS_QUERYSUCCESS"));
        logDao.recordLog(logInfo);
    }
}
