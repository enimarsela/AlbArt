package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "artisti")
public class Artisti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    // lidhja me user-in
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "eksperienca")
    private String eksperienca;
    @Column(name = "certifikime")
    private String certifikime;
    @Column(name = "description")
    private String description;
    @Column(name = "vleresimi_total")
    private Integer vleresimiTotal;


    //     KONSTRUKTORËT

    public Artisti() {

    }


    public Artisti(Long artistId, User user, String eksperienca, String certifikime, String description, Integer vleresimiTotal) {
        this.artistId = artistId;
        this.user = user;
        this.eksperienca = eksperienca;
        this.certifikime = certifikime;
        this.description = description;
        this.vleresimiTotal = vleresimiTotal;
    }


}