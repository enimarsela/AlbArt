package services;

import dtos.UserDto;
import models.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_success() {
        User u = new User();
        u.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(u));

        User result = service.getById(1L);

        assertThat(result).isNotNull();
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void update_success() {
        User existing = new User();
        existing.setId(1L);

        UserDto dto = new UserDto();
        dto.setEmri("Test");
        dto.setEmail("test@test.com");
        dto.setPassword("1234");
        dto.setRoli("ADMIN");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(existing);

        User updated = service.update(1L, dto);

        assertThat(updated.getEmri()).isEqualTo("Test");
        verify(repo).save(existing);
    }

    @Test
    void delete_success() {
        when(repo.existsById(1L)).thenReturn(true);

        boolean result = service.delete(1L);

        assertThat(result).isTrue();
        verify(repo).deleteById(1L);
    }

    @Test
    void delete_fail() {
        when(repo.existsById(1L)).thenReturn(false);

        assertThat(service.delete(1L)).isFalse();
    }
}
