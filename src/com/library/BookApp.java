/**
 * 
 * @author Alex Wood, John Brinker, Michelle Lam
 * @group Three Laptops And A Mouse
 * @project Midterm Project - Library Terminal 
 * 
 * 
 * 
 */
package com.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BookApp {

	/**
	 * 
	 * @param main method prints the contents of the library and presents the user
	 *             with a menu of options.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		bookList = BookHelper.readFromFile("booklist.txt");
		int inputNum = 0;

		do {

			printMenu(bookList);
			printOptions();
			inputNum = Validator.getInt(scan, "Please choose!\n", 1, 7);

			if (inputNum == 1) {
				authorSearch(bookList, scan);
			} else if (inputNum == 2) {
				titleSearch(bookList, scan);
			} else if (inputNum == 3) {
				checkOutBook(bookList, selectBook(bookList, scan), scan); // FIXME
			} else if (inputNum == 4) {
				returnBook(bookList, scan);
			} else if (inputNum == 5) {
				sortMenu(bookList, scan);
			} else if (inputNum == 6) {
				addBook(scan, bookList);
			} else if (inputNum == 7) {
				System.out.println("You are leaving the lie-brary of Three Laptops and A Mouse");
				// BookHelper.writeToFiles(bookList);
			}
		} while (!(inputNum == 7));
	}

	/**
	 * 
	 * @param sortMenu -- This generates a menu that sorts the contents of the
	 *                 library by user-selected criteria.
	 */
	public static void sortMenu(ArrayList<Book> sortList, Scanner scan) {
		String output = "";
		int inputNum = 0;
		do {
			System.out.println("1 - Sort By Author's Last Name");
			System.out.println("2 - Sort By Book Title");
			System.out.println("3 - Sort By Shelf Status");
			System.out.println("4 - Sort By Due Date");
			System.out.println("5 - Exit");
			inputNum = Validator.getInt(scan, "Please choose!", 1, 5);

			if (inputNum == 1) {
				sortByLastName(sortList);
				output = "Sorting by Last Name";
			} else if (inputNum == 2) {
				sortByTitle(sortList);
				output = "Sorting by Title";
			} else if (inputNum == 3) {
				sortByStatus(sortList);
				output = "Sorting by Status";
			} else if (inputNum == 4) {
				sortByDueDate(sortList);
				output = "Sorting by Due Date";
			} else if (inputNum == 5)
				printMenu(sortList);
		} while (!(inputNum == 5));
		System.out.println(output);
	}

	/**
	 * 
	 * @param returnBook -- This method allows a user to return a book, as long as
	 *                   its status is "Checked Out." It returns the book's status
	 *                   to "On Shelf." 
	 */
	public static void returnBook(ArrayList<Book> bookList, Scanner scan) {
		ArrayList<Book> sortList = new ArrayList<>();
		System.out.println("Are you returning a book?");
		int returnBook = Validator.getInt(scan, "(1) Yes \n(2) No\n", 1, 2);

		
		/**Display all books that are currently checked out.
		 */
		
		if (returnBook == 1) {
			for (Book b : bookList) {
				if (b.isStatus() == false) {
					sortList.add(b);
				}
			}
		}
		printMenu(sortList);
		
		/**
		 * Returning book as "On shelf".
		 */
		int userInput = Validator.getInt(scan, "Please select a book to return.", 1, sortList.size());
		for (int i = 0; i < sortList.size(); i++) {
			if (i == (userInput - 1)) {
				if (sortList.get(i).isStatus() == false) {
					sortList.get(i).setStatus(true);
					sortList.get(i).setDueDate("N/A");
					
					System.out.println("You have returned " + sortList.get(i).getTitle());
				}
			}
		}
	}

	/**
	 * 
	 * @param selectBook -- This method lets the user choose between books in display menu.
	 * @return The input number corresponding to the chosen book.
	 */
	public static int selectBook(ArrayList<Book> sortList, Scanner scan) {
		int inputNum = Validator.getInt(scan, "What book would you like to select? \n" + "Select by option number.", 1,
				sortList.size());
		return inputNum;
	}

	/**
	 * 
	 * @param checkOutBook -- This method takes a user-selected title, confirms that
	 *                     the user would like to check out the book, assigns a due
	 *                     date, and changes the book's status from "On Shelf" to
	 *                     "Checked Out."
	 */
	public static void checkOutBook(ArrayList<Book> sortList, int input, Scanner scan) {
		System.out.println("Would you like to check out this book?");
		int checkoutBook = Validator.getInt(scan, "(1) Yes \n(2) No\n", 1, 2);

		if (checkoutBook == 1 && (sortList.get(input - 1).isStatus() == true)) { 
			
			
			// Using the LocalDate library to import the current time 
			LocalDate checkOut = LocalDate.now();
			String date = "";
			for (int i = 0; i < sortList.size(); i++) {
				if (i == (input - 1)) {
					if (sortList.get(i).isStatus()) {
						sortList.get(i).setStatus(false);
						date = addTwoWeeks(checkOut).toString();
						sortList.get(i).setDueDate(date);
						System.out.println("You have checked out " + sortList.get(i).getTitle());
					}
				}
			}
		} else if (checkoutBook == 2) {
			System.out.println("Restarting search...");
		} else {
			System.out.println("That book is already checked out.");
		}
	}

	/**
	 * 
	 * @param statusToString -- This method converts the boolean status to the
	 *                       appropriate String.
	 * @return Returns either "On Shelf" or "Checked Out."
	 */
	public static String statusToString(boolean pStatus) {
		String status = null;
		if (pStatus == true) {
			status = "On Shelf";
		} else if (pStatus == false) {
			status = "Checked Out";
		}
		return status;
	}

	/**
	 * 
	 * @param addTwoWeeks -- This method generates due dates by adding two weeks to
	 *                    today's date.
	 * @param Method utilized LocalDate library 
	 * @return The due date is returned.
	 */
	public static LocalDate addTwoWeeks(LocalDate checkOut) {
		//added a specific number of weeks
		LocalDate returnDate = checkOut.plusWeeks(2);
		return returnDate;
	}

	/**
	 * 
	 * @param addBook -- This method adds new books to the library and writes/stores them
	 *                to the booklist text file. 
	 */
	public static void addBook(Scanner scan, ArrayList<Book> bookList) {
		int inputNum = 1;
		inputNum = Validator.getInt(scan, "Would you like to add a book?\n 1. yes\n2. no", 1, 2);
		do {
			if (inputNum == 1) {
				String newTitle = Validator.getString(scan, "Please enter a title:");
				String newAuthorLast = Validator.getString(scan, "Please the author's last name:");
				String newAuthorFirst = Validator.getString(scan, "Please enter the author's first name:");
				Book addBook = new Book(newTitle, newAuthorLast, newAuthorFirst, true, "N/A");
				
				boolean copyFound = false;
				System.out.println("You added " + newTitle + ".");
				
				for (Book b : bookList) {

					if (addBook.getTitle().equalsIgnoreCase(b.getTitle())) {
						copyFound = true;
					}
				}
				if (copyFound == false) {
					bookList.add(addBook);
				} else {
					System.out.println("That book already exists!");
				}
				
				//using the helper class to write the new files to the booklist
				BookHelper.writeToFiles(bookList);
				inputNum = Validator.getInt(scan, "Add another book?\n1. yes\n2. no", 1, 2);
			} 
		} while (inputNum == 1);

	}

	/**
	 * 
	 * @param printMenu -- This method prints a list of all books in the library with specific format.
	 */
	public static void printMenu(ArrayList<Book> sortList) {
		System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "Option", "Title", "Author", "Status", "Due Date");
		System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "*******", "*******", "*******", "*******", "*******");
		int count = 1;
		for (Book b : sortList) {

			System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "(" + count + ")", b.getTitle(),
					b.getAuthorLast() + ", " + b.getAuthorFirst(), statusToString(b.isStatus()), b.getDueDate());
			count++;
		}
		System.out.println("****************************************");

	}

	/**
	 * 
	 * @param titleSearch -- This method takes a user-input String, searches the
	 *                    title field for matches, adds them to an ArrayList and
	 *                    prints it to the console.
	 */
	public static void titleSearch(ArrayList<Book> sortList, Scanner scan) {
		int inputNum = 0;
		do {
			String input = Validator.getString(scan, "Enter a search term: ").toLowerCase();
			ArrayList<Book> searchBook = new ArrayList<>();

			int count = 1;
			for (Book b : sortList) {
				if ((b.getTitle().toLowerCase().contains(input))) {
					searchBook.add(b);
				}
			}

			if (searchBook.size() > 0) {
				System.out.println("Here are matches to the term " + input + ":");
				System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "Option", "Title", "Author", "Status", "Due Date");
				System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "*******", "*******", "*******", "*******",
						"*******");
				for (Book b : searchBook) {
					System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "(" + count + ")", b.getTitle(),
							b.getAuthorLast() + ", " + b.getAuthorFirst(), statusToString(b.isStatus()),
							b.getDueDate());
					count++;
				}
				checkOutBook(searchBook, selectBook(searchBook, scan), scan);
			} else {
				System.out.println("Sorry, search conditions not found! Try Again!");
			}

			inputNum = (Validator.getInt(scan, "Please choose 1 to search or 2 to exit:", 1, 2));
		} while (inputNum == 1);
	}

	/**
	 * 
	 * @param authorSearch -- This method takes a user-input string, searches
	 *                     authorLast and authorFirst fields for matches, adds them
	 *                     to an arrayList and prints to the console.
	 */
	public static void authorSearch(ArrayList<Book> sortList, Scanner scan) {
		int inputNum = 0;
		String inputLast = "";
		String inputFirst = "";
		do {
			inputLast = Validator.getString(scan, "Enter author's last name, or enter nothing " + "if unknown: ")
					.toLowerCase();
			inputFirst = Validator.getString(scan, "Enter author's first name, or enter nothing" + " if unknown: ")
					.toLowerCase();
			ArrayList<Book> searchBook = new ArrayList<>();
			
			/** This will allow users to look up author without
			 *  knowing their full first and last name.
			 */
			if (inputLast.length() < 1) {
				inputLast = "\"";
			}
			if (inputFirst.length() < 1) {
				inputFirst = "\"";
			}

			int count = 1;
			for (Book b : sortList) {
				if ((b.getAuthorLast().toLowerCase().contains(inputLast))
						|| (b.getAuthorFirst().toLowerCase().contains(inputFirst))) {

					searchBook.add(b);
				}
			}


			if (searchBook.size() > 0) {
				System.out.println("Here are matches to the term " + inputLast + ", " + inputFirst + ":");
				System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "Option", "Title", "Author", "Status", "Due Date");
				System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "*******", "*******", "*******", "*******",
						"*******");
				for (Book b : searchBook) {
					System.out.printf("%-10s %-50s %-35s %-20s %-35s%n", "(" + count + ")", b.getTitle(),
							b.getAuthorLast() + ", " + b.getAuthorFirst(), statusToString(b.isStatus()),
							b.getDueDate());
					count++;
				}
				checkOutBook(searchBook, selectBook(sortList, scan), scan);
			} else {
				System.out.println("Sorry, search conditions not found! Try Again!");
			}

			inputNum = (Validator.getInt(scan, "Please choose 1 to search or 2 to exit:", 1, 2));
		} while (inputNum == 1);
	}

	/**
	 * 
	 * @param printOptions -- This method prints the main menu.
	 */
	public static void printOptions() {
		System.out.println("***********************************");
		System.out.println("1 - Search by Author");
		System.out.println("2 - Search by Title");
		System.out.println("3 - Select Book");
		System.out.println("4 - Return Book");
		System.out.println("5 - Sort Menu");
		System.out.println("6 - Add Book");
		System.out.println("7 - Exit");
	}

	/**
	 * 
	 * @param sortByTitle -- This method sorts the books by title.
	 */
	public static void sortByTitle(ArrayList<Book> sortList) {
		Collections.sort(sortList, Comparator.comparing(Book::getTitle));
		printMenu(sortList);

	}

	/**
	 * 
	 * @param sortByLastName -- This method sorts the books by author's last name.
	 */
	public static void sortByLastName(ArrayList<Book> sortList) {
		Collections.sort(sortList, Comparator.comparing(Book::getAuthorLast));
		printMenu(sortList);
	}

	/**
	 * 
	 * @param sortByStatus -- This method sorts the books by On Shelf or Checked Out.
	 */
	public static void sortByStatus(ArrayList<Book> sortList) {
		Collections.sort(sortList, Comparator.comparing(Book::isStatus));
		printMenu(sortList);
	}

	/**
	 * 
	 * @param sortByDueDate -- This method sorts the books by due date.
	 */
	public static void sortByDueDate(ArrayList<Book> sortList) {
		Collections.sort(sortList, Comparator.comparing(Book::getDueDate));
		printMenu(sortList);

	}

}
