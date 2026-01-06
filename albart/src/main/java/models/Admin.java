package models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Admin(){

    }


}
