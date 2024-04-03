package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityDelivery;
import com.sena.arepasRJ.entity.EntityDeliveryPrice;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryDeliveryPrice;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceDeliveryPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class ControllerDeliveryPrice {

    /*
    ...............................................................................................
    MÉTODO PARA AGREGAR NUEVOS PRECIOS CON SUS RESPECTIVAS ZONAS
     */

    @Autowired
    private ServiceDeliveryPrice addDeliveryPrice;

    @PostMapping("/delivery/save")
    public ResponseEntity<?> saveDeliveryPrice(@RequestBody EntityDeliveryPrice deliveryPrice) {
        try {
            if (!addresExists(deliveryPrice)) {
                addDeliveryPrice.addDeliveryPrice(deliveryPrice.getIdDeliveryPrice(),
                        deliveryPrice.getDepartment(), deliveryPrice.getCity(),
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

    /*
    .........................................................................................
    MÉTODO PARA MODIFICAR LAS ZONAS O LOS PRECIOS SOLICITADOS
     */

    @Autowired
    private RepositoryDeliveryPrice searchAddressUpdate;

    @Autowired
    private ServiceDeliveryPrice addressService;

    @PutMapping("/delivery/update/{idDelivery}")
    public ResponseEntity<?> updateDeliveryPrice(
            @PathVariable Long idDelivery, @RequestBody EntityDeliveryPrice updatedDomicilio) {

        try {
            Optional<EntityDeliveryPrice> existingDomicilio = searchAddressUpdate.findById(idDelivery);

            if (existingDomicilio.isPresent()) {
                EntityDeliveryPrice deliveryPriceUpdate = existingDomicilio.get();

                deliveryPriceUpdate.setDeliveryPrice(updatedDomicilio.getDeliveryPrice());
                deliveryPriceUpdate.setCity(updatedDomicilio.getCity()); // Actualiza la ciudad si la recibe
                deliveryPriceUpdate.setDeliveryAddress(updatedDomicilio.getDeliveryAddress()); // Actualiza la dirección si la recibe
                deliveryPriceUpdate.setDepartment(updatedDomicilio.getDepartment()); // Actualiza el departamento si lo recibe

                searchAddressUpdate.save(deliveryPriceUpdate);

                Responses responseUpdate = new Responses("Se actualizó el precio del domicilio con éxito");
                return new ResponseEntity<>(responseUpdate, HttpStatus.OK);
            } else {
                Responses responseUpdate = new Responses("El domicilio no existe");
                return new ResponseEntity<>(responseUpdate, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    ......................................................................................
    MÉTODO PARA ELIMINAR ALGUNA ZONA CON SUS PRODUCTOS
     */


    @Autowired
    private RepositoryDeliveryPrice searchAddressDelete;

    @Autowired
    private ServiceDeliveryPrice deleteProducts;

    @DeleteMapping("/delivery/delete/{idDeliveryPrice}")
    public ResponseEntity<?> deleteDeliveryPrice(@PathVariable Long idDeliveryPrice) throws Exception {
        EntityDeliveryPrice getAddress = searchAddressDelete.findAddressByIdDeliveryPrice(idDeliveryPrice);

        if (getAddress != null) {

            deleteProducts.deleteDeliveryPrice(idDeliveryPrice);
            Responses savedResponse = new Responses("Se eliminó la dirección con su precio");
            return new ResponseEntity<>(savedResponse, HttpStatus.OK);

        } else {
            Responses savedResponse = new Responses("La dirección buscada no existe");
            return new ResponseEntity<>(savedResponse, HttpStatus.NOT_FOUND);
        }
    }

    /*
    ................................................................................................
    MÉTODO PARA VER TODAS LAS ZONAS CON SUS RESPECTIVOS PRECIOS
     */

    @Autowired
    private RepositoryDeliveryPrice searchAddressGet;

    @GetMapping("/delivery/getAll")
    public ResponseEntity<List<EntityDeliveryPrice>> getAllAddress() {
        List<EntityDeliveryPrice> getAddress = searchAddressGet.findAll();
        return new ResponseEntity<>(getAddress, HttpStatus.OK);
    }

}



 /*





@Autowired
    private RepositoryDeliveryPrice searchAddressUpdate;

    @Autowired
    private ServiceDeliveryPrice addressService;



    @PutMapping("/delivery/update/{idDelivery}")
    public ResponseEntity<?> updateDeliveryPrice(
            @PathVariable Long idDelivery, @RequestBody EntityDeliveryPrice deliveryPrice) {

        try {
            Optional<EntityDeliveryPrice> existingProducts = searchAddressUpdate.findById(idDelivery);

            if (existingProducts.isPresent()) {
                EntityDeliveryPrice deliveryPriceUpdate = existingProducts.get();

                deliveryPriceUpdate.setDepartment(deliveryPrice.getDepartment());
                deliveryPriceUpdate.setCity(deliveryPrice.getCity());
                deliveryPriceUpdate.setDeliveryAddress(deliveryPrice.getDeliveryAddress());
                deliveryPriceUpdate.setDeliveryPrice(deliveryPrice.getDeliveryPrice());

                searchAddressUpdate.save(deliveryPriceUpdate);

                Responses responseUpdate = new Responses("Se actualizó la solicitud con éxito");
                return new ResponseEntity<>(responseUpdate, HttpStatus.OK);
            } else {

                Responses responseUpdate = new Responses("La dirección no existe");
                return new ResponseEntity<>(responseUpdate, HttpStatus.NOT_FOUND);

            }

        } catch (PersonalExceptions pe) {

            return new ResponseEntity<>(pe, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            return new ResponseEntity<>("Error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }*/