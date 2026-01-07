package services;

import dtos.UserDto;
import org.springframework.stereotype.Service;

import dtos.ArtistiDto;
import jakarta.persistence.EntityNotFoundException;
import models.Artisti;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.ArtistiRepository;

import java.util.List;

@Service
public class ArtistiService {

    private ArtistiRepository artistiRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // REGISTER
    public Artisti registerArtist(ArtistiDto artistDto) {
        if (artistiRepository.existsByEmail(artistDto.getUser().getEmail())) {
            throw new RuntimeException("Ky email është tashmë i regjistruar!");
        }

        Artisti artist = new Artisti();
        artist.getUser().setEmri(artistDto.getUser().getEmri());
        artist.getUser().setEmail(artistDto.getUser().getEmail());
        artist.getUser().setPassword(passwordEncoder.encode(artistDto.getUser().getPassword()));
        artist.getUser().setRoli(artistDto.getUser().getRoli());
        artist.setDescription(artistDto.getDescription());
        artist.setEksperienca(artistDto.getEksperienca());
        artist.setVleresimiTotal(artistDto.getVleresimiTotal());

        return artistiRepository.save(artist);
    }

    // LOGIN
    public Artisti login(UserDto userDto) {
        Artisti artist = artistiRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email nuk ekziston!"));

        boolean passwordMatch = passwordEncoder.matches(userDto.getPassword(), artist.getUser().getPassword());

        if (!passwordMatch) {
            throw new RuntimeException("Incorrect password!");
        }

        return artist;
    }

    // CRUD BAZË
    public Artisti getArtistById(Long id) {
        return artistiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist nuk u gjet me id: " + id));
    }

    public List<Artisti> getAllArtists() {
        return artistiRepository.findAll();
    }

    public Artisti updateArtist(Long id, ArtistiDto artistDto) {
        Artisti artist = getArtistById(id);

        artist.getUser().setEmri(artistDto.getUser().getEmri());
        artist.getUser().setEmail(artistDto.getUser().getEmail());
        artist.getUser().setPassword(passwordEncoder.encode(artistDto.getUser().getPassword()));
        artist.getUser().setRoli(artistDto.getUser().getRoli());
        artist.setDescription(artistDto.getDescription());
        artist.setEksperienca(artistDto.getEksperienca());
        artist.setVleresimiTotal(artistDto.getVleresimiTotal());

        return artistiRepository.save(artist);
    }

    public boolean deleteArtist(Long id) {
        if (!artistiRepository.existsById(id)) {
            return false;
        }
        artistiRepository.deleteById(id);
        return true;
    }
}
