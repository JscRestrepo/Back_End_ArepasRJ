package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityBuy;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryBuy;
import com.sena.arepasRJ.repository.RepositoryUsers;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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


    /*
    Traer todas las compras para la lista de admin
     */
    @Autowired
    private ServiceBuy getBuy;

    @GetMapping("/get-buy")
    public ResponseEntity<List<EntityBuy>> getAllBuys() {
        try {
            List<EntityBuy> getAllBuys = getBuy.readBuy();
            return new ResponseEntity<>(getAllBuys, HttpStatus.OK);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + "Error al acceder a la base de datos. Contacte con el servidor");
        }
    }


    /*
    Traer las compras por el usuario
     */

    @Autowired
    private ServiceBuy getUserBuy;

    @GetMapping("/get-buy/{email}")
    public ResponseEntity<List<EntityBuy>> getBuyById(@PathVariable String email){
        try {
            List<EntityBuy> getBuy = getUserBuy.readUsersBuy(email);
            return new ResponseEntity<>(getBuy, HttpStatus.OK);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + "Error al acceder a la base de datos. Contacte con el servidor");
        }
    }
}
