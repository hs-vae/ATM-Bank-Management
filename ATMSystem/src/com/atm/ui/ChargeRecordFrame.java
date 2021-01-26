package com.atm.ui;

import com.atm.dao.ChargeDao;
import com.atm.service.PrintService;
import com.atm.util.DateGenerateUtil;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * 查询交易记录界面
 */
public class ChargeRecordFrame {

	private JFrame frame;
	public JTable table = new JTable();
	public DefaultTableModel model = new DefaultTableModel();
	public Vector<String> title = new Vector<>();
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
					ChargeRecordFrame window = new ChargeRecordFrame();
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
	public ChargeRecordFrame() {
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
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


		table = new JTable();
		model = new DefaultTableModel();
		title = new Vector<String>();
		title.add("交易流水号");
		title.add("交易时间");
		title.add("交易金额");
		title.add("客户名称");
		title.add("交易账号");
		title.add("交易类型");
		title.add("账户余额");
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JButton printButton = new JButton("\u6253\u5370");
		printButton.setBackground(Color.GREEN);
		printButton.setFont(new Font("宋体", Font.BOLD, 20));
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					map.put("CARDNUMBER",textCardNumber.getText().trim());
					printService.getListReportPDFAction(map,path);
					String detailInformation ="您的凭条已成功导出，路径为:"+path;
					JOptionPane.showMessageDialog(null, detailInformation, "提示信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(printButton);


		JButton returnButton = new JButton("\u8FD4\u56DE");
		returnButton.setBackground(new Color(244, 164, 96));
		returnButton.setFont(new Font("宋体", Font.BOLD, 20));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainFrame mainFrame = new MainFrame();
				mainFrame.getFrame().setVisible(true);
				mainFrame.textCardNumber.setText(textCardNumber.getText().trim());
				mainFrame.textCustomerName.setText(textCustomerName.getText().trim());
				mainFrame.textRemainMoney.setText(textRemainMoney.getText().trim());
			}
		});
		panel.add(returnButton);
		

	}
	public void showData(String cardNumber){
		ChargeDao chargeDao = new ChargeDao();
		Vector<Vector<String>> data = chargeDao.queryChargeRecord(cardNumber);
		if(data==null) {
			model.setDataVector(null, title);
		}else {
			model.setDataVector(data, title);
		}
	}
}
