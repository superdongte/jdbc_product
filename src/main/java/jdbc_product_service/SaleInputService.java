package jdbc_product_service;

import jdbc_product.dao.ProductDao;
import jdbc_product.dao.ProductDaolmpl;
import jdbc_product.dao.SaleDao;
import jdbc_product.dao.SaleDaoImpl;

public class SaleInputService {
	private ProductDao productDao;
	private SaleDao saleDao;
	public SaleInputService(){
		productDao = new ProductDaolmpl();
		saleDao = new SaleDaoImpl();
	}
	
}
