package dtos;

import lombok.Data;
import models.Artisti;
import models.Kategoria;

@Data
public class ProduktiDto {
    private String emri;

    private String pershkrimi;

    private double cmimi;

    private String fotoProdukti;

    private String watermark;

    private String statusi;

    private double vleresimiTotal;

    private Kategoria kategoria;

    private Artisti artisti;

}
