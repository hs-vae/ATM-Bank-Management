package com.atm.util;


import java.sql.*;

/**
 * ���ݿ⹤����
 */
public class DBUtil {

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ptmt=null;

    // 2.��ȡ����
    public Connection getConnection() throws SQLException {
        String url= Config.getValue("url");
        String user= Config.getValue("user");
        String password= Config.getValue("password");
        String driver= Config.getValue("driver");
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    // 3.�ͷ���Դ
    public  void closeAll(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(ptmt!=null){
            try {
                ptmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    // 4.ִ��SQL���,���Խ��в�ѯ
    public ResultSet executeQuery(String preparedSql , String[] param){
        try {
            // ����ִ��sql��PrepareStatement����
            ptmt=conn.prepareStatement(preparedSql);
            if(param!=null){
                for (int i = 0; i < param.length; i++) {
                    //ΪԤ�����sql���ò���,����ע�����ı����1,������������һ��������Ҫ��1
                    ptmt.setString(i+1,param[i]);
                }
            }
            // ��ȡִ��sql����,ִ��sql���
            rs=ptmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
    // 5.ִ��SQL���,���Խ�����,ɾ,�ĵĲ���
    public int executeUpdate(String preparedSql , String[] param){
        int count=0;
        try {
            // ����ִ��sql��PrepareStatement����
            ptmt=conn.prepareStatement(preparedSql);
            if(param!=null){
                for (int i = 0; i < param.length; i++) {
                    //ΪԤ�����sql���ò���,����ע�����ı����1,������������һ��������Ҫ��1
                    ptmt.setString(i+1,param[i]);
                }
            }
            count=ptmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    // 5.ִ��SQL���,���Խ�����,ɾ,�ĵĲ���
    public int executeUpdatePK(String preparedSql , String[] param){
        int count=0;
        int pk=0;
        try {
            // ����ִ��sql��PrepareStatement����
            ptmt=conn.prepareStatement(preparedSql,PreparedStatement.RETURN_GENERATED_KEYS);
            if(param!=null){
                for (int i = 0; i < param.length; i++) {
                    //ΪԤ�����sql���ò���,����ע�����ı����1,������������һ��������Ҫ��1
                    ptmt.setString(i+1,param[i]);
                }
            }
            count=ptmt.executeUpdate();
            ResultSet rs = ptmt.getGeneratedKeys();
            if (count>0 && rs.next()){
                pk = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pk;
    }
}
