package models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "artikull_cart")
public class ArtikullCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artikullId;

    @Column(name = "sasia")
    private Integer sasia;

    @OneToOne
    @JoinColumn(name = "produkt_id", unique = true)
    private Produkti produkti;

    @OneToOne
    @JoinColumn(name= "porosi_id", unique = true)
    private Porosi porosi;

    public ArtikullCart() {

    }

    public ArtikullCart(Long artikullId, Integer sasia, Produkti produkti, Porosi porosi) {
        this.artikullId = artikullId;
        this.sasia = sasia;
        this.produkti = produkti;
        this.porosi = porosi;
    }


}
