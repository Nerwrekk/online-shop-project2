package online_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import online_shop.product.Category;
import online_shop.product.Manufacturer;
import online_shop.product.Product;

/*
 * OnlineShopSystem purpose is to guide a user through the online store so they can buy the
 * groceries they need
 * 
 */
public class OnlineShopSystem {
	private WareHouse wareHouse;
	private Cart userCart;
	private Scanner userInput;
	private boolean isShopping;


	private String[] commands = {"view categories", "view [category]","view manufacturers", "view [manufacturer]", 
								 "view cart", "save cart", "load cart",
								 "add [product]", "remove [product]",
								 "search [product]",
								 "exit" };
	
	public OnlineShopSystem() {
		userInput = new Scanner(System.in);
	}
	
	public void goShopping(Cart userCart, WareHouse wareHouse) {
		//initialize
		this.userCart = userCart;
		this.wareHouse = wareHouse;
		
		//boolean for running the while loop.
		isShopping = true;
		
		System.out.println("============================");
		System.out.println("| Welcome to Console Shop! |");
		System.out.println("============================");
		
		//printCommands();
		
		while(isShopping) {
			System.out.println("What would you like to do?");
			System.out.println("Type --help to see a list of commands you can type.");
			
			//get an input from the user
			String command = userInput.nextLine().toLowerCase();
			
			// view commands
			if (command.startsWith("view")) {
				viewCommands(command);
			}
			// add product
			else if (command.startsWith("add")) {
				addingProduct(command);
			}
			//remove product or amount
			else if (command.startsWith("remove")) {
				removeProduct(command);
			}
			//standard commands
			else if (command.startsWith("search")){
				List<List> view = new ArrayList(search(command.replaceFirst("search ", "")));
				for (List list : view) {
					System.out.println(list);
				}
			} else {
				standardCommands(command);
			}
			
		}
		System.out.println("Goodbye! Thank for shopping in Console shop, come back soon :)");
	}
	
	private void addingProduct(String command) {
		String[] productStringArray = command.split(" ");
		
		String productName = extractName(productStringArray);
		
		//check so that product is not null
		if (wareHouse.getProduct(productName) == null) {
			System.out.println("Invalid product");
			return;
		}
		
		//check if product stock is not empty
		if (wareHouse.isOutOfStock(wareHouse.getWareHouseProduct(productName))) {
			System.out.println(wareHouse.getWareHouseProduct(productName).getName() + " is currently out of order.");
			return;
		}
		
		//get a clone product if it does not already exists in the cart
		Product product = userCart.getProductFromCart(productName); 
		if (product == null) {
			product = wareHouse.getProduct(productName);
		}
		
		//this check is if the urser have misspelled the product name.
		if (product == null) {
			System.out.println("Invalid product name, please try again");
			return;
		} else {
			while (true) {
				System.out.println("Please enter the amount you want.");
				System.out.println("the amount in stock for [" + product.getName() + "] is: " + 
									wareHouse.getWareHouseProduct(productName).getAmount());
				System.out.print("Amount: ");
				
				String amountString = userInput.nextLine();
				
				if (amountString.equalsIgnoreCase("exit")) {
					System.out.println("Adding product aborted");
					return;
				}
				//using try catch in case the user makes an incorrect input value.
				try {
					//check so that we don't pick a number higher then warehouse have in stock.
					int amount = Integer.parseInt(amountString);
					//check so that the amount is not less or equal to zero.
					Product wareHouseProduct = wareHouse.getWareHouseProduct(productName);
					if (wareHouse.isAmountInRange(wareHouseProduct, amount)) {
						
						//change stock in warehouse product
						wareHouse.changeItemStock(wareHouseProduct, -amount);
						
						//adding product to cart
						userCart.addProduct(product, amount);
						System.out.println(product.getName() + " successfully added to cart");
						
						//done
						return;
					} else {
						System.out.println("invalid number, please try again.");
						continue;
					}
					
				} catch (NumberFormatException e) {
					System.out.println("Invalid number, please try again");
					continue;
				}
			}
		}
		
	}
	
	public void removeProduct(String command) {
		//check so that cart is not empty
		if (userCart.getMyList().isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		
		String[] productStringArray = command.split(" ");
		
		String productName = extractName(productStringArray);
		
		//get warehouse stock product and cart product
		Product wareHouseProduct = wareHouse.getWareHouseProduct(productName);
		Product productToRemove = userCart.getProductFromCart(productName);
		
		while (true) {
			System.out.println("preparing to remove [" + productToRemove.getName() + "] in cart, amount: " + productToRemove.getAmount());
			System.out.println("Please enter the amount you wish to remove.");
			System.out.print("Amount: ");
			
			String amountString = userInput.nextLine();
			
			if (amountString.equalsIgnoreCase("exit")) {
				System.out.println("Removing product aborted");
				return;
			}
			//using try catch in case the user makes an incorrect input value.
			try {
				//check so that we don't pick a number higher then the cart have.
				int amount = Integer.parseInt(amountString);
				
				if (amount <= 0 || amount > productToRemove.getAmount()) {
					System.out.println("Invalid number, please try again");
					continue;
				}
				
				userCart.removeProduct(productToRemove, amount);
				wareHouse.changeItemStock(wareHouseProduct, amount);
				
				if (!(userCart.getMyList().contains(productToRemove))) {
					System.out.println("[" + productToRemove.getName() + "] has been removed from cart");
				} else {
					System.out.println(productToRemove.getName() + " amount now set to: " + productToRemove.getAmount());
				}
				
				System.out.println("stock for [" + wareHouseProduct.getName() + "] have been refilled by " + amount);
				return;
				
			} catch (NumberFormatException e) {
				System.out.println("Invalid number, please try again");
				continue;
			}
		}
	}

	private String extractName(String[] productStringArray) {
		String productName = "";
		for (int i = 1; i < productStringArray.length; i++) {
			productName += productStringArray[i];
			
			if (i != productStringArray.length -1) {
				productName += " ";
			}
		}
		return productName;
	}

	private void standardCommands(String command) {
		switch (command.toLowerCase()) {
		case "--help":
			printCommands();
			break;
		case "save cart":
			userCart.saveCart();
			isShopping = false;
			break;
		case "load cart":
			userCart.getSavedCart();
			//update stock in warehouse
			for (Product product : userCart.getMyList()) {
				Product wareHouseProduct = wareHouse.getWareHouseProduct(product.getName());
				wareHouse.changeItemStock(wareHouseProduct, -product.getAmount());
			}
			break;
		case "exit":
			isShopping = false;
			return;
		default:
			System.out.println("Invalid command, please try again.");
			break;
		}
	}

	private void viewCommands(String command) {	
		String viewCommandString = extractName(command.split(" "));
		
		//check if the second word matches any specific category
		checkIfCategory(viewCommandString);
		
		//check if the second word matches any specific manufacturer
		checkIfManufacturer(viewCommandString);
		
		switch (viewCommandString.toLowerCase()) {
			case "cart":
				userCart.viewCart();
				break;
			case "categories":
				System.out.println(viewCategories());
				break;
			case "manufacturers":
				Manufacturer.printAllManufacturers();
				break;
			default:
				break;
		}
	}

	private void checkIfManufacturer(String manufacturerString) {
		Manufacturer manufacturer = Manufacturer.getManufacturer(manufacturerString);
		if (manufacturer != null) {
			System.out.println(manufacturer.getName() + " products: ");
			System.out.println(viewManufacturer(manufacturer));
		}
		
	}

	private void checkIfCategory(String categoryString) {
		Category category = Category.getCatagory(categoryString);
		if (category != null) {
			System.out.println(category.getName() + " products: ");
			System.out.println(viewCategory(category));
		}
	}

	private void printCommands() {
		System.out.println("Here are the commands you can type");
		System.out.println("=================");
		for (String command : commands) {
			System.out.println(command);
		}
		System.out.println("=================");
	}
	
	public List<List> search(String searchString) {
		List<List> screen = new ArrayList();
		List<Product> foundProducts = new ArrayList();
		for(Product product : wareHouse.getStock()) {
			if(product.getName().toLowerCase().contains(searchString.toLowerCase())) {
				foundProducts.add(product);
			}
		}

		for (Product product : foundProducts) {
			List<String> productView = new ArrayList();
			for (String productString : viewProduct(product.getName())) {
				productView.add(productString);
			}
			screen.add(productView);
		}
		return screen;
	}

	public List<String> viewCategories() {
		List<String> screen = new ArrayList();
		Category[] categories = Category.values();
		for(Category category : categories) {
			screen.add(category.getName());
		}
		return screen;
	}

	public List<String> viewCategory(Category category) {
		List<String> screen = new ArrayList();
		List<Product> productsInCategory = new ArrayList();
		for (Product product : wareHouse.getStock()) {
			if (product.getMyCatagory().getName().equalsIgnoreCase(category.getName())) {
				productsInCategory.add(product);
			}
		}
		screen = productList(productsInCategory);
		return screen;
	}
	
	public List<String> viewManufacturer(Manufacturer manufacturer) {
		List<String> screen = new ArrayList();
		List<Product> productsInManufacturer = new ArrayList();
		for (Product product : wareHouse.getStock()) {
			if (product.getMyManufacturer().getName().equalsIgnoreCase(manufacturer.getName())) {
				productsInManufacturer.add(product);
			}
		}
		screen = productList(productsInManufacturer);
		return screen;
	}

	// Takes a list of products and returns a list of strings, where each string in the list
	// inlcudes a certain product's: Name, Stock amount, and Price.
	private static List<String> productList (List<Product> products) {
		List<String> productList = new ArrayList();
		for(Product product : products) {
			productList.add(product.getName() + "\t Stock:" + product.getAmount() + "\t Price: " + product.getPrice());
		}
		return productList;
	}

	public List<String> viewProduct (String productName) {
		List<String> screen = new ArrayList();
		Product product = wareHouse.getWareHouseProduct(productName);
		screen.add(product.getName());
		screen.add(product.getMyManufacturer().getName());
		screen.add(product.getDescription());
		screen.add("Stock: " + product.getAmount());
		screen.add("Unit price: " + product.getPrice());
		return screen;
	}
}
