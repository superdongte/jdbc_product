package jdbc_product.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_product.dto.Product;
import jdbc_product.dto.sale;
public interface ProductDao {
	List<Product> selectDepartmentByAll();
	int insertDepartment(Product department) throws SQLException;
	
	int deleteDepartment(Product department) throws SQLException;
	int updateDepartment(Product department) throws SQLException;
	Product selectDepartmentByNo(Product department) throws SQLException;
}
