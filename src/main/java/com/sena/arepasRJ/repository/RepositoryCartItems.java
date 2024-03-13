package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCartItems extends JpaRepository<EntityCartItem, Long> {

    public EntityCartItem findItemByIdItem(Long idItem);
}
