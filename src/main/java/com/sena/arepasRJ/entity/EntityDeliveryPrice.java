package com.sena.arepasRJ.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_delivery_prices")
public class EntityDeliveryPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeliveryPrice;

    private String deliveryAddress;
    private BigDecimal deliveryPrice;
}
