package com.sena.arepasRJ.service;

import com.sena.arepasRJ.components.UserPrincipal;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.repository.RepositoryUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private RepositoryUsers emailSearch;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EntityUsers user = emailSearch.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo " + email);
        }
        return new UserPrincipal(user);
    }
}