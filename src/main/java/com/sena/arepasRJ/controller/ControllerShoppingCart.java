package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityDelivery;
import com.sena.arepasRJ.entity.EntityShoppingCart;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class ControllerShoppingCart {

    @Autowired
    private ServiceShoppingCart cartService;

    /*
    Método para agregar productos al carrito
     */

    @PostMapping("/addCart/{idProduct}/{quantityProducts}/{idDeliveryPrice}")
    public ResponseEntity<?> saveToCart (@PathVariable Long idProduct, @PathVariable int quantityProducts, @PathVariable Long idDeliveryPrice) {
        try {
            cartService.addProductsToCart(idProduct, quantityProducts, idDeliveryPrice);
            Responses addedResponse = new Responses("Se agregó el producto al carrito");
            return new ResponseEntity<>(addedResponse, HttpStatus.OK);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe.getMessage() + "No se pudo conectar a la base de datos");
        }
    }

    /*
    Método para completar los datos de la venta
     */

    @DeleteMapping("/deleteFromCart/{idProduct}")
    public ResponseEntity<?> deleteFromCart (@PathVariable Long idProduct) {
        try {
            cartService.deleteFromCart(idProduct);
            Responses deleteResponse = new Responses("Producto eliminado del carrito");
            return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe.getMessage() + "Error al acceder a la base de datos");
        }
    }

    @GetMapping("/seeUserCart")
    public ResponseEntity<EntityShoppingCart> seeShoppingCart() {
        try {
            EntityShoppingCart userCart = cartService.getCar();
            return ResponseEntity.ok(userCart);

        } catch (UsernameNotFoundException ue) {
            throw new PersonalExceptions("El usuario especificado no existe " + ue);

        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions("El carrito buscado no existe " + pe);

        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/shoppingPage")
    public ResponseEntity<?> userProductPage() {
        try {
            EntityShoppingCart userCart = cartService.getCar();
            return ResponseEntity.ok(userCart);
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions(pe + "Error al acceder a la base de datos");
        }
    }
}
