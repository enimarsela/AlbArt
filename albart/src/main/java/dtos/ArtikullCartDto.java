package dtos;

import lombok.Data;
import models.Porosi;
import models.Produkti;

@Data
public class ArtikullCartDto {
    private Integer sasia;

    private Produkti produkti;

    private Porosi porosi;
}