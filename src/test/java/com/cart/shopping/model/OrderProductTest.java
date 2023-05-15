package com.cart.shopping.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderProductTest {

    @Test
    void createOrder() {
        OrderProduct order = new OrderProduct();
        order.setDescription("Prueba");
        order.setLastUser("admin");
        Assertions.assertEquals("Prueba",order.getDescription());
        Assertions.assertEquals("admin",order.getLastUser());
    }
}
