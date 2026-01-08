package controllers;

import dtos.KategoriaDto;
import models.Kategoria;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import services.KategoriaService;

import java.util.List;

@RestController
@RequestMapping("api/kategoria")
public class KategoriaController {

    private final KategoriaService service;

    public KategoriaController(KategoriaService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Kategoria>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Kategoria> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Kategoria> create(@RequestBody KategoriaDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Kategoria> update(@PathVariable Long id,@RequestBody KategoriaDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
