package repository;

import models.Artisti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistiRepository extends JpaRepository<Artisti, Long> {
    public boolean existsByEmail(String email);
    public Optional<Artisti> findByEmail(String email);
}
