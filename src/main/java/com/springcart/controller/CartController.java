
package com.springcart.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcart.model.Cart;

@RestController
@RequestMapping("/cart")

public class CartController {
	private int counter = 0;
	private ArrayList<Cart> allCarts;
	
	public CartController() {
		this.allCarts = new ArrayList<Cart> ();
		
		this.allCarts.add(new Cart(++counter));
		this.allCarts.add(new Cart(++counter));
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	/*
	public String getAllCarts() {
		return "Get list of available carts";
	}
	*/
	public ArrayList<Cart> getAllCarts() {
		return this.allCarts;
	}
	
	// May not need this if intend to fix the total number of carts available
	@RequestMapping(value="/", method = RequestMethod.PUT)
	public String saveNewCart() {
		return "Save a new cart";
	}
	
	@RequestMapping(value="/{cartid}", method = RequestMethod.GET)
	public String getCartContent(@PathVariable("cartid") int cartId) {		
		return "Get contents of cart with ID " + cartId;		
	}	
	
	// This can be used to clear contents of the cart
	// Or delete a cart from the cart list
	@RequestMapping(value="/{cartid}", method = RequestMethod.DELETE)
	public String deleteCart(@PathVariable int cartId) {
		return "Delete cart with ID " + cartId;
	}	
}
