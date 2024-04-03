package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityBuy;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.repository.RepositoryBuy;
import com.sena.arepasRJ.repository.RepositoryUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceBuy {

    @Autowired
    private RepositoryBuy saveBuy;

    @Transactional
    public void saveProducts(EntityBuy setEntity) {
        saveBuy.save(setEntity);
    }

    @Transactional
    public List<EntityBuy> readBuy() {
        return saveBuy.findAll();
    }

    @Autowired
    private RepositoryUsers getUser;

    public List<EntityBuy> readUsersBuy(String email) {
        // Obtener todas las compras realizadas por el usuario con el correo electrónico dado
        return saveBuy.findListBuyByUserEmail(email);
    }
}
