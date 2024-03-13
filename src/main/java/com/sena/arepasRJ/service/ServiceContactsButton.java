package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityContacts;
import com.sena.arepasRJ.repository.RepositoryContacts;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceContactsButton {
    
    @Autowired
    private RepositoryContacts viewContacts;
    
    public List<EntityContacts> getListContacts() {
        return viewContacts.findAll();
    }
}
