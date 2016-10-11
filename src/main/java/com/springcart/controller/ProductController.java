package com.springcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcart.model.Product;

@RestController
@RequestMapping("/product")

public class ProductController {
	@RequestMapping(value="/", method = RequestMethod.GET)
	
	
	public String getAllProducts() {

 		Product productList[] = new Product[Product.allItems.size()];
    	productList = Product.allItems.toArray(productList);

    	return Product.toJson(productList) ;
		 
	}
		
}
