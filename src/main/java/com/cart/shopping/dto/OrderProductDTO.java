package com.cart.shopping.dto;

import com.cart.shopping.model.OrderProductDetails;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class OrderProductDTO {

    private Integer id;
    private Date dateCreated;
    @NotNull(message = "Description cannot be null")
    private String description;
    private Date print;
    private String lastUser;
    private Integer state;
    @NotNull(message = "Details cannot be null")
    private List<OrderProductDetails> details;
}
