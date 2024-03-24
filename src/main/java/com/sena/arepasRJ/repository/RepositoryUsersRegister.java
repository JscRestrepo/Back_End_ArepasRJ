package com.sena.arepasRJ.repository;

import com.sena.arepasRJ.entity.EntityUsersRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUsersRegister extends JpaRepository<EntityUsersRegister, Long> {
    
    @Override
    public <S extends EntityUsersRegister> S save(S entity);
    
    public void deleteByIdUsers(Long idUsers);
    
    public EntityUsersRegister findUserByIdUsers(Long idUser);

    public EntityUsersRegister findByEmailContainingIgnoreCase(String email); //Método para buscar el error sin tener en cuenta mayúsculas y minúsculas

    public EntityUsersRegister findByRole(String Role);

    /*
    Método para buscar los datos específicos de la lista solicitada
    En este caso es la lista que se muestra cuando se busca un usuario para
    modificar su rol
     */
    @Query("SELECT u.name, u.email, u.role FROM EntityUsersRegister u WHERE u.email = :email")
    List<Object[]> findAllUsersWithSpecificData(@Param("email") String email);
}
