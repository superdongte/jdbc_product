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

import jdbc_product.dao.ProductDao;
import jdbc_product.dao.ProductDaolmpl;
import jdbc_product.dto.Product;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
	static ProductDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start ProductDaoTest");
		dao = new ProductDaolmpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End ProductDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectDepartmentByAll() {
		List<Product> lists = dao.selectDepartmentByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}
	@Test
	public void test02InsertDepartment() {
		try {
			Product newDept = new Product("A006","가라멜 마끼야또");
			int rowAffected = dao.insertDepartment(newDept);
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

	@Test
	public void test04DeleteDepartment() {
		try {
			Product delDept = new Product();
			delDept.setCode("A006");
			int rowAffected = dao.deleteDepartment(delDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1451) {
				LogUtil.prnLog("해당 음식 존재한다.");
			} else {
				LogUtil.prnLog(e);
			}
		}
	}

	@Test
	public void test03UpdateDepartment() {
		try {
			Product updateDept = new Product("A008", "카푸치노");
			int rowAffected = dao.updateDepartment(updateDept);
			LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
			Assert.assertEquals(1, rowAffected);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
		try {
			Product selDept = new Product();
			selDept.setCode("A004");
			Product department = dao.selectDepartmentByNo(selDept);
			LogUtil.prnLog(String.format("%s - %s", department.getClass().getSimpleName(), department));
			Assert.assertNotNull(department);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
	}
}
