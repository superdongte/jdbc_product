package jdbc_product.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_product.ConnectionProvider;
import jdbc_product.LogUtil;
import jdbc_product.dto.Product;
import jdbc_product.dto.SaleDetail;
import jdbc_product.dto.sale;


public  class SaleDaoImpl implements SaleDao {

	@Override
	public List<sale> sellRanklist(){
		List<sale> list = new ArrayList<>();
		String sql = "select no, code, price, saleCnt, marginRate from sale";
		try(Connection conn =ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getSale(rs));
				}
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			return list;
		}
	private sale getSale(ResultSet rs) throws SQLException{
		int no = rs.getInt("no");
		Product code = new Product(rs.getString("code"));
		int price = rs.getInt("price");
		int SaleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		return new sale(no,code,price,SaleCnt,marginRate);
	}
	@Override
	public int insertSale(sale department) throws SQLException {
		LogUtil.prnLog("insertsale()");
		String sql = "insert into sale values(?,?,?,?,?)";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, department.getNo());
			pstmt.setString(2, department.getCode().getCode());
			pstmt.setInt(3, department.getPrice());
			pstmt.setInt(4, department.getSaleCnt());
			pstmt.setInt(5, department.getMarginRate());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}
	@Override
	public List<sale> selectSaleRank(boolean isSale) throws SQLException {
		LogUtil.prnLog("selectSaleRank");
		List<sale> list = new ArrayList<>();
		String sql = "{call price_rank(?)}";
		try(Connection conn = ConnectionProvider.getConnection();
				CallableStatement cs = conn.prepareCall(sql);){
			cs.setBoolean(1, isSale);
			LogUtil.prnLog(cs.toString());
			try(ResultSet rs = cs.executeQuery()){
				while(rs.next()) {
					list.add(getSaleDetail(rs));
				}
				
			}

		}
		LogUtil.prnLog("selectSaleRank" + list.size());
		return list;
		
	}
	private sale getSaleDetail(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
//		String code = rs.getString("code");
		Product product = new Product(rs.getString("code"), rs.getString("name"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		int supplyprice = rs.getInt("supplyprice");
		int addtax = rs.getInt("addtax");
		int saleprice = rs.getInt("saleprice");
		int marginprice = rs.getInt("marginprice");
		int rank = rs.getInt("rank");
		
		SaleDetail detail = new SaleDetail(supplyprice, addtax, saleprice, marginprice, rank);
		sale sale = new sale(no, product, price, saleCnt, marginRate, detail);
		LogUtil.prnLog(sale.toString());
		return sale;
		
	}

}
