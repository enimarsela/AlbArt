package controllers;

import dtos.KlientDto;
import dtos.UserDto;
import models.Klient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import services.KlientService;

import java.util.List;

@RestController
@RequestMapping("/api/klient")
@CrossOrigin
public class KlientController {

    private final KlientService klientService;

    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<Klient> register(@RequestBody KlientDto klientDto) {
        return ResponseEntity.ok(klientService.register(klientDto));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Klient> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(klientService.login(userDto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Klient>> getAll() {
        return ResponseEntity.ok(klientService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Klient> getById(@PathVariable Long id) {
        return ResponseEntity.ok(klientService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Klient> update(@PathVariable Long id, @RequestBody KlientDto klientDto) {
        return ResponseEntity.ok(klientService.update(id, klientDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = klientService.delete(id);

        if (!deleted)
            return ResponseEntity.badRequest().body("Klient nuk u gjet!");

        return ResponseEntity.ok("Klient u fshi me sukses!");
    }
}

