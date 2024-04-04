package com.sena.arepasRJ.entity;

import com.sena.arepasRJ.components.ProductsBuy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    private String name;
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "entity_buy_products", joinColumns = @JoinColumn(name = "id_buy"))
    private List<ProductsBuy> products;

    private BigDecimal totalPayment;
    private String department;
    private String city;
    private String commune;
    private String address;
    private String orderStatus;
    private String email;
}
