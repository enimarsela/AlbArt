package controllers;

import dtos.ArtikullCartDto;
import models.ArtikullCart;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import services.ArtikullCartService;

import java.util.List;

@RestController
@RequestMapping("api/artikullcart")
public class ArtikullCartController {

    private final ArtikullCartService service;

    public ArtikullCartController(ArtikullCartService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<ArtikullCart>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtikullCart> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtikullCart> create(@RequestBody ArtikullCartDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ArtikullCart> update(@PathVariable Long id,@RequestBody ArtikullCartDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
