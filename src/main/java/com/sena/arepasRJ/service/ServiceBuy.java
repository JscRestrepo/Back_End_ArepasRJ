package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityBuy;
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
    public void updateOrderStatus(Long idBuy, String orderStatus) {
        EntityBuy searchBuy = saveBuy.findById(idBuy).orElse(null);

        if (searchBuy != null) {
            searchBuy.setOrderStatus(orderStatus);
            saveBuy.save(searchBuy);
        }
    }

    @Transactional
    public List<EntityBuy> readBuy() {
        return saveBuy.findAll();
    }

    @Autowired
    private RepositoryUsers getUser;

    public List<EntityBuy> readUsersBuy(String email) {
        return saveBuy.findListBuyByEmail(email);
    }
}
