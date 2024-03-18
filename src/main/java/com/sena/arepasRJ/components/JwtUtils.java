package com.sena.arepasRJ.components;

import com.sena.arepasRJ.entity.EntityUsersRegister;
import com.sena.arepasRJ.repository.RepositoryUsers;
import com.sena.arepasRJ.repository.RepositoryUsersRegister;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {


    /*
    Se llama a la llave secreta definida en las variables de entorno para tener un mejor control y
    mayor seguridad. Después se crea un objeto estático de tipo Key y se le asigna la clave original
    para enviarla hacia la creación del token y a las validaciones
    */
    @Value("${JWT_SECRET_KEY}")
    private String secretKeyOrigin;

    private static Key secretKeyString; //Creación de la llave estática

    @PostConstruct
    public void init() {
        secretKeyString = Keys.hmacShaKeyFor(secretKeyOrigin.getBytes()); //Asignación de la llave original a la llave estática
    }

    public static String generationToken(String name, String role) { //Se genera el token y por parámetros se envía la informacón contenida
        Date now = new Date();
        Date tokenExpiration = new Date(now.getTime() + 3600000);
        /*
        En los dos atributos anteriores creamos la fecha actual y la fecha de vencimiento del token
         */

        return Jwts.builder() //Inicio de la construcción del token
                .setSubject(name) //Nombre del usuario que se loguea
                .claim("role", role) //Rol asignado al usuario
                .setIssuedAt(now) //Fecha de creación del token
                .setExpiration(tokenExpiration) //Tiempo de expiración
                .signWith(SignatureAlgorithm.HS512, secretKeyString) //Clave con la que se firma el token para validar su autenticidad
                .compact(); //Finalización de la construcción del token
    }


    /*
    Método para validar la autenticidad del token que se trabaja
     */
    public static boolean validateToken(String token) {
        try {
            //Contiene tanto la clave como la información enviada al token y valida que sea la información correcta
            Jwts.parser().setSigningKey(secretKeyString).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /*
    Este método es el que cumple la función de permitir la extracción de las reclamaciones hechas al token
     */
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /*
   Los siguientes métodos extraen toda la información del token como su usuario, el cuerpo del token,
   el rol de usuario entre otros datos
    */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKeyString).parseClaimsJws(token).getBody();
    }


    public static EntityUsersRegister validateTokenWithUser(String token, RepositoryUsersRegister getEmail) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKeyString).parseClaimsJws(token).getBody();
            String email = claims.getSubject();
            String role = (String) claims.get("role");

            EntityUsersRegister user = getEmail.findByEmailContainingIgnoreCase(email);

            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
