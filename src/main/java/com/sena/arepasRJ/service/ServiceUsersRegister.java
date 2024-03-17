package com.sena.arepasRJ.service;

import com.sena.arepasRJ.components.UserRole;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.entity.EntityUsersRegister;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import com.sena.arepasRJ.repository.RepositoryUsers;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ServiceUsersRegister {
    
    /*..........................................................................*/
    
    @Autowired
    private RepositoryUsersRegister registerRepository;
    
    @Autowired
    private RepositoryUsers userInsert;
    
    @Autowired
    private PasswordEncoder passEncoder;
    
    @Transactional
    public void registerUsers(Long idUser, String name, String lastName,
            String email, String password, String phone,
            String address, UserRole role, EntityUsers userSearch) {

        String passEncripted = passEncoder.encode(password);

        /* Crear y guardar la entidad entityUsers */
        EntityUsers user = new EntityUsers(idUser, email, passEncripted, null, role);
        userInsert.save(user);

        /* Crear y guardar la entidad entityUsersRegister con la referencia al mismo objeto entityUsers */
        EntityUsersRegister userRegister = new EntityUsersRegister(idUser, name, lastName,
                email, passEncripted, phone, address, role, user);
        registerRepository.save(userRegister);
    }
    
    public Boolean emailExists(String email) {
        return userInsert.findByEmail(email) != null;
    }

    /*..........................................................................*/
    
    @Transactional
    public boolean deleteUsers(Long idUser) {
        try {
            registerRepository.deleteByIdUsers(idUser);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /*..........................................................................*/
    
    @Transactional
    public void updateUsers(Long idUser, String name, String lastName,
            String email, String password, String phone, String address) {

        EntityUsersRegister userRegister = registerRepository.findUserByIdUsers(idUser);
        String passEncripted = passEncoder.encode(password);

        /*
        Se permite cualquier modificación menos el correo, el id y el rol
         */
        if (userRegister != null) {
            userRegister.setName(name);
            userRegister.setLastName(lastName);
            userRegister.setPassword(passEncripted);
            userRegister.setPhone(phone);
            userRegister.setAddress(address);
            registerRepository.save(userRegister);

            EntityUsers user = userRegister.getUserSearch();

            /*
            Al confirmar la modificación, se actualiza la contraseña en la tabla usuarios automáticamente
             */
            if (user != null) {
                user.setPassword(passEncripted);
            }
        }
    }
    
    /*..........................................................................*/
    
    @Transactional(readOnly = true)
    public List<EntityUsersRegister> getAllUsers() {
        return registerRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public EntityUsersRegister getUsersById(Long idUser) {
        return registerRepository.findUserByIdUsers(idUser);
    }
}
