package controllers;

import dtos.ArtikullCartDto;
import models.ArtikullCart;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import services.ArtikullCartService;

import java.util.List;

@RestController
@RequestMapping("api/cart")
public class ArtikullCartController {

    private final ArtikullCartService service;

    public ArtikullCartController(ArtikullCartService service){
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtikullCart> getById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ArtikullCart> update(@PathVariable Long id,@RequestBody ArtikullCartDto dto){
        return new ResponseEntity<>(service.update(id,dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long id){
        if(!service.delete(id))
            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Removed!", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ArtikullCart> addToCart(@RequestBody ArtikullCartDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    //Merr Cart për Klient
    @GetMapping("/{klientId}")
    public ResponseEntity<List<ArtikullCart>> getCart(@PathVariable Long klientId){
        return new ResponseEntity<>(service.getCartByKlient(klientId), HttpStatus.OK);
    }

    //Fshij Cart
    @DeleteMapping("/{klientId}")
    public ResponseEntity<String> clearCart(@PathVariable Long klientId){
        service.clearCart(klientId);
        return new ResponseEntity<>("Shopping Cart cleared!", HttpStatus.OK);
    }

    //Llogarit Total ne baze te strategjise
    @GetMapping("/{klientId}")
    public ResponseEntity<Double> calculateTotal(
            @PathVariable Long klientId,
            @RequestParam(defaultValue = "normal") String strategy){

        double total = service.calculateCartTotal(klientId, strategy);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
