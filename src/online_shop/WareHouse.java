package online_shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		//TODO: remove after finished, quick stock fill.
		for (Product product : stock) {
			changeItemStock(product, 10);
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
	
	public Product getProduct(String name, int amount) {
		if (amount <= 0) {
			System.out.println("invalid amount");
			return null;
		}
		Product product = getProduct(name);
		product.setAmount(amount);
		return product;
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
}
