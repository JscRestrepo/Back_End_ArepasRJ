package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryShoppingCart extends JpaRepository<EntityShoppingCart, Long> {
}
