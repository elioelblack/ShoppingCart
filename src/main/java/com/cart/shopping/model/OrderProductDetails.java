package com.cart.shopping.model;

import com.cart.shopping.dto.ProductDTO;
import com.cart.shopping.json.OrderDeserialize;
import com.cart.shopping.json.OrderSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_product_details")
public class OrderProductDetails {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name = "id")
    private Integer id;
    @JsonDeserialize(using = OrderDeserialize.class)
    @JsonSerialize(using = OrderSerialize.class)
    @JoinColumn(name = "id_order")
    @ManyToOne
    private OrderProduct idOrder;
    @NotNull
    private Integer idProduct;
    @NotNull
    private Integer quantity;
    private Double iva;
    @NotNull
    private Double total;
    @NotNull
    private Double price;
    private String lastUser;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;
    @Transient
    private ProductDTO productSpecification;
}
