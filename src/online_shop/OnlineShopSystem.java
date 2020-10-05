package online_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import online_shop.product.Catagory;
import online_shop.product.Manufacturer;
import online_shop.product.Product;
import online_shop.product.StockProduct;

public class OnlineShopSystem {
	private WareHouse wareHouse;
	private Scanner userInput;
	private List<Product> allProducts;
	
	public OnlineShopSystem() {
		allProducts = new ArrayList<Product>();
		userInput =  new Scanner(System.in);
		initilizeProducts();
	}
	
	public List<StockProduct> getSavedCart(String securityNumber) {
		List<List<String>> carts = FileManager.getInstance().loadInCarts();
		
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
			int choosenCart = chooseCart(userSavedCarts);
			return generateCart(userSavedCarts.get(choosenCart), securityNumber);
		}
	}
	
	private int chooseCart(List<List<String>> userSavedCarts) {
		while (true) {
			System.out.println("It seems you have multiple saved carts");
			System.out.println("please enter the a number that corresponds with the cart you wish to choose");
			int cartIndex = 0;
			for (List<String> list : userSavedCarts) {
				cartIndex++;
				System.out.println("Cart: " + cartIndex);
				System.out.println(list);
			}
			System.out.print("Cart: ");
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
	
	private List<StockProduct> generateCart(List<String> cartString, String securityNumber) {
		List<StockProduct> userCart = new ArrayList<StockProduct>();
		
		for (String productString : cartString) {
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
	

	public Product getNewProduct(String name) {
		for (Product product : allProducts) {
			if (product.getName().equalsIgnoreCase(name)) {
				return new Product(product.getName(), product.getDescription(), 
								   product.getMyManufacturer(), product.getMyCatagory(), product.getPrice());
			}
		}
		return null;
	}
	
	public void initilizeProducts() {
		List<String> productStrings = FileManager.getInstance().readFromFile(FileManager.PRODUCT_PATH);
		
		for (String productString : productStrings) {
			String[] productRow = productString.split(";"); 
			allProducts.add(new Product(productRow[0], productRow[1], Manufacturer.getManufacturer(productRow[2]), 
							Catagory.getCatagory(productRow[3]), Double.parseDouble(productRow[4])));
		}
		
		/*
		for (Product product : allProducts) {
			System.out.println(product.getName());
			System.out.println(product.getDescription());
			System.out.println(product.getMyManufacturer().getName());
			System.out.println(product.getMyCatagory().getName());
			System.out.println(product.getPrice());
			System.out.println(product.getAmount());
		}
		*/
	}
}
