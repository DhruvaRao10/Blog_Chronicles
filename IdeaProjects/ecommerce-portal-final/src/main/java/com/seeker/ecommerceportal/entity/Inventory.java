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

public class Inventory {

    private Long item_qty ;

    @ManyToOne
    @JoinColumn(name= "item_id")
    @MapsId
    @Id
    private Item item ;



}
