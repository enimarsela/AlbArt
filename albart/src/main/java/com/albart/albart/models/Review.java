package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "vleresimi")
    private Integer vleresimi;

    @Column(name = "koment")
    private String koment;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artisti artisti;

    @OneToOne
    @JoinColumn(name = "produkt_id")
    private Produkti produkti;

    public Review(Long reviewId, Integer vleresimi, String koment, Klient klient, Artisti artisti, Produkti produkti) {
        this.reviewId = reviewId;
        this.vleresimi = vleresimi;
        this.koment = koment;
        this.klient = klient;
        this.artisti = artisti;
        this.produkti = produkti;
    }
    public Review() {

    }
}
