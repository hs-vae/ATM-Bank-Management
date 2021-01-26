package com.atm.ui;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;
import com.atm.entity.CustomerInfo;
import com.atm.service.ClientService;
import com.atm.service.PrintService;
import com.atm.util.DateGenerateUtil;
import com.atm.util.StatusEnumEntity;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 存款的界面
 */
public class DepositFrame {

	private JFrame frame;
	public JTextField textCardNumber = new JTextField();
	public JTextField textCustomerName = new JTextField();
	public JTextField textRemainMoney = new JTextField();
	private JTextField textDepositMoney;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositFrame window = new DepositFrame();
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
	public DepositFrame() {
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
		frame.setBounds(100, 100, 700, 495);
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

		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel.setBounds(184, 113, 80, 38);
		panel.add(lblNewLabel);

		textCardNumber = new JTextField();
		textCardNumber.setBounds(285, 120, 113, 28);
		panel.add(textCardNumber);
		textCardNumber.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u5B58\u6B3E\u754C\u9762");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel_1.setBounds(245, 22, 161, 57);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_2.setBounds(184, 175, 67, 38);
		panel.add(lblNewLabel_2);

		textCustomerName = new JTextField();
		textCustomerName.setBounds(285, 182, 113, 28);
		panel.add(textCustomerName);
		textCustomerName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u4F59\u989D\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_3.setBounds(184, 250, 80, 38);
		panel.add(lblNewLabel_3);

		textRemainMoney = new JTextField();
		textRemainMoney.setBounds(285, 250, 113, 28);
		panel.add(textRemainMoney);
		textRemainMoney.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u5B58\u6B3E\u91D1\u989D\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_4.setBounds(184, 308, 113, 38);
		panel.add(lblNewLabel_4);

		textDepositMoney = new JTextField();
		textDepositMoney.setBounds(285, 308, 111, 36);
		panel.add(textDepositMoney);
		textDepositMoney.setColumns(10);

		JButton submitButton = new JButton("\u786E\u5B9A");
		submitButton.setFont(new Font("宋体", Font.BOLD, 17));
		submitButton.setBackground(Color.GREEN);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//获取文本框的余额和卡号
				String depositMoney = textDepositMoney.getText().trim();
				String cardNumber = textCardNumber.getText().trim();
				CustomerInfo customerInfo = new CustomerInfo(cardNumber,String.valueOf(new LoginFrame().textPassword.getPassword()));
				//将操作的金额和操作类型进行封装
				commonAtmVO.setOperatorMoney(Double.valueOf(depositMoney));
				commonAtmVO.setType(StatusEnumEntity.getValue("STATUS_SAVE"));
				commonAtmVO.setSourceCardNumber(cardNumber);
				commonAtmVO.setCustomerInfo(customerInfo);
				//客户端并上传信息
				ClientService cs = new ClientService();
				CommonResult commonResult = cs.clientSendObject(commonAtmVO);
				int printOrNot = JOptionPane.showConfirmDialog(null, commonResult.getMessage() + ",是否打印凭条?", "提示信息", JOptionPane.INFORMATION_MESSAGE);
				if (printOrNot==0){
					//打印凭条
					JFileChooser jFileChooser = new JFileChooser();
					String path=null;
					//设置默认的pdf名称
					String defaultPdfName= DateGenerateUtil.randomNumber()+String.valueOf(new Random().nextInt(100))+".pdf";
					jFileChooser.setDialogTitle("保存文件");
					jFileChooser.setSelectedFile(new File(defaultPdfName));
					if (JFileChooser.APPROVE_OPTION == jFileChooser.showSaveDialog(null)){
						 path = jFileChooser.getSelectedFile().getAbsolutePath();
					}
					PrintService printService = new PrintService();
					Map<String,Object> map = new HashMap<>();
					map.put("CHARGEID",commonResult.getId());
					printService.getSingleReportPDFAction(map,path);
					String detailInformation ="您的凭条已成功导出，路径为:"+path;
					JOptionPane.showMessageDialog(null, detailInformation, "提示信息", JOptionPane.INFORMATION_MESSAGE);
				}
				//定义存款后的金额
				Double deposit = Double.valueOf(depositMoney);
				Double remain = Double.valueOf(textRemainMoney.getText().trim());
				Double newRemainMoney =deposit+remain;
				textRemainMoney.setText(String.valueOf(newRemainMoney));
				textDepositMoney.setText("");
			}
		});
		submitButton.setBounds(161, 383, 93, 38);
		panel.add(submitButton);

		JButton cancleButton = new JButton("\u8FD4\u56DE");
		cancleButton.setBackground(new Color(250, 128, 114));
		cancleButton.setFont(new Font("宋体", Font.BOLD, 17));
		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cardNumber = textCardNumber.getText().trim();
				String customerName = textCustomerName.getText().trim();
				Double deposit;
				/*
				    判断该用户进入存款界面有没有输入存款金额,如果没有输入那么返回主界面时候余额还是原来的金额
				 */
				if (textDepositMoney.getText().trim().equals("")){
					deposit =0d;
				}else {
					deposit = Double.valueOf(textDepositMoney.getText().trim());
				}
				Double remain = Double.valueOf(textRemainMoney.getText().trim());
				Double newRemainMoney =deposit+remain;
				frame.setVisible(false);
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrame().setVisible(true);
				//将存款后的信息更新到主界面
				mainFrame.textCustomerName.setText(customerName);
				mainFrame.textCardNumber.setText(cardNumber);
				mainFrame.textRemainMoney.setText(String.valueOf(newRemainMoney));
			}
		});
		cancleButton.setBounds(343, 383, 93, 38);
		panel.add(cancleButton);

	}
}
