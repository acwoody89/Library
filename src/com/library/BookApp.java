/**
 * 
 */
package com.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		bookList = BookHelper.readFromFile("booklist.txt");		
		sortByTitle(bookList);
//		for (Book b : bookList) {
//			System.out.print(b.getTitle() + " ");
//			System.out.println(b.getAuthorLast());
//		}
		
		printMenu(bookList);
		
		do {
			printMenu(bookList);
			
			inputNum = Validator.getInt(scan, "Please choose!", 1, 3);
			
			if(inputNum == 1) {
				list = CountriesTextFile.readFromFile();
				printCountries(list);
			} else if (inputNum == 2) {
				input = Validator.getString(scan, "What is the name of the country you would like to add?");
				CountriesTextFile.writeToFile(input);
			}else if(inputNum == 3) {
				System.out.println("Good Bye");
			}
		}while(!(inputNum == 3));
	}

	public static void sortByTitle(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getTitle));

//		for (Book b : sortList) {
//			System.out.println(b.getTitle());
//		}
	}
	
	public static void sortByLastName(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getAuthorLast));

//		for (Book b : sortList) {
//			System.out.println(b.getTitle());
//		}
	}
	
	public static void sortByStatus(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::isStatus));

//		for (Book b : sortList) {
//			System.out.println(b.getTitle());
//		}
	}
	
	public static void sortByDate(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getDueDate));

//		for (Book b : sortList) {
//			System.out.println(b.getTitle());
//		}
	}
	
	public static void returnBook(ArrayList<Book> sortList) {
		
	}
	
	public static void selectBook(ArrayList<Book> sortList) {
		
	}
	
	public 
	
	public static void printMenu(ArrayList<Book> sortList) {
		System.out.printf("%-50s %-35s %-30s %-35s%n", "Title", "Author", "Status", "Due Date");
		System.out.printf("%-50s %-35s %-30s %-35s%n", "*******", "*******", "*******", "*******");
		int count = 1;
		for (Book b : sortList) {

			System.out.printf("%-50s %-35s %-30s %-35s%n", b.getTitle(), b.getAuthorLast() + ", " + b.getAuthorFirst(),
								b.isStatus(), b.getDueDate());
			count++;
		}
		System.out.println("****************************************");
		
		
	}
	
	public static void printOptions() {
		System.out.println("***********************************");
		System.out.println("1 - Search by Author");
		System.out.println("2 - Search by Title");
		System.out.println("3 - Select Book");
		System.out.println("4 - Return Book");
		System.out.println("5 - Exit");
	}

}
