package dtos;

import lombok.Data;
import models.User;

@Data
public class ArtistiDto {
    private User user;

    private String eksperienca;
    private String certifikime;
    private String description;
    private Integer vleresimiTotal;
}
