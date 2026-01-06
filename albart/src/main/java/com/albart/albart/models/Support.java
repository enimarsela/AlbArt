package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "support")
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportId;

    @Column(name = "pershkrimi")
    private String pershkrimi;

    @Column(name = "statusi")
    private String statusi;

    @OneToMany
    @JoinColumn(name = "klient_id")
    private List<Klient> klienta;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Support(Long supportId, String pershkrimi, String statusi, List<Klient> klienta, Admin admin) {
        this.supportId = supportId;
        this.pershkrimi = pershkrimi;
        this.statusi = statusi;
        this.klienta = klienta;
        this.admin = admin;
    }
    public Support() {

    }
}
