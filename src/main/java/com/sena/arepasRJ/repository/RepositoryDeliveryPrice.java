package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDeliveryPrice extends JpaRepository<EntityDeliveryPrice, Long> {

    public EntityDeliveryPrice findAddressByIdDeliveryPrice(Long idDeliveryPrice);

    public String findAddresByDeliveryAddress(String deliveryAddress);
}
