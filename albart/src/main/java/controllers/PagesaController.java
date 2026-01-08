package controllers;

import dtos.PagesaDto;
import models.Pagesa;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import services.PagesaService;

import java.util.List;

@RestController
@RequestMapping("api/pagesa")
public class PagesaController {

    private final PagesaService service;

    public PagesaController(PagesaService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Pagesa>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pagesa> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pagesa> create(@RequestBody PagesaDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pagesa> update(@PathVariable Long id,@RequestBody PagesaDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
