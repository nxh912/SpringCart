<!--  JSON: /SpringCart/product/ -->

<!DOCTYPE html>
<html>
<head>
  <title>Shopping Cart using Bootstrap </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  body {
      position: relative;
  }
  .affix {
      top:0;
      width: 100%;
      z-index: 9999 !important;
  }
  .navbar {
      margin-bottom: 0px;
  }

  .affix ~ .container-fluid {
     position: relative;
     top: 50px;
  }
  #section_music {padding-top:50px;color: #fff; background-color: #1E88E5;}
  #section_grocery {padding-top:50px;height:500px;color: #fff; background-color: #673ab7;}
  #section_electronics {padding-top:50px;height:500px;color: #fff; background-color: #ff9800;}
  #section_electronics_pc {padding-top:50px;height:500px;color: #fff; background-color: #00bcd4;}
  #section_electronics_home {padding-top:50px;height:500px;color: #fff; background-color: #009688;}
  #section_checkout {padding-top:50px;height:500px;color: #fff; background-color: #1E88E5;}
  
  </style>
</head>
<body onload="LoadedFuntion();" data-spy="scroll" data-target=".navbar" data-offset="50">

<div class="container-fluid" style="background-color:#F44336;color:#fff;height:200px;">
  <h1>Shopping Cart</h1>
  <!-- <h3>Fixed navbar on scroll</h3> 
  <p>Scroll this page to see how the navbar behaves with data-spy="affix" and data-spy="scrollspy".</p>
  <p>The navbar is attached to the top of the page after you have scrolled a specified amount of pixels, and the links in the navbar are automatically updated based on scroll position.</p>
  -->
</div>

<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
  <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Shopping</a>
    </div>
    <div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li>
            <a href="#section_music">Music</a>
          </li>
          <li><a href="#section_grocery">Grocery</a></li>
          <!-- <li><a href="#section_electronics">Electronics</a></li> -->
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Electronics<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#section_electronics_pc">Computer Related</a></li>
              <li><a href="#section_electronics_home">Home Appliances</a></li>
            </ul>
          </li>
          

        </ul>
        
        <ul class="nav navbar-nav navbar-right">
        
        
          <li>

             <a href=""  ondrop="drop(event)" ondragover="allowDrop(event)">
             
             
                     
             
             	<span class="glyphicon glyphicon-shopping-cart" ></span>
	            Cart
	            <span id='badge_count' class="badge">0</span>
             </a>
          
				
          </li>
          <li>

             <a href="javascript:alert('check out');">
             
             	<span class="glyphicon glyphicon glyphicon-credit-card" data-toggle="modal" data-target="#myModal"></span>
	            Checkout

             </a>
          
				
          </li>
          
        </ul>
    
        
      </div>
    </div>
  </div>
</nav>

<div id="CategoriesContainer" class="container-fluid">
</div>

<div id="section_music" class="container-fluid">
  <h1>Music</h1>

	<ul class="thumbnails">
	
	  
	  <li class="span4">
	    <a href="#" class="thumbnail">	      
	      <img id="image1" class="img-rounded" draggable="true" ondragstart="drag(event)" 
	        src="https://images-na.ssl-images-amazon.com/images/I/51uAySJJF%2BL._PJStripe-Prime-Only-500px,TopLeft,0,0._AC_SY200_.jpg" alt=""> 
	    </a>
	  </li>
	  <li class="span4">
	    <a href="#" class="thumbnail">
	      <img id="image2" class="img-rounded" draggable="true" ondragstart="drag(event)" 
	        src="https://images-na.ssl-images-amazon.com/images/I/51uAySJJF%2BL._PJStripe-Prime-Only-500px,TopLeft,0,0._AC_SY200_.jpg" alt="">
	    </a>
	  </li>
	  <li class="span4">
	    <a href="#" class="thumbnail">
	      <img id="image3" class="img-rounded" draggable="true" ondragstart="drag(event)" 
	       src="https://images-na.ssl-images-amazon.com/images/I/51uAySJJF%2BL._PJStripe-Prime-Only-500px,TopLeft,0,0._AC_SY200_.jpg" alt="">
	    </a>
	  </li>
	  <li class="span4">
	    <a href="#" class="thumbnail">
	      <img data-src="holder.js/300x200" alt="">
	    </a>
	  </li>
	</ul>

            <ul id="productList">
               <li>item #1...</li>
               <li>item #2...</li>
               <!--  <img class="img-responsive" src="img_chania.jpg" alt="Chania" width="460" height="345"> -->
               
            </ul>

  <p>Try to scroll this x`and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
</div>
<div id="section_grocery" class="container-fluid">
  <h1>Grocery</h1>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
</div>
<div id="section_electronics" class="container-fluid">
  <h1>Electronics</h1>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
</div>
<div id="section_electronics_pc" class="container-fluid">
  <h1>Computer Related</h1>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
</div>
<div id="section_electronics_home" class="container-fluid">
  <h1>Home Appliances</h1>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
  <p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>
</div>

<div id="section_checkout" class="container-fluid">
  <h1>checkout</h1>
  <!--   <p>Checkout</p> -->
  items:
</div>

<script>
// using Cross-Origin Resource Sharing (CORS) 

function handler( data )  {
    console.log(data.type);
}

function createCORSRequest(method, url) {
  var xhr = new XMLHttpRequest();
  if ("withCredentials" in xhr) {

    // Check if the XMLHttpRequest object has a "withCredentials" property.
    // "withCredentials" only exists on XMLHTTPRequest2 objects.
    xhr.open(method, url, true);

  } else if (typeof XDomainRequest != "undefined") {

    // Otherwise, check if XDomainRequest.
    // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    xhr = new XDomainRequest();
    xhr.open(method, url);

  } else {

    // Otherwise, CORS is not supported by the browser.
    xhr = null;

  }
  return xhr;
}

function LoadedFuntion() {
	///////////////////////////////////////
	if (typeof console === "undefined") {
		alert("undefined console");
	} else {
		//console.log("submit");
	}
	// TextPart
          			
	var successful = false;
	var allCategories = null;

	// display cart
	$.support.cors = true;
	
	<!-- category http://localhost:8080/SpringCart/category/ -->
	$.ajax({

		url: "/SpringCart/category/"

	}).then(function(categories) {
		//alert("1: categories : " + categories);
		allCategories = categories;
		// jsonCallback( data );
	    // alert("categories : "  + allCategories );
	}).then(function(data) {

		$.ajax({
			url: "/SpringCart/product/"
		}).then(function(allProducts) {
			alert("categories : " + allCategories + "\n\nproducts : " + allProducts);
			
			displayProducts(allProducts,allCategories);
			successful = true;
		});
	
	});
			
	///////////////////////////////////////


	
	$.support.cors = true;
	$.ajax({

		url: "/SpringCart/product/"

	}).then(function(data) {

		jsonCallback( data );
		
		successful = true;
	   
	}).then(function (data) {
		//alert(data);
		/*
		if(typeof console === "undefined") {
			;
		} else {
			console.log("done! " + data);
		}
		*/
	});
}

function displayProducts(allProducts,allCategories) {
	// console.log("displayProducts( '" + products +  "','" + allCategories + "');");
	// console.log( products );
	// console.log( typeof products );
	
	if (typeof allProducts == "string" ) {
		allProducts = JSON.parse(allProducts);
	}
	
	if (typeof allCategories == "string" ) {
		allCategories = JSON.parse(allCategories);
	}
	
	// console.log( allCategories );
	for ( var c in allCategories) {
		console.log("Category:" + allCategories[c]);
	}
	
	for (i in allCategories) {
		var type = allCategories[i];
		console.log("TYPE");
		console.log("TYPE");
		console.log("TYPE:" + type);
		console.log("TYPE");
		console.log("TYPE");
		for ( var p in allProducts.products) {
			if (type == allProducts.products[p].ProductType) {
				console.log("Product:" + JSON.stringify( allProducts.products[p]) ) ;				
			//} else {
			//	console.log("\n\n\nUNKNOWMN:" + JSON.stringify( allProducts.products[p] ) ) ;				
			}
		}
	}
	//console.log( allProducts );
  // display all products in <CategoriesContainer>
	// CategoriesContainer

}


function jsonCallback( data ) {
	var json = null;
	if ((typeof data) === "string") {
		//console.log( "TYPE:" + (typeof data) );
		//console.log( "DATA:" + data );		
		json = JSON.parse(data);
		//console.log( json );
		//console.log( json.products );
		

	    var l = $("#productList")[0];
    
        //console.log(  1  ); 
      
        //console.log(   $('ul li')  ); 
		
		for (var i in json.products ) {
			// console.log( "products["+i+"] : " + JSON.stringify( json.products[i] ) );
	//		console.log( JSON.stringify( json.products[i].ProductName ) );
	//		console.log( JSON.stringify( json.products[i].ProductURL ) );
			// ProductURL
		}
		
	}
	
}

function drag(event) {
    console.log("drag(...);");	
    console.log(event);	
    
    event.dataTransfer.setData("text", event.target.id);
} 

function drop(ev) {
	
    console.log(ev);
    ev.preventDefault();
    
    var imageid = ev.dataTransfer.getData("text");

    console.log("dragged image id : " + imageid);
    
    console.log( $("#badge_count")[0].innerHTML );
    var count = parseInt( $("#badge_count")[0].innerHTML );
    
    $("#badge_count")[0].innerHTML = (count + 1);
    
    console.log( $("#badge_count")[0].innerHTML );
    //badge_count
    //ev.target.appendChild(document.getElementById(data));
        
}

function allowDrop(event) {
    //console.log("allowDrop(...);");	
    //alert("allowDrop");
	event.preventDefault();
}

</script>
</body>
</html>

