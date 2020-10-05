package online_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import online_shop.product.Catagory;
import online_shop.product.Manufacturer;
import online_shop.product.Product;
import online_shop.product.StockProduct;
import online_shop.product.WeightProduct;

/*
 * OnlineShopSystem purpose is to guide a user through the onlin store so they can buy the
 * groceries they need
 */
public class OnlineShopSystem {
	private WareHouse wareHouse;
	private Scanner userInput;
	private List<Product> allProducts;
	
	public OnlineShopSystem() {
		allProducts = new ArrayList<Product>();
		userInput =  new Scanner(System.in);
		initilizeProducts();
	}
	/*
	 * returns a list of StockProduct 
	 */
	public List<StockProduct> getSavedCart(String securityNumber) {
		List<List<String>> carts = FileManager.getInstance().loadInCarts();
		
		//list that will add all of our user existing carts that matches the social security number
		List<List<String>> userSavedCarts = new ArrayList<List<String>>();
		for (List<String> list : carts) {
			if (list.contains(securityNumber)) {
				userSavedCarts.add(list);
			}
		}
		//here we check if our customer have multiple saved carts or just one
		if (userSavedCarts.size() == 1) {
			return generateCart(userSavedCarts.get(0), securityNumber);
		} else {
			//if the user have multiple carts we need to ask them which cart they want
			//we call "chooseCart()" method that returns a number that matches an index inside "userSavedCarts" list
			int choosenCart = chooseCart(userSavedCarts);
			return generateCart(userSavedCarts.get(choosenCart), securityNumber);
		}
	}
	
	/*
	 * This method loops forever until the user enters a valid number.
	 * 
	 * TODO: implement a way for the user to exit the method in case they changed their mind about retrieving a cart.
	 */
	private int chooseCart(List<List<String>> userSavedCarts) {
		while (true) {
			System.out.println("It seems you have multiple saved carts");
			System.out.println("please enter the number that corresponds with the cart you wish to choose");
			int cartIndex = 0;
			for (List<String> list : userSavedCarts) {
				cartIndex++;
				System.out.println("Cart: " + cartIndex);
				System.out.println(list);
			}
			System.out.print("I choose cart: ");
			String choosenCart = userInput.nextLine();
			if (isValidatedCartNumber(choosenCart, userSavedCarts)) {
				int choosenCartNum = Integer.parseInt(choosenCart) -1;
				System.out.println("You choose cart: " + (choosenCartNum + 1));
				return choosenCartNum;
			} else {
				System.out.println("Invalid input, please try again.");
			}
			
		}
	}
	/*
	 * This method validates that the user have entered a valid number when choosing an existing cart.
	 */
	private boolean isValidatedCartNumber(String numberString, List<List<String>> userSavedCarts) {
		//Convert string to an integer
		int number = 0;
		try {
			//check if we got a value of type int
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			return false;
		}
		
		//check so the number is not bigger or less then list size
		if (number < 0 || number > userSavedCarts.size()) {
			System.out.println("Invalid number, please try again");
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * This method is the last step when retrieving an existing cart. It recreates all the products that are inside the cart.
	 */
	private List<StockProduct> generateCart(List<String> cartString, String securityNumber) {
		List<StockProduct> userCart = new ArrayList<StockProduct>();
		
		for (String productString : cartString) {
			//The first line is always the security number so we skip it.
			if (productString.equalsIgnoreCase(securityNumber)) {
				continue;
			}
			String[] productStringArray = productString.split(",");
			
			Product product = getNewProduct(productStringArray[0]);
			int amount = Integer.parseInt(productStringArray[1]);
			
			StockProduct stockProduct = new StockProduct(product, amount);
			userCart.add(stockProduct);
		}
		
		return userCart;
	}
	
	/*
	 * Method that recreates an existing product, this method is used when the user retrieves a stored cart.
	 */
	public Product getNewProduct(String name) {
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
}
