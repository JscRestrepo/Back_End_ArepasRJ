package com.sena.arepasRJ.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "users")
public class EntityUsers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private EntityShoppingCart cart;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
