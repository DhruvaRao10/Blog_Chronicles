package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem,Long> {

}