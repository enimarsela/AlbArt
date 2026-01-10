package repository;

import models.ArtikullCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtikullCartRepository extends JpaRepository<ArtikullCart, Long> {
    List<ArtikullCart> findByKlientId(Long klientId);
    void deleteByKlientId(Long klientId);
}
