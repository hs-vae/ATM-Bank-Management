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
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 转账界面
 */
public class TransferFrame {

	private JFrame frame;
	private JPasswordField passwordField;
	public JTextField textCardNumber = new JTextField();
	public JTextField textCustomerName = new JTextField();
	public JTextField textRemainMoney = new JTextField();
	private JTextField textTargetCardNumber;
	private JTextField textTransferMoney;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferFrame window = new TransferFrame();
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
	public TransferFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workbace\\ATMSystem\\logo\\ConstructionBank.jpg"));
		frame.setTitle("\u4E2D\u56FD\u5EFA\u8BBE\u94F6\u884CATM\u7CFB\u7EDF");
		frame.setBounds(100, 100, 774, 531);
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

		JLabel lblNewLabel = new JLabel("\u8F6C\u8D26\u754C\u9762");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
		lblNewLabel.setBounds(276, 10, 158, 64);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7:");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(218, 106, 78, 29);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u8D26\u6237\u540D\u79F0:");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_2.setBounds(218, 145, 130, 29);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u5F53\u524D\u4F59\u989D:");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_3.setBounds(218, 184, 132, 45);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u5BF9\u65B9\u8D26\u53F7\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_4.setBounds(218, 239, 93, 29);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\u8F6C\u8D26\u91D1\u989D\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_5.setBounds(218, 282, 93, 29);
		panel.add(lblNewLabel_5);

		passwordField = new JPasswordField();
		passwordField.setBounds(326, 327, 158, 21);
		panel.add(passwordField);

		JLabel lblNewLabel_6 = new JLabel("转账密码：");
		lblNewLabel_6.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_6.setBounds(218, 321, 93, 30);
		panel.add(lblNewLabel_6);

		JRadioButton transferInRadioButton = new JRadioButton("\u884C\u5185\u8F6C\u8D26");
		transferInRadioButton.setBackground(Color.WHITE);
		transferInRadioButton.setSelected(true);
		transferInRadioButton.setFont(new Font("宋体", Font.BOLD, 17));
		transferInRadioButton.setBounds(207, 374, 121, 38);
		panel.add(transferInRadioButton);

		JRadioButton transferOutRadioButton = new JRadioButton("\u8DE8\u884C\u8F6C\u8D26");
		transferOutRadioButton.setBackground(Color.WHITE);
		transferOutRadioButton.setFont(new Font("宋体", Font.BOLD, 17));
		transferOutRadioButton.setBounds(396, 374, 121, 38);
		panel.add(transferOutRadioButton);
		//实现单选按钮
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(transferInRadioButton);
		buttonGroup.add(transferOutRadioButton);

		JButton submitButton = new JButton("\u786E\u5B9A");
		submitButton.setForeground(Color.DARK_GRAY);
		submitButton.setBackground(Color.GREEN);
		submitButton.setFont(new Font("宋体", Font.BOLD, 17));
		//转账按钮的监听事件
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cardNumber = textCardNumber.getText().trim();
				String targetCardNumber = textTargetCardNumber.getText().trim();
				String remainMoney = textRemainMoney.getText().trim();
				String transferMoney = textTransferMoney.getText().trim();
				String confirmPwd = String.valueOf(passwordField.getPassword());
				CustomerInfo customerInfo = new CustomerInfo(cardNumber,confirmPwd);
				String type=null;
				if (transferInRadioButton.isSelected()){
					type= StatusEnumEntity.getValue("STATUS_INTRANSFER");
				}else {
					type=StatusEnumEntity.getValue("STATUS_OUTTRANSFER");
				}
				//将转账输入的信息进行封装
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				commonAtmVO.setCustomerInfo(customerInfo);
				commonAtmVO.setOperatorMoney(Double.valueOf(transferMoney));
				commonAtmVO.setSourceCardNumber(cardNumber);
				commonAtmVO.setTargetCardNumber(targetCardNumber);
				commonAtmVO.setType(type);
				//客户端通过Socket上传封装过的信息
				ClientService cs = new ClientService();
				CommonResult commonResult = cs.clientSendObject(commonAtmVO);
				if (commonResult.isStatus()){
					double rm = Double.parseDouble(remainMoney);  //原有的金额
					double tm = Double.parseDouble(transferMoney);  //转账的金额
					double rm_new=rm- tm;
					textRemainMoney.setText(rm_new+"");
					textTransferMoney.setText("");
					textTargetCardNumber.setText("");
					//打印凭条
					int printOrNot = JOptionPane.showConfirmDialog(null, commonResult.getMessage() + ",是否打印凭条？", "提示信息", JOptionPane.INFORMATION_MESSAGE);
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
				}else {
					textTransferMoney.setText("");
					JOptionPane.showMessageDialog(null,commonResult.getMessage(),"失败信息",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		submitButton.setBounds(203, 418, 93, 44);
		panel.add(submitButton);

		JButton cancelButton = new JButton("\u8FD4\u56DE");
		cancelButton.setForeground(Color.DARK_GRAY);
		cancelButton.setBackground(new Color(255, 127, 80));
		cancelButton.setFont(new Font("宋体", Font.BOLD, 17));
		cancelButton.setBounds(419, 418, 93, 44);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cardNumber = textCardNumber.getText().trim();
				String customerName = textCustomerName.getText().trim();
				String remainMoney = textRemainMoney.getText().trim();
				Double transfer;
				if (textTransferMoney.getText().trim().equals("")){
					transfer=0d;
				}else {
					transfer=Double.valueOf(textTransferMoney.getText().trim());
				}
				Double remain = Double.valueOf(remainMoney);
				Double newRemainMoney =remain-transfer;
				frame.setVisible(false);
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrame().setVisible(true);
				mainFrame.textCardNumber.setText(cardNumber);
				mainFrame.textCustomerName.setText(customerName);
				mainFrame.textRemainMoney.setText(String.valueOf(newRemainMoney));
			}
		});
		panel.add(cancelButton);

		textCardNumber = new JTextField();
		textCardNumber.setBounds(326, 110, 158, 21);
		panel.add(textCardNumber);
		textCardNumber.setColumns(10);

		textCustomerName = new JTextField();
		textCustomerName.setBounds(326, 150, 158, 21);
		panel.add(textCustomerName);
		textCustomerName.setColumns(10);

		textRemainMoney = new JTextField();
		textRemainMoney.setBounds(326, 197, 158, 21);
		panel.add(textRemainMoney);
		textRemainMoney.setColumns(10);

		textTargetCardNumber = new JTextField();
		textTargetCardNumber.setBounds(326, 239, 158, 21);
		panel.add(textTargetCardNumber);
		textTargetCardNumber.setColumns(10);

		textTransferMoney = new JTextField();
		textTransferMoney.setBounds(324, 284, 160, 21);
		panel.add(textTransferMoney);
		textTransferMoney.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\u4E3A\u4E86\u63D0\u9AD8\u5B89\u5168\u6027,\u8BF7\u518D\u6B21\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_7.setForeground(new Color(255, 0, 0));
		lblNewLabel_7.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_7.setBounds(511, 318, 237, 38);
		panel.add(lblNewLabel_7);
	}
}
