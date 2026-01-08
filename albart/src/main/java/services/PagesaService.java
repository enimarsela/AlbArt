package services;

import dtos.PagesaDto;
import jakarta.persistence.EntityNotFoundException;
import models.Pagesa;
import org.springframework.stereotype.Service;
import repository.PagesaRepository;

import java.util.List;

@Service
public class PagesaService {

    private final PagesaRepository repo;

    public PagesaService(PagesaRepository repo){ this.repo = repo; }

    public Pagesa create(PagesaDto dto){
        Pagesa p = new Pagesa();
        p.setKarta(dto.getKarta());
        p.setShuma(dto.getShuma());
        p.setData(dto.getData());
        p.setStatusi(dto.getStatusi());
        return repo.save(p);
    }

    public Pagesa getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagesa not found " + id));
    }

    public List<Pagesa> getAll(){ return repo.findAll(); }

    public Pagesa update(Long id, PagesaDto dto){
        Pagesa p = getById(id);
        p.setKarta(dto.getKarta());
        p.setShuma(dto.getShuma());
        p.setData(dto.getData());
        p.setStatusi(dto.getStatusi());
        return repo.save(p);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
