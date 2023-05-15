package com.cart.shopping.controller;

import com.cart.shopping.dto.OrderProductDetailsDTO;
import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.service.OrderProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order/details")
@RequiredArgsConstructor
public class OrderProductDetailsController {

    private final OrderProductDetailsService orderProductDetailsService;

    @GetMapping
    public ResponseEntity<?> getAll() throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.getAllDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.getDetailsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody List<OrderProductDetailsDTO> details) throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.saveDetails(details), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable Integer id, @Valid @RequestBody OrderProductDetailsDTO detail) throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.updateDetail(id,detail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ApiRequestException {
        return new ResponseEntity<>(orderProductDetailsService.deleteById(id), HttpStatus.OK);
    }
}
