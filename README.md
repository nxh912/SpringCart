# SpringCart

## using Cross-Origin Resource Sharing (CORS)

```javascript
$.support.cors = true;
			$.ajax({
				url: "http://rest-service.guides.spring.io/greeting"
			}).then(function(data) {
			   alert( "DATA : " + JSON.stringify(data) );
			   
			   jsonCallback( static_result );
			   //jsonCallback( data );
			   
			   successful = true;
			   
			}).then(function (data) {
				if(typeof console === "undefined") {
					;
				} else {
					console.log("done! " + data);
				}
			});
```
<script>

</script>
