package services;

import dtos.KategoriaDto;
import jakarta.persistence.EntityNotFoundException;
import models.Kategoria;
import org.springframework.stereotype.Service;
import repository.KategoriaRepository;

import java.util.List;

@Service
public class KategoriaService {
    private final KategoriaRepository repo;

    public KategoriaService(KategoriaRepository repo){
        this.repo = repo;
    }

    public Kategoria create(KategoriaDto dto){
        Kategoria k = new Kategoria();
        k.setEmri(dto.getEmri());
        k.setPershkrimi(dto.getPershkrimi());
        return repo.save(k);
    }

    public Kategoria getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kategoria not found " + id));
    }

    public List<Kategoria> getAll(){ return repo.findAll(); }

    public Kategoria update(Long id, KategoriaDto dto){
        Kategoria k = getById(id);
        k.setEmri(dto.getEmri());
        k.setPershkrimi(dto.getPershkrimi());
        return repo.save(k);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
