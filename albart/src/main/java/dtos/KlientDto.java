package dtos;

import lombok.Data;
import models.User;

import java.util.List;

@Data
public class KlientDto {
    private List<String> preferenca;

    private User user;
}
