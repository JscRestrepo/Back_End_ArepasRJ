package com.sena.arepasRJ.service;

import com.sena.arepasRJ.components.UserRole;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.repository.RepositoryUsers;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceButtonRoleModify {

    /*
    En este código se crea un objeto el cuál almacena la información que se solicita desde el repositorio
     */

    @Autowired
    private RepositoryUsersRegister userSearch;

    @Autowired
    public ServiceButtonRoleModify(RepositoryUsersRegister usersRepository) {
        this.userSearch = usersRepository;
    }

    /*
    Después se almacena en una lista que va a retornar únicamente los valores solicitados
     */
    public List<Object[]> getUserDataSelected(String email) {
        return userSearch.findAllUsersWithSpecificData(email);
    }

    @Autowired
    private RepositoryUsersRegister searchUserRole;

    @Autowired
    private RepositoryUsers updateUserRole;

    @Transactional
    public void roleModify(String email, UserRole newRole) {
        EntityUsersRegister userSearch = searchUserRole.findByEmailContainingIgnoreCase(email);
        EntityUsers userModify = updateUserRole.findUsersByEmail(email);

        if (userSearch == null) {
            throw new RuntimeException("No se encontró ningún usuario");
        }

        if (userModify == null) {
            throw new RuntimeException("No se encontró ningún usuario");
        }

        userSearch.setRole(newRole);
        userModify.setRole(newRole);
        updateUserRole.save(userModify);
        searchUserRole.save(userSearch);
    }
}
