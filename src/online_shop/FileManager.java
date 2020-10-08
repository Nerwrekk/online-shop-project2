package online_shop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * FileManager sole purpose is to manage all files that are inside the system. 
 * 
 * The Singleton pattern is used here since it would be unnecessary to have multiple instances of this class because 
 * they would all do the exact same thing.
 */
public class FileManager {
	
	private static FileManager instance;
	
	public static final String PRODUCT_PATH = "files/products.csv";
	public static final String CART_DIRECTORY = "files/saved_carts";
	
	
	private FileManager() {
		initilize();
	}
	
	public static FileManager getInstance() {
		if (instance == null)  {
			instance = new FileManager();
		}
		
		return instance;
	}

	
	private void initilize() {
		
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

	public void writeToFile(String csv, String path) {
		try (PrintWriter writer = new PrintWriter(new File(path));) {
			writer.write(csv);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void writeToFile(List<String> content, String path, String fileName, String format) {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(path + "/" + fileName+format));
			
			for (String element : content) {
				bWriter.write(element);
				bWriter.newLine();
			}
			bWriter.close();
			System.out.println("File saved succesfully");
		} catch (IOException e) {
			System.err.println("Something went wrong when trying to write the file");
			e.printStackTrace();
		}
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
