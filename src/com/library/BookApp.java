/**
 * 
 */
package com.library;

import java.util.ArrayList;
import java.util.Scanner;

public class BookApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		bookList = BookHelper.readFromFile("booklist.txt");
		System.out.println(bookList);
	}

}

