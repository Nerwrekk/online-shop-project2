package online_shop;

import java.util.ArrayList;
import java.util.List;

import online_shop.product.Catagory;
import online_shop.product.Manufacturer;
import online_shop.product.Product;

/*
 * This class will store the products and manage the quantity of each product.
 * 
 * TODO: build the class.
 */
public class WareHouse {
	
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
		}
		*/
		
	}
}
