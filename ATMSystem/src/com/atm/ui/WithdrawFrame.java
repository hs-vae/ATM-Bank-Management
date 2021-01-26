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
 * ȡ��Ľ���
 */
public class WithdrawFrame {

	private JFrame frame;
	public JTextField textCardNumber = new JTextField();
	public JTextField textCustomerName = new JTextField();
	public JTextField textRemainMoney = new JTextField();
	private JTextField textWithdrawMoney = new JTextField();
	private JPasswordField textPassword;   //Ϊ����߰�ȫ������ȡ������

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawFrame window = new WithdrawFrame();
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
	public WithdrawFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\eclipse-workbace\\ATMSystem\\logo\\ConstructionBank.jpg"));
		frame.setTitle("\u4E2D\u56FD\u5EFA\u8BBE\u94F6\u884CATM\u7CFB\u7EDF");
		frame.setBounds(100, 100, 700, 526);
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

		JLabel cardNumberLabel = new JLabel("\u8D26\u53F7\uFF1A");
		cardNumberLabel.setFont(new Font("����", Font.BOLD, 17));
		cardNumberLabel.setBounds(185, 95, 51, 39);
		panel.add(cardNumberLabel);

		textCardNumber = new JTextField();
		textCardNumber.setBounds(281, 100, 132, 31);
		panel.add(textCardNumber);
		textCardNumber.setColumns(10);

		JLabel customerNameLabel = new JLabel("\u59D3\u540D\uFF1A");
		customerNameLabel.setFont(new Font("����", Font.BOLD, 17));
		customerNameLabel.setBounds(183, 169, 69, 29);
		panel.add(customerNameLabel);

		textCustomerName = new JTextField();
		textCustomerName.setBounds(281, 169, 132, 31);
		panel.add(textCustomerName);
		textCustomerName.setColumns(10);

		JLabel remainMoneyLabel = new JLabel("\u4F59\u989D\uFF1A");
		remainMoneyLabel.setFont(new Font("����", Font.BOLD, 17));
		remainMoneyLabel.setBounds(185, 229, 89, 29);
		panel.add(remainMoneyLabel);

		textRemainMoney = new JTextField();
		textRemainMoney.setBounds(281, 230, 132, 29);
		panel.add(textRemainMoney);
		textRemainMoney.setColumns(10);

		JLabel withdrawMoneyLabel = new JLabel("\u53D6\u6B3E\u91D1\u989D\uFF1A");
		withdrawMoneyLabel.setFont(new Font("����", Font.BOLD, 17));
		withdrawMoneyLabel.setBounds(185, 286, 89, 29);
		panel.add(withdrawMoneyLabel);

		textWithdrawMoney = new JTextField();
		textWithdrawMoney.setToolTipText("\u8BF7\u8F93\u5165\u53D6\u6B3E\u7684\u91D1\u989D");
		textWithdrawMoney.setBounds(281, 286, 132, 31);
		panel.add(textWithdrawMoney);
		textWithdrawMoney.setColumns(10);

		JLabel withdrawPwdLabel = new JLabel("\u53D6\u6B3E\u5BC6\u7801\uFF1A");
		withdrawPwdLabel.setFont(new Font("����", Font.BOLD, 17));
		withdrawPwdLabel.setBounds(182, 347, 92, 29);
		panel.add(withdrawPwdLabel);

		textPassword = new JPasswordField();
		textPassword.setBounds(281, 348, 132, 29);
		panel.add(textPassword);

		JLabel lblNewLabel_4 = new JLabel("\u53D6\u6B3E\u754C\u9762");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel_4.setBounds(240, 33, 139, 44);
		panel.add(lblNewLabel_4);

		JButton submitButton = new JButton("\u786E\u5B9A");
		submitButton.setForeground(Color.DARK_GRAY);
		submitButton.setBackground(Color.GREEN);
		submitButton.setFont(new Font("����", Font.BOLD, 17));
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommonAtmVO commonAtmVO = new CommonAtmVO();
				//��ȡ�ı�������ȡ����,ȡ������,ȡ���˺�,�û�����
				String withdrawMoney= textWithdrawMoney.getText().trim();
				String withdrawPwd = String.valueOf(textPassword.getPassword());
				String cardNumber = textCardNumber.getText().trim();
				String remainMoney = textRemainMoney.getText().trim();
				CustomerInfo customerInfo = new CustomerInfo(cardNumber,withdrawPwd);
				//����Ϣ���з�װ
				commonAtmVO.setSourceCardNumber(cardNumber);
				commonAtmVO.setType(StatusEnumEntity.getValue("STATUS_FETCH"));
				commonAtmVO.setCustomerInfo(customerInfo);
				commonAtmVO.setOperatorMoney(Double.valueOf(withdrawMoney));
				commonAtmVO.setSourceCardNumber(cardNumber);
				//�����ͻ����ϴ���Ϣ��ѡ��ȡ�����
				ClientService cs = new ClientService();
				CommonResult commonResult = cs.clientSendObject(commonAtmVO);
				boolean status = commonResult.isStatus();
				System.out.println(status);
				if (commonResult.isStatus()){
					//ȡ��ɹ�����ʾ�Ƿ��ӡƾ��
					int printOrNot = JOptionPane.showConfirmDialog(null, commonResult.getMessage() + ",�Ƿ��ӡƾ����", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					if (printOrNot==0){
						//��ӡƾ��
						JFileChooser jFileChooser = new JFileChooser();
						String path=null;
						//����Ĭ�ϵ�pdf����
						String defaultPdfName= DateGenerateUtil.randomNumber()+String.valueOf(new Random().nextInt(100))+".pdf";
						jFileChooser.setDialogTitle("�����ļ�");
						jFileChooser.setSelectedFile(new File(defaultPdfName));
						if (JFileChooser.APPROVE_OPTION == jFileChooser.showSaveDialog(null)){
							path = jFileChooser.getSelectedFile().getAbsolutePath();
						}
						PrintService printService = new PrintService();
						Map<String,Object> map = new HashMap<>();
						map.put("CHARGEID",commonResult.getId());
						printService.getSingleReportPDFAction(map,path);
						String detailInformation ="����ƾ���ѳɹ�������·��Ϊ:"+path;
						JOptionPane.showMessageDialog(null, detailInformation, "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					}
					Double wm = Double.valueOf(withdrawMoney);
					Double rm = Double.valueOf(remainMoney);
					textRemainMoney.setText(String.valueOf(rm-wm));
					textWithdrawMoney.setText("");
				}else {
					textWithdrawMoney.setText("");
					JOptionPane.showMessageDialog(null,commonResult.getMessage(),"��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		submitButton.setBounds(171, 416, 93, 44);
		panel.add(submitButton);

		JButton cancelButton = new JButton("\u8FD4\u56DE");
		cancelButton.setForeground(Color.DARK_GRAY);
		cancelButton.setFont(new Font("����", Font.BOLD, 17));
		cancelButton.setBackground(new Color(240, 128, 128));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String withdrawMoney= textWithdrawMoney.getText().trim();
				String cardNumber = textCardNumber.getText().trim();
				String customerName = textCustomerName.getText().trim();
				String remainMoney = textRemainMoney.getText().trim();
				Double withdraw;
				//�жϸ��û�����ȡ�������û������ȡ����,���û��������ô����������ʱ������ԭ���Ľ��
				if (textWithdrawMoney.getText().trim().equals("")){
					withdraw=0d;
				}else {
					withdraw=Double.valueOf(withdrawMoney);
				}
				frame.setVisible(false);
				Double remain=Double.valueOf(remainMoney);
				Double newRemainMoney=remain-withdraw;
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrame().setVisible(true);
				mainFrame.textRemainMoney.setText(String.valueOf(newRemainMoney));
				mainFrame.textCardNumber.setText(cardNumber);
				mainFrame.textCustomerName.setText(customerName);
			}
		});
		cancelButton.setBounds(374, 417, 93, 42);
		panel.add(cancelButton);
		
		JLabel lblNewLabel = new JLabel("\u4E3A\u4E86\u63D0\u9AD8\u5B89\u5168\u6027,\u8BF7\u518D\u6B21\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setBounds(436, 347, 227, 31);
		panel.add(lblNewLabel);


	}
}
