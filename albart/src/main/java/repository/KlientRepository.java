package repository;

import models.Klient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlientRepository extends JpaRepository<Klient, Long> {
}
