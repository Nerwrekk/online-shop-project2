package online_shop;


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
