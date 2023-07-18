package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {

}

