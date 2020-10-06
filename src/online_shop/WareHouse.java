package online_shop;

import java.util.ArrayList;
import java.util.List;

import online_shop.product.*;

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

	// Changes the stock of weighted products.
	public void changeItemStock(WeightProduct product, double stockChange) {
		product.setWeight(product.getWeight() + stockChange);
	}

	// Changes the stock of non-weighted products.
	public void changeItemStock(StockProduct product, int stockChange) {
		product.setAmount(product.getAmount() + stockChange);
	}

	public void loadStock() {
		List<String> productStrings = FileManager.getInstance().readFromFile(stockPath);
		stock = new ArrayList<Product>();

		for (String productString : productStrings) {
			String[] productRow = productString.split(";");
			//if the length of productRow is equal to 6 that means it is a "WeightProduct"
			if (productRow.length == 6) {
				stock.add(new WeightProduct(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]),
						Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4]), Double.parseDouble(productRow[5])));
			} else {
				stock.add(new Product(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]),
						Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4])));
			}
		}
	}

	public void saveStock() {

	}
}
