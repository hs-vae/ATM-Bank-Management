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
 * 登陆界面(登陆,重置)
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

	    int windowWidth = frame.getWidth();                     //获得窗口宽
	    int windowHeight = frame.getHeight();                   //获得窗口高       
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
	    frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u8D26  \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(184, 115, 83, 40);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
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
		//登录按钮的监听事件
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDao loginDao = new LoginDao();
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//获取文本输入框的账号和密码
				String inputNumber = textCardNumber.getText().trim();
				String inputPassword = String.valueOf(textPassword.getPassword());
				//通过文本输入的账号查询是否存在该用户并返回该用户对象
				CustomerInfo customerInfo = loginDao.queryCustomerInfo(inputNumber);
				String type =null;
				//通过用户信息对象获取账户,密码,姓名,余额
				String password = customerInfo.getPassword();
				String cardNumber = customerInfo.getCardNumber();
				String customerName = customerInfo.getCustomerName();
				double remainMoney = customerInfo.getRemainMoney();
				//判断密码和账户是否正确,得出操作类型
				if (inputNumber.equals(cardNumber)&&inputPassword.equals(password)){
					type =StatusEnumEntity.getValue("STATUS_LOGIN");
				}else {
					type =StatusEnumEntity.getValue("STATUS_LOGINFAILED");
				}
				//客户端上传信息并将其进行封装
				commonAtmVO.setCustomerInfo(customerInfo);
				commonAtmVO.setSourceCardNumber(inputNumber);
				commonAtmVO.setType(type);
				//同时将文本框输入的信息也进行封装
				customerInfo.setCardNumber(inputNumber);
				customerInfo.setPassword(inputPassword);
				//开启客户端上传信息
				ClientService cs = new ClientService();
				CommonResult commonResult = cs.clientSendObject(commonAtmVO);
				//判断密码是否输入正确即这个结果状态是否为true
				if (commonResult.isStatus()){
					frame.setVisible(false);
					MainFrame mainFrame = new MainFrame();
					mainFrame.textCardNumber.setText(cardNumber);
					mainFrame.textCustomerName.setText(customerName);
					mainFrame.textRemainMoney.setText((remainMoney+"").trim());
					mainFrame.getFrame().setVisible(true);
				}else {
					//如果账户和密码有错误
					JOptionPane.showMessageDialog(null,"账户或密码错误","提示信息",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		loginButton.setFont(new Font("宋体", Font.BOLD, 20));
		loginButton.setForeground(SystemColor.textHighlight);
		loginButton.setBounds(274, 309, 123, 49);
		panel.add(loginButton);

		JButton resetPwdButton = new JButton("\u91CD\u7F6E");
		resetPwdButton.setBackground(Color.CYAN);
		resetPwdButton.setToolTipText("\u5FD8\u8BB0\u5BC6\u7801\u6216\u4FEE\u6539\u5BC6\u7801\uFF1F");
		resetPwdButton.setFont(new Font("宋体", Font.BOLD, 15));
		resetPwdButton.setForeground(Color.BLACK);
		//修改密码的监听事件
		resetPwdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDao loginDao = new LoginDao();
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//获取文本输入框的账号和密码
				String inputNumber = textCardNumber.getText().trim();
				String inputPassword = String.valueOf(textPassword.getPassword());
				//获取修改密码后的用户信息对象
				CustomerInfo customerInfo = loginDao.changePassword(inputNumber,inputPassword);
				//操作类型为修改密码
				if (customerInfo.getCertifyNumber()!=null){
					String type =StatusEnumEntity.getValue("STATUS_CHANGEPWD");
					//客户端上传信息并将其进行封装
					commonAtmVO.setCustomerInfo(customerInfo);
					commonAtmVO.setSourceCardNumber(inputNumber);
					commonAtmVO.setType(type);
					//开启客户端上传信息
					ClientService cs = new ClientService();
					CommonResult commonResult = cs.clientSendObject(commonAtmVO);
					System.out.println(type);
					//提示修改信息
					JOptionPane.showMessageDialog(null,commonResult.getMessage(),"提示信息",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"该账号不存在,修改失败!","提示信息",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		resetPwdButton.setBounds(460, 223, 105, 30);
		panel.add(resetPwdButton);

		JLabel lblNewLabel_2 = new JLabel("\u767B\u5F55\u754C\u9762");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel_2.setBounds(258, 28, 201, 66);
		panel.add(lblNewLabel_2);
	}
}
