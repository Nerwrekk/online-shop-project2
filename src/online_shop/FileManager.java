package online_shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	private static FileManager instance;
	
	public static final String PRODUCT_PATH = "files/products.csv";
	public static final String CART_DIRECTORY = "files/saved_carts";
	
	private File productsFile;
	
	private FileManager() {
		initilize();
	}
	
	public static FileManager getInstance() {
		if (instance == null)  {
			instance = new FileManager();
		}
		
		return instance;
	}

	public File getProductsFile() {
		return instance.productsFile;
	}
	
	private void initilize() {
		productsFile = new File(PRODUCT_PATH);
	}
	
	public void writeToFile(File file) {
		
	}
	
	public List<String> readFromFile(String path) {
		List<String> tempList = new ArrayList<String>();
		try {
			BufferedReader bfReader = new BufferedReader(new FileReader(path));
			String text = bfReader.readLine();
			while (text != null) {
				tempList.add(text);
				text = bfReader.readLine();
			}
			bfReader.close();
			return tempList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public List<List<String>> loadInCarts() {
		File[] cartFiles = new File(CART_DIRECTORY).listFiles();
		
		List<List<String>> stringCarts = new ArrayList<List<String>>();
		for (File cart : cartFiles) {
			stringCarts.add(readFromFile(cart.getPath()));
		}
		
		return stringCarts;
	}
}
