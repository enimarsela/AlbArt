package services;

import dtos.PorosiDto;
import jakarta.persistence.EntityNotFoundException;
import models.Porosi;
import org.springframework.stereotype.Service;
import repository.PorosiRepository;

import java.util.List;

@Service
public class PorosiService {

    private final PorosiRepository repo;

    public PorosiService(PorosiRepository repo){ this.repo = repo; }

    public Porosi create(PorosiDto dto){
        Porosi p = new Porosi();
        p.setData(dto.getData());
        p.setStatusi(dto.getStatusi());
        p.setKlient(dto.getKlient());
        p.setPagesa(dto.getPagesa());
        p.setCart(dto.getCart());
        return repo.save(p);
    }

    public Porosi getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Porosi not found " + id));
    }

    public List<Porosi> getAll(){ return repo.findAll(); }

    public Porosi update(Long id, PorosiDto dto){
        Porosi p = getById(id);
        p.setData(dto.getData());
        p.setStatusi(dto.getStatusi());
        p.setKlient(dto.getKlient());
        p.setPagesa(dto.getPagesa());
        p.setCart(dto.getCart());
        return repo.save(p);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
