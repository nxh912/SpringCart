package com.springcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")

public class ProductController {
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getAllProducts() {
		return "Get list of all products";
	}
}
