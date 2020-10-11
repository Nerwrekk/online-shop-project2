package online_shop;

import java.util.ArrayList;
import java.util.List;

import online_shop.product.Product;



/*
 * This class will store the products and manage the quantity of each product.
 *
 */
public class WareHouse {
	private ArrayList<Product> stock;
	private String stockPath;

	public WareHouse(String stockPath) {
		this.stockPath = stockPath;
		loadStock();
		
		for (Product product : stock) {
			changeItemStock(product, 100);
		}
	}

	public ArrayList<Product> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Product> stock) {
		this.stock = stock;
	}

	public Product getProduct(String name) {
		for (Product product : stock) {
			//changed this to equal instead because == is unreliable
			if (product.getName().equalsIgnoreCase(name)) {
				return product.clone();
			}
		}
		return null;
	}
	
	public Product getWareHouseProduct(String name) {
		for (Product product : stock) {
			//changed this to equal instead because == is unreliable
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		return null;
	}
	
	public boolean isOutOfStock(Product product) { 
		if (product.getAmount() <= 0) {
			return true;
		}
		
		return false;
	}

	// Changes the stock of a product.
	public void changeItemStock(Product product, int stockChange) {
		product.setAmount(product.getAmount() + stockChange);
	}
	

	public void loadStock() {
		List<String> productStrings = FileManager.getInstance().readFromFile(stockPath);
		stock = new ArrayList<Product>();

		for (String productString : productStrings) {
			stock.add(Product.fromCSV(productString));
		}
	}

	public void saveStock() {
		StringBuilder sb = new StringBuilder();
		for (Product product : stock) {
			sb.append(product.toCSV());
			sb.append('\n');
		}

		FileManager.getInstance().writeToFile(sb.toString(), stockPath);
	}

	public boolean isAmountInRange(Product wareHouseProduct, int amount) {
		if (amount <= 0 || amount > wareHouseProduct.getAmount()) {
			return false;
		}
		
		return true;
	}
}
