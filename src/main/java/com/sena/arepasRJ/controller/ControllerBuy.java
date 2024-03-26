package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityBuy;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryBuy;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerBuy {

    @Autowired
    private ServiceBuy saveBuy;

    @PostMapping("/buy")
    public ResponseEntity<?> executeBuy(@RequestBody EntityBuy setBuy) {
        try {
            saveBuy.saveProducts(setBuy);

            Responses response = new Responses("Se guardó el pedido con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + "Ocurrió un error al conectar a la base de datos");
        }
    }
}
