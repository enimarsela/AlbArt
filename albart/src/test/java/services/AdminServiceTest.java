package services;

import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.Admin;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService service;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    void registerAdmin_emailExists_throws() {
        UserDto dto = new UserDto();
        User user = new User();
        user.setEmail("test@test.com");

        when(adminRepository.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> service.registerAdmin(dto));
    }

    @Test
    void login_success() {
        UserDto dto = new UserDto("test@test.com", "123", "password", "ADMIN");
        Admin admin = new Admin();
        admin.setUser(new User());
        admin.getUser().setPassword("encoded");

        when(adminRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(admin));
        when(encoder.matches(dto.getPassword(), "encoded")).thenReturn(true);

        Admin result = service.login(dto);
        assertNotNull(result);
    }

    @Test
    void login_wrongPassword_throws() {
        UserDto dto = new UserDto("test@test.com", "wrong", "password", "ADMIN");
        Admin admin = new Admin();
        admin.setUser(new User());
        admin.getUser().setPassword("encoded");

        when(adminRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(admin));
        when(encoder.matches(dto.getPassword(), "encoded")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.login(dto));
    }

    @Test
    void getById_notFound_throws() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.getById(1L));
    }
}
