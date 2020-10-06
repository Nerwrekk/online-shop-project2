package online_shop;

import java.util.List;

import online_shop.product.Product;
import online_shop.product.StockProduct;

public class Program {

	public static void main(String[] args) {
		
		OnlineShopSystem onlineShop = new OnlineShopSystem();
		WareHouse wareHouse = new WareHouse();
		Cart userCart = new Cart(onlineShop, wareHouse);
		
		userCart.getSavedCart("9204064316");
		
		for (StockProduct stockProduct : userCart.getMyList()) {
			System.out.println(stockProduct.toString());
		}
	}

}
