package com.example.selenge.model;

public class ContactModel {
	
	private String title;
	private int image;

	public ContactModel(String title, int image) {
		this.title = title;
		this.image = image;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getImage() {
		return this.image;
	}
	
}
