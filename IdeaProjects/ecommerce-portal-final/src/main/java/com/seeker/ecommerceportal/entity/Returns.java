package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long return_id;

    @ManyToOne
    @JoinColumn(name= "orderitem_Id")
    private OrderItem orderItem;
}