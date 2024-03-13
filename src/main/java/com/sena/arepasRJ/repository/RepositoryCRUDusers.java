package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityUsersRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCRUDusers extends JpaRepository<EntityUsersRegister, Long> {
    
    @Override
    public <S extends EntityUsersRegister> S save(S entity);
    
    public void deleteByIdUsers(Long idUsers);
    
    public EntityUsersRegister findUserByIdUsers(Long idUser);

    /*@Query("SELECT NEW OrderDetailsDTO(data.name, data.lastName, data.phone, data.address, order.orderId) " +
            "FROM EntityUsersRegister data " +
            "JOIN .")
    public EntityUsersRegister insert*/

    public EntityUsersRegister findByRole(String Role);
}
