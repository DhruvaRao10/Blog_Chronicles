package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerOrderRepository  extends JpaRepository<CustomerOrder,Long> {
    @Query("select s from CustomerOrder s  where s.customer.customer_id = ?1")
    List<CustomerOrder> getAllOrdersForCustomer(Long customerId);
}