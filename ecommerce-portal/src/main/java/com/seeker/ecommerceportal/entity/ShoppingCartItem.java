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
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartItemId;

    private Long itemQty;
    @ManyToOne
    @JoinColumn(name= "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name= "shoppingcartitem_id")
    private ShoppingCartItem shoppingCartItem;
}
