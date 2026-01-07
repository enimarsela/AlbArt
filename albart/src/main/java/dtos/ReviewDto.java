package dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import models.Artisti;
import models.Klient;
import models.Produkti;

@Data
public class ReviewDto {
    private Integer vleresimi;

    private String koment;

    private Klient klient;

    private Artisti artisti;

    private Produkti produkti;
}
