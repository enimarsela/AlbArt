package com.albart.albart;

import com.albart.albart.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "artisti")
public class Artisti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    // lidhja me user-in
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String eksperienca;
    private String certifikime;
    private String description;
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


    //   GETTERS & SETTERS


    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEksperienca() {
        return eksperienca;
    }

    public void setEksperienca(String eksperienca) {
        this.eksperienca = eksperienca;
    }

    public String getCertifikime() {
        return certifikime;
    }

    public void setCertifikime(String certifikime) {
        this.certifikime = certifikime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVleresimiTotal() {
        return vleresimiTotal;
    }

    public void setVleresimiTotal(Integer vleresimiTotal) {
        this.vleresimiTotal = vleresimiTotal;
    }
}
