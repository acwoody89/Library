package com.library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//helper class
public class BookHelper {


	public static ArrayList<Book>  readFromFile(String book) {
		ArrayList <Book> bList = new ArrayList<>(); 
		boolean status = false; 
		// creating a read file class

		String fileName = "booklist.txt"; // my txt file name

		Path filePath = Paths.get(fileName);
		File file = filePath.toFile(); // converting path to a file for future uses

		// trying to read from reader

		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr); // create new string for buffer
			// will act like scanner

			String lineRead = reader.readLine(); // will read each line from reader
			String [] prompt = new String [5];
			
			
			
			while (lineRead != null) {
				prompt = lineRead.split(","); //??????
								
				if (prompt[3].equalsIgnoreCase("false")) {
			status = false;
				}
				else if (prompt[3].equalsIgnoreCase("true")) {
					status = true;
				}
				
				Book display = new Book(prompt[0],prompt[1],prompt[2],status,prompt[4]);
				bList.add(display);
				
				
				//System.out.println(lineRead);
				
				lineRead = reader.readLine(); // this will read the next line
			}
				
			
			
			
			
			
			
			reader.close();

			} catch (FileNotFoundException e) {
				System.out.println("File is not found");
			

		} catch (IOException e) { // catching it from parent class using catch clause
			System.out.println("This book does not exist..");

		}
		return bList;
	}
		
		public static void writeToFiles(String book) { //is this part right?***
			String fileName = "booklist.txt";
			Path writeFile = Paths.get(fileName);
			File file = writeFile.toFile();
			
			try {
				PrintWriter outW = new PrintWriter(new FileOutputStream(file)) ; 
				//no "true", it will overwrite the entire txt file each time
				outW.println(book);
				outW.close();
			
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
			
			
		}
		
		public static void writeToFiles(ArrayList<Book> bookList) { //overloaded method reads from BookApp
			String fileName = "booklist.txt";
			Path writeFile = Paths.get(fileName);
			File file = writeFile.toFile();
			
			try {
				PrintWriter outW = new PrintWriter(new FileOutputStream(file)) ; 
				//no "true", it will overwrite the entire txt file each time
				for (Book b : bookList) {
					outW.println(b.getTitle() + "," + b.getAuthorLast() + "," 
							+ b.getAuthorFirst() + "," + b.isStatus() + "," + b.getDueDate());
				}
				
				outW.close();
			
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
			
			
		}
		
		public static void createFile() { //this is to create a new file if it's not created yet
			String fileName = "booklist.txt";
			Path createFile = Paths.get(fileName);
			
			if (Files.notExists(createFile)) { //checking if the file exists
				
				try {
					Files.createFile(createFile);
					System.out.println("This book has been successfully stored!");
				} catch (IOException e) {
					System.out.println("Something went wrong..");
				}
			}
		}
}
		
		

	
	
	
	
	
	


