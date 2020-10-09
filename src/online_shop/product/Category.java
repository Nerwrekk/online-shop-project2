package online_shop.product;

public enum Category {
	Dairy("Dairy"), Fruit("Fruit"), Greens("Greens");
	
	private final String name;
	
	private Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Category getCatagory(String name) {
		Category[] catagories = Category.values();
		for (Category category : catagories) {
			if (category.getName().equalsIgnoreCase(name)) {
				return category;
			}
		}
		return null;
	}
}
