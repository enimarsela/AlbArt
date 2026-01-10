package services;

import dtos.PorosiDto;
import models.Porosi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.PorosiRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PorosiServiceTest {

    @Mock
    private PorosiRepository repo;

    @InjectMocks
    private PorosiService service;

    public PorosiServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_success() {
        PorosiDto dto = new PorosiDto();
        dto.setStatusi("CREATED");

        Porosi saved = new Porosi();
        saved.setPorosiId(1L);

        when(repo.save(any())).thenReturn(saved);

        Porosi result = service.create(dto);

        assertThat(result.getPorosiId()).isEqualTo(1L);
        verify(repo).save(any(Porosi.class));
    }

    @Test
    void getById_success() {
        Porosi p = new Porosi();
        p.setPorosiId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Porosi result = service.getById(1L);

        assertThat(result).isNotNull();
    }

    @Test
    void getById_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(1L))
                .isInstanceOf(EntityNotFoundException.class);
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
        verify(repo, never()).deleteById(any());
    }
}
