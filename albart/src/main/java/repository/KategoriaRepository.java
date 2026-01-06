package repository;

import models.Kategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {
}
