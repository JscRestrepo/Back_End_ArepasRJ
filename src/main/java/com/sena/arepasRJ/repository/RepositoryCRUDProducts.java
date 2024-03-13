package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCRUDProducts extends JpaRepository<EntityProducts, Long> {
    
    @Override
    public <S extends EntityProducts> S save (S entity);
    
    public EntityProducts findByIdProduct(Long idProduct);

    public void deleteProductByIdProduct(Long IdProduct);
}
