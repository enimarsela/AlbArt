package dtos;

import lombok.Data;
import models.Klient;
import models.Pagesa;

import java.util.Date;

@Data
public class PorosiDto {
    private Date data;

    private String statusi;

    private Klient klient;

    private Pagesa pagesa;
}
