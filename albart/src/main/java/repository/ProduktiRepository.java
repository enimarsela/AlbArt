package repository;

import models.Produkti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktiRepository extends JpaRepository<Produkti, Long> {
}
