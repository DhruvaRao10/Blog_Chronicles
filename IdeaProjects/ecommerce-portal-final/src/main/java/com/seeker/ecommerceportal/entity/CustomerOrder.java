package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private java.time.Instant   orderDate;
    @ManyToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;
    private String shippingAddress;
}
