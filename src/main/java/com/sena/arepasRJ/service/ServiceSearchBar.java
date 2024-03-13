package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.repository.RepositoryViewProducts;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceSearchBar {
    
    @Autowired
    private RepositoryViewProducts searchProducts;
    
    @Transactional(readOnly = true)
    public List<EntityProducts> searchProductsByName(String productName) {
        return searchProducts.findByProductNameContainingIgnoreCase(productName);
    }
}
