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
 * ��ѯ���׼�¼����
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
	    int windowWidth = frame.getWidth();                     //��ô��ڿ�
	    int windowHeight = frame.getHeight();                   //��ô��ڸ�       
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
	    frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ
	    
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


		table = new JTable();
		model = new DefaultTableModel();
		title = new Vector<String>();
		title.add("������ˮ��");
		title.add("����ʱ��");
		title.add("���׽��");
		title.add("�ͻ�����");
		title.add("�����˺�");
		title.add("��������");
		title.add("�˻����");
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JButton printButton = new JButton("\u6253\u5370");
		printButton.setBackground(Color.GREEN);
		printButton.setFont(new Font("����", Font.BOLD, 20));
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					map.put("CARDNUMBER",textCardNumber.getText().trim());
					printService.getListReportPDFAction(map,path);
					String detailInformation ="����ƾ���ѳɹ�������·��Ϊ:"+path;
					JOptionPane.showMessageDialog(null, detailInformation, "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(printButton);


		JButton returnButton = new JButton("\u8FD4\u56DE");
		returnButton.setBackground(new Color(244, 164, 96));
		returnButton.setFont(new Font("����", Font.BOLD, 20));
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
