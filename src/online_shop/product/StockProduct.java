package online_shop.product;

public class StockProduct {
	private Product product;
	private int amount;
	
	
	public StockProduct(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return product.toString() + " amount: " + amount;
	}
	
	
}
