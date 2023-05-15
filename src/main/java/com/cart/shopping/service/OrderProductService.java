package com.cart.shopping.service;

import com.cart.shopping.dto.OrderProductDTO;
import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.model.OrderProduct;
import com.cart.shopping.repository.OrderProductRepository;
import com.cart.shopping.utils.Constants;
import com.cart.shopping.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    public final OrderProductRepository orderRepository;
    public final ProductService productService;

    public List<OrderProduct> getAllOrders() throws ApiRequestException{
        try {
            return orderRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED,e);
        }
    }

    public OrderProduct save(OrderProductDTO dto) throws ApiRequestException{
        try {
            ModelMapper modelMapper = new ModelMapper();
            OrderProduct order = modelMapper.map(dto, OrderProduct.class);

            return orderRepository.save(order);
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED,e);
        }
    }

    public OrderProduct findById(Integer id) throws ApiRequestException{
        try {
            Optional<OrderProduct> order = orderRepository.findById(id);
            if(!order.isPresent()){
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }else{
                return order.get();
            }

        }catch (Exception e){
            throw new ApiRequestException(e.getMessage(),e);
        }
    }

    public OrderProduct updateById(Integer id, OrderProductDTO dto) throws ApiRequestException{
        try {
            ModelMapper modelMapper = new ModelMapper();
            OrderProduct order = modelMapper.map(dto, OrderProduct.class);
            Optional<OrderProduct> optionalFromDataBase = orderRepository.findById(id);

            if (!optionalFromDataBase.isPresent()) {
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }

            OrderProduct modelFromDatabase = optionalFromDataBase.get();

            //Todo lo que viene en el json lo copio al extraido de la base, de esa manera
            //lo que venga null en el json no se sobreescribira.
            BeanUtils.copyProperties(order, modelFromDatabase, Utils.getNullPropertyNames(order));
            order =  orderRepository.save(modelFromDatabase);
            return order;

        }catch (Exception e){
            throw new ApiRequestException(e.getMessage(),e);
        }
    }

    public OrderProduct deleteById(Integer id) throws ApiRequestException{
        try {
            Optional<OrderProduct> order = orderRepository.findById(id);
            if(!order.isPresent()){
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }else{
                orderRepository.deleteById(id);
                return order.get();
            }

        }catch (Exception e){
            throw new ApiRequestException(e.getMessage(),e);
        }
    }

}
