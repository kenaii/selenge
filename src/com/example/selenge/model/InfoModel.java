package com.example.selenge.model;

public class InfoModel {
	private String title;
	private String image;
	private String description;
	private String date;
	private String url;
	
	public InfoModel(String title, String image, String description, String date, String url) {
		this.title = title;
		this.image = image;
		if(description.length() > 150) {
			this.description = description.substring(0, 150) + "...";
		}
		else {
			this.description = description + "...";
		}
		this.date = date;
		this.url = url;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getUrl() {
		return this.url;
	}
}
