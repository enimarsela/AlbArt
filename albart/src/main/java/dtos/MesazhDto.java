package dtos;

import lombok.Data;
import models.Chat;

import java.sql.Timestamp;

@Data
public class MesazhDto {
    private String derguesi;

    private String permbajtja;

    private Timestamp koha;

    private Chat chat;
}
