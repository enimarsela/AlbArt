package repository;

import models.Mesazh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesazhRepository extends JpaRepository<Mesazh, Long> {
}
