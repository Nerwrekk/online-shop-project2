package online_shop.product;

import online_shop.Priceable;

/*
 * This class is in charge of creating every unique product in the store. 
 */
public class Product implements Priceable {
	private String name;
	private String description;
	private double price;
	private Catagory myCatagory;
	private Manufacturer myManufacturer;
	private int amount;
	
	public Product(String name, String description, Manufacturer myManufacturer, Catagory myCatagory, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.myCatagory = myCatagory;
		this.myManufacturer = myManufacturer;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Catagory getMyCatagory() {
		return myCatagory;
	}


	public void setMyCatagory(Catagory myCatagory) {
		this.myCatagory = myCatagory;
	}


	public Manufacturer getMyManufacturer() {
		return myManufacturer;
	}


	public void setMyManufacturer(Manufacturer myManufacturer) {
		this.myManufacturer = myManufacturer;
	}
	
	
	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	@Override
	public double calculatePrice() {
		return (amount * price);
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.description + ", amount: " + this.amount + 
				", price: " + this.price + ", total cost: " + this.calculatePrice() + " sek";
	}
}
