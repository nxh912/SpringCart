package com.springcart.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcart.model.Category;
import com.springcart.model.Product;

@RestController
@RequestMapping("/category")

public class CategoryController {
	private ArrayList<Category> allCategories;
	
	public CategoryController() {
		Category newCategory;
		this.allCategories = new ArrayList<Category>();		
		
		for (int count = 1; count < 5; count++) {
			newCategory = new Category();
			newCategory.setCategoryId(count);
			switch (count) {
				case 1: newCategory.setCategoryName("Music"); break;
				case 2: newCategory.setCategoryName("Foodstuff"); break;
				case 3: newCategory.setCategoryName("Electrical"); break;
				case 4: newCategory.setCategoryName("Computer"); break;
				default: break;
			}
			//newCategory.setCategoryName("Category " + count);
			//newCategory.setProductCount(0);
			this.allCategories.add(newCategory);
		}
		
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:8080", maxAge=2)
	public ArrayList<Category> getAllCategory() {
		return this.allCategories;
	}	
}
