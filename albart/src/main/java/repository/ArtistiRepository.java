package repository;

import models.Artisti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistiRepository extends JpaRepository<Artisti, Long> {
}
