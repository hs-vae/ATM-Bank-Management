package com.atm.service;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import com.atm.util.DBUtil;

import net.sf.jasperreports.engine.JasperRunManager;

/**
 * 打印凭条服务
 */

public class PrintService {

    public void getListReportPDFAction(Map<String,Object> parameterMap,String savePdfPathname){
        Connection conn =null;//连接
        try {
            //报表生成之后编译生成的.jasper文件位置
            //1.绝对路径
            File jasperFile=new File("E:\\eclipse-workbace\\ATMSystem\\jasper\\chargeList.jasper");
            DBUtil dbUtil = new DBUtil();
            conn = dbUtil.getConnection();
            //设置参数生成pdf
            byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(),parameterMap,conn);
            //指定位置保存pdf位置
            FileOutputStream outs =new FileOutputStream(savePdfPathname);
            outs.write(bytes,0,bytes.length);
            outs.flush();
            outs.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ireport生成报表出错！");
        }finally {
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getSingleReportPDFAction(Map<String,Object> parameterMap,String savePdfPathname){
        Connection conn =null;//连接
        try {

            //报表生成之后编译生成的.jasper文件位置
            //1.绝对路径
            File jasperFile=new File("E:\\eclipse-workbace\\ATMSystem\\jasper\\singleRecord.jasper");
            DBUtil dbUtils = new DBUtil();
            conn = dbUtils.getConnection();
            //设置参数生成pdf
            byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(),parameterMap,conn);
            System.out.println(jasperFile.getPath());
            //指定位置保存pdf位置
            FileOutputStream outs =new FileOutputStream(savePdfPathname);
            outs.write(bytes,0,bytes.length);
            outs.flush();
            outs.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ireport生成报表出错！");
        }finally {
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

