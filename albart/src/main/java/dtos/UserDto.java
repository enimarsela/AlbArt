package dtos;

import lombok.Data;

@Data
public class UserDto {
    private String emri;

    private String email;

    private String password;

    private String roli;

    public UserDto(String emri, String email, String password, String roli) {
        this.emri = emri;
        this.email = email;
        this.password = password;
        this.roli = roli;
    }
    public UserDto() {

    }
}
