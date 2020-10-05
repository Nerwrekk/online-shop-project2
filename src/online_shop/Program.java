package online_shop;

import java.util.List;

import online_shop.product.Product;
import online_shop.product.StockProduct;

public class Program {

	public static void main(String[] args) {
		
		OnlineShopSystem onlineShop = new OnlineShopSystem();
		
		List<StockProduct> cartList = onlineShop.getSavedCart("9204064316");
		
		for (StockProduct product : cartList) {
			System.out.println(product.toString());
		}
	}

}
