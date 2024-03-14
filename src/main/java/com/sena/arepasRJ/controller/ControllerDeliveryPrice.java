package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import com.sena.arepasRJ.repository.RepositoryDeliveryPrice;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceDeliveryPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class ControllerDeliveryPrice {

    @Autowired
    private ServiceDeliveryPrice addDeliveryPrice;

    @PostMapping("/domicilios/guardarPrecio")
    public ResponseEntity<?> saveDeliveryPrice(@RequestBody EntityDeliveryPrice deliveryPrice) {
        try {
            if (!addresExists(deliveryPrice)) {
                addDeliveryPrice.addDeliveryPrice(deliveryPrice.getIdDeliveryPrice(),
                        deliveryPrice.getDeliveryAddress(), deliveryPrice.getDeliveryPrice());

                Responses savedResponse = new Responses("Se agregó la dirección con su precio");
                return new ResponseEntity<>(savedResponse, HttpStatus.OK);

            } else {
                Responses addresExistsResponse = new Responses("Esta dirección ya fue registrada");
                return new ResponseEntity<>(addresExistsResponse, HttpStatus.CONFLICT);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    private RepositoryDeliveryPrice addressSearch;

    public boolean addresExists(EntityDeliveryPrice deliveryPrice) {
        String addressExists = deliveryPrice.getDeliveryAddress();
        String address = addressSearch.findAddresByDeliveryAddress(addressExists);

        return address != null;
    }

}
