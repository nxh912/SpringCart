package com.springcart.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcart.model.MyProduct;

@RestController
@RequestMapping("/product")

public class ProductController {
	@RequestMapping(value="/", method = RequestMethod.GET)
	
	/*
	public String getAllProducts() {

 		MyProduct productList[] = new MyProduct[MyProduct.allItems.size()];
    	productList = MyProduct.allItems.toArray(productList);

    	return MyProduct.toJson(productList) ;
		 
	}*/	
	public ArrayList<MyProduct> getAllProducts() {
		return MyProduct.allItems;
	}
	
	
	
	@RequestMapping(value="/{categoryId}", method = RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:8080")
	public ArrayList<MyProduct> getCategoryProducts(@PathVariable("categoryId") int catId) {
		ArrayList<MyProduct> categoryProducts;
		MyProduct product;
		Iterator<MyProduct> i;
		
		categoryProducts = new ArrayList<MyProduct>();
		i = MyProduct.allItems.iterator();
		while (i.hasNext()) {
			product = (MyProduct) i.next();
			if (product.getCategory() == catId) {
				categoryProducts.add(product);
			}
		}
		
		return categoryProducts;		
	}	
		
}
