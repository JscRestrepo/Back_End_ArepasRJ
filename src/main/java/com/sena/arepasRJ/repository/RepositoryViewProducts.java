package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityProducts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryViewProducts extends JpaRepository<EntityProducts, Long> {
    
    public List<EntityProducts> findByProductNameContainingIgnoreCase(String productName);
    
    public List<EntityProducts> findAll();
}
