package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShipmentRepository  extends JpaRepository<Shipment,Long> {

}