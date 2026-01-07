package controllers;

import dtos.ArtistiDto;
import dtos.UserDto;
import models.Artisti;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import services.ArtistiService;

import java.util.List;

@RestController
@RequestMapping("/api/artisti")
@CrossOrigin
public class ArtistiController {

    private ArtistiService artistiService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<Artisti> registerArtist(@RequestBody ArtistiDto artistiDto) {
        Artisti artist = artistiService.registerArtist(artistiDto);
        return ResponseEntity.ok(artist);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Artisti> login(@RequestBody UserDto userDto) {
        Artisti artist = artistiService.login(userDto);
        return ResponseEntity.ok(artist);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Artisti>> getAll() {
        return ResponseEntity.ok(artistiService.getAllArtists());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Artisti> getById(@PathVariable Long id) {
        return ResponseEntity.ok(artistiService.getArtistById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Artisti> update(@PathVariable Long id, @RequestBody ArtistiDto artistiDto) {

        return ResponseEntity.ok(artistiService.updateArtist(id, artistiDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = artistiService.deleteArtist(id);

        if (!deleted)
            return ResponseEntity.badRequest().body("Artisti nuk u gjet!");

        return ResponseEntity.ok("Artisti u fshi me sukses!");
    }
}

