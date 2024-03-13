package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.repository.RepositoryViewProducts;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProductsButton {
    
    @Autowired
    private RepositoryViewProducts showProducts;
    
    public List<EntityProducts> getListProducts() {
        return showProducts.findAll();
    }
}
