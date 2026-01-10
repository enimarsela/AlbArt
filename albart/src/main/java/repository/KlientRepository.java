package repository;

import models.Klient;
import models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlientRepository extends JpaRepository<Klient, Long> {
    Optional<Klient> findByUser(User user);

    Optional<Klient> findByEmail(String email);
}
