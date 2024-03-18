package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.components.JwtUtils;
import com.sena.arepasRJ.components.UserRole;
import com.sena.arepasRJ.entity.EntityUsers;
import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import com.sena.arepasRJ.responses.Responses;
import com.sena.arepasRJ.service.ServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerLogin {

    @Autowired
    private ServiceLogin userConfirm;

    private EntityUsersRegister searchRole = new EntityUsersRegister();

    /*
    ¡¡ANOTACIÓN IMPORTANTE!!
    El @RequestBody en Spring es una anotación que se utiliza para indicar que 
    el método del controlador debe convertir automáticamente el cuerpo de la 
    solicitud HTTP en un objeto del tipo especificado.
    
    Es decir, si el cuerpo de mi solicitud es un POST entonces Spring se encarga
    de transformar los datos en el tipo que se especifica en los parámetros
    */

    @PostMapping("/login")
    public ResponseEntity<?> loginUsers(@RequestBody EntityUsers loginUser) {
        String email = loginUser.getEmail();
        String password = loginUser.getPassword();

        /*
        Instanciamos las 3 validaciones que vamos a utilizar
        1. Validar la existencia de los datos y su igualdad
        2. Validar que el correo realmente exista
        3. Validar que la contraseña también exista*/


        Boolean loginSuccess = userConfirm.loginUsers(email, password);
        Boolean incorrectEmail = userConfirm.incorrectEmail(email);
        Boolean incorrectPassword = userConfirm.incorrectPassword(password);

        /*
        Sucesión de validaciones. Primero validamos si los datos son iguales.
        En caso de no serlo, validamos la existencia del email, en caso de que
        exista validamos la contraseña, y si también existe se arroja un error
        inesperado*/


        if (!loginSuccess) {
            if (!incorrectEmail) {
                if (!incorrectPassword) {
                    Responses loginResponse = new Responses("Ocurrió un error inesperado");
                    return new ResponseEntity<>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);

                } else {
                    Responses loginResponse = new Responses("La contraseña es incorrecta");
                    return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
                }
            } else {
                Responses loginResponse = new Responses("El correo es incorrecto");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
        } else {
            UserRole userRole = userConfirm.getUserRoleByEmail(email);

            if (userRole == null) {
                Responses nullResponse = new Responses("El usuario no existe");
                return new ResponseEntity<>(nullResponse, HttpStatus.NOT_FOUND);

            } else {
                searchRole.setRole(userRole);

                if (searchRole.getRole() == null) {
                    Responses notUserResponse = new Responses("Rol de usuario no identificado");
                    return new ResponseEntity<>(notUserResponse, HttpStatus.NOT_FOUND);
                }

                String token = JwtUtils.generationToken(email, userRole.toString());

                String welcomeMessage = userRole == UserRole.ADMIN ? "Bienvenido, Administrador" : "Bienvenido, Usuario";
                Responses loginResponse = new Responses(welcomeMessage + " tu token es: " + token);
                return new ResponseEntity<>(loginResponse, HttpStatus.OK);
            }
        }
    }

    @Autowired
    private RepositoryUsersRegister getEmail;

    @GetMapping("/getUser")
    public ResponseEntity<?> getUserWithToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); //Se elimina el prefijo "Bearer"

        //Generar la validación del token
        EntityUsersRegister getUser = JwtUtils.validateTokenWithUser(token, getEmail);

        if (getUser != null) {
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        } else {
            Responses notUserResponse = new Responses("El token no es válido o ya expiró");
            return new ResponseEntity<>(notUserResponse, HttpStatus.NOT_FOUND);
        }
    }
}
