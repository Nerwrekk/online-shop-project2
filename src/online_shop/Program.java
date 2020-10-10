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
		
		onlineShop.goShopping(userCart, wareHouse);
	}

}
