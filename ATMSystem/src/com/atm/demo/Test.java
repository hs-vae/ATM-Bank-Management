package com.atm.demo;

import com.atm.ui.LoginFrame;

import java.awt.*;

/**
 * ATMϵͳ����
 */
public class Test {
    public static void main(String[] args) {
        //����֮ǰ�ȴ�server���µ�BankCenterServer������
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
