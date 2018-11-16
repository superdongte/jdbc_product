package jdbc_product_service;

import java.sql.SQLException;

import jdbc_product.dao.ProductDao;
import jdbc_product.dao.ProductDaolmpl;
import jdbc_product.dao.SaleDao;
import jdbc_product.dao.SaleDaoImpl;
import jdbc_product.dto.Product;
import jdbc_product.dto.sale;

public class SaleInputService {
	private ProductDao productDao;
	private SaleDao saleDao;
	public SaleInputService(){
		productDao = new ProductDaolmpl();
		saleDao = new SaleDaoImpl();
	}
	
	public Product searchProduct(Product product) throws SQLException{
	return productDao.selectDepartmentByNo(product);					
	}
	public int registerSale(sale sales) throws SQLException{
		return saleDao.insertSale(sales);
	}

}