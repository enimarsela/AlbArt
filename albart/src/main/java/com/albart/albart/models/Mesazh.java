package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "mesazh")
public class Mesazh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mesazhId;

    @Column(name = "derguesi")
    private String derguesi;

    @Column(name = "permbajtja")
    private String permbajtja;

    @Column(name = "koha")
    private Timestamp koha;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Mesazh(Long mesazhId, String derguesi, String permbajtja, Timestamp koha, Chat chat) {
        this.mesazhId = mesazhId;
        this.derguesi = derguesi;
        this.permbajtja = permbajtja;
        this.koha = koha;
        this.chat = chat;
    }

    public Mesazh() {

    }
}
