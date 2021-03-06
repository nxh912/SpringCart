
package com.springcart.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.springcart.model.Cart;
import com.springcart.model.LineItem;
import com.springcart.model.MyProduct;
import com.springcart.serializer.CartSerializer;

@RestController
@RequestMapping("/cart")

public class CartController {
	private int counter = 0;
	private ArrayList<Cart> allCarts;
	private Cart cart;
	
	public CartController() {
		this.allCarts = new ArrayList<Cart> ();
		
		this.allCarts.add(new Cart(++counter));
		this.allCarts.add(new Cart(++counter));
		
		this.cart = new Cart(1);
	}
	
	// Returns the contents of the cart
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<String> getCartContentsCustom() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		HttpHeaders headers;
		String serialized = "";
		
		module.addSerializer(Cart.class, new CartSerializer());
		mapper.registerModule(module);

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		try {
			serialized = mapper.writeValueAsString(this.cart);

			return new ResponseEntity<String>(serialized, headers, HttpStatus.OK);
		}
		catch (JsonProcessingException je) {
			System.out.println("Problem processing JSON");
			serialized = je.getMessage();
			return new ResponseEntity<String>(serialized, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	@RequestMapping(value="/", method = RequestMethod.GET)
	public Cart getCartContents() {
		return this.cart;
	}
	*/
	
	// Clears the content of the cart
	@RequestMapping(value="/", method = RequestMethod.DELETE)
	public String clearCart(@PathVariable int cartId) {
		return "Delete cart with ID " + cartId;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public void putItem(@RequestBody LineItem newItem, HttpServletResponse response) {			
		MyProduct product;
		Iterator<MyProduct> iterator;
		boolean itemExists;
		LineItem item;
		
		// Check to see if the product is a valid product
		iterator = MyProduct.allItems.iterator();
		itemExists = false;
		while (iterator.hasNext()) {
			product = (MyProduct) iterator.next();
			
			if (product.getProductId() == newItem.getProductId()) {
				itemExists = true;
				break;
			}
		}
		
		if (itemExists) {
			// Update (if exists) or add to the cart
			if (cart.lineItemExists(newItem.getProductId())) {
				item = cart.getLineItem(newItem.getProductId());
				item.setQuantity(newItem.getQuantity() + item.getQuantity());
			}
			else {			
				item = new LineItem();
				item.setProductId(newItem.getProductId());
				item.setQuantity(newItem.getQuantity());
				
				cart.addLineItem(newItem);
			}
			response.setStatus(200);
		}
		else {
			// Product does not exist, return with error status
			response.setStatus(400);
		}
	}
	
	// Removes an item from the cart
	@RequestMapping(value="/{productid}", method = RequestMethod.DELETE)
	public void deleteCartItem(@PathVariable("productid") int productId) {
		ArrayList<LineItem> lineItems;
		Iterator<LineItem> i;
		LineItem lineItem;
		
		lineItems = this.cart.getLineItems();
		i = lineItems.iterator();
		while (i.hasNext()) {
			lineItem = (LineItem) i.next();
			
			if (lineItem.getProductId() == productId) {
				lineItems.remove(lineItem);
				break;
			}
		}
	}
	
}
