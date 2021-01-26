package com.atm.service;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import com.atm.util.DBUtil;

import net.sf.jasperreports.engine.JasperRunManager;

/**
 * ��ӡƾ������
 */

public class PrintService {

    public void getListReportPDFAction(Map<String,Object> parameterMap,String savePdfPathname){
        Connection conn =null;//����
        try {
            //��������֮��������ɵ�.jasper�ļ�λ��
            //1.����·��
            File jasperFile=new File("E:\\eclipse-workbace\\ATMSystem\\jasper\\chargeList.jasper");
            DBUtil dbUtil = new DBUtil();
            conn = dbUtil.getConnection();
            //���ò�������pdf
            byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(),parameterMap,conn);
            //ָ��λ�ñ���pdfλ��
            FileOutputStream outs =new FileOutputStream(savePdfPathname);
            outs.write(bytes,0,bytes.length);
            outs.flush();
            outs.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ireport���ɱ������");
        }finally {
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getSingleReportPDFAction(Map<String,Object> parameterMap,String savePdfPathname){
        Connection conn =null;//����
        try {

            //��������֮��������ɵ�.jasper�ļ�λ��
            //1.����·��
            File jasperFile=new File("E:\\eclipse-workbace\\ATMSystem\\jasper\\singleRecord.jasper");
            DBUtil dbUtils = new DBUtil();
            conn = dbUtils.getConnection();
            //���ò�������pdf
            byte[] bytes = JasperRunManager.runReportToPdf(jasperFile.getPath(),parameterMap,conn);
            System.out.println(jasperFile.getPath());
            //ָ��λ�ñ���pdfλ��
            FileOutputStream outs =new FileOutputStream(savePdfPathname);
            outs.write(bytes,0,bytes.length);
            outs.flush();
            outs.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ireport���ɱ������");
        }finally {
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

