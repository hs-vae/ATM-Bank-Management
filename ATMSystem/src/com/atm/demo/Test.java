package com.atm.demo;

import com.atm.ui.LoginFrame;

import java.awt.*;

/**
 * ATM系统测试
 */
public class Test {
    public static void main(String[] args) {
        //测试之前先打开server包下的BankCenterServer服务器
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
