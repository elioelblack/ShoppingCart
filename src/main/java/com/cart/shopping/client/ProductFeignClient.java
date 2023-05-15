package com.cart.shopping.client;

import com.cart.shopping.dto.Product;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ProductClient", url = "${fakestoreapi}")
public interface ProductFeignClient {

    @GetMapping(value = "/products",headers = {HttpHeaders.USER_AGENT + "=Mozilla/5.0"})
    List<Product> getProducts();

    @GetMapping(value = "/products/{id}")
    Product getProductById(@PathVariable Integer id);

}
