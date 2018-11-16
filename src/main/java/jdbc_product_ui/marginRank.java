package jdbc_product_ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

public class marginRank extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public marginRank() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("판 매 금 액 순위");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		add(panel);
		
		table = new JTable();
		panel.add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"순위", "제품코드", "제품단가", "판매수량", null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
	}

}
