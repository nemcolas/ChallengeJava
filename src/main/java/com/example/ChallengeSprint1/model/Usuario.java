package com.example.ChallengeSprint1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USUARIO")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_USUARIO_ROLES", joinColumns = @JoinColumn(name = "USUARIO_ID"))
    @Column(name = "ROLE")
    private Set<String> roles = new HashSet<>();
}
