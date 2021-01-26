package com.atm.dao;

import com.atm.entity.LogInfo;
import com.atm.util.DBUtil;

import java.sql.SQLException;

/**
 * ��־��Ϣҵ���
 */
public class LogDao {
    DBUtil dbUtil = new DBUtil();

    //�����¼��־��Ϣ�ķ���
    public int recordLog(LogInfo logInfo){
        int result = -1;
        String sql="insert into t_log_info(loginfo,logtype,logtime,operateUser,status) " +
                "values(?,?,?,?,?)";
        String[] param=new String[]{
                logInfo.getLogInfo(),
                logInfo.getLogType(),
                logInfo.getLogTime(),
                logInfo.getOperateUser(),
                logInfo.getStatus()};
        try {
            dbUtil.getConnection();
            result= dbUtil.executeUpdate(sql, param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return result;
    }
}
