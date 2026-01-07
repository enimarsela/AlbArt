package repository;

import models.Produkti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduktiRepository extends JpaRepository<Produkti, Long> {
    public List<Produkti> findProduktiByKategoria(Long kategoriaId);
    public List<Produkti> findProduktiByArtisti(Long artistiId);
}
