package com.cart.shopping.service;

import com.cart.shopping.client.ProductFeignClient;
import com.cart.shopping.dto.Product;
import com.cart.shopping.exception.ApiException;
import com.cart.shopping.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductFeignClient client;

    public List<Product> getProducts() throws ApiRequestException{
        try {
            return client.getProducts();
        }catch (Exception e){
            throw new ApiRequestException("Oops cannot get all products");
        }
    }

    public Product getProductById(Integer id) throws ApiRequestException{
        try {
            return client.getProductById(id);
        }catch (Exception e){
            throw new ApiRequestException("Oops cannot get product");
        }
    }

}
