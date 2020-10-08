package online_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import online_shop.product.Catagory;
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
	private Scanner userInput;

	private String[] commands = {"view catagories", "view manufacturer"};
	
	public OnlineShopSystem() {
		userInput = new Scanner(System.in);
	}
	
	public void goShopping(Cart userCart, WareHouse wareHouse) {
		System.out.println("Hello! Welcome to Console Shop!");
		printCommands();
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("Type --help to see a list of commands you can type.");
			
			//get an input from the user
			String command = userInput.nextLine();
			
			switch (command) {
				case "--help":
					printCommands();
					break;
				case "exit":
					System.out.println("goodbye");
					return;
				default:
					break;
				}
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

		return screen;
	}

	public List<String> add(Product product, int amount) {
		List<String> screen = new ArrayList();

		return screen;
	}

	public List<String> viewCategories() {
		List<String> screen = new ArrayList();

		return screen;
	}

	public List<String> viewCategory(Catagory category) {
		List<String> screen = new ArrayList();

		return screen;
	}

	public List<String> viewItem (Product product) {
		List<String> screen = new ArrayList();

		return screen;
	}
	
}
