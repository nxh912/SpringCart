package com.springcart.model;

import java.util.ArrayList;

public class Cart {
	private int cartId;
	private float cartTotal;
	private ArrayList<LineItem> lineItems;
	
	public Cart(int cartId) {
		this.cartId = cartId;
		this.lineItems = new ArrayList<LineItem> ();
	}
	public int getCartId() {
		return this.cartId;
	}
	
	public float getCartTotal() {
		// Return cart total
		return this.cartTotal;
	}
	
	public ArrayList<LineItem> getLineItems() {
		return this.lineItems;
	}
		
}
