package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

}
