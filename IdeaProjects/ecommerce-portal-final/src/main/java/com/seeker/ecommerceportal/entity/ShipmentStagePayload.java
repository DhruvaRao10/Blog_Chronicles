
package com.seeker.ecommerceportal.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentStagePayload {
    private String stageName;

    private int stageOrder;

    private Long shipment_id;
}
