package dtos;

import lombok.Data;
import models.Artisti;
import models.Klient;

import java.util.Date;

@Data
public class ChatDto {
    private Date dataKrijimit;

    private Artisti artisti;

    private Klient klient;
}
