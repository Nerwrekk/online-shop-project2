package online_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import online_shop.product.Product;

public class Cart {
	
	private List<Product> myList;
	private Scanner userInput;
	private OnlineShopSystem onlineShop;
	private WareHouse wareHouse;
	
	
	public Cart(OnlineShopSystem onlineShop, WareHouse wareHouse) {
		myList = new ArrayList<Product>();
		this.userInput = new Scanner(System.in);
		this.onlineShop = onlineShop;
		this.wareHouse = wareHouse;
	}
	
	
	public List<Product> getMyList() {
		return myList;
	}

	public void setMyList(List<Product> myList) {
		this.myList = myList;
	}

	public void viewCart() {
		double totalCost = 0.0;
		for (Product product : myList) {
			System.out.println(product.toString());
			totalCost += product.calculatePrice();
		}
		System.out.println("The final price is: " + totalCost + " sek");
	}

	public void getSavedCart(String securityNumber) {
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
			myList = generateCart(userSavedCarts.get(0), securityNumber);
		} else {
			//if the user have multiple carts we need to ask them which cart they want
			//we call "chooseCart()" method that returns a number that matches an index inside "userSavedCarts" list
			int choosenCart = chooseCart(userSavedCarts);
			myList = generateCart(userSavedCarts.get(choosenCart), securityNumber);
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
	 * This method is here because OnlineShopSystem is in charge of containing all existing products.
	 */
	public List<Product> generateCart(List<String> cartString, String securityNumber) {
		List<Product> userCart = new ArrayList<Product>();
		
		for (String productString : cartString) {
			//The first line is always the security number so we skip it.
			if (productString.equalsIgnoreCase(securityNumber)) {
				continue;
			}
			String[] productStringArray = productString.split(",");
			
			Product product = wareHouse.getProduct(productStringArray[0]);
			int amount = Integer.parseInt(productStringArray[1]);
			
			product.setAmount(amount);
			
			userCart.add(product);
		}
		
		return userCart;
	}
	
	/*
	 * This method sends a list of strings to our FileManager to store a cart in a file.
	 */
	public void saveCart() {
		List<String> cartToSave = new ArrayList<String>();
		
		String securityNumber = "";
		while(true) {
			System.out.println("Please enter your social security number");
			securityNumber = userInput.nextLine();
			
			//double check the value
			System.out.print("Is this your security number: " + securityNumber + " (y/n): ");
			String input = userInput.nextLine();
			
			if (input.equalsIgnoreCase("y")) {
				break;
			} 
		}
		
		//add security number
		cartToSave.add(securityNumber);
		
		//now lets loop through all products in our cart
		for (Product product : myList) {
			//we only need to store the name of the product followed by the amount
			cartToSave.add(product.getName() + "," + product.getAmount());
		}
		
		//we need to get a number that will be in the end of our filename.
		int cartNumber = FileManager.getInstance().loadInCarts().size() + 1;
		
		//Send the List to FileManager so that the cart will be saved.
		FileManager.getInstance().writeToFile(cartToSave, FileManager.CART_DIRECTORY, "cart" + cartNumber, ".txt");
	}
}
