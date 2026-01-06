package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "kategoria")
public class Kategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kategoriId;

    @Column(name = "emri")
    private String emri;

    @Column(name = "pershkrimi")
    private String pershkrimi;

    public Kategoria() {

    }

    public Kategoria(String emri, String pershkrimi) {
        this.emri = emri;
        this.pershkrimi = pershkrimi;
    }

}
