package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewsRepository  extends JpaRepository<Reviews,Long> {

}