package com.sena.arepasRJ.controller;

import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.exceptions.PersonalExceptions;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import com.sena.arepasRJ.service.ServiceButtonRoleModify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/modify-role-button")
public class ControllerButtonRoleModify {

    @Autowired
    private ServiceButtonRoleModify getButton;

    @Autowired
    private RepositoryUsersRegister userGet;

    /*
    Se trae la lista ya compactada y se almacena en una variable, la cual se envía directamente hacia el Endpoint
    También se deja un error por si el usuario no fue encontrado
     */
    @GetMapping("/getUser/{email}")
    public ResponseEntity<List<Object[]>> getUsersWithSpecificData(@PathVariable String email) throws Exception {
        try {
            EntityUsersRegister userExists = userGet.findByEmailContainingIgnoreCase(email); //Este método busca el usuario sin tener en cuenta mayúsuclas y minúsculas

            if (userExists != null) {
                List<Object[]> returnObjects = getButton.getUserDataSelected(email); //Aunque se podía enviar directamente el objeto dentro de la respuesta, prefiero enviarla en una lista para manejar mejor futuros cambios en el código
                return new ResponseEntity<>(returnObjects, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            //Se manejan dos excepciones para mejorar la lectura de los posibles errores
        } catch (PersonalExceptions pe) {

            throw  new PersonalExceptions("Ocurrió un error en la red " + pe + HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            throw new Exception("Ocurrió un error inesperado " + e);

        }
    }
}
