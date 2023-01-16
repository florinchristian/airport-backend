package dev.florinchristian.airportbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "hashedPassword")
    private String hashedPassword;

    @Column(name = "role")
    private String role;

    public User(String email, String hashedPassword, String role) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }
}
