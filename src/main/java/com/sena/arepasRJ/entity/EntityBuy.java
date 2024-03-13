package com.sena.arepasRJ.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_buy")
public class EntityBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuy;

    @OneToOne
    @JoinColumn(name = "buyId", referencedColumnName = "idCart")
    private EntityShoppingCart idCart;

    @OneToOne
    @JoinColumn(name = "deliveryId", referencedColumnName = "idDelivery")
    private EntityDelivery idDelivery;
}
