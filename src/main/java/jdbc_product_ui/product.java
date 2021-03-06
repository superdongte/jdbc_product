package jdbc_product_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;

import org.omg.IOP.ServiceContextListHolder;

import jdbc_product.dto.Product;
import jdbc_product.dto.sale;
import jdbc_product_service.MyDocumentListener;
import jdbc_product_service.SaleInputService;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class product extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textcode;
	private JTextField textname;
	private JTextField textprice;
	private JTextField textcount;
	private JTextField textmargin;
	private JButton btnNewIN;
	private JButton btn1;
	private JButton btn2;
	private SaleInputService service;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					product frame = new product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public product() {
		service = new SaleInputService();
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblCode = new JLabel("제품코드");
		panel.add(lblCode);
		
		textcode = new JTextField();
		panel.add(textcode);
		textcode.setColumns(10);
		
		JLabel lblName = new JLabel("제품명");
		panel.add(lblName);
		
		textname = new JTextField();
		panel.add(textname);
		textname.setColumns(10);
		
		JLabel lblPrice = new JLabel("제품단가");
		panel.add(lblPrice);
		
		textprice = new JTextField();
		panel.add(textprice);
		textprice.setColumns(10);
		
		JLabel lblCount = new JLabel("판매수량");
		panel.add(lblCount);
		
		textcount = new JTextField();
		panel.add(textcount);
		textcount.setColumns(10);
		
		JLabel lblMargin = new JLabel("마진율");
		panel.add(lblMargin);
		
		textmargin = new JTextField();
		panel.add(textmargin);
		textmargin.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnNewIN = new JButton("입력");
		btnNewIN.addActionListener(this);
		panel_1.add(btnNewIN);
		
		btn1 = new JButton("출력");
		btn1.addActionListener(this);
		panel_1.add(btn1);
		
		btn2 = new JButton("출력2");
		btn2.addActionListener(this);
		panel_1.add(btn2);
	
		textcode.getDocument().addDocumentListener(new MyDocumentListener() {
			
			@Override
			public void msg() {
				if(textcode.getText().length() == 4) {
					
					Product pdt = new Product(textcode.getText().trim());
					try {
					Product searchPdt = service.searchProduct(pdt);
						System.out.println(searchPdt);
						textname.setText(searchPdt.getName());
					}catch(SQLException e) {
						e.printStackTrace();
					}catch(NullPointerException e) {
						textname.setText("제품이없음");
					}
				}
			
			}
			
		
		});	
	}
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn2) {
			do_btn2_actionPerformed(e);
		}
		if (e.getSource() == btn1) {
			do_btn1_actionPerformed(e);
		}
		if (e.getSource() == btnNewIN) {
			do_btnNewIN_actionPerformed(e);
		}
	
	}
	protected void do_btnNewIN_actionPerformed(ActionEvent e) {
			sale sales = getSale();
			int res = 0;
			try {
				res = service.registerSale(sales);
			if(res == 1) {
				JOptionPane.showMessageDialog(null, "추가했다.");
			}
			clearTf();
			}catch(SQLException e1) {
				JOptionPane.showMessageDialog(null, "유효하지않은 명령이다.");
				e1.printStackTrace();
			}
	}			
	private void clearTf() {
		textcode.setText("");
		textname.setText("");
		textprice.setText("");
		textcount.setText("");
		textmargin.setText("");
		
	}

	private sale getSale() {
		String code = textcode.getText().trim();
		int price = Integer.parseInt(textprice.getText().trim());
		int saleCnt = Integer.parseInt(textcount.getText().trim());
		int marginRate = Integer.parseInt(textmargin.getText().trim());
		
		
		return new sale(0,new Product(code),price,saleCnt,marginRate);
	}
	//출력1
	protected void do_btn1_actionPerformed(ActionEvent e) {
			OutputUI ui = new OutputUI(true);
			ui.setVisible(true);
			
	
	}
	protected void do_btn2_actionPerformed(ActionEvent e) {
		OutputUI ui = new OutputUI(false);
		ui.setVisible(true);
	}

}
