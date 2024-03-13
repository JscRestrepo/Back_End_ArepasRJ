package com.sena.arepasRJ.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entity_contacts")
public class EntityContacts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContact;
    
    private String companyName;
    private String emailContact;
    private int phoneContact;
    private String contactMessage;
    
}
