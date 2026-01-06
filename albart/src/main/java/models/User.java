package models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emri")
    private String emri;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roli")
    private String roli;

    //   KONSTRUKTORËT

    public User() {
    }

    public User(String emri, String email, String password, String roli) {
        this.emri = emri;
        this.email = email;
        this.password = password;
        this.roli = roli;
    }
}