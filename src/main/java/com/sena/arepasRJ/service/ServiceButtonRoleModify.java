package com.sena.arepasRJ.service;

import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Object[]> getUserDataSelected() {
        return userSearch.findAllUsersWithSpecificData();
    }
}
