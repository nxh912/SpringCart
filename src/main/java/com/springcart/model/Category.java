package com.springcart.model;

public class Category {
	private int categoryId;
	private String categoryName;
	private int productCount;
	
	public int getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int getProductCount() {
		return this.productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}	
		
}
