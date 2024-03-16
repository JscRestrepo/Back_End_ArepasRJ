package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface RepositoryDelivery extends JpaRepository<EntityDelivery, Long> {

    public <S extends EntityDelivery> S save(S entity);

    public EntityDelivery findByIdDelivery(Long idDelivery);

    public void deleteByIdDelivery(Long idDelivery);

}
