package com.atm.dao;

import com.atm.entity.CustomerInfo;
import com.atm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ��¼��ҵ���
 */
public class LoginDao {
    DBUtil dbUtil = new DBUtil();

    //����һ�������˻����Ų�ѯ�˻��ķ���
    public CustomerInfo queryCustomerInfo(String cardNumber){
        CustomerInfo customerInfo = new CustomerInfo();
        //�ж�������Ų�Ϊ�����ַ���Ҳ��Ϊ��
        if (cardNumber!=null&&!cardNumber.equals("")){
            String sql = "select * from t_customer_info where cardNumber=?";
            String[] param = new String[]{cardNumber};
            try {
                dbUtil.getConnection();
                ResultSet rs = dbUtil.executeQuery(sql, param);
                while (rs.next()){
                    //���ͻ���Ϣ���з�װ
                    customerInfo.setCardNumber(rs.getString("cardNumber"));
                    customerInfo.setCertifyNumber(rs.getString("certifyNumber"));
                    customerInfo.setCustomerName(rs.getString("customerName"));
                    customerInfo.setPassword(rs.getString("password"));
                    customerInfo.setRemainMoney(rs.getDouble("remainMoney"));
                    customerInfo.setCreateDate(rs.getString("createDate"));
                    customerInfo.setCreateCardBank(rs.getString("createCardBank"));
                    customerInfo.setStatus(rs.getString("status"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                dbUtil.closeAll();
            }
        }
        return customerInfo;
    }

    //����һ���޸��˻�����ķ���
    public CustomerInfo changePassword(String cardNumber,String changedPassword){
        CustomerInfo customerInfo = new CustomerInfo();
        //�ж�������Ų�Ϊ�����ַ���Ҳ��Ϊ��
        if (cardNumber!=null&&!cardNumber.equals("")){
            //�����޸������sql���
            String changeSql = "update t_customer_info set password=? where cardNumber=?";
            String[] param = new String[]{changedPassword,cardNumber};
            try {
                dbUtil.getConnection();
                int count = dbUtil.executeUpdate(changeSql, param);
                //�ж��Ƿ���ڸ��˻�
                if (count>0){
                    //�����ѯ�޸ĺ��˻���Ϣ��sql���
                    String selectSql ="select * from t_customer_info where cardNumber=?";
                    ResultSet rs = dbUtil.executeQuery(selectSql, new String[]{cardNumber});
                    while (rs.next()){
                        //���ͻ���Ϣ���з�װ
                        customerInfo.setCardNumber(rs.getString("cardNumber"));
                        customerInfo.setCertifyNumber(rs.getString("certifyNumber"));
                        customerInfo.setCustomerName(rs.getString("customerName"));
                        customerInfo.setPassword(rs.getString("password"));
                        customerInfo.setRemainMoney(rs.getDouble("remainMoney"));
                        customerInfo.setCreateDate(rs.getString("createDate"));
                        customerInfo.setCreateCardBank(rs.getString("createCardBank"));
                        customerInfo.setStatus(rs.getString("status"));
                    }
                }else {
                    customerInfo.setCertifyNumber(null);
                    System.out.println("�����ڸ��˻�,�޷��޸�����");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                dbUtil.closeAll();
            }
        }
        return customerInfo;
    }
}
