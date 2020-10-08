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
	}

	public ArrayList<Product> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Product> stock) {
		this.stock = stock;
	}

	public Product getProduct(String name) {
		for (Product product : stock) {
			if (product.getName() == name) {
				return product;
			}
		}
		return null;
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
		System.out.println(sb);
		System.out.println(sb.toString());

		FileManager.getInstance().writeToFile(sb.toString(), stockPath);
	}
}
