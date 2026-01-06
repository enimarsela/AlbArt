package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "transporti")
public class Transporti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportiId;

    @Column(name = "emri")
    private String emri;
    @Column(name = "adresa")
    private String adresa;
    @Column(name = "statusi")
    private String statusi;
    @Column(name = "dataDergimit")
    private Date dataDergimit;

    public Transporti(Long transportiId, String emri, String adresa, String statusi, Date dataDergimit) {
        this.transportiId = transportiId;
        this.emri = emri;
        this.adresa = adresa;
        this.statusi = statusi;
        this.dataDergimit = dataDergimit;
    }
    public Transporti() {

    }
}
