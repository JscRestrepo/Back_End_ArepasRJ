package com.sena.arepasRJ.components;

import com.sena.arepasRJ.entity.EntityUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails { //Método donde definimos el usuario

    /*
    En este método se obtiene el usuario, la contraseña, el rol del usuario y desde aquí se envía la información
    hacia las diferentes clases que soliciten estos datos
     */

    public EntityUsers user;

    public UserPrincipal (EntityUsers user) {
        this.user = user;
    }

    //Este método es el que obtiene la autoridad del rol que vamos a manejar y sus respectivos permisos
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
