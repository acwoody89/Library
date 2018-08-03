/**
 * 
 */
package com.library;

public class Book {
	private String title;
	private String authorLast;
	private String authorFirst;
	private boolean status;
	private String dueDate;

	// public Book(String title, String authorLast, String authorFirst, boolean
	// status, String dueDate) {
	// super();
	// this.title = title;
	// this.authorLast = authorLast;
	// this.authorFirst = authorFirst;
	// this.status = status;
	// this.dueDate = dueDate;
	// }
	public Book() {
		super();
	}

	public Book(String title2, String authorLast2, String authorFirst2, boolean status2, String dueDate2) {
		
		this.title = title2;
		this.authorLast = authorLast2;
		this.authorFirst = authorFirst2;
		this.status = status2;
		this.dueDate = dueDate2;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the authorLast
	 */
	public String getAuthorLast() {
		return authorLast;
	}

	/**
	 * @param authorLast
	 *            the authorLast to set
	 */
	public void setAuthorLast(String authorLast) {
		this.authorLast = authorLast;
	}

	/**
	 * @return the authorFirst
	 */
	public String getAuthorFirst() {
		return authorFirst;
	}

	/**
	 * @param authorFirst
	 *            the authorFirst to set
	 */
	public void setAuthorFirst(String authorFirst) {
		this.authorFirst = authorFirst;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return title + "," + authorLast + "," + authorFirst + "," + status + "," + dueDate;
	}

}
