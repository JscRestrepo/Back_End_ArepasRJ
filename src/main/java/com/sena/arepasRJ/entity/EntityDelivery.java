package com.sena.arepasRJ.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_delivery")
public class EntityDelivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    private BigDecimal subtotalPrice;
    private BigDecimal totalPrice;
    private String observations;
    private String deliveryStatus;

    public EntityDelivery(BigDecimal subtotalPrice, BigDecimal totalPrice) {
        this.subtotalPrice = subtotalPrice;
        this.totalPrice = totalPrice;
    }
}
