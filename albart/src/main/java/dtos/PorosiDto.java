package dtos;

import lombok.Data;
import models.ArtikullCart;
import models.Klient;
import models.Pagesa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PorosiDto {
    private Date data;

    private String statusi;

    private Klient klient;

    private Pagesa pagesa;

    private List<ArtikullCart> cart;
}
