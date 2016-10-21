package com.springcart.model;

import java.util.ArrayList;
import java.util.Iterator;

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
		return this.lineItems.size();
	}
	
	public ArrayList<LineItem> getLineItems() {
		return this.lineItems;
	}
	
	public boolean lineItemExists(int productId) {
		Iterator<LineItem> lineItemIterator;
		LineItem item;
		
		lineItemIterator = lineItems.iterator();
		while (lineItemIterator.hasNext()) {
			item = (LineItem) lineItemIterator.next();
			if (item.getProductId() == productId)
				return true;
		}
		
		return false;
	}
	
	public void addLineItem(LineItem newItem) {
		this.lineItems.add(newItem);
	}
	
	public LineItem getLineItem(int productId) {
		Iterator<LineItem> lineItemIterator;
		LineItem item;
		
		lineItemIterator = lineItems.iterator();
		while (lineItemIterator.hasNext()) {
			item = (LineItem) lineItemIterator.next();
			if (item.getProductId() == productId)
				return item;
		}
		
		return null;
	}
		
}
