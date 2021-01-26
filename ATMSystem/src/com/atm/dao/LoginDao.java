package com.atm.dao;

import com.atm.entity.CustomerInfo;
import com.atm.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 登录的业务层
 */
public class LoginDao {
    DBUtil dbUtil = new DBUtil();

    //定义一个根据账户卡号查询账户的方法
    public CustomerInfo queryCustomerInfo(String cardNumber){
        CustomerInfo customerInfo = new CustomerInfo();
        //判断这个卡号不为空且字符串也不为空
        if (cardNumber!=null&&!cardNumber.equals("")){
            String sql = "select * from t_customer_info where cardNumber=?";
            String[] param = new String[]{cardNumber};
            try {
                dbUtil.getConnection();
                ResultSet rs = dbUtil.executeQuery(sql, param);
                while (rs.next()){
                    //将客户信息进行封装
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

    //定义一个修改账户密码的方法
    public CustomerInfo changePassword(String cardNumber,String changedPassword){
        CustomerInfo customerInfo = new CustomerInfo();
        //判断这个卡号不为空且字符串也不为空
        if (cardNumber!=null&&!cardNumber.equals("")){
            //定义修改密码的sql语句
            String changeSql = "update t_customer_info set password=? where cardNumber=?";
            String[] param = new String[]{changedPassword,cardNumber};
            try {
                dbUtil.getConnection();
                int count = dbUtil.executeUpdate(changeSql, param);
                //判断是否存在该账户
                if (count>0){
                    //定义查询修改后账户信息的sql语句
                    String selectSql ="select * from t_customer_info where cardNumber=?";
                    ResultSet rs = dbUtil.executeQuery(selectSql, new String[]{cardNumber});
                    while (rs.next()){
                        //将客户信息进行封装
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
                    System.out.println("不存在该账户,无法修改密码");
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
