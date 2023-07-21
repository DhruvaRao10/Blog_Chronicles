package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    //@Transactional
    //@Modifying
    //@Query("update Inventory i set i.item_qty = ?1")
    //int updateInventory(Long item_qty);

    void update(Inventory inventory);
}
