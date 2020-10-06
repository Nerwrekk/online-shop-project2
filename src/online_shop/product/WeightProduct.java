package online_shop.product;

/*
 * Products that calculates its price by its weight.
 * 
 * TODO: maybe generate some randomness to the weight attribute?
 */
public class WeightProduct extends Product {
	
	private double weight;

	public WeightProduct(String name, String description, Manufacturer myManufacturer, Catagory myCatagory, double price, double weight) {
		super(name, description, myManufacturer, myCatagory, price);
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	

	@Override
	public double calculatePrice() {
		return Math.round(weight * super.getPrice());
	}
	

	@Override
	public String toString() {
		return super.toString() + ", " + weight + "kg";
	}
	
	
}
