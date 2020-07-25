package com.baihe.orange.bean;

import java.io.Serializable;

//�ղؼ�
public class Favorite implements Serializable {
	private String favorite_id;// �ղ���id
	private User user;// �û���Ϣ
	private Book book;// �鼮��Ϣ

	public Favorite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favorite(String favorite_id, User user, Book book) {
		super();
		this.favorite_id = favorite_id;
		this.user = user;
		this.book = book;
	}

	public String getFavorite_id() {
		return favorite_id;
	}

	public void setFavorite_id(String favorite_id) {
		this.favorite_id = favorite_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Favorite [favorite_id=" + favorite_id + ", user=" + user + ", book=" + book + "]";
	}

}
