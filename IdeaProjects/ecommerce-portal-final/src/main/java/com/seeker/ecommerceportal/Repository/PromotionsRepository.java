package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PromotionsRepository  extends JpaRepository<Promotions,Long> {

}