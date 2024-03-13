package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDelivery extends JpaRepository<EntityDelivery, Long> {

    public <S extends EntityDelivery> S save(S entity);

    public EntityDelivery findDeliveryByIdDelivery(Long idDelivery);

}
