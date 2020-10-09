package online_shop.product;


public enum Manufacturer {
	Arla("Arla"), GreenGiant("Green Giant");
	
	private final String name;
	
	private Manufacturer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Manufacturer getManufacturer(String name) {
		Manufacturer[] manufacturers = Manufacturer.values();
		for (Manufacturer manufacturer : manufacturers) {
			if (manufacturer.getName().equalsIgnoreCase(name)) {
				return manufacturer;
			}
		}
		return null;
	}
	
	public static void printAllManufacturers() {
		for (Manufacturer manufacturer : Manufacturer.values()) {
			System.out.println(manufacturer.name);
		}
	}
}
