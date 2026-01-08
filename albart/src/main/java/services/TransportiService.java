package services;

import dtos.TransportiDto;
import jakarta.persistence.EntityNotFoundException;
import models.Transporti;
import org.springframework.stereotype.Service;
import repository.TransportiRepository;

import java.util.List;

@Service
public class TransportiService {

    private final TransportiRepository repo;

    public TransportiService(TransportiRepository repo){ this.repo = repo; }

    public Transporti create(TransportiDto dto){
        Transporti t = new Transporti();
        t.setEmri(dto.getEmri());
        t.setAdresa(dto.getAdresa());
        t.setStatusi(dto.getStatusi());
        t.setDataDergimit(dto.getDataDergimit());
        t.setPorosi(dto.getPorosi());
        return repo.save(t);
    }

    public Transporti getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transporti not found " + id));
    }

    public List<Transporti> getAll(){ return repo.findAll(); }

    public Transporti update(Long id, TransportiDto dto){
        Transporti t = getById(id);
        t.setEmri(dto.getEmri());
        t.setAdresa(dto.getAdresa());
        t.setStatusi(dto.getStatusi());
        t.setDataDergimit(dto.getDataDergimit());
        t.setPorosi(dto.getPorosi());
        return repo.save(t);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
