
package com.seeker.ecommerceportal.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmenttrack_id;

    @ManyToOne
    @JoinColumn(name= "shipmentstage_id")
    private ShipmentStage currentStageOfShipment;

}