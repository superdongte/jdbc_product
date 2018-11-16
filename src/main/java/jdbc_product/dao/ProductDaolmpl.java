package jdbc_product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_product.ConnectionProvider;
import jdbc_product.LogUtil;
import jdbc_product.dto.Product;
import jdbc_product.dto.sale;
import jdbc_product_ui.sellRank;

public  class ProductDaolmpl implements ProductDao {

	@Override
	public List<Product> selectDepartmentByAll() {
		List<Product> list = new ArrayList<>();
		String sql = "select code, name from product";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	/*@Override
	public List<sale> sellRanklist(){
		List<sale> list = new ArrayList<>();
		String sql = "select no, code, price, saleCnt, marginRate from sale";
		try(Connection conn =ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getProduct(rs));
				}
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			return list;
		}*/
	private Product getDepartment(ResultSet rs) throws SQLException {
		String code = rs.getString("code");
		String Name = rs.getString("name");
		return new Product(code,Name);
	}
	private sale getProduct(ResultSet rs) throws SQLException{
		int no = rs.getInt("no");
		String code = rs.getString("code");
		int price = rs.getInt("price");
		int SaleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		return new sale(no,new Product(code),price,SaleCnt,marginRate);
	}
	
	@Override
	public int insertDepartment(Product department) throws SQLException {
		LogUtil.prnLog("insertProduct()");
		String sql = "insert into product values(?, ?)";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, department.getCode());
			pstmt.setString(2, department.getName());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int deleteDepartment(Product department) throws SQLException {
		LogUtil.prnLog("deleteProduct()");
		String sql = "delete from product where code=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, department.getCode());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int updateDepartment(Product department) throws SQLException {
		LogUtil.prnLog("updateProduct()");
		String sql = "update product set code=?, name=? where code=?";
		int rowAffected = 0;
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, department.getCode());
			pstmt.setString(2, department.getName());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;

	}

	@Override
	public Product selectDepartmentByNo(Product department) throws SQLException {
		LogUtil.prnLog("selectProductByNo()");
		Product dept = null;
		String sql = "select code, name from Product where code=?";

		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, department.getCode());
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					dept = getDepartment(rs);
				}
			}
		}
		return dept;
	}


}
