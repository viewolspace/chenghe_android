package com.parttime.orange.bean;

import java.io.Serializable;

/*
 * �鼮ʵ����
 */
public class Book implements Serializable {
	private String book_id;// �鼮id
	private String book_name;// �鼮����
	private String book_author;// �鼮����
	private String book_press;// ������
	private Category category;// �鼮����
	private String filename; // ͼƬ������
	private String path; // ·��
	private String book_desc;// ���
	private double book_price;// �鼮�۸�
	private int book_kunumber;// ���
	private int book_xiaonumber;// ����

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String book_id, String book_name, String book_author, String book_press, Category category,
			String filename, String path, String book_desc, double book_price, int book_kunumber, int book_xiaonumber) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_press = book_press;
		this.category = category;
		this.filename = filename;
		this.path = path;
		this.book_desc = book_desc;
		this.book_price = book_price;
		this.book_kunumber = book_kunumber;
		this.book_xiaonumber = book_xiaonumber;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_press() {
		return book_press;
	}

	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBook_desc() {
		return book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	public double getBook_price() {
		return book_price;
	}

	public void setBook_price(double book_price) {
		this.book_price = book_price;
	}

	public int getBook_kunumber() {
		return book_kunumber;
	}

	public void setBook_kunumber(int book_kunumber) {
		this.book_kunumber = book_kunumber;
	}

	public int getBook_xiaonumber() {
		return book_xiaonumber;
	}

	public void setBook_xiaonumber(int book_xiaonumber) {
		this.book_xiaonumber = book_xiaonumber;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_press=" + book_press + ", category=" + category + ", filename=" + filename + ", path=" + path
				+ ", book_desc=" + book_desc + ", book_price=" + book_price + ", book_kunumber=" + book_kunumber
				+ ", book_xiaonumber=" + book_xiaonumber + "]";
	}

}
