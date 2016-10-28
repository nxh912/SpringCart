/**
 * springcart.js
 * Contains all functionality related to SpringCart client functionality
 */

//var BASE_URL = "http://localhost:8080/SpringCart/";
var BASE_URL = "http://10.213.34.104:8080/SpringCart/";

var BASE_URL_GATEWAY = "http://10.213.34.104:8280/springcart/v1/";
var TOKEN_URL = "https://10.213.34.104:8243/token";

// This should be hidden from plain view
var CONSUMER_KEY = "Ze05nifQTo5fW7thq_rUts30t4ca";
var CONSUMER_SECRET = "vEdNd_MMqH_ghbi7KjH5ApUmvVAa";

// Base64 encoding is done using format {CONSUMER_KEY}:{CONSUMER_SECRET}
var BASE64_ENCODING = "WmUwNW5pZlFUbzVmVzd0aHFfclV0czMwdDRjYTp2RWROZF9NTXFIX2doYmk3S2pINUFwVW12VkFh";

var AUTHORISATION_TOKEN;
var USE_GATEWAY;

$(document).ready(function() {
	USE_GATEWAY = true;
	
	if (USE_GATEWAY == true) {	
		// Need to get the access token from the API gateway
		// If it fails, likely it is due to insecure certificate as the demo API gateway uses https and self signed certificate
		// Client browser needs to access the TOKEN_URL first (or add as an exception)
		$.ajax({
			async: true,
			type: 'POST',
			url: TOKEN_URL,
			contentType: 'application/x-www-form-urlencoded',
			dataType: 'text',
			data: { 'grant_type':'client_credentials' },
			headers: { 'authorization' : 'Basic ' + BASE64_ENCODING },
			success: function(data) {
				var json_object = $.parseJSON(data);
				//console.log(json_object);
				AUTHORISATION_TOKEN = json_object.access_token;
				console.log('Access token: ' + AUTHORISATION_TOKEN);
			},
			error: function(data, textStatus, errorThrown) {
				alert("Error encountered while getting access token from gateway. " + errorThrown);
			},
			complete: function() {
				getAllCategories();
				updateCartItemCount();
			}
		});		
	}
	else {
		getAllCategories();
		updateCartItemCount();		
	}
	
	// Event handler for the category dropdown
	$('#menu-dropdown').on('click', 'a', retrieveProductCategory);
	
	// Event handler for the cart
	$('#cart').on('click', viewCart);
	
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
	
	// The difference between using API Gateway and direct call is
	// 1. URL
	// 2. Headers need to include authorization token
	if (USE_GATEWAY == true) {
		$.ajax({
			type: 'GET',
			url: BASE_URL_GATEWAY + 'category/',
			dataType: 'text',
			headers: { 'authorization' : 'Bearer ' + AUTHORISATION_TOKEN },
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
				console.log(textStatus);
				console.log(data);
				alert("Gateway: Error encountered while retrieving product categories. " + errorThrown);
			}
		});		
	}
	else {
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
}

/*
 * updateCartItemCount()
 * Updates cart item count badge
 */
function updateCartItemCount() {
	var output = "";
	if (USE_GATEWAY == true) {
		$.ajax({
			type: 'GET',
			url: BASE_URL_GATEWAY + 'cart/',
			dataType: 'text',
			headers: { 'authorization' : 'Bearer ' + AUTHORISATION_TOKEN },
			success: function(data) {
				console.log('updateCartItemCount: ' + data);
				
				var json_object = $.parseJSON(data);			
				console.log('updateCartItemCount: ' + json_object.cartTotal);
				
				// Update the badge with the item count
				$('.badge').text(json_object.cartTotal);
			},
			error: function(data, textStatus, errorThrown) {
				alert("Gateway: Error encountered while retrieving cart item count. " + errorThrown);
			}
		});		
	}
	else {
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
				alert("Gateway: Error encountered while retrieving cart item count. " + errorThrown);
			}
		});	
	}
}

/*
 * retrieveProductCategory()
 * Retrieves the product under the selected category
 */
function retrieveProductCategory(eventObject) {
	eventObject.preventDefault();
	//console.log('retrieveProductCategory: ' + eventObject);

	// Hide the jumbotron
	$('.jumbotron').hide();
	// Page header
	$('#product-page-header h1').text(eventObject.target.text);

	if (USE_GATEWAY == true) {
		var url = eventObject.target.href;
		var gatewayURL = url.replace(BASE_URL, BASE_URL_GATEWAY);
		console.log('gatewayURL: ' + gatewayURL);
		
		$.ajax( {
			type: 'GET',
			url: gatewayURL,
			dataType: 'text',
			headers: { 'authorization' : 'Bearer ' + AUTHORISATION_TOKEN },
			success: processProductCategory,
			error: function(data, textStatus, errorThrown) {
				alert("Gateway: Error encountered while product in category. " + errorThrown);
			}			
		});
		
	}
	else {
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
	
	if (USE_GATEWAY == true) {
		$.ajax( {
			type: 'POST',
			url: BASE_URL_GATEWAY + 'cart/',
			contentType: 'application/json; charset=UTF-8',
			headers: { 'authorization' : 'Bearer ' + AUTHORISATION_TOKEN },
			data: product,
			dataType: "json",
			statusCode: {			
				200: function() {
					alert('Gateway: Item added to cart');
				},
				400: function(response) {
					alert('Gateway: Error encountered while adding item to the cart. ' + response.statusText);
				}			
			},
			complete: updateCartItemCount
		});		
	}
	else {
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
	
}

/*
 * viewCart()
 * View the contents of the cart
 */
function viewCart(eventObject) {
	eventObject.preventDefault();
	console.log('viewcart');
	// Hide the jumbotron
	$('.jumbotron').hide();
	// Page header
	$('#product-page-header h1').text('Cart');
	
	if (USE_GATEWAY) {
		$.ajax({
			type: 'GET',
			url: BASE_URL_GATEWAY + 'cart/',
			dataType: 'text',
			headers: { 'authorization' : 'Bearer ' + AUTHORISATION_TOKEN },
			success: processCartContent,
			error: function(data, textStatus, errorThrown) {
				alert("Gateway: Error encountered while retrieving cart contents. " + errorThrown);
			}
		});			
	}
	else {
		$.ajax({
			type: 'GET',
			url: BASE_URL + 'cart/',
			dataType: 'text',
			success: processCartContent,
			error: function(data, textStatus, errorThrown) {
				alert("Gateway: Error encountered while retrieving cart contents. " + errorThrown);
			}
		});		
	}	
}

/*
 * processCartContent()
 * Generates the cart content rows after a successful AJAX call
 */
function processCartContent(data) {
	console.log('processCartContent: ' + data);
	var json_object = $.parseJSON(data);
	var output;
	
	// Add to product listing
	$('#product-page-list').empty();
	console.log(json_object['lineItems']);
	
	for (var count in json_object['lineItems']) {
		//console.log(count);
		//console.log(json_object['lineItems'][count].url);
		output = "";
		
		output += "<div class=\"row\">" +
			"<div class=\"col-md-4\">" + "<img class=\"product-image\" src=\"" +  json_object['lineItems'][count].url  +"\"></div>" +
				"<div class=\"col-md-8\">" + 
					"<div class=\"row\" id=\"" + json_object['lineItems'][count].productId + "\">" +  
						"<div class=\"col-md-12\">" + "<span class=\"product-title\">" + json_object['lineItems'][count].description + "</span>" + "</div>"  + 
						"<div class=\"col-md-12\">" + "<span class=\"product-price\">$" + json_object['lineItems'][count].priceString + "</span>" + "</div>" +
						"<div class=\"col-md-12\">&nbsp;" + "</div>" +
						"<div class=\"col-md-2\">" + 
							"<div class=\"input-group\">" +								
								"<input type=\"text\" class=\"form-control\" value=\"" + json_object['lineItems'][count].quantity + "\" disabled>" +
							"</div>" +
						"</div>" +
					"</div>" +
				"</div>" +
			"</div>";	
		$('#product-page-list').append(output);
	}
	
}
