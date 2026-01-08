package controllers;

import dtos.PorosiDto;
import models.Porosi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.PorosiService;

import java.util.List;

@RestController
@RequestMapping("api/porosi")
public class PorosiController {

    private final PorosiService service;

    public PorosiController(PorosiService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Porosi>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Porosi> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Porosi> create(@RequestBody PorosiDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Porosi> update(@PathVariable Long id,@RequestBody PorosiDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
