package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.*;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ServiceShoppingCart {

    //...........................................................................................
    //                          Atributos generales del código

    @Autowired
    private RepositoryShoppingCart cartReposiroty;

    @Autowired
    private RepositoryUsers searchUser;

    private String getAuthUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    //......................................................................................
    //                          Método para crear el carrito

    @Transactional
    public EntityShoppingCart getCar() {

        //Se obtiene el correo desde la clase RequestFilter y se valida su existencia para asignarle el carrito
        String username = getAuthUserEmail();
        EntityUsers user = searchUser.findByEmail(username);

        //En caso de no existir, se devuelve el mensaje de error. Si existe, se devuelve el carrito
        if (user == null) {
            throw new UsernameNotFoundException("El usuario no fue encontrado");
        }

        //Se retorna el método con el carrito
        return user.getCart();
    }

    /*
    Anotación importante:
    getCart: Método getter creado en la entidad shoppingCart para almacenar los nuevos carritos
    getCar: Método creado en el servicio para manipular el contenido del carrito
     */

    //..........................................................................................
    //                          Método para agregar productos al carrito

    @Autowired
    private RepositoryProducts searchProduct;

    @Autowired
    private RepositoryDeliveryPrice shipments;

    @Autowired
    private RepositoryCartItems repositoryItems;

    @Transactional
    public void addProductsToCart(Long idProduct, int quantityProducts, Long idDeliveryPrice) {
        EntityProducts product = searchProduct.findByIdProduct(idProduct);
        EntityDeliveryPrice shipmentsPrice = shipments.findAddressByIdDeliveryPrice(idDeliveryPrice);

        if (product == null) { //Validar existencia del producto
            throw new PersonalExceptions("El producto no pudo ser encontrado");
        }

        if (shipmentsPrice == null) {
            throw new PersonalExceptions("La ubicación solicitada no existe");
        }

        BigDecimal shipment = shipmentsPrice.getDeliveryPrice(); //Tomar el precio del domicilio

        EntityShoppingCart cart = getCar(); //Tomar el método para manipular el carrito y enviar el producto

        //En caso de que el carrito buscado no exista, se crea un nuevo carrito para el usuario
        if (cart == null) {

            cart = new EntityShoppingCart();
            EntityUsers userExists = searchUser.findByEmail(getAuthUserEmail());

            userExists.setCart(cart); //Se asigna un carrito al usuario existente
            cart.setUser(userExists); //Se asigna ese mismo usuario al carrito anterior

            /*
            En estas dos líneas se genera una conexión mutua para permitir que un único carrito
            esté asociado a un único cliente y el token pueda hacer la validación de forma
            indirecta
             */
        }

        boolean productFound = false;

        //Se itera sobre los productos del carrito para buscar su cantidad actual y sumarle lo que se solicita
        for (EntityCartItem cartProducts : cart.getProductsShoppingCart()) { //Iteración de los productos sobre el carrito
            if (cartProducts.getProduct().getIdProduct().equals(idProduct)) {

                cartProducts.setQuantity(cartProducts.getQuantity() + quantityProducts); //En caso de que se encuentre, se envía la cantidad

                cartProducts.setSubtotalPrice(cartProducts.getUnityPrice().multiply(BigDecimal.valueOf(cartProducts.getQuantity())));

                cartProducts.setShipmentPrice(shipmentsPrice.getDeliveryPrice());
                productFound = true;
                break;
            }
        }


        //Si no hay ningún producto, se envían los nuevos datos del carrito
        if (!productFound) {

            EntityCartItem newItems = new EntityCartItem();

            newItems.setProduct(product); //Envía nuevo producto
            newItems.setQuantity(quantityProducts); //Envía cantidad seleccionada
            newItems.setUnityPrice(product.getUnityPrice()); //Envía el nuevo precio

            BigDecimal setFinalPrice = newItems.getUnityPrice().multiply(BigDecimal.valueOf(newItems.getQuantity()));
            newItems.setSubtotalPrice(setFinalPrice);
            newItems.setShipmentPrice(shipmentsPrice.getDeliveryPrice());

            repositoryItems.save(newItems); //Se guardan los datos en el repositorio
            cart.getProductsShoppingCart().add(newItems);

        }

        cartReposiroty.save(cart);//Se guarda el carrito con sus productos
    }


    //..................................................................................
    //                  Método para eliminar un producto del carrito

    /*
    Flujo (.stream()): Secuencia de elementos sobre la cual se pueden ejecutar operaciones de procesamiento
    como por ejemplo buscar información
     */

    @Transactional
    public void deleteFromCart(Long idProduct) {
        EntityShoppingCart cart = getCar();

        //Se busca el item del carrito que se va a eliminar
        EntityCartItem itemRemove = cart.getProductsShoppingCart().stream() //Esta línea de código transforma nuestra lista en un flujo

                .filter(product -> product.getIdItem().equals(idProduct)) //Esta línea se encarga de utilizar un filtro para buscar el id del item que coincida con el idProduct

                .findFirst().orElse(null); /*
        Esta línea devuelve la primera coincidencia de Id´s, También puede devolver un null
        en caso de que no existan coincidencias
        */

        //Si la operación anterior fue exitosa, se elimina también el item y su respectivo espacio en el carrito
        if (itemRemove != null) {
            cart.getProductsShoppingCart().remove(itemRemove);
        }

        //Se guarda el carrito actualizado y se eliminan los items del carrito
        cartReposiroty.save(cart);
        repositoryItems.delete(itemRemove);
    }
}
