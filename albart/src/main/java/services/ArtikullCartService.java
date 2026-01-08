package services;

import dtos.ArtikullCartDto;
import jakarta.persistence.EntityNotFoundException;
import models.ArtikullCart;
import org.springframework.stereotype.Service;
import repository.ArtikullCartRepository;

import java.util.List;

@Service
public class ArtikullCartService {

    private final ArtikullCartRepository repo;

    public ArtikullCartService(ArtikullCartRepository repo){ this.repo = repo; }

    public ArtikullCart create(ArtikullCartDto dto){
        ArtikullCart a = new ArtikullCart();
        a.setPorosi(dto.getPorosi());
        a.setProdukti(dto.getProdukti());
        a.setSasia(dto.getSasia());
        return repo.save(a);
    }

    public ArtikullCart getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ArtikullCart not found " + id));
    }

    public List<ArtikullCart> getAll(){ return repo.findAll(); }

    public ArtikullCart update(Long id, ArtikullCartDto dto){
        ArtikullCart a = getById(id);
        a.setPorosi(dto.getPorosi());
        a.setProdukti(dto.getProdukti());
        a.setSasia(dto.getSasia());
        return repo.save(a);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
