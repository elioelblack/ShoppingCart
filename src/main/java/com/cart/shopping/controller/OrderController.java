package com.cart.shopping.controller;

import com.cart.shopping.dto.OrderProductDTO;
import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderProductService orderService;

    @GetMapping
    public ResponseEntity<?> getAll() throws ApiRequestException {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws ApiRequestException {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws ApiRequestException {
        return new ResponseEntity<>(orderService.deleteById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id,@RequestBody OrderProductDTO order) throws ApiRequestException {
        return new ResponseEntity<>(orderService.updateById(id,order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrderProductDTO order) throws ApiRequestException{
        return new ResponseEntity<>(orderService.save(order),HttpStatus.OK);
    }

}
