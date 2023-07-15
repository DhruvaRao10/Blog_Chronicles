package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingcart_id;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;
}