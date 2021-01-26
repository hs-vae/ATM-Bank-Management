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
 * 交易业务(转账,存款,取款)层
 */
public class ChargeDao {
    DBUtil dbUtil = new DBUtil();
    LoginDao loginDao = new LoginDao();
    //定义操作单条记录的方法
    public int operateRecord(String operateAccountInfo, double operateMoney,
                             String chargeType,double remainMoney){
        int pk = -1;  //定义一个主键并初始化
        //定义插入交易操作记录的sql语句
        String sql = "insert into " +
                "t_charge_record(chargeTime,chargeMoney,customerName," +
                "cardNumber,chargeType,remainMoney) values(?,?,?,?,?,?)";
        String queryKey="select chargeId from t_charge_record where cardNumber=?";
        //客户名字可以通过LoginDao里的查询方法(按照卡号)进行获取
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
            //连接数据库
            dbUtil.getConnection();
            //执行插入语句
            dbUtil.executeUpdate(sql, param);
            //插入交易表信息后再查询该信息的id号
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

    //定义行内转账的方法,参数列表(转出与转入的卡号,用户名,密码,转账类型)
    public int transferInnerBank(String fromAccountInfo,String toAccountInfo,
                              double transferMoney,String confirmPwd){
        int pk=-1;
        //定义转出的sql语句
        String fromSql="update t_customer_info set remainMoney=? where cardNumber=? and password=?";
        //定义转入的sql语句
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
            //判断这两个执行结果的条数是否同时大于0
            if (from>0 && to>0){
                //将转出的信息记录更新到交易表中
                pk = operateRecord(fromAccountInfo,transferMoney,
                        "行内"+StatusEnumEntity.getValue("STATUS_OUT"),
                        fromCustomerInfo.getRemainMoney()-transferMoney);
                //将转入的信息记录更新到交易表中
                operateRecord(toAccountInfo,transferMoney,
                        "行内"+StatusEnumEntity.getValue("STATUS_IN"),
                        toCustomerInfo.getRemainMoney()+transferMoney);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }
    //定义跨行转账的方法
    public int transferOuterBank(String fromAccountInfo, double transferMoney,String confirmPwd){
        int pk=-1;
        //定义转出的sql语句
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
            //判断这个执行结果的条数是否大于0
            if (from>0 ){
                //符合条件那么将该操作记录更新到交易表里
                pk = operateRecord(fromAccountInfo,transferMoney,
                        "跨行"+StatusEnumEntity.getValue("STATUS_OUT"),
                        fromCustomerInfo.getRemainMoney()-transferMoney);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return pk;
    }

    //定义存款的方法
    public int depositBank(String accountInfo,double depositMoney){
        int pk =-1;
        //定义存款的sql语句
        String depositSql="update t_customer_info set remainMoney=?" +
                "where cardNumber=? ";
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(accountInfo);
        //设置sql语句中的参数,注意里面要将double转为String类型
        String[] param={String.valueOf(customerInfo.getRemainMoney()+depositMoney), accountInfo};
        try {
            dbUtil.getConnection();
            pk= dbUtil.executeUpdate(depositSql, param);
            //判断这个执行结果的条数是否大于0
            if (pk>0){
                //符合条件那么将该操作记录更新到交易表里
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

    //定义取款的方法
    public int withdrawBank(String accountInfo,double withdrawMoney,String withdrawPwd){
        int pk =-1;
        //定义取款的sql语句
        String withdrawSql="update t_customer_info set remainMoney=? where cardNumber=? and password=?";
        CustomerInfo customerInfo = loginDao.queryCustomerInfo(accountInfo);
        //保证余额大于取款金额并且密码正确
        if (customerInfo.getRemainMoney()>=withdrawMoney && withdrawPwd.equals(customerInfo.getPassword())){
            //设置sql语句中的参数,注意里面要将double转为String类型
            String[] param={String.valueOf(customerInfo.getRemainMoney()-withdrawMoney),
                    accountInfo,withdrawPwd};
            try {
                dbUtil.getConnection();
                int withdrawResult = dbUtil.executeUpdate(withdrawSql, param);
                //判断这个执行结果的条数是否大于0
                if (withdrawResult>0){
                    //符合条件那么将该操作记录更新到交易表里
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


    //定义查询交易信息的方法(不知道信息有多少条可以用Vector类)
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
