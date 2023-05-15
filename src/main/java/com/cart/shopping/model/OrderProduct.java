package com.cart.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    private Integer id;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "description")
    private String description;
    @Column(name = "print")
    private Date print;
    @Column(name = "last_user")
    private String lastUser;

}
