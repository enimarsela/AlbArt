package services;

import dtos.ArtistiDto;
import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.Artisti;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ArtistiRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistiServiceTest {

    @Mock
    private ArtistiRepository repo;

    @InjectMocks
    private ArtistiService service;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    void registerArtist_success() {
        ArtistiDto dto = new ArtistiDto();
        User user = new User();
        user.setEmail("a@test.com");
        user.setPassword("123");
        dto.setUser(user);

        when(repo.existsByEmail(user.getEmail())).thenReturn(false);
        when(repo.save(any())).thenReturn(new Artisti());

        Artisti result = service.registerArtist(dto);
        assertNotNull(result);
    }

    @Test
    void registerArtist_emailExists_throws() {
        ArtistiDto dto = new ArtistiDto();
        User user = new User();
        user.setEmail("a@test.com");
        dto.setUser(user);

        when(repo.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> service.registerArtist(dto));
    }

    @Test
    void login_success() {
        UserDto login = new UserDto("a@test.com", "123", "password", "ARTIST");

        Artisti artist = new Artisti();
        artist.setUser(new User());
        artist.getUser().setPassword("encoded");

        when(repo.findByEmail(login.getEmail())).thenReturn(Optional.of(artist));
        when(encoder.matches(login.getPassword(), "encoded")).thenReturn(true);

        assertNotNull(service.login(login));
    }

    @Test
    void login_badPassword_throws() {
        UserDto login = new UserDto("a@test.com", "123", "password", "ARTIST");

        Artisti artist = new Artisti();
        artist.setUser(new User());
        artist.getUser().setPassword("encoded");

        when(repo.findByEmail(login.getEmail())).thenReturn(Optional.of(artist));
        when(encoder.matches(login.getPassword(), "encoded")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.login(login));
    }

    @Test
    void getArtistById_notFound_throws() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.getArtistById(1L));
    }
}
