package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionPayload {
    private Long itemId;
    private int percentage;
}