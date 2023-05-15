package com.cart.shopping.service;

import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.model.OrderProductDetails;
import com.cart.shopping.repository.OrderProductDetailsRepository;
import com.cart.shopping.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductDetailsService {

    private final OrderProductDetailsRepository orderProductDetailsRepository;

    public List<OrderProductDetails> getAllDetails() throws ApiRequestException{
        try {
            return orderProductDetailsRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED);
        }
    }
}
