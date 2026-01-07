package dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PagesaDto {
    private List<String> karta;

    private Double shuma;

    private Date data;

    private String statusi;
}
