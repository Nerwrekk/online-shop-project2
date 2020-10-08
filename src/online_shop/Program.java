package online_shop;

import java.util.List;

import online_shop.product.Product;

public class Program {

	public static void main(String[] args) {
		
		OnlineShopSystem onlineShop = new OnlineShopSystem();
		WareHouse wareHouse = new WareHouse(FileManager.PRODUCT_PATH);

		//wareHouse.loadStock();
		//wareHouse.saveStock();

		Cart userCart = new Cart(onlineShop, wareHouse);
		userCart.getMyList().add(wareHouse.getStock().get(0).clone());
		userCart.getMyList().get(0).setAmount(3);
		userCart.getMyList().add(wareHouse.getStock().get(3).clone());
		userCart.getMyList().get(1).setAmount(66);
		
		onlineShop.goShopping(userCart, wareHouse);
		
		//userCart.getSavedCart("9204064316");
		//userCart.getSavedCart("9204064316");
		//Just a fast test to see if i could save a cart
		/*
		userCart.getMyList().add(wareHouse.getStock().get(0).clone());
		userCart.getMyList().get(0).setAmount(3);
		userCart.getMyList().add(wareHouse.getStock().get(3).clone());
		userCart.getMyList().get(1).setAmount(66);
		userCart.viewCart();
		userCart.saveCart();
		*/
	}

}
