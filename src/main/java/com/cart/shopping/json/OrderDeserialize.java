package com.cart.shopping.json;

import com.cart.shopping.model.OrderProduct;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author eliezer
 *
 */
public class OrderDeserialize extends JsonDeserializer<OrderProduct> {
    @Override
    public OrderProduct deserialize(JsonParser jp, DeserializationContext arg1)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if(node.asText()==null || node.asText().equalsIgnoreCase("")) {
            return null;
        }
        return new OrderProduct(Integer.valueOf((node.asText())));
    }
}
