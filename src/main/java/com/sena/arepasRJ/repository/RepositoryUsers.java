package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsers extends JpaRepository<EntityUsers, Long> {

    @Override
    public <S extends EntityUsers> S save(S entity);

    public EntityUsers findUsersByEmail(String email);

    public EntityUsers findUsersByPassword(String password);
    
    public EntityUsers findByEmail(String email);
    
}
