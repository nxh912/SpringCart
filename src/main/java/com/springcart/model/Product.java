package com.springcart.model;

import java.util.ArrayList;

public class Product {
	String name="";
	String url="";
	
	
	public Product (String name) {
		this.name = name;
		this.url = "";
	}
	public Product (String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String toString() {
		return "{\"ProductName\":\""+ name.replaceAll("\"", "\\\"") +"\",\"ProductURL\":\""+url+"\"}";
	}
	
	
	
	public static ArrayList<Product> allItems = new ArrayList<Product>();   
	public static String toJson(Product [] products) {
    	StringBuffer buf = new StringBuffer();
    	buf.append("{");
    	
    	buf.append("  \"products\": [");
    	for (int i=0; i<products.length; i++) {
    		Product product = products[i];
    		if (i != 0) buf.append(",");
        	buf.append("\n    ").append(product.toString()).append("");
    	}
    	buf.append("\n  ]\n");
    	
    	buf.append("}");
    	
    	System.err.println(buf.toString());
    	
    	return buf.toString();
    }
    static {
    	allItems.add(
    			new Product(
    					"CD:Farm Tour...Here's To The Farmer",
    					"https://images-na.ssl-images-amazon.com/images/I/51DykKxapHL._SS500.jpg") );
    	allItems.add(
    			new Product(
    					"Music:American Band",
    					"https://images-na.ssl-images-amazon.com/images/I/51ZjYAXalaL._SS500.jpg") );
    	allItems.add(
    			new Product(
    					"The Last Hero",
    					"https://images-na.ssl-images-amazon.com/images/I/61yrovKUyHL._SS500.jpg") );
    	allItems.add(
    			new Product(
    					"CD:Revolution Radio",
    					"https://images-na.ssl-images-amazon.com/images/I/51kpakxvHhL._SS500.jpg") );
    	allItems.add(
    			new Product(
    					"Enjoy Life Not Nuts! Mountain Mambo Seed and Fruit Mix, Gluten, Dairy, Nut & Soy Free, 6-Ounce Pouch (Pack of 6)",
    					"https://images-na.ssl-images-amazon.com/images/I/511%2BBSZ8nkL._AC_UL115_.jpg") );
    	allItems.add(
    			new Product(
    					"Simply Protein Bar, Peanut Butter Chocolate, 12 Count",
    					"https://images-na.ssl-images-amazon.com/images/I/81dpY17rtdL._SX425_.jpg") );
    	allItems.add(
    			new Product(
    					"Slim Jim Snack Sticks, Original, .28 Ounce, 26 Sticks",
    					"https://images-na.ssl-images-amazon.com/images/I/716KygckltL._SX425_.jpg") );
    	allItems.add(
    			new Product(
    					"M&M'S Peanut Butter Chocolate Candy Fun Size 10.57-Ounce Bag (Pack of 6)",
    					"https://images-na.ssl-images-amazon.com/images/I/51O67PL2msL._SL160_.jpg") );
    	allItems.add(
    			new Product(
    					"MARS Chocolate Fall Harvest Minis Size Candy Bars Variety Mix 45.8-Ounce Bag",
    					"https://images-na.ssl-images-amazon.com/images/I/61TkmOHBb0L._SL160_.jpg") );
    	allItems.add(
    			new Product(
    					"SNICKERS Halloween Singles Size Chocolate Candy Bar Pumpkins 1.1-Ounce Bar 24-Count Box",
    					"https://images-na.ssl-images-amazon.com/images/I/61D6Tx0HjkL._SL160_.jpg") );
    	allItems.add(
    			new Product(
    					"Sunbeam Massaging Xl Renue Heat Therapy Wrap, Lavender",
    					"https://images-na.ssl-images-amazon.com/images/I/518B25efgRL._AC_US190_.jpg") );
    	allItems.add(
    			new Product(
    					"SCT Class 10 UHS-1 128GB SD XC Flash Memory Card, S.F128.RT - Retail Packaging",
    					"https://images-na.ssl-images-amazon.com/images/I/419y7AUTk7L._AC_US160_.jpg") );
    	allItems.add(
    			new Product(
    					"SanDisk Ultra 64GB microSDXC UHS-I Card with Adapter, Grey/Red, Standard Packaging (SDSQUNC-064G-GN6MA)",
    					"https://images-na.ssl-images-amazon.com/images/I/41tfOAJ0lfL._AC_US160_.jpg") );
    	allItems.add(
    			new Product(
    					"Transcend 512GB USB 3.0 External Solid State Drive (TS512GESD400K)",
    					"https://images-na.ssl-images-amazon.com/images/I/51RR6SsPBCL._AC_UL115_.jpg") );
    	allItems.add(
    			new Product(
    					"Cricut Explore Air Wireless Cutting Machine",
    					"https://images-na.ssl-images-amazon.com/images/I/71lq-jIz3vL._SL1500_.jpg") );
    }

}
