package online_shop;

import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream
import online_shop.product.Catagory;
import online_shop.product.Manufacturer;
import online_shop.product.Product;
import online_shop.product.WeightProduct;
=======
import online_shop.product.*;
>>>>>>> Stashed changes

/*
 * This class will store the products and manage the quantity of each product.
 *
 */
public class WareHouse {
<<<<<<< Updated upstream
	
	private List<Product> allProducts;
	
	public WareHouse() {
		allProducts = new ArrayList<Product>();
		initilizeProducts();
	}
	
	
	/*
	 * Method that recreates an existing product, this method is used when the user retrieves a stored cart.
	 */
	public Product getProduct(String name) {
		for (Product product : allProducts) {
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
				/*
				return new Product(product.getName(), product.getDescription(), 
								   product.getMyManufacturer(), product.getMyCatagory(), product.getPrice());
								   */
			}
		}
		return null;
	}
	
	/*
	 * Creates all products that are stored in "products.csv"
	 */
	public void initilizeProducts() {
		List<String> productStrings = FileManager.getInstance().readFromFile(FileManager.PRODUCT_PATH);
		
		for (String productString : productStrings) {
			String[] productRow = productString.split(";"); 
			//if the length of productRow is equal to 6 that means it is a "WeightProduct"
			if (productRow.length == 6) {
				allProducts.add(new WeightProduct(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]), 
						Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4]), Double.parseDouble(productRow[5])));
			} else {
				allProducts.add(new Product(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]), 
							Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4])));
			}
		}
		
		/*
		// if we wanna see all products properties, just remove the comment.
		for (Product product : allProducts) {
			System.out.println(product.getName());
			System.out.println(product.getDescription());
			System.out.println(product.getMyManufacturer().getName());
			System.out.println(product.getMyCatagory().getName());
			System.out.println(product.getPrice());
			if (product instanceof WeightProduct) {
				System.out.println(((WeightProduct) product).getWeight() + "kg"); 
			}
		}
		*/
		
	}
=======
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
>>>>>>> Stashed changes
}
