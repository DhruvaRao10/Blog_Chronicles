package com.seeker.ecommerceportal.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.seeker.ecommerceportal.entity.eSTARS;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    private String review;

//    public enum eSTARS { ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5};

    private eSTARS reviewStars;

    @ManyToOne
    @JoinColumn(name= "item_Id")
    private Item item;
}