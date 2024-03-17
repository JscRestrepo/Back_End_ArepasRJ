package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryProducts;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServiceProductsRegister {

    /*..........................................................................*/
    
    @Autowired
    private RepositoryProducts productsRegister;
    
    @Transactional
    public EntityProducts registerPoducts(EntityProducts products, MultipartFile images) {
        try {
            byte[] imageBytes = images.getBytes();
            products.setImage(imageBytes);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return productsRegister.save(products);
    }

    /*..........................................................................*/

    @Transactional
    public boolean deleteProducts(Long idProduct) {
        try {
            productsRegister.deleteProductByIdProduct(idProduct);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (PersonalExceptions pe) {
            pe.printStackTrace();
            return false;
        }
    }

    /*..........................................................................*/

    @Transactional
    public void updateProducts(Long idProduct, String productName, String productDescription, BigDecimal unityPrice) {
        EntityProducts update = productsRegister.findById(idProduct).orElse(null);

        if (update != null) {
            update.setProductName(productName);
            update.setProductDescription(productDescription);
            update.setUnityPrice(unityPrice);

            productsRegister.save(update);
        }
    }

    /*..........................................................................*/

    @Transactional
    public List<EntityProducts> getAllProducts() {
        return productsRegister.findAll();
    }

    @Transactional(readOnly = true)
    public EntityProducts getProductsById(Long idProduct) {
        return productsRegister.findByIdProduct(idProduct);
    }
}
