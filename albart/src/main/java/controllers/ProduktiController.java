package controllers;

import dtos.ProduktiDto;
import models.Produkti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProduktiService;

import java.util.List;

@RestController
@RequestMapping("api/produkti")
public class ProduktiController {
    @Autowired
    private ProduktiService produktiService;

    @GetMapping
    public ResponseEntity<List<Produkti>> getAllProdukt(){
        return new ResponseEntity<>(produktiService.getAllProdukti(), HttpStatus.OK);
    }
    @GetMapping("kategoria/{kategoriaId}")
    public ResponseEntity<List<Produkti>> getAllProduktibyKategoria(@PathVariable("kategoriaId") Long kategoriaId){
        return new ResponseEntity<>(produktiService.getProduktiByKategoria(kategoriaId), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produkti> getProduktibyId(@PathVariable("id") Long produktId){
        Produkti produkti = produktiService.getProduktiById(produktId);
        return new ResponseEntity<>(produkti, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produkti> createProdukt(@RequestBody ProduktiDto produktiDto){
        Produkti produkti = produktiService.createProdukt(produktiDto);
        return new ResponseEntity<>(produkti, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public  ResponseEntity<Produkti> updateProdukti(@PathVariable("id") Long produktId, ProduktiDto produktiDto){
        Produkti produktiUpdated = produktiService.updateProdukti(produktiDto, produktId);
        return new ResponseEntity<>(produktiUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProdukti(@PathVariable("id") Long produktId){
        if(!produktiService.deleteProdukti(produktId)){
            return new ResponseEntity<>("Produkti not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Produkti deleted successfully!", HttpStatus.OK);

        }
}
