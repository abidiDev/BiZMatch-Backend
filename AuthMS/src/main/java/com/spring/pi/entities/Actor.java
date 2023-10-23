package com.spring.pi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.pi.models.Entreprise;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actor")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String FullName;
    @Temporal(TemporalType.DATE)
    private Date Birthdate;
    private  int phone;
    private String gender;
    private String email;
    private String username;

    private String password;
    private String picture;
    private String address;
    @Enumerated(EnumType.STRING)

    private ERole role;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    public Actor(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    @ManyToMany(fetch = FetchType.LAZY)

    private Set<Role> roles = new HashSet<>();
    @Transient
    private Set<Entreprise> entreprises = new HashSet<>();


}