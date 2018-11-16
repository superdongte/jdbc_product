package jdbc_product_service;

import java.sql.SQLException;
import java.util.List;

import jdbc_product.dao.SaleDao;
import jdbc_product.dao.SaleDaoImpl;
import jdbc_product.dto.sale;

public class OutPutService {
	private SaleDao saleDao;

	public OutPutService() {
		saleDao = new SaleDaoImpl();
	}
	public List<sale> outputOrder(boolean isSale) throws SQLException {
		return saleDao.selectSaleRank(isSale);
	}
}
