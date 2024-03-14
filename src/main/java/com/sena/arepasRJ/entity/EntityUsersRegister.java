package com.sena.arepasRJ.entity;

import javax.persistence.*;

import com.sena.arepasRJ.components.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_register")
public class EntityUsersRegister {

    //Llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsers;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ADMIN;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "idUser")
    private EntityUsers userSearch;
}
