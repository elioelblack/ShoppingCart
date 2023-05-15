package com.cart.shopping.json;

import com.cart.shopping.model.OrderProduct;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author eliezer
 *
 */
public class OrderSerialize extends JsonSerializer<OrderProduct> {
    @Override
    public void serialize(OrderProduct model, JsonGenerator jsonGenerator, SerializerProvider serializers)
            throws IOException {
        jsonGenerator.writeObject(model.getId());
    }

}