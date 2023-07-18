
package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seeker.ecommerceportal.entity.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item,Long> {

}
