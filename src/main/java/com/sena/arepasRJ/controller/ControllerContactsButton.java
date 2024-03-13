package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityContacts;
import com.sena.arepasRJ.service.ServiceContactsButton;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ControllerContactsButton {
    
    @Autowired
    private ServiceContactsButton getContacts;
    
    @GetMapping
    public List<EntityContacts> getAllContacts() {
        System.out.println("Se muestran los contactos con éxito");
        return getContacts.getListContacts();
    }
}
