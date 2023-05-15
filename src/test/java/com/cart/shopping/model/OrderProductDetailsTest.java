package com.cart.shopping.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderProductDetailsTest {
    @Test
    void createdDetail() {
        OrderProductDetails detail = new OrderProductDetails();
        detail.setIdOrder(new OrderProduct((1)));
        detail.setIdProduct(1);
        detail.setPrice(20.0);
        detail.setQuantity(2);
        detail.setIva(0.0);
        detail.setTotal(40.0);

        Assertions.assertEquals(40.0,detail.getTotal());
    }
}
