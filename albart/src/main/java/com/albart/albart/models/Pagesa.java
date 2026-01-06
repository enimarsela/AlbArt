package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "pagesa")
public class Pagesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pagesaId;

    @ElementCollection
    @Column(name = "karta")
    private List<String> karta;

    @Column(name = "shuma")
    private Double shuma;

    @Column(name = "data")
    private Date data;

    @Column(name = "statusi")
    private String statusi;

    public Pagesa(){

    }

    public Pagesa(List<String> karta, Double shuma, Date data, String statusi) {
        this.karta = karta;
        this.shuma = shuma;
        this.data = data;
        this.statusi = statusi;
    }


}
