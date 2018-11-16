package jdbc_product.dao;
import java.sql.SQLException;
import java.util.List;

import jdbc_product.dto.sale;
public interface SaleDao {
	int insertSale(sale department) throws SQLException;
	List<sale> sellRanklist();
}
