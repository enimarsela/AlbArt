package dtos;

import lombok.Data;
import models.Admin;
import models.Klient;

import java.util.List;

@Data
public class SupportDto {
    private String pershkrimi;

    private String statusi;

    private List<Klient> klienta;

    private Admin admin;
}
