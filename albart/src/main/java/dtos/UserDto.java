package dtos;

import lombok.Data;

@Data
public class UserDto {
    private String emri;

    private String email;

    private String password;

    private String roli;
}
