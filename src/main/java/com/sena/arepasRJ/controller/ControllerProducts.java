package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryProducts;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceProductsRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerProducts {

    @Autowired
    public ServiceProductsRegister sendProduct;

    /*
    Primero se crea el método para insertar los datos de los productos en la base de datos.
    */

    @PostMapping("/admin/regProduct")
    public ResponseEntity<?> saveProducts(
            @RequestParam("name") String productName,
            @RequestParam("description") String productDescription,
            @RequestParam("price") BigDecimal unityPrice,
            @RequestParam("image") MultipartFile imageFile) {

        EntityProducts newProduct = new EntityProducts();

        newProduct.setProductName(productName);
        newProduct.setProductDescription(productDescription);
        newProduct.setUnityPrice(unityPrice);
        newProduct.setQuantity(1);

        sendProduct.registerPoducts(newProduct, imageFile);

        Responses productResponse = new Responses("Producto registrado con éxito");
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    /*
    Después se crea el método para la eliminación de algún producto en caso de necesitarlo
     */

    @Autowired
    public ServiceProductsRegister deleteProducts;

    @Autowired
    private RepositoryProducts searchProducts;

    @DeleteMapping("/admin/deleteProduct/{idProduct}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long idProduct) {
        EntityProducts searchProduct = searchProducts.findByIdProduct(idProduct);
        try {
            if (searchProduct != null) {
                
                deleteProducts.deleteProducts(idProduct);
                Responses okReponse = new Responses("Se eliminó el producto con éxito");
                return new ResponseEntity<>(okReponse, HttpStatus.OK);

            } else {
                Responses notReponse = new Responses("No se encontró un producto");
                return new ResponseEntity<>(notReponse, HttpStatus.NOT_FOUND);
            }
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions("Ocurrió un error en la red");
        }
    }
    /*
    El siguiente método es para actualizar los productos en caso de ser necesario
     */

    @Autowired
    public ServiceProductsRegister updateProducts;

    @PutMapping("/admin/updateProduct/{idProduct}")
    public ResponseEntity<?> productsUpdate(
            @PathVariable Long idProduct,
            @RequestParam("productName") String productName,
            @RequestParam("productDescription") String productDescription,
            @RequestParam("unityPrice") BigDecimal unityPrice,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        EntityProducts existingProduct = updateProducts.getProductsById(idProduct);

        if (existingProduct == null) {

            Responses notResponse = new Responses("El producto solicitado no existe");
            return new ResponseEntity<>(notResponse, HttpStatus.NOT_FOUND);

        }

        // Actualizar los campos del producto
        existingProduct.setProductName(productName);
        existingProduct.setProductDescription(productDescription);
        existingProduct.setUnityPrice(unityPrice);

        // Si se proporciona una nueva imagen, actualizarla
        if (imageFile != null) {
            try {
                existingProduct.setImage(imageFile.getBytes());

            } catch (IOException e) {

                e.printStackTrace();
                Responses errorResponse = new Responses("Error al cargar la imagen");
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Guardar los cambios en el producto
        updateProducts.updateProducts(existingProduct.getIdProduct(), productName, productDescription, unityPrice);

        Responses okResponse = new Responses("Producto actualizado con éxito");
        return new ResponseEntity<>(okResponse, HttpStatus.OK);
    }


    @GetMapping("/products")
    public ResponseEntity<List<EntityProducts>> getAllProducts() {
        List<EntityProducts> getProducts = sendProduct.getAllProducts();
        return new ResponseEntity<>(getProducts, HttpStatus.OK);
    }


    @GetMapping("/products/{idProduct}")
    public ResponseEntity<?> getProductsById(@PathVariable Long idProduct) {
        EntityProducts searchProduct = sendProduct.getProductsById(idProduct);

        try {
            if (searchProduct != null) {
                return new ResponseEntity<>(searchProduct, HttpStatus.OK);
            } else {
                Responses notResponse = new Responses("No se encontró el producto solicitado");
                return new ResponseEntity<>(notResponse, HttpStatus.NOT_FOUND);
            }
        } catch (PersonalExceptions pe) {
            throw new PersonalExceptions("Ocurrió un error en la red");
        }
    }

    @GetMapping("/imageProduct/{idProduct}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long idProduct) {
        /*
        Se trae el producto por su id para confirmar que exista y también confirmamos que
        este producto contenga una imagen agregad
         */
        EntityProducts product = sendProduct.getProductsById(idProduct);

        if (product != null && product.getImage() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(product.getImage());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}