package com.duong.entity;

import com.duong.util.AppConfig;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "phone", length = 15)
    String phone;

    @Column(name = "fullName", columnDefinition = "nvarchar(100)")
    String fullName;

    @Column(name = "image", columnDefinition = "nvarchar(100)")
    String image;

    @Column(name = "role", nullable = false)
    String role;

    @PrePersist
    public void prePersist() {
        if (role == null || role.isEmpty()) {
            role = AppConfig.get("users.role.user");
        }
    }
}
