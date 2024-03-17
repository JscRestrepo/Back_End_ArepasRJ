package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.service.ServiceProductsButton;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Butproducts")
public class ControllerProductsButton {
    
    @Autowired
    private ServiceProductsButton getProducts;
    
    @GetMapping
    public List<EntityProducts> allProducts() {
        return getProducts.getListProducts();
    }
}
