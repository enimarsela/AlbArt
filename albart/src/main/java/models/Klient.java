package models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "klient")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long klientId;
    @Column(name = "preferenca")
    @ElementCollection
    private List<String> preferenca;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Klient() {

    }
    public Klient(Long klientId, List<String> preferenca, User user) {
        this.klientId = klientId;
        this.preferenca = preferenca;
        this.user = user;
    }


}
