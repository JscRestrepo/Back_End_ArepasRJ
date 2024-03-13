package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityProducts;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.service.ServiceSearchBar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/searchProducts")
public class ControllerSearchBar {
    
    @Autowired
    private ServiceSearchBar setQuery;
    
    @PostMapping
    /*
    Map<String, String> request: Se encarga de recibir la solicitud en formato json 
    para procesarlas y enviarlas al código
    */
    public ResponseEntity<List<EntityProducts>> getProductsByName(@RequestBody Map<String, String> request) {
        try {
            
            /* 
            String productName = request.get("productName"): Recibe los datos del mapeo
            y los compara con los datos solicitados con la línea:
            List<entityProducts> products = setQuery.searchProductsByName(productName)
            
            */
            
            String productName = request.get("productName");
            List<EntityProducts> products = setQuery.searchProductsByName(productName);
            
            return ResponseEntity.ok(products);
        } catch (PersonalExceptions e) {
            
            e.printStackTrace();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            
        }
    }
}