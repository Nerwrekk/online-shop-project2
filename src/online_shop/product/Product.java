package online_shop.product;

import online_shop.Priceable;

/*
 * This class is in charge of creating every unique product in the store. 
 */
public class Product implements Priceable {
	private String name;
	private String description;
	private double price;
	private Category myCatagory;
	private Manufacturer myManufacturer;
	private int amount;
	
	public Product(String name, String description, Manufacturer myManufacturer, Category myCatagory, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.myCatagory = myCatagory;
		this.myManufacturer = myManufacturer;
	}

	// Creates a new product from another product, ignoring stock.
	public Product(Product product) {
		this.name = product.name;
		this.description = product.description;
		this.price = product.price;
		this.myCatagory = product.myCatagory;
		this.myManufacturer = product.myManufacturer;
	}

	// Creates a product based on a list of property strings.
	public Product (String[] properties) {
		this.name = properties[0];
		this.description = properties[1];
		this.myManufacturer = Manufacturer.getManufacturer(properties[2]);
		this.myCatagory = Category.getCatagory(properties[3]);
		this.price = Double.parseDouble(properties[4]);
		this.amount = Integer.parseInt(properties[5]);
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


	public Category getMyCatagory() {
		return myCatagory;
	}


	public void setMyCatagory(Category myCatagory) {
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
				", price: " + this.price + ", total cost: " + Math.round(this.calculatePrice() * 100.0)/100.0 + " sek";
	}
	
	
	@Override
	public Product clone() {
		return new Product(this);
	}

	// Returns the properties of a product formatted for saving in a CSV-file.
	public String toCSV() {
		String csv = new String();

		csv = 	name + ";" +
				description + ";" +
				myManufacturer.getName() + ";" +
				myCatagory.getName() + ";" +
				price + ";" +
				amount + ";";

		return	csv;
	}

	// Creates a new product from a CSV-string.
	public static Product fromCSV(String csv) {
		String[] properties = csvToProperties(csv);
		return new Product(properties);
	}

	// Creates a string[] of properties from a CSV-string.
	public static String[] csvToProperties(String properties) {
		return (properties.split(";"));
	}
}
