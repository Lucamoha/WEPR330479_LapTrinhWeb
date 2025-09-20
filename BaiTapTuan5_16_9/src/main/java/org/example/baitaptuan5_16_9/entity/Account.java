package org.example.baitaptuan5_16_9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 50, unique=true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullName", length = 255, columnDefinition = "NVARCHAR(255)")
    private String fullName;

    @Column(name = "image")
    private String image;

    @Column(name = "role", length = 10)
    private String role = "USER";
}
