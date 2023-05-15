package com.cart.shopping.service;

import com.cart.shopping.client.ProductFeignClient;
import com.cart.shopping.dto.ProductDTO;
import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductFeignClient client;

    public List<ProductDTO> getProducts() throws ApiRequestException{
        try {
            return client.getProducts();
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED);
        }
    }

    public ProductDTO getProductById(Integer id) throws ApiRequestException{
        try {
            return client.getProductById(id);
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED);
        }
    }

}
