package services;

import dtos.ProduktiDto;
import jakarta.persistence.EntityNotFoundException;
import models.Produkti;
import org.springframework.stereotype.Service;
import repository.ProduktiRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProduktiService {
    private ProduktiRepository produktiRepository;

    public Produkti createProdukt(ProduktiDto produktiDto) {
        Produkti produkti = new Produkti();

        produkti.setFotoProdukti(produktiDto.getFotoProdukti());
        produkti.setEmri(produktiDto.getEmri());
        produkti.setArtisti(produktiDto.getArtisti());
        produkti.setCmimi(produktiDto.getCmimi());
        produkti.setKategoria(produktiDto.getKategoria());
        produkti.setPershkrimi(produktiDto.getPershkrimi());
        produkti.setStatusi(produktiDto.getStatusi());
        produkti.setVleresimiTotal(produktiDto.getVleresimiTotal());
        produkti.setWatermark(produktiDto.getWatermark());

        return produktiRepository.save(produkti);
    }

    public Produkti getProduktiById(Long produktId) {
        return produktiRepository.findById(produktId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produkt not found with id: " + produktId));
    }

    public List<Produkti> getAllProdukti() {
        return produktiRepository.findAll();
    }

    public Produkti updateProdukti(ProduktiDto produktiDto, Long produktId) {
        Optional<Produkti> optionalProdukti = produktiRepository.findById(produktId);
        if (optionalProdukti.isPresent()) {
            Produkti produkti = optionalProdukti.get();
            produkti.setEmri(produktiDto.getEmri());
            produkti.setArtisti(produktiDto.getArtisti());
            produkti.setCmimi(produktiDto.getCmimi());
            produkti.setKategoria(produktiDto.getKategoria());
            produkti.setPershkrimi(produktiDto.getPershkrimi());
            produkti.setStatusi(produktiDto.getStatusi());
            produkti.setWatermark(produktiDto.getWatermark());
            return produktiRepository.save(produkti);
        }else{
            throw new EntityNotFoundException("Produkt not found with id: " + produktId);
        }
    }

    public boolean deleteProdukti(Long produktId) {
        if(!produktiRepository.existsById(produktId)){
            return false;
        }
        produktiRepository.deleteById(produktId);
        return true;
    }

    public List<Produkti> getProduktiByKategoria(Long kategoriId) {
        return produktiRepository.findProduktiByKategoria(kategoriId);
    }

    public List<Produkti> getProduktiByArtisti(Long artistId) {
        return produktiRepository.findProduktiByArtisti(artistId);
    }


}
