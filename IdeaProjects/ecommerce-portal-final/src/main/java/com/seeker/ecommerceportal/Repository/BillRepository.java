package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository  extends JpaRepository<Bill,Long> {

}

