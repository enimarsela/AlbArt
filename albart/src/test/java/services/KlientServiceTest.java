package services;

import dtos.KlientDto;
import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.Klient;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.KlientRepository;
import repository.UserRepository;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KlientServiceTest {

    @Mock
    private KlientRepository klientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private KlientService klientService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ================= REGISTER =================
    @Test
    void register_success() {
        KlientDto dto = new KlientDto();
        User userDto = new User();
        userDto.setEmri("Test User");
        userDto.setEmail("test@test.com");
        userDto.setPassword("1234");
        dto.setUser(userDto);
        dto.setPreferenca(List.of("Pikture", "Artizanale"));

        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(klientRepository.save(any(Klient.class))).thenAnswer(inv -> inv.getArgument(0));

        Klient klient = klientService.register(dto);

        assertNotNull(klient);
        assertEquals(List.of("Pikture", "Artizanale"), klient.getPreferenca());
        assertNotNull(klient.getUser());
        assertEquals("test@test.com", klient.getUser().getEmail());
    }

    @Test
    void register_emailExists_throwsException() {
        KlientDto dto = new KlientDto();
        User u = new User();
        u.setEmail("exists@test.com");
        dto.setUser(u);

        when(userRepository.existsByEmail("exists@test.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> klientService.register(dto));
    }

    // ================= LOGIN =================
    @Test
    void login_success() {
        UserDto dto = new UserDto();
        dto.setEmail("user@test.com");
        dto.setPassword("1234");

        User user = new User();
        user.setEmail("user@test.com");
        user.setPassword(encoder.encode("1234"));

        Klient klient = new Klient();
        klient.setUser(user);

        when(klientRepository.findByEmail("user@test.com")).thenReturn(Optional.of(klient));

        Klient result = klientService.login(dto);

        assertNotNull(result);
        assertEquals("user@test.com", result.getUser().getEmail());
    }

    @Test
    void login_emailNotFound_throwsException() {
        UserDto dto = new UserDto();
        dto.setEmail("missing@test.com");

        when(klientRepository.findByEmail("missing@test.com")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> klientService.login(dto));
    }

    @Test
    void login_wrongPassword_throwsException() {
        UserDto dto = new UserDto();
        dto.setEmail("user@test.com");
        dto.setPassword("wrong");

        User user = new User();
        user.setEmail("user@test.com");
        user.setPassword(encoder.encode("correct"));

        Klient klient = new Klient();
        klient.setUser(user);

        when(klientRepository.findByEmail("user@test.com"))
                .thenReturn(Optional.of(klient));

        assertThrows(RuntimeException.class,
                () -> klientService.login(dto));
    }

    // ================= CRUD =================
    @Test
    void getById_success() {
        Klient klient = new Klient();
        klient.setKlientId(1L);

        when(klientRepository.findById(1L)).thenReturn(Optional.of(klient));

        Klient result = klientService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getKlientId());
    }

    @Test
    void getById_notFound() {
        when(klientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> klientService.getById(1L));
    }

    @Test
    void getAll_success() {
        when(klientRepository.findAll()).thenReturn(List.of(new Klient(), new Klient()));
        assertEquals(2, klientService.getAll().size());
    }

    @Test
    void update_success() {
        Klient existing = new Klient();
        existing.setKlientId(1L);
        User user = new User();
        existing.setUser(user);

        when(klientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(klientRepository.save(any(Klient.class))).thenAnswer(inv -> inv.getArgument(0));

        KlientDto dto = new KlientDto();
        dto.setPreferenca(List.of("Pikture", "Skulpture"));
        User u = new User();
        u.setEmri("Updated");
        u.setEmail("u@test.com");
        u.setPassword("pass");
        dto.setUser(u);

        Klient result = klientService.update(1L, dto);

        assertEquals(List.of("Pikture", "Skulpture"), result.getPreferenca());
        assertEquals("u@test.com", result.getUser().getEmail());
    }

    @Test
    void delete_success() {
        when(klientRepository.existsById(1L)).thenReturn(true);

        boolean result = klientService.delete(1L);

        assertTrue(result);
        verify(klientRepository).deleteById(1L);
    }

    @Test
    void delete_notFound() {
        when(klientRepository.existsById(1L)).thenReturn(false);

        boolean result = klientService.delete(1L);

        assertFalse(result);
        verify(klientRepository, never()).deleteById(any());
    }
}
