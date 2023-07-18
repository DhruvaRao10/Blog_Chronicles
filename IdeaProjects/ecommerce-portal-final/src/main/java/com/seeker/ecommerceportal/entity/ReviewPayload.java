package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.seeker.ecommerceportal.entity.Reviews;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPayload {
    private Long item_id;
    private eSTARS stars;

    private String reviewDesc;
}