package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.components.JwtUtils;
import com.sena.arepasRJ.service.ServiceUsersRegister;
import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.responses.Responses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerUsersRegister {
    
    /*..........................................................................*/
    
    /*
    En primer lugar se encuentra el Registro de usuario donde se maneja la
    lógica de validaciones e inserción de nuevos usuarios
    */
    
    /*..........................................................................*/

    @Autowired
    private ServiceUsersRegister registerUsers;
    

    @PostMapping("/login/register")
    public ResponseEntity<?> userSave(@RequestBody EntityUsersRegister user) {
        
        try {
            if (nameAndLasNameValidation(user)) {

                if (emailValidation(user)) {

                    if (passValidation(user)) {

                        if (registerUsers.emailExists(user.getEmail())) {
                            Responses passResponse = new Responses("La dirección de "
                                    + "correo electrónico ya existe");
                            return new ResponseEntity<>(passResponse, HttpStatus.IM_USED);
                        }

                        registerUsers.registerUsers(user.getIdUsers(), user.getName(), user.getLastName(),
                                user.getEmail(), user.getPassword(), user.getPhone(),
                                user.getAddress(), user.getRole(), user.getUserSearch());

                        return new ResponseEntity<>(user, HttpStatus.OK);
                    } else {
                        Responses passResponse = new Responses("La "
                                + "contraseña debe tener al menos una mayúscula");
                        return new ResponseEntity<>(passResponse, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    Responses emailResponse = new Responses("Por "
                            + "favor ingrese un correo válido");
                    return new ResponseEntity<>(emailResponse, HttpStatus.BAD_REQUEST);
                }
            } else {
                Responses nameOrLastNameResponse = new Responses("El nombre o "
                        + "el apellido contiene caracteres incorrectos");
                return new ResponseEntity<>(nameOrLastNameResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            Responses responseError = new Responses("Error en el servidor");
            System.out.println("Error" + e);
            return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Validación de que el nombre solo contenga letras
    public Boolean nameAndLasNameValidation(EntityUsersRegister user) {
        String name = user.getName();
        String lastName = user.getLastName();
        String numbersSearch = ".*\\d.*"; //Atributo para buscar números
        
        return !name.matches(numbersSearch) && !lastName.matches(numbersSearch); //Se busca que no tenga números ni el nombre ni el apellido
    }

    public Boolean passValidation(EntityUsersRegister user) {
        String regexPass = "^(?=.*[a-z])(?=.*[A-Z]).*$";
        int characters = user.getPassword().length();

        return user.getPassword().matches(regexPass) && characters >= 8;
    }

    public Boolean emailValidation(EntityUsersRegister user) {
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        
        return user.getEmail().matches(regexEmail);
    }
    
    /*..........................................................................*/
    
    /*
    En segundo lugar se encuentra el controlador que ejecuta la modificación de 
    datos del usuario y ejecuta la acción exitosamente en la base de datos
    */
    
    /*..........................................................................*/
    
    @Autowired
    private ServiceUsersRegister updateUser;

    @PutMapping("/update/{idUser}")
    public ResponseEntity<String> updateUser(@PathVariable Long idUser, @RequestBody EntityUsersRegister userUpdated) {
        EntityUsersRegister userExists = updateUser.getUsersById(idUser);
        
        if (userExists != null) {
            updateUser.updateUsers(idUser, userUpdated.getName(), 
                    userUpdated.getLastName(),
                    userUpdated.getEmail(), userUpdated.getPassword(), 
                    userUpdated.getPhone(), userUpdated.getAddress());
            
            return new ResponseEntity<>("El usuario se actualizó exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("El usuario que busca actualizar no existe", HttpStatus.NOT_FOUND);
        }
    }
    
    /*..........................................................................*/
    
    /*
    En tercer lugar se encuentra el controlador de eliminación de datos, donde podemos
    eliminar usuarios por su Id y ejecuta la acción con la base de datos
    */
    
    /*..........................................................................*/
    
    @Autowired
    private ServiceUsersRegister deleteUser;
    
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
        try {
            if (deleteUser.deleteUsers(idUser)) {
                Responses okResponse = new Responses("El usuario se eliminó con éxito");
                return new ResponseEntity<>(okResponse, HttpStatus.OK);
            } else {
                Responses notResponse = new Responses("No se encontró un usuario");
                return new ResponseEntity<>(notResponse, HttpStatus.NOT_FOUND);
            }
        } catch (PersonalExceptions e) {
            throw new PersonalExceptions("Error al acceder a la base de datos");
        }
    }
}
