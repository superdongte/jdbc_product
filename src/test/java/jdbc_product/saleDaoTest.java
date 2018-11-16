package jdbc_product;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_product.dao.SaleDao;
import jdbc_product.dao.SaleDaoImpl;
import jdbc_product.dto.Product;
import jdbc_product.dto.sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class saleDaoTest {
	static SaleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start SaleDaoTest");
		dao = new SaleDaoImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End SaleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectSaleRankByAll() {
		List<sale> lists = dao.sellRanklist();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}
	@Test
	public void test02InsertSale() {
		try {
			sale newDept = new sale(11,new Product("A001"),6000,130,16);
			int rowAffected = dao.insertSale(newDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				LogUtil.prnLog("해당 음식 존재함");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}


}
