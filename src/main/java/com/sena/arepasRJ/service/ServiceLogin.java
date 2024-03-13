package com.sena.arepasRJ.service;

import com.sena.arepasRJ.components.UserRole;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.repository.RepositoryUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogin {
    
    @Autowired
    private RepositoryUsers loginRepository;
    
    @Autowired
    private PasswordEncoder passEncode;
    
    public Boolean loginUsers(String email, String password) {
        EntityUsers userLogin = loginRepository.findUsersByEmail(email);
        
        if (userLogin != null) {
            if (passEncode.matches(password, userLogin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public Boolean incorrectEmail(String email) {
        EntityUsers emailExists = loginRepository.findUsersByEmail(email);

        if (emailExists == null) {
            return true;
        }
        return false;
    }

    public Boolean incorrectPassword(String password) {
        EntityUsers userPassword = loginRepository.findUsersByPassword(password);

        if (userPassword == null) {
            return true;
        }
        return false;
    }

    public UserRole getUserRoleByEmail(String email) {
        EntityUsers userRole = loginRepository.findByEmail(email);

        if (userRole != null) {
            return userRole.getRole();
        } else {
            return null;
        }
    }
}
