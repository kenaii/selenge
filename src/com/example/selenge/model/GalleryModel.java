package com.example.selenge.model;

import java.util.ArrayList;

public class GalleryModel {
	
	private String title;
	private String image;
	private ArrayList<String> detailImages;
	
	public GalleryModel(String title, String image, ArrayList<String> detailImages) {
		this.title = title;
		this.image = image;
		this.detailImages = detailImages;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public ArrayList<String> getDetailImages() {
		return this.detailImages;
	}
	
}
