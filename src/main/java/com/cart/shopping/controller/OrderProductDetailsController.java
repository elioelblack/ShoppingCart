package com.cart.shopping.controller;

import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.service.OrderProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/details")
@RequiredArgsConstructor
public class OrderProductDetailsController {

    private final OrderProductDetailsService orderProductDetailsService;

    @GetMapping
    public ResponseEntity<?> getAll() throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.getAllDetails(), HttpStatus.OK);
    }
}
