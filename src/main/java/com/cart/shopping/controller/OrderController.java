package com.cart.shopping.controller;

import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll() throws ApiRequestException {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

}
