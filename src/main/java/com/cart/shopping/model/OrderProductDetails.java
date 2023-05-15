package com.cart.shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.criterion.Order;

import javax.persistence.*;
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
    @JoinColumn(name = "id_order",  nullable = false)
    @ManyToOne
    private OrderProduct idOrder;
    private Integer idProduct;
    private Integer quantity;
    private Double iva;
    private Double total;
    private Double price;
    private String lastUser;
    @Column(name = "date_created")
    private Date dateCreated;
}
