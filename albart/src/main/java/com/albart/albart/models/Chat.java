package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(name = "data_krijimit")
    private Date dataKrijimit;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artisti artisti;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    public Chat(Long chatId, Date dataKrijimit, Artisti artisti, Klient klient) {
        this.chatId = chatId;
        this.dataKrijimit = dataKrijimit;
        this.artisti = artisti;
        this.klient = klient;
    }

    public Chat() {

    }
}
