package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository  extends JpaRepository<CustomerOrder,Long> {

}