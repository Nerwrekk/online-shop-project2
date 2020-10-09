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
 * IDEA: should maybe implement singleton pattern.
 */
public class OnlineShopSystem {
	private WareHouse wareHouse;
	private Cart userCart;
	private Scanner userInput;

	private String[] commands = {"view catagories", "view [category]","view manufacturers", "view [manufacturer]", 
								 "view cart", "get saved cart",
								 "exit" };
	
	public OnlineShopSystem() {
		userInput = new Scanner(System.in);
	}
	
	public void goShopping(Cart userCart, WareHouse wareHouse) {
		//initialize
		this.userCart = userCart;
		this.wareHouse = wareHouse;
		
		System.out.println("============================");
		System.out.println("| Welcome to Console Shop! |");
		System.out.println("============================");
		
		//printCommands();
		
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("Type --help to see a list of commands you can type.");
			
			//get an input from the user
			String command = userInput.nextLine().toLowerCase();
			
			// view commands
			viewCommands(command);
			
			//standard commands
			standardCommands(command);
			
			switch (command.toLowerCase()) {
			case "--help":
				printCommands();
				break;
			case "exit":
				System.out.println("goodbye");
				return;
			default:
				System.out.println("Invalid command, please try again.");
				break;
			}
	
		}
	}
	
	private void standardCommands(String command) {
		
	}

	private void viewCommands(String command) {
		if (command.startsWith("view")) {
			String secondWord = command.split(" ")[1];
			
			//check if the second word matches any category
			checkIfCategory(secondWord);
			
			switch (secondWord.toLowerCase()) {
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
	}

	private void checkIfCategory(String secondWord) {
		Category category = Category.getCatagory(secondWord);
		if (category != null) {
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
	
	public List<String> search(String searchString) {
		List<String> screen = new ArrayList();
		List<Product> foundProducts = new ArrayList();
		for(Product product : wareHouse.getStock()) {
			if(product.getName().toLowerCase().contains(searchString.toLowerCase())) {
				foundProducts.add(product);
			}
		}
		screen = productList(foundProducts);
		return screen;
	}

	public List<String> add(String productName, int amount) {
		List<String> screen = new ArrayList();

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

	public List<String> productList (List<Product> products) {
		List<String> productList = new ArrayList();
		for(Product product : products) {
			productList.add(product.getName() + "\t Stock:" + product.getAmount() + "\t Price: " + product.getPrice());
		}
		return productList;
	}

	public List<String> viewItem (String productName) {
		List<String> screen = new ArrayList();
		Product product = wareHouse.getProduct(productName);
		screen.add(product.getName());
		screen.add(product.getMyManufacturer().getName());
		screen.add(product.getDescription());
		screen.add("Stock: " + product.getAmount());
		screen.add("Unit price: " + product.getPrice());
		return screen;
	}
}
