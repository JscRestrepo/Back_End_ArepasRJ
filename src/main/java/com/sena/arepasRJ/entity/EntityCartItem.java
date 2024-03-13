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
public class EntityCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private EntityProducts product;

    private int quantity;

    private BigDecimal unityPrice;

    private BigDecimal subtotalPrice;

    private BigDecimal shipmentPrice;
}
