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

	
	public OnlineShopSystem() {

	}
	
	public void goShopping(Cart userCart) {
		
	}
	
	public List<String> search(String searchString) {
		List<String> screen = new ArrayList();

		return screen;
	}

	public List<String> add(String productName, int amount) {
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

	public List<String> viewItem (String productName) {
		List<String> screen = new ArrayList();
			
		return screen;
	}
	
}
