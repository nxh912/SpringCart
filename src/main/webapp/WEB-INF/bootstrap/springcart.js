/**
 * springcart.js
 * Contains all functionality related to SpringCart client functionality
 */

var BASE_URL = "http://localhost:8080/SpringCart/";
//var BASE_URL = "http://10.213.34.101:8080/SpringCart/";

$(document).ready(function() {
	getAllCategories();
	updateCartItemCount();
	
	// Event handler for the category dropdown
	$('#menu-dropdown').on('click', 'a', retrieveProductCategory);
	
	// Event handler for quantity and add to cart buttons
	$('#product-page-list').on('click', '.btn-danger', decrementQuantity);
	$('#product-page-list').on('click', '.btn-success', incrementQuantity);
	$('#product-page-list').on('click', '.btn-primary', addItemToCart);
	
});

/*
 * getAllCategories()
 * Retrieves all product categories and fill up the dropdown menu
 */

function getAllCategories() {
	var output = "";
	
	$.ajax({
		type: 'GET',
		url: BASE_URL + 'category/',
		dataType: 'text',
		success: function(data) {
			//console.log('getAllCategories: ' + data);
			
			var json_object = $.parseJSON(data);
			
			for (var count in json_object) {
				//console.log('getAllCategories: ' + json_object[count].categoryName);
				output += "<li><a href=\"../product/" + json_object[count].categoryId + "\" class=\"category-click\">"  + json_object[count].categoryName + "</a></li>";
			}
			
			$('#menu-dropdown').append(output);
		},
		error: function(data, textStatus, errorThrown) {
			alert("Error encountered while retrieving product categories. " + errorThrown);
		}
	});
}

/*
 * updateCartItemCount()
 * Updates cart item count badge
 */
function updateCartItemCount() {
	var output = "";
	$.ajax({
		type: 'GET',
		url: BASE_URL + 'cart/',
		dataType: 'text',
		success: function(data) {
			console.log('updateCartItemCount: ' + data);
			
			var json_object = $.parseJSON(data);			
			console.log('updateCartItemCount: ' + json_object.cartTotal);
			
			// Update the badge with the item count
			$('.badge').text(json_object.cartTotal);
		},
		error: function(data, textStatus, errorThrown) {
			alert("Error encountered while retrieving cart item count. " + errorThrown);
		}
	});	
}

/*
 * retrieveProductCategory()
 * Retrieves the product under the selected category
 */
function retrieveProductCategory(eventObject) {
	eventObject.preventDefault();
	//console.log('retrieeProductCategory: ' + eventObject);

	// Hide the jumbotron
	$('.jumbotron').hide();
	// Page header
	$('#product-page-header h1').text(eventObject.target.text);
	
	$.ajax( {
		type: 'GET',
		url: eventObject.target,
		dataType: 'text',
		success: processProductCategory,
		error: function(data, textStatus, errorThrown) {
			alert("Error encountered while product in category. " + errorThrown);
		}			
	});
	
}

/*
 * processProductCategory()
 * Generates the product listing rows after a successful AJAX call
 */
function processProductCategory(data) {
	//console.log('getProcessProductCategory: ' + data);
	var json_object = $.parseJSON(data);
	var output;
	
	// Add to product listing
	$('#product-page-list').empty();
	for (var count in json_object) {
		//console.log('getProcessProductCategory: ' + json_object[count].categoryName);
		output = "";
		output += "<div class=\"row\">" +
			"<div class=\"col-md-4\">" + "<img class=\"product-image\" src=\"" +  json_object[count].url  +"\"></div>" +
				"<div class=\"col-md-8\">" + 
					"<div class=\"row\" id=\"" + json_object[count].productId + "\">" +  
						"<div class=\"col-md-12\">" + "<span class=\"product-title\">" + json_object[count].name + "</span>" + "</div>"  + 
						"<div class=\"col-md-12\">" + "<span class=\"product-price\">$" + json_object[count].priceString + "</span>" + "</div>" +
						"<div class=\"col-md-12\">&nbsp;" + "</div>" +
						"<div class=\"col-md-3\">" + 
							"<div class=\"input-group\">" +								
								"<input type=\"text\" class=\"form-control\" value=\"1\">" +
								"<span class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-minus\"></span></button></span>" +
								"<span class=\"input-group-btn\"><button type=\"button\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-plus\"></span></button></span>" +								
							"</div>" +
						"</div>" +
						"<div class=\"col-md-6\"></div>" +
						"<div class=\"col-md-3\">" + 
							"<button id=\"btn-add-cart\" type=\"button\" class=\"btn btn-primary pull-right\">Add To Cart</button>" +
						"</div>" +
					"</div>" +
				"</div>" +
			"</div>";		
		$('#product-page-list').append(output);
	}	
}

/*
 * decrementQuantity()
 * Decrement the quantity value
 */
function decrementQuantity(eventObject) {
	var buttonClicked, quantityField, quantity;
	
	// Get the button that was clicked and it's corresponding input field
	buttonClicked = $(eventObject.target);
	quantityField = buttonClicked.closest('.input-group').find('input');
	
	quantity = parseInt(quantityField.attr('value'));
	if (!isNaN(quantity)) {
		// Decrease the quantity
		if (quantity == 1) {
			quantityField.attr('value', 20);
		}
		else {
			quantityField.attr('value', quantity - 1);
		}
	}		
}

/*
 * incrementQuantity()
 * Increment the quantity value
 */
function incrementQuantity(eventObject) {
	var buttonClicked, quantityField, quantity;
	
	// Get the button that was clicked and it's corresponding input field
	buttonClicked = $(eventObject.target);
	quantityField = buttonClicked.closest('.input-group').find('input');
	
	quantity = parseInt(quantityField.attr('value'));
	if (!isNaN(quantity)) {
		// Increase the quantity
		if (quantity == 20) {
			quantityField.attr('value', 1);
		}
		else {
			quantityField.attr('value', quantity + 1);
		}
	}
}

/*
 * addItemToCart()
 * Add the product to the cart
 */
function addItemToCart(eventObject) {
	var product = "";
	var buttonClicked, productId, quantityField, quantity;
	
	// Get the button that was clicked and it's corresponding input field
	buttonClicked = $(eventObject.target);
	//console.log(buttonClicked);
	
	// Get the product and quantity
	productId = buttonClicked.closest('.row');
	quantityField = productId.find('input');
	console.log('addItemToCart: Product ' + productId.attr('id') + ', Quantity ' + quantityField.attr('value'));
	
	product = "{\"productId\": " + productId.attr('id') + ", \"quantity\": " + quantityField.attr('value') + " }";
	console.log(product);
	
	$.ajax( {
		type: 'POST',
		url: BASE_URL + 'cart/',
		contentType: 'application/json; charset=UTF-8',
		data: product,
		dataType: "json",
		statusCode: {			
			200: function() {
				alert('Item added to cart');
			},
			400: function(response) {
				alert('Error encountered while adding item to the cart. ' + response.statusText);
			}			
		},
		complete: updateCartItemCount
	});	
	
}

