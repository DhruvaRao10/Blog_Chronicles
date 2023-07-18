
package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Returns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReturnsRepository  extends JpaRepository<Returns,Long> {

}