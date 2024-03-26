package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBuy extends JpaRepository<EntityBuy, Long> {
}
