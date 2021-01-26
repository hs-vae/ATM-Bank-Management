package com.atm.ui;

import com.atm.dao.ChargeDao;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.util.Vector;
/**
 * ATM主界面(存款,取款,转账,查询交易记录)
 */
public class MainFrame {

	private JFrame frame;
	public JTextField textCardNumber = new JTextField();
	public JTextField textCustomerName = new JTextField();
	public JTextField textRemainMoney = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workbace\\ATMSystem\\logo\\ConstructionBank.jpg"));
		frame.setTitle("\u4E2D\u56FD\u5EFA\u8BBE\u94F6\u884CATM\u7CFB\u7EDF");
		frame.setBounds(100, 100, 703, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8D26\u6237\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel.setBounds(95, 101, 77, 44);
		panel.add(lblNewLabel);

		JButton saveMoneyButton = new JButton("\u5B58\u94B1");
		saveMoneyButton.setBackground(Color.PINK);
		saveMoneyButton.setFont(new Font("宋体", Font.BOLD, 17));
		saveMoneyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				DepositFrame depositFrame = new DepositFrame();
				depositFrame.getFrame().setVisible(true);
				depositFrame.textCustomerName.setText(textCustomerName.getText().trim());
				depositFrame.textCardNumber.setText(textCardNumber.getText().trim());
				depositFrame.textRemainMoney.setText(textRemainMoney.getText().trim());
			}
		});
		saveMoneyButton.setBounds(369, 101, 104, 44);
		panel.add(saveMoneyButton);

		JButton withdrawMoneyButton = new JButton("\u53D6\u94B1");
		withdrawMoneyButton.setBackground(Color.PINK);
		withdrawMoneyButton.setFont(new Font("宋体", Font.BOLD, 17));
		withdrawMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				WithdrawFrame withdrawFrame = new WithdrawFrame();
				withdrawFrame.getFrame().setVisible(true);
				withdrawFrame.textCardNumber.setText(textCardNumber.getText().trim());
				withdrawFrame.textCustomerName.setText(textCustomerName.getText().trim());
				withdrawFrame.textRemainMoney.setText(textRemainMoney.getText().trim());

			}
		});
		withdrawMoneyButton.setBounds(369, 209, 104, 46);
		panel.add(withdrawMoneyButton);

		JButton transferMoneyButton = new JButton("\u8F6C\u8D26");
		transferMoneyButton.setBackground(Color.PINK);
		transferMoneyButton.setFont(new Font("宋体", Font.BOLD, 17));
		transferMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TransferFrame transferFrame = new TransferFrame();
				transferFrame.getFrame().setVisible(true);
				transferFrame.textCardNumber.setText(textCardNumber.getText().trim());
				transferFrame.textCustomerName.setText(textCustomerName.getText().trim());
				transferFrame.textRemainMoney.setText(textRemainMoney.getText().trim());
			}
		});
		transferMoneyButton.setBounds(369, 350, 104, 45);
		panel.add(transferMoneyButton);

		JButton chargeRecordButton = new JButton("\u4EA4\u6613\u8BB0\u5F55");
		chargeRecordButton.setBackground(Color.PINK);
		chargeRecordButton.setFont(new Font("宋体", Font.BOLD, 17));
		chargeRecordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ChargeRecordFrame chargeRecordFrame = new ChargeRecordFrame();
				chargeRecordFrame.getFrame().setVisible(true);
				chargeRecordFrame.textCardNumber.setText(textCardNumber.getText().trim());
				chargeRecordFrame.textCustomerName.setText(textCustomerName.getText().trim());
				chargeRecordFrame.textRemainMoney.setText(textRemainMoney.getText().trim());
				chargeRecordFrame.showData(textCardNumber.getText().trim());
			}
		});
		chargeRecordButton.setBounds(520, 210, 104, 44);
		panel.add(chargeRecordButton);

		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.setBackground(new Color(255, 0, 0));
		exitButton.setForeground(new Color(255, 255, 255));
		exitButton.setFont(new Font("宋体", Font.BOLD, 17));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		exitButton.setBounds(520, 350, 104, 44);
		panel.add(exitButton);

		textCardNumber = new JTextField();
		textCardNumber.setFont(new Font("宋体", Font.BOLD, 17));
		textCardNumber.setBounds(182, 102, 104, 43);
		panel.add(textCardNumber);
		textCardNumber.setColumns(10);

		JLabel customerNameLabel = new JLabel("\u59D3\u540D\uFF1A");
		customerNameLabel.setFont(new Font("宋体", Font.BOLD, 17));
		customerNameLabel.setBounds(95, 215, 54, 33);
		panel.add(customerNameLabel);

		textCustomerName = new JTextField();
		textCustomerName.setFont(new Font("宋体", Font.BOLD, 17));
		textCustomerName.setBounds(182, 211, 106, 44);
		panel.add(textCustomerName);
		textCustomerName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u4F59\u989D\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(95, 353, 80, 44);
		panel.add(lblNewLabel_1);

		textRemainMoney = new JTextField();
		textRemainMoney.setFont(new Font("宋体", Font.BOLD, 17));
		textRemainMoney.setBounds(182, 353, 106, 44);
		panel.add(textRemainMoney);
		textRemainMoney.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u4E3B\u754C\u9762");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel_2.setBounds(281, 34, 192, 56);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2\u4F59\u989D");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 17));
		btnNewButton.setBounds(520, 101, 104, 46);
		panel.add(btnNewButton);
		
		
	}
}
