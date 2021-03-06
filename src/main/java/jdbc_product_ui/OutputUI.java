package jdbc_product_ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc_product.dto.sale;
import jdbc_product_service.OutPutService;
import jdbc_product_service.SaleInputService;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;

public class OutputUI extends JFrame {
	private JPanel contentPane;
	private boolean isSale;
	private SaleInputService service;
	private sellRank pRankList;
	private OutPutService service2;
	
	/**
	 * Create the panel.
	 */
	public OutputUI(boolean isSale) {
		service = new SaleInputService();
		service2 = new OutPutService();
		this.isSale = isSale;
		setTitle(isSale ? "판매금액순위":"마진액순위");		
		initComponents();
	}
		private void initComponents() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 646, 258);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
		/*JPanel pRankList1 = new JPanel();
		getContentPane().add(pRankList1, BorderLayout.NORTH);	
		JPanel pTitle = new JPanel();
		getContentPane().add(pTitle, BorderLayout.NORTH);	
		JLabel lblNewLabel = new JLabel("마진액순위");
		pTitle.add(lblNewLabel);
		setContentPane(getContentPane());*/
		
		List<sale> list;		
		try {
			list = service2.outputOrder(isSale);
			pRankList = new sellRank();
			pRankList.setList(list);
			pRankList.loadDatas();
			contentPane.add(pRankList, BorderLayout.CENTER);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		JPanel pTitle2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pTitle2.getLayout();
		flowLayout.setVgap(20);
		contentPane.add(pTitle2, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel(isSale ? "판매금액순위" : "마진액순위");
		pTitle2.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
	
	
	}

}
