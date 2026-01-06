package models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "njoftim")
public class Njoftim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long njoftimId;

    @Column(name = "permbajtja")
    private String permbajtja;

    @Column(name = "data")
    private Date data;

    @Column(name = "lloji")
    private String lloji;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Njoftim(Long njoftimId, String permbajtja, Date data, String lloji, User user) {
        this.njoftimId = njoftimId;
        this.permbajtja = permbajtja;
        this.data = data;
        this.lloji = lloji;
        this.user = user;
    }

    public Njoftim() {

    }
}
