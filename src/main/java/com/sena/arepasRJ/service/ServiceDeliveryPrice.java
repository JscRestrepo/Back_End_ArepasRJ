package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryDeliveryPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ServiceDeliveryPrice {

    @Autowired
    private RepositoryDeliveryPrice deliveryRepository;

    @Transactional
    public void addDeliveryPrice(Long idDeliveryPrice, String deliveryAddress, BigDecimal price) {
        try {
            EntityDeliveryPrice deliveryPrice = new EntityDeliveryPrice(idDeliveryPrice, deliveryAddress, price);
            deliveryRepository.save(deliveryPrice);

        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + " Error al acceder a la base de datos");

        }
    }
}
