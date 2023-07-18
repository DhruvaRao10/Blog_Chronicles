package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPayload {
    private Long item_id;

    private Long item_price;
    private String item_name;
    private Long category_id;
}
