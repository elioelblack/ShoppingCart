package com.cart.shopping.service;

import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.model.OrderProduct;
import com.cart.shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

    public List<OrderProduct> getAllOrders() throws ApiRequestException{
        try {
            return orderRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException("Oops cannot get all orders",e);
        }
    }

}
