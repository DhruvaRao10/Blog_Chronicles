package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart,Long> {

}
