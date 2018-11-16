package jdbc_product_ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class sellRank extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public sellRank() {

		initComponents();
	}
	private void initComponents() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("판매금액순위");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		add(panel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
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
		panel.add(table);
	}

}
