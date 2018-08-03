/**
 * 
 */
package com.library;

import java.time.LocalDate;
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
		int inputNum = 0;
		sortByTitle(bookList);
		// for (Book b : bookList) {
		// System.out.print(b.getTitle() + " ");
		// System.out.println(b.getAuthorLast());
		// }

		do {
			printMenu(bookList);
			printOptions();
			inputNum = Validator.getInt(scan, "Please choose!", 1, 5);

			if (inputNum == 1) {

			} else if (inputNum == 2) {

			} else if (inputNum == 3) {
				checkOutBook(bookList, selectBook(bookList, scan), scan);
			} else if (inputNum == 4) {

			} else if (inputNum == 5) {
				sortMenu(bookList, scan);
			} else if (inputNum == 6) {
				System.out.println("You are leaving the lie-brary of Three Laptops and A Mouse");
			}
		} while (!(inputNum == 6));
	}

	public static void sortMenu(ArrayList<Book> sortList, Scanner scan) {
		String output = "";
		int inputNum = 0;
		do {
			System.out.println("1 - Sort By...Last Name");
			System.out.println("2 - Sort By...Title");
			System.out.println("3 - Sort By...Status");
			System.out.println("4 - Sort By...Due Date");
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
			}
			printMenu(sortList);
		} while (!(inputNum == 5));
		System.out.println(output);
	}

	public static void returnBook(ArrayList<Book> sortList, Scanner scan) {
		System.out.println("Are you returning a book?");
		int checkoutBook = Validator.getInt(scan, "(1) Yes \n (2) No", 1, 2);
	}

	public static int selectBook(ArrayList<Book> sortList, Scanner scan) {
		int inputNum = Validator.getInt(scan,
				"What book would you like to select? \n " + "select by option number or Title", 1, sortList.size());
		return inputNum;
	}

	public static void checkOutBook(ArrayList<Book> sortList, int input, Scanner scan) {
		System.out.println("Would you like to checkout this book?");
		int checkoutBook = Validator.getInt(scan, "(1) Yes \n (2) No", 1, 2);

		if (checkoutBook == 1 && (sortList.get(input - 1).isStatus() == true)) {

			LocalDate checkOut = LocalDate.now();
			String date = "";
			for (int i = 0; i < sortList.size(); i++) {
				if (i == (input - 1)) {
					if (sortList.get(i).isStatus()) {
						sortList.get(i).setStatus(false);
						date = addTwoWeeks(checkOut).toString();
						sortList.get(i).setDueDate(date);
						System.out.println("You have checked out");
					}
				}
			}
		} else {
			System.out.println("That book is already checked out. Selc");
		}
	}

	public static String statusToString(boolean pStatus) {
		String status = null;
		if (pStatus == true) {
			status = "On Shelf";
		} else if (pStatus == false) {
			status = "Checked Out";
		}
		return status;
	}

	public static LocalDate addTwoWeeks(LocalDate checkOut) {
		LocalDate returnDate = checkOut.plusWeeks(2);

		return returnDate;
	}

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

	public static void printOptions() {
		System.out.println("***********************************");
		System.out.println("1 - Search by Author");
		System.out.println("2 - Search by Title");
		System.out.println("3 - Select Book");
		System.out.println("4 - Return Book");
		System.out.println("5 - Sort Menu");
		System.out.println("6 - Exit");
	}

	public static void sortByTitle(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getTitle));

		// for (Book b : sortList) {
		// System.out.println(b.getTitle());
		// }
	}

	public static void sortByLastName(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getAuthorLast));

		// for (Book b : sortList) {
		// System.out.println(b.getTitle());
		// }
	}

	public static void sortByStatus(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::isStatus));

		// for (Book b : sortList) {
		// System.out.println(b.getTitle());
		// }
	}

	public static void sortByDueDate(ArrayList<Book> sortList) {
		// import java.util.Collections;
		Collections.sort(sortList, Comparator.comparing(Book::getDueDate));

		// for (Book b : sortList) {
		// System.out.println(b.getTitle());
		// }
	}

}
