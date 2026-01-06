package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "porosi")
public class Porosi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long porosiId;

    @Column(name = "data")
    private Date data;

    @Column(name = "statusi")
    private String statusi;

    @ManyToOne
    @JoinColumn(name = "klient_id", unique = true)
    private Klient klient;

    @OneToOne
    @JoinColumn(name = "pagesa_id", unique = true)
    private Pagesa pagesa;

    public Porosi(Long porosiId, Date data, String statusi, Klient klient, Pagesa pagesa) {
        this.porosiId = porosiId;
        this.data = data;
        this.statusi = statusi;
        this.klient = klient;
        this.pagesa = pagesa;
    }
    public Porosi() {

    }
}
