package controllers;

import dtos.TransportiDto;
import models.Transporti;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import services.TransportiService;

import java.util.List;

@RestController
@RequestMapping("api/transporti")
public class TransportiController {

    private final TransportiService service;

    public TransportiController(TransportiService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Transporti>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transporti> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transporti> create(@RequestBody TransportiDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Transporti> update(@PathVariable Long id,@RequestBody TransportiDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
}
