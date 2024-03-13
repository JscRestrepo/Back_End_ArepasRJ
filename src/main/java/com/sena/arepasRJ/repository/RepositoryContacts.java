package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityContacts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryContacts extends JpaRepository<EntityContacts, Long> {
    
    public List<EntityContacts> findAll();
}
