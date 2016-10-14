/**
 * springcart.js
 * Contains all functionality related to SpringCart client functionality
 */

//var BASE_URL = "http://localhost:8080/SpringCart/";
var BASE_URL = "http://10.213.34.101:8080/SpringCart/";

$(document).ready(function() {
	getAllCategories();	
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
			//alert(data);
			
			var json_object = $.parseJSON(data);
			
			for (var count in json_object) {
				//alert(json_object[count].categoryName);
				output += "<li><a href=\"#" + "\" class=\"category-click\">"  + json_object[count].categoryName + "</a></li>";
			}
			
			$('#menu-dropdown').append(output);
		},
		error: function(data, textStatus, errorThrown) {
			alert("Error encountered. " + errorThrown);
		}
	});
}