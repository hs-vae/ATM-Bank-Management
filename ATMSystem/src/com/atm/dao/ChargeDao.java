package com.atm.dao;

import com.atm.entity.CustomerInfo;
import com.atm.service.ChargeService;
import com.atm.util.DBUtil;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * ����ҵ��(ת��,���,ȡ��)��
 */
public class ChargeDao {
    DBUtil dbUtil = new DBUtil();
    LoginDao loginDao = new LoginDao();
    //�������������¼�ķ���
    public int operateRecord(String operateAccountInfo, double operateMoney,
                             String chargeType,double remainMoney){
        int pk = -1;  //����һ����������ʼ��
        //������뽻�ײ�����¼��sql���
        String sql = "insert into " +
                "t_charge_record(chargeTime,chargeMoney,customerName," +
                "cardNumber,chargeType,remainMoney) values(?,?,?,?,?,?)";
        String queryKey="select chargeId from t_charge_record where cardNumber=?";
        //�ͻ����ֿ���ͨ��LoginDao��Ĳ�ѯ����(���տ���)���л�ȡ
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(operateAccountInfo);
        String[] param = new String[]{
                DateGenerateUtil.dateStringGenerate(),
                String.valueOf(operateMoney),
                customerInfo.getCustomerName(),
                operateAccountInfo,
                chargeType,
                String.valueOf(remainMoney)
        };
        String[] queryParam= new String[]{operateAccountInfo};
        try {
            //�������ݿ�
            dbUtil.getConnection();
            //ִ�в������
            dbUtil.executeUpdate(sql, param);
            //���뽻�ױ���Ϣ���ٲ�ѯ����Ϣ��id��
            ResultSet resultSet = dbUtil.executeQuery(queryKey, queryParam);
            while (resultSet.next()){
                pk = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }

    //��������ת�˵ķ���,�����б�(ת����ת��Ŀ���,�û���,����,ת������)
    public int transferInnerBank(String fromAccountInfo,String toAccountInfo,
                              double transferMoney,String confirmPwd){
        int pk=-1;
        //����ת����sql���
        String fromSql="update t_customer_info set remainMoney=? where cardNumber=? and password=?";
        //����ת���sql���
        String toSql="update t_customer_info set remainMoney=? where cardNumber=?";
        CustomerInfo fromCustomerInfo = loginDao.queryCustomerInfo(fromAccountInfo);
        CustomerInfo toCustomerInfo = loginDao.queryCustomerInfo(toAccountInfo);
        String[] paramFrom = new String[]{
                String.valueOf(fromCustomerInfo.getRemainMoney()-transferMoney),
                fromAccountInfo,
                confirmPwd
        };
        String[] paramTo = new String[]{
                String.valueOf(toCustomerInfo.getRemainMoney()+transferMoney),
                toAccountInfo
        };
        try {
            dbUtil.getConnection();
            int from = dbUtil.executeUpdate(fromSql, paramFrom);
            int to = dbUtil.executeUpdate(toSql, paramTo);
            //�ж�������ִ�н���������Ƿ�ͬʱ����0
            if (from>0 && to>0){
                //��ת������Ϣ��¼���µ����ױ���
                pk = operateRecord(fromAccountInfo,transferMoney,
                        "����"+StatusEnumEntity.getValue("STATUS_OUT"),
                        fromCustomerInfo.getRemainMoney()-transferMoney);
                //��ת�����Ϣ��¼���µ����ױ���
                operateRecord(toAccountInfo,transferMoney,
                        "����"+StatusEnumEntity.getValue("STATUS_IN"),
                        toCustomerInfo.getRemainMoney()+transferMoney);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }
    //�������ת�˵ķ���
    public int transferOuterBank(String fromAccountInfo, double transferMoney,String confirmPwd){
        int pk=-1;
        //����ת����sql���
        String fromSql="update t_customer_info set remainMoney=? where cardNumber=? and password=?";
        CustomerInfo fromCustomerInfo = loginDao.queryCustomerInfo(fromAccountInfo);
        String[] paramFrom = new String[]{
                String.valueOf(fromCustomerInfo.getRemainMoney()-transferMoney),
                fromAccountInfo,
                confirmPwd
        };
        try {
            dbUtil.getConnection();
            int from = dbUtil.executeUpdate(fromSql, paramFrom);
            //�ж����ִ�н���������Ƿ����0
            if (from>0 ){
                //����������ô���ò�����¼���µ����ױ���
                pk = operateRecord(fromAccountInfo,transferMoney,
                        "����"+StatusEnumEntity.getValue("STATUS_OUT"),
                        fromCustomerInfo.getRemainMoney()-transferMoney);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }

    //������ķ���
    public int depositBank(String accountInfo,double depositMoney){
        int pk =-1;
        //�������sql���
        String depositSql="update t_customer_info set remainMoney=?" +
                "where cardNumber=? ";
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(accountInfo);
        //����sql����еĲ���,ע������Ҫ��doubleתΪString����
        String[] param={String.valueOf(customerInfo.getRemainMoney()+depositMoney), accountInfo};
        try {
            dbUtil.getConnection();
            pk= dbUtil.executeUpdate(depositSql, param);
            //�ж����ִ�н���������Ƿ����0
            if (pk>0){
                //����������ô���ò�����¼���µ����ױ���
                pk = operateRecord(accountInfo,depositMoney,
                        StatusEnumEntity.getValue("STATUS_SAVE"),
                        customerInfo.getRemainMoney()+depositMoney);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }

    //����ȡ��ķ���
    public int withdrawBank(String accountInfo,double withdrawMoney,String withdrawPwd){
        int pk =-1;
        //����ȡ���sql���
        String withdrawSql="update t_customer_info set remainMoney=? where cardNumber=? and password=?";
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(accountInfo);
        //��֤������ȡ�����������ȷ
        if (customerInfo.getRemainMoney()>=withdrawMoney && withdrawPwd.equals(customerInfo.getPassword())){
            //����sql����еĲ���,ע������Ҫ��doubleתΪString����
            String[] param={String.valueOf(customerInfo.getRemainMoney()-withdrawMoney),
                    accountInfo,withdrawPwd};
            try {
                dbUtil.getConnection();
                int withdrawResult = dbUtil.executeUpdate(withdrawSql, param);
                //�ж����ִ�н���������Ƿ����0
                if (withdrawResult>0){
                    //����������ô���ò�����¼���µ����ױ���
                    pk = operateRecord(accountInfo,withdrawMoney,
                            StatusEnumEntity.getValue("STATUS_FETCH"),
                            customerInfo.getRemainMoney()-withdrawMoney);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                dbUtil.closeAll();
            }
        }
        return pk;
    }


    //�����ѯ������Ϣ�ķ���(��֪����Ϣ�ж�����������Vector��)
    public Vector<Vector<String>> queryChargeRecord(String cardNumber){
        String sql = "select * from t_charge_record where cardNumber=?";
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        int rowCount = 0;
        try{
            dbUtil.getConnection();
            ResultSet rs = dbUtil.executeQuery(sql, new String[]{cardNumber});
            Vector<String> rowData;
            while(rs.next()) {
                rowCount++;
                rowData = new Vector<String>();
                rowData.add(rs.getString("chargeId"));
                rowData.add(rs.getString("chargeTime"));
                rowData.add(rs.getString("chargeMoney"));
                rowData.add(rs.getString("customerName"));
                rowData.add(rs.getString("cardNumber"));
                rowData.add(rs.getString("chargeType"));
                rowData.add(rs.getString("remainMoney"));
                data.add(rowData);
            }
            ChargeService chargeService = new ChargeService();
            chargeService.queryChargeRecord(cardNumber);
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally {
            dbUtil.closeAll();
        }
        if(rowCount == 0) {
            return null;
        }else {
            return data;
        }
    }
}
