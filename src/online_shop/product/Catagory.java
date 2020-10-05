package online_shop.product;

public enum Catagory {
	Dairy("Dairy"), Fruit("Fruit"), Greens("Greens");
	
	private final String name;
	
	private Catagory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Catagory getCatagory(String name) {
		Catagory[] catagories = Catagory.values();
		for (Catagory category : catagories) {
			if (category.getName().equalsIgnoreCase(name)) {
				return category;
			}
		}
		return null;
	}
}
