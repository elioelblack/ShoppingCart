package com.cart.shopping.service;

import com.cart.shopping.client.ProductFeignClient;
import com.cart.shopping.dto.OrderProductDetailsDTO;
import com.cart.shopping.dto.ProductDTO;
import com.cart.shopping.exception.ApiRequestException;
import com.cart.shopping.model.OrderProductDetails;
import com.cart.shopping.repository.OrderProductDetailsRepository;
import com.cart.shopping.utils.Constants;
import com.cart.shopping.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductDetailsService {

    private final OrderProductDetailsRepository orderProductDetailsRepository;
    private final ProductFeignClient client;

    public List<OrderProductDetails> getAllDetails() throws ApiRequestException{
        try {
            List<OrderProductDetails> results = orderProductDetailsRepository.findAll();
            results.forEach(
                    e->{
                        e.setProductSpecification(client.getProductById(e.getIdProduct()));
                    }
            );
            return orderProductDetailsRepository.findAll();
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED);
        }
    }

    public OrderProductDetails getDetailsById(Integer id) throws ApiRequestException{
        try {
            Optional<OrderProductDetails> details = orderProductDetailsRepository.findById(id);
            if(!details.isPresent()){
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }else{
                ProductDTO productDTO = client.getProductById(details.get().getIdProduct());
                details.get().setProductSpecification(productDTO);
                return details.get();
            }
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public OrderProductDetails deleteById(Integer id) throws ApiRequestException{
        try {
            Optional<OrderProductDetails> details = orderProductDetailsRepository.findById(id);
            if(!details.isPresent()){
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }else{
                orderProductDetailsRepository.deleteById(id);
                return details.get();
            }
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<OrderProductDetails> saveDetails(List<OrderProductDetailsDTO> details) throws ApiRequestException{
        try {
            List<OrderProductDetails> detailsToSave = new ArrayList<>();
            details.forEach(
                    e->{
                        ModelMapper modelMapper = new ModelMapper();
                        OrderProductDetails detail = modelMapper.map(e, OrderProductDetails.class);
                        detailsToSave.add(detail);
                    }
            );
            return orderProductDetailsRepository.saveAll(detailsToSave);
        }catch (Exception e){
            throw new ApiRequestException(Constants.MESSAGE_ERROR_OCCURRED);
        }
    }

    public OrderProductDetails updateDetail(Integer id, OrderProductDetailsDTO dto) throws ApiRequestException{
        try {
            ModelMapper modelMapper = new ModelMapper();
            OrderProductDetails detail = modelMapper.map(dto, OrderProductDetails.class);
            Optional<OrderProductDetails> optionalFromDataBase = orderProductDetailsRepository.findById(id);

            if (!optionalFromDataBase.isPresent()) {
                throw new ApiRequestException(Constants.MESSAGE_RECORD_NOT_FOUND);
            }

            OrderProductDetails modelFromDatabase = optionalFromDataBase.get();
            BeanUtils.copyProperties(detail, modelFromDatabase, Utils.getNullPropertyNames(detail));
            detail =  orderProductDetailsRepository.save(modelFromDatabase);
            return detail;
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
