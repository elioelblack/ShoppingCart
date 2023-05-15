package com.cart.shopping.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderProductDetailsDTO {
    private Integer id;
    @NotNull
    private Integer idOrder;
    @NotNull
    private Integer idProduct;
    @NotNull
    private Integer quantity;
    private Double iva;
    private Double total;
    @NotNull
    private Double price;
    private String lastUser;
    private Date dateCreated;
}
