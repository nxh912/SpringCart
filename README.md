# SpringCart

## using Cross-Origin Resource Sharing (CORS)

### loading JSON file (example: http://rest-service.guides.spring.io/greeting) ###

```javascript
      /**********/
      $.support.cors = true;
      /**********/
      
			$.ajax({
      
				url: "http://rest-service.guides.spring.io/greeting"
        
			}).then(function(data) {
        // SUCCESS FUNCTION
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
