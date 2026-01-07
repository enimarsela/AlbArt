package dtos;

import lombok.Data;
import models.User;

import java.util.Date;

@Data
public class NjoftimDto {
    private String permbajtja;

    private Date data;

    private String lloji;

    private User user;
}
