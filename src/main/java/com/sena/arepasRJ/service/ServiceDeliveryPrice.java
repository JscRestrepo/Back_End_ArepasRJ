package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryDeliveryPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServiceDeliveryPrice {

    @Autowired
    private RepositoryDeliveryPrice deliveryRepository;

    @Transactional
    public void addDeliveryPrice(Long idDeliveryPrice, String department, String city,
                                 String deliveryAddress, BigDecimal price) {
        try {
            EntityDeliveryPrice deliveryPrice = new EntityDeliveryPrice(idDeliveryPrice, department,
                    city, deliveryAddress, price);
            deliveryRepository.save(deliveryPrice);

        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + " Error al acceder a la base de datos");

        }
    }

    @Transactional
    public List<EntityDeliveryPrice> getAllPrices() {
        return deliveryRepository.findAll();
    }


    @Transactional
    public void deleteDeliveryPrice(Long idDeliveryPrice) throws Exception {
        try {
            deliveryRepository.deleteById(idDeliveryPrice);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + "Error");
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }
}
