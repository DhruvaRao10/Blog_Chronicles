
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
public class ShipmentStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentstage_id;

    private String stageName;

    private int stageOrder;
    @ManyToOne
    @JoinColumn(name= "shipment_id")
    private Shipment shipment;
}