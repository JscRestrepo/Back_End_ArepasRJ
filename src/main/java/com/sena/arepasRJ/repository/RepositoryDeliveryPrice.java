package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface RepositoryDeliveryPrice extends JpaRepository<EntityDeliveryPrice, Long> {

    public EntityDeliveryPrice findAddressByIdDeliveryPrice(Long idDeliveryPrice);

    public String findAddresByDeliveryAddress(String deliveryAddress);
}
