package com.atm.ui;

import com.atm.dao.LoginDao;
import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.entity.CustomerInfo;
import com.atm.service.ClientService;
import com.atm.util.StatusEnumEntity;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * ��½����(��½,����)
 */
public class LoginFrame {

	private JFrame frame;
	private JTextField textCardNumber;
	public JPasswordField textPassword = new JPasswordField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.PINK);
		frame.setTitle("\u4E2D\u56FD\u5EFA\u8BBE\u94F6\u884CATM\u7CFB\u7EDF");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workbace\\ATMSystem\\logo\\ConstructionBank.jpg"));
		frame.setBounds(100, 100, 670, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    int windowWidth = frame.getWidth();                     //��ô��ڿ�
	    int windowHeight = frame.getHeight();                   //��ô��ڸ�       
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
	    frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8D26  \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(184, 115, 83, 40);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(184, 221, 83, 30);
		panel.add(lblNewLabel_1);

		textCardNumber = new JTextField();
		textCardNumber.setText("1015447235");
		textCardNumber.setToolTipText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		textCardNumber.setBounds(277, 124, 145, 30);
		panel.add(textCardNumber);
		textCardNumber.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setToolTipText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		textPassword.setBounds(277, 224, 145, 30);
		panel.add(textPassword);

		JButton loginButton = new JButton("\u767B\u5F55");
		loginButton.setToolTipText("\u70B9\u51FB\u767B\u5F55");
		loginButton.setBackground(Color.GREEN);
		//��¼��ť�ļ����¼�
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDao loginDao = new LoginDao();
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//��ȡ�ı��������˺ź�����
				String inputNumber = textCardNumber.getText().trim();
				String inputPassword = String.valueOf(textPassword.getPassword());
				//ͨ���ı�������˺Ų�ѯ�Ƿ���ڸ��û������ظ��û�����
				CustomerInfo customerInfo = loginDao.queryCustomerInfo(inputNumber);
				String type =null;
				//ͨ���û���Ϣ�����ȡ�˻�,����,����,���
				String password = customerInfo.getPassword();
				String cardNumber = customerInfo.getCardNumber();
				String customerName = customerInfo.getCustomerName();
				double remainMoney = customerInfo.getRemainMoney();
				//�ж�������˻��Ƿ���ȷ,�ó���������
				if (inputNumber.equals(cardNumber)&&inputPassword.equals(password)){
					type =StatusEnumEntity.getValue("STATUS_LOGIN");
				}else {
					type =StatusEnumEntity.getValue("STATUS_LOGINFAILED");
				}
				//�ͻ����ϴ���Ϣ��������з�װ
				commonAtmVO.setCustomerInfo(customerInfo);
				commonAtmVO.setSourceCardNumber(inputNumber);
				commonAtmVO.setType(type);
				//ͬʱ���ı����������ϢҲ���з�װ
				customerInfo.setCardNumber(inputNumber);
				customerInfo.setPassword(inputPassword);
				//�����ͻ����ϴ���Ϣ
				ClientService cs = new ClientService();
				CommonResult commonResult = cs.clientSendObject(commonAtmVO);
				//�ж������Ƿ�������ȷ��������״̬�Ƿ�Ϊtrue
				if (commonResult.isStatus()){
					frame.setVisible(false);
					MainFrame mainFrame = new MainFrame();
					mainFrame.textCardNumber.setText(cardNumber);
					mainFrame.textCustomerName.setText(customerName);
					mainFrame.textRemainMoney.setText((remainMoney+"").trim());
					mainFrame.getFrame().setVisible(true);
				}else {
					//����˻��������д���
					JOptionPane.showMessageDialog(null,"�˻����������","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		loginButton.setFont(new Font("����", Font.BOLD, 20));
		loginButton.setForeground(SystemColor.textHighlight);
		loginButton.setBounds(274, 309, 123, 49);
		panel.add(loginButton);

		JButton resetPwdButton = new JButton("\u91CD\u7F6E");
		resetPwdButton.setBackground(Color.CYAN);
		resetPwdButton.setToolTipText("\u5FD8\u8BB0\u5BC6\u7801\u6216\u4FEE\u6539\u5BC6\u7801\uFF1F");
		resetPwdButton.setFont(new Font("����", Font.BOLD, 15));
		resetPwdButton.setForeground(Color.BLACK);
		//�޸�����ļ����¼�
		resetPwdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDao loginDao = new LoginDao();
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//��ȡ�ı��������˺ź�����
				String inputNumber = textCardNumber.getText().trim();
				String inputPassword = String.valueOf(textPassword.getPassword());
				//��ȡ�޸��������û���Ϣ����
				CustomerInfo customerInfo = loginDao.changePassword(inputNumber,inputPassword);
				//��������Ϊ�޸�����
				if (customerInfo.getCertifyNumber()!=null){
					String type =StatusEnumEntity.getValue("STATUS_CHANGEPWD");
					//�ͻ����ϴ���Ϣ��������з�װ
					commonAtmVO.setCustomerInfo(customerInfo);
					commonAtmVO.setSourceCardNumber(inputNumber);
					commonAtmVO.setType(type);
					//�����ͻ����ϴ���Ϣ
					ClientService cs = new ClientService();
					CommonResult commonResult = cs.clientSendObject(commonAtmVO);
					System.out.println(type);
					//��ʾ�޸���Ϣ
					JOptionPane.showMessageDialog(null,commonResult.getMessage(),"��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"���˺Ų�����,�޸�ʧ��!","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		resetPwdButton.setBounds(460, 223, 105, 30);
		panel.add(resetPwdButton);

		JLabel lblNewLabel_2 = new JLabel("\u767B\u5F55\u754C\u9762");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel_2.setBounds(258, 28, 201, 66);
		panel.add(lblNewLabel_2);
	}
}
