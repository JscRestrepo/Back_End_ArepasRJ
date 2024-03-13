package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.responses.Responses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ControllerLogout {

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Limpiar el contexto de seguridad
        SecurityContextHolder.clearContext();

        // Invalidar el token JWT eliminando la cookie que lo contiene
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JWT_TOKEN")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        Responses responses = new Responses("Se cerró sesión exitosamente");
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
