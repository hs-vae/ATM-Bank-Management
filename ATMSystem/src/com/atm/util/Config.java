package com.atm.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * �����ļ����ؾ�̬��
 */
//������Config
public class Config {
    private static Properties p=null;
    static {
        try {
            //���������ļ�
            p=new Properties();
            p.load(new FileInputStream("E:\\eclipse-workbace\\ATMSystem\\config\\mysql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ��ȡ����Ӧ��ֵ
    public static String getValue(String key){
        return p.get(key).toString();
    }
}
