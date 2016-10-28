package com.springcart.serializer;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.springcart.model.Cart;
import com.springcart.model.LineItem;
import com.springcart.model.MyProduct;

@SuppressWarnings("serial")
public class CartSerializer extends StdSerializer<Cart> {
	public CartSerializer() {
		this(null);
	}
	
	public CartSerializer(Class<Cart> t) {
		super(t);
	}

	@Override
	public void serialize(Cart arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
		arg1.writeStartObject();
		arg1.writeNumberField("cartId", arg0.getCartId());
		arg1.writeNumberField("cartTotal", arg0.getCartTotal());
		
		arg1.writeArrayFieldStart("lineItems");
		LineItem lineItem;
		MyProduct product = null;
		Iterator<LineItem> lineItemIterator;
		Iterator<MyProduct> productIterator;
		
		lineItemIterator = arg0.getLineItems().iterator();
		while (lineItemIterator.hasNext()) {
			lineItem = (LineItem) lineItemIterator.next();
			arg1.writeStartObject();			
			arg1.writeNumberField("productId", lineItem.getProductId());
			
			productIterator = MyProduct.allItems.iterator();
			while (productIterator.hasNext()) {
				product = (MyProduct) productIterator.next();
				if (product.getProductId() == lineItem.getProductId()) {
					break;
				}
			}
			if (product != null) {
				arg1.writeStringField("description", product.getName());
				arg1.writeStringField("priceString", product.getPriceString());
				arg1.writeStringField("url", product.getURL());
				arg1.writeNumberField("quantity", lineItem.getQuantity());
			}
			else {
				arg1.writeStringField("description", "Product description for id:" + lineItem.getProductId());
				arg1.writeStringField("priceString", "0.0");
				arg1.writeStringField("url", "Product url");
				arg1.writeNumberField("quantity", 0);
			}
						
			arg1.writeEndObject();
		}
		
		/*
		for (int i = 0; i <= 5; i++) {
			arg1.writeStartObject();			
			arg1.writeNumberField("productId", i);			
			//arg1.writeStringField("Description", "Product description");
			arg1.writeNumberField("quantity", i);
			arg1.writeEndObject();
		}
		*/		
		arg1.writeEndArray();
		
		arg1.writeEndObject();
	}
}
