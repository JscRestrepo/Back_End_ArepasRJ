package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityBuy;
import com.sena.arepasRJ.repository.RepositoryBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

@Service
public class ServiceBuy {

    @Autowired
    private RepositoryBuy saveBuy;

    @Transactional
    public void saveProducts(EntityBuy setEntity) {
        saveBuy.save(setEntity);
    }
}
