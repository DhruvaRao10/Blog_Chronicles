package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.seeker.ecommerceportal.entity.ShoppingCartItemPayload;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartPayload {
    private Long customer_id;
    private List<ShoppingCartItemPayload> shoppingCartItems;

}



