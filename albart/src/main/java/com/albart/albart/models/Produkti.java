package com.albart.albart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "produkti")
public class Produkti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int produktId;
    @Column(name = "emri")
    private String emri;
    @Column(name = "pershkrimi")
    private String pershkrimi;
    @Column(name = "cmimi")
    private double cmimi;
    @Column(name = "foto_produktit")
    private String fotoProdukti;
    @Column(name = "watermark")
    private String watermark;
    @Column(name = "statusi")
    private String statusi;
    @Column(name = "vleresimi_total")
    private double vleresimiTotal;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategoria kategoria;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artisti artisti;

    public Produkti(int produktId, String emri, String pershkrimi, double cmimi,
                   String fotoProdukti, String watermark, String statusi, double vleresimiTotal) {
        this.produktId = produktId;
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
        this.fotoProdukti = fotoProdukti;
        this.watermark = watermark;
        this.statusi = statusi;
        this.vleresimiTotal = vleresimiTotal;
    }

    public Produkti() {

    }
     
}