package jdbc_product.dto;

public class sale {
	private int no;
	private Product code;
	private int price;
	private int saleCnt;
	private int marginRate;
	private SaleDetail detail;
	/*@Override
	public String toString() {
		return String.format("sale [no=%s, code=%s, price=%s, saleCnt=%s, marginRate=%s]", no, code, price, saleCnt,
				marginRate);
	}*/

	public int getNo() {
		return no;
	}

	@Override
	public String toString() {
		return String.format("sale [no=%s, code=%s, price=%s, saleCnt=%s, marginRate=%s, detail=%s]", no, code, price,
				saleCnt, marginRate, detail);
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Product getCode() {
		return code;
	}

	public void setCode(Product code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaleCnt() {
		return saleCnt;
	}

	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}

	public int getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(int marginRate) {
		this.marginRate = marginRate;
	}

	public sale(int no, Product product, int price, int saleCnt, int marginRate) {
		this.no = no;
		this.code = product;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
	}

	public sale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public sale(int no) {
		this.no = no;
	}

	public SaleDetail getDetail() {
		return detail;
	}

	public void setDetail(SaleDetail detail) {
		this.detail = detail;
	}

	public sale(Product code) {
		this.code = code;
	}
	
	public Product getProduct() {
		return code;
	}

	public void setProduct(Product product) {
		this.code = product;
	}

	public sale(int no, Product code, int price, int saleCnt, int marginRate, SaleDetail detail) {
		this.no = no;
		this.code = code;
		this.price = price;
		this.saleCnt = saleCnt;
		this.marginRate = marginRate;
		this.detail = detail;
	}
	

	
}
