package dtos;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class TransportiDto {
    private String emri;

    private String adresa;

    private String statusi;

    private Date dataDergimit;
}
