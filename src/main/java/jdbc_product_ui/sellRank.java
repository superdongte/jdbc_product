package jdbc_product_ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc_product.LogUtil;
import jdbc_product.dto.sale;
import jdbc_product_service.SaleInputService;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class sellRank extends JPanel {
	//리스트 만들기
	private List<sale>list;
	private JTable table;
	private JTable table_1;
	
	public void setList(List<sale> list) {
		this.list = list;
	}
	
	/**
	 * Create the panel.
	 * @param b 
	 * @param b 
	 */
	public sellRank() {
		initComponents();
	}
	private void initComponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);		
	}
	//tableCellAlignment, tableSetWidth 추상메서드로 하고 setAlignWidth만 조절하면 됨.
		private void setAlignWidth() {
			tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
			tableCellAlignment(SwingConstants.RIGHT, 3, 4, 5, 6, 7, 8, 9);
			tableSetWidth(100, 150, 200, 150, 150, 200, 150, 200, 100, 150);
			
		}
	//정렬
		private void tableCellAlignment(int align, int...idx) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(align);
			TableColumnModel tcm = table_1.getColumnModel();
			for(int i = 0 ; i < idx.length ; i++) {
				tcm.getColumn(idx[i]).setCellRenderer(dtcr);
			}
		}
		//폭
		private void tableSetWidth(int...width) {
			TableColumnModel tcm = table_1.getColumnModel();
			for(int i = 0 ; i < width.length ; i++) {
				tcm.getColumn(i).setPreferredWidth(width[i]);
			}
		}
		//loadDatas
		public void loadDatas() {
			table_1.setModel(new DefaultTableModel(
					getDatas(),
					getColumnNames()
				));
			
			//정렬, 폭 => 메서드 만들고 가변배열로 처리
					setAlignWidth();
		}
		private Object[][] getDatas() {
			//2차원 배열만들기
			Object[][]datas = new Object[list.size()+1][];
			for(int i = 0; i<list.size(); i++) {
				datas[i] = getSaleRow(list.get(i));
			}
			datas[list.size()] = getTotal();
			return datas;
		}
		private Object[] getTotal() {
			LogUtil.prnLog("getTotal()");
				int totalSupplyPrice = 0;
				int totalAddTax = 0;
				int totalSalePrice = 0;
				int totalMarginPrice = 0;
				for(sale s:list) {
					totalSupplyPrice += s.getDetail().getSupplyprice();
					totalAddTax += s.getDetail().getAddtax();
					totalSalePrice += s.getDetail().getMarginprice();
					LogUtil.prnLog(s.toString());
				}
			LogUtil.prnLog(totalSupplyPrice + "");
			return new Object[] {"합계","","","","",String.format("%,d",totalSupplyPrice),
														String.format("%,d", totalAddTax),String.format("%,d",totalSalePrice),"",
														String.format("%,d", totalMarginPrice)};
		}
		private Object[] getSaleRow(sale sale) {
			return new Object[] {
					sale.getDetail().getRank(),//순위
					sale.getProduct().getCode(),
					sale.getProduct().getName(),
					String.format("%,d", sale.getPrice()),//제품단가
					String.format("%,d", sale.getSaleCnt()),
					String.format("%,d", sale.getDetail().getAddtax()),
					String.format("%,d", sale.getDetail().getSaleprice()),//판매금액
					sale.getMarginRate() + "%",//마진율
					String.format("%,d", sale.getDetail().getMarginprice())//마진액
			};
		}
		private String[] getColumnNames() {
			return new String[] {
				"순위","제품코드","제품명","제품단가","판매단가","판매수량","공급가액","부가세액","판매금액","마진율","마진액"	
			};
		}
}
