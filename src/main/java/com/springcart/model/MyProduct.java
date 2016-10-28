package com.springcart.model;

import java.util.ArrayList;

public class MyProduct {
	int productId;
	private String name="";
	String url="";	
	float price;
	int category;
	
	public MyProduct (String name) {
		this.name = name;
		this.url = "";
	}
	public MyProduct (String name, String url) {
		this.name = name;
		this.url = url;
	}
	public MyProduct(int productId, String name, String url, int category, float price) {
		this.productId = productId;
		this.name = name;
		this.url = url;
		this.category = category;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public String getURL() {
		return url;
	}
	public int getProductId() {
		return productId;
	}
	public int getCategory() {
		return category;
	}
	public float getPrice() {
		return price;
	}
	public String getPriceString() {
		return String.format("%.2f", this.price);
	}
	
	
	public String toString() {
		return "{\"ProductName\":\""+ getName().replaceAll("\"", "\\\"") +"\",\"ProductURL\":\""+url+"\"}";
	}
	
		
	public static ArrayList<MyProduct> allItems = new ArrayList<MyProduct>();   
	public static String toJson(MyProduct [] products) {
    	StringBuffer buf = new StringBuffer();
    	buf.append("{");
    	
    	//buf.append("  \"products\": [");
    	for (int i=0; i<products.length; i++) {
    		MyProduct product = products[i];
    		if (i != 0) buf.append(",");
        	buf.append("\n    ").append(product.toString()).append("");
    	}
    	//buf.append("\n  ]\n");
    	
    	buf.append("}");
    	
    	System.err.println(buf.toString());
    	
    	return buf.toString();
    }
    static {
    	allItems.add(
    			new MyProduct(
    					1,
    					"CD:Farm Tour...Here's To The Farmer",
    					"https://images-na.ssl-images-amazon.com/images/I/51DykKxapHL._SS500.jpg",
    					1, 15.99f) );
    	allItems.add(
    			new MyProduct(
    					2,
    					"Music:American Band",
    					"https://images-na.ssl-images-amazon.com/images/I/51ZjYAXalaL._SS500.jpg",
    					1, 15.99f) );
    	allItems.add(
    			new MyProduct(
    					3,
    					"The Last Hero",
    					"https://images-na.ssl-images-amazon.com/images/I/61yrovKUyHL._SS500.jpg",
    					1, 19.99f) );
    	allItems.add(
    			new MyProduct(
    					4,
    					"CD:Revolution Radio",
    					"https://images-na.ssl-images-amazon.com/images/I/51kpakxvHhL._SS500.jpg",
    					1, 9.99f) );
    	allItems.add(
    			new MyProduct(
    					5,
    					"Enjoy Life Not Nuts! Mountain Mambo Seed and Fruit Mix, Gluten, Dairy, Nut & Soy Free, 6-Ounce Pouch (Pack of 6)",
    					"https://images-na.ssl-images-amazon.com/images/I/511%2BBSZ8nkL._AC_UL115_.jpg",
    					2, 3.99f) );
    	allItems.add(
    			new MyProduct(
    					6,
    					"Simply Protein Bar, Peanut Butter Chocolate, 12 Count",
    					"https://images-na.ssl-images-amazon.com/images/I/81dpY17rtdL._SX425_.jpg",
    					2, 2.50f) );
    	allItems.add(
    			new MyProduct(
    					7,
    					"Slim Jim Snack Sticks, Original, .28 Ounce, 26 Sticks",
    					"https://images-na.ssl-images-amazon.com/images/I/716KygckltL._SX425_.jpg",
    					2, 3.20f) );
    	allItems.add(
    			new MyProduct(
    					8,
    					"M&M'S Peanut Butter Chocolate Candy Fun Size 10.57-Ounce Bag (Pack of 6)",
    					"https://images-na.ssl-images-amazon.com/images/I/51O67PL2msL._SL160_.jpg",
    					2, 5.50f) );
    	allItems.add(
    			new MyProduct(
    					9,
    					"MARS Chocolate Fall Harvest Minis Size Candy Bars Variety Mix 45.8-Ounce Bag",
    					"https://images-na.ssl-images-amazon.com/images/I/61TkmOHBb0L._SL160_.jpg",
    					2, 4.25f) );
    	allItems.add(
    			new MyProduct(
    					10,
    					"SNICKERS Halloween Singles Size Chocolate Candy Bar Pumpkins 1.1-Ounce Bar 24-Count Box",
    					"https://images-na.ssl-images-amazon.com/images/I/61D6Tx0HjkL._SL160_.jpg",
    					2, 12.55f) );
    	allItems.add(
    			new MyProduct(
    					11,
    					"Sunbeam Massaging Xl Renue Heat Therapy Wrap, Lavender",
    					"https://images-na.ssl-images-amazon.com/images/I/518B25efgRL._AC_US190_.jpg",
    					3, 59.99f) );
    	allItems.add(
    			new MyProduct(
    					12,
    					"SCT Class 10 UHS-1 128GB SD XC Flash Memory Card, S.F128.RT - Retail Packaging",
    					"https://images-na.ssl-images-amazon.com/images/I/419y7AUTk7L._AC_US160_.jpg",
    					4, 35.99f) );
    	allItems.add(
    			new MyProduct(
    					13,
    					"SanDisk Ultra 64GB microSDXC UHS-I Card with Adapter, Grey/Red, Standard Packaging (SDSQUNC-064G-GN6MA)",
    					"https://images-na.ssl-images-amazon.com/images/I/41tfOAJ0lfL._AC_US160_.jpg",
    					4, 27.99f) );
    	allItems.add(
    			new MyProduct(
    					14,
    					"Transcend 512GB USB 3.0 External Solid State Drive (TS512GESD400K)",
    					"https://images-na.ssl-images-amazon.com/images/I/51RR6SsPBCL._AC_UL115_.jpg",
    					4, 199.99f) );
    	allItems.add(
    			new MyProduct(
    					15,
    					"Cricut Explore Air Wireless Cutting Machine",
    					"https://images-na.ssl-images-amazon.com/images/I/71lq-jIz3vL._SL1500_.jpg",
    					3, 149.99f) );
    }
}
