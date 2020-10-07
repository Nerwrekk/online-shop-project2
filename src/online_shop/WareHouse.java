package online_shop;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import online_shop.product.Catagory;
import online_shop.product.Manufacturer;
import online_shop.product.Product;
=======
import online_shop.product.*;
>>>>>>> 3f4d1e45658ed36ccd6d96071fa2170ee6f101d8

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
<<<<<<< HEAD
			String[] productRow = productString.split(";"); 
				allProducts.add(new Product(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]), 
							Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4])));
		}
		
		/*
		// if we wanna see all products properties, just remove the comment.
		for (Product product : allProducts) {
			System.out.println(product.getName());
			System.out.println(product.getDescription());
			System.out.println(product.getMyManufacturer().getName());
			System.out.println(product.getMyCatagory().getName());
			System.out.println(product.getPrice());
=======
			String[] productRow = productString.split(";");
			//if the length of productRow is equal to 6 that means it is a "WeightProduct"
			if (productRow.length == 6) {
				stock.add(new WeightProduct(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]),
						Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4]), Double.parseDouble(productRow[5])));
			} else {
				stock.add(new Product(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]),
						Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4])));
			}
>>>>>>> 3f4d1e45658ed36ccd6d96071fa2170ee6f101d8
		}
	}

	public void saveStock() {

	}
}
