package services;

import dtos.PagesaDto;
import models.Pagesa;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.PagesaRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PagesaServiceTest {

    @Mock
    private PagesaRepository repo;

    @InjectMocks
    private PagesaService service;

    public PagesaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_success() {
        PagesaDto dto = new PagesaDto();
        dto.setKarta(List.of("1234-1111-5678-2222", "09/27"));
        dto.setShuma(50.0);
        dto.setStatusi("PAID");

        Pagesa saved = new Pagesa();
        saved.setPagesaId(1L);

        when(repo.save(any())).thenReturn(saved);

        Pagesa result = service.create(dto);

        assertThat(result.getPagesaId()).isEqualTo(1L);
        verify(repo).save(any(Pagesa.class));
    }

    @Test
    void getById_success() {
        Pagesa p = new Pagesa();
        p.setPagesaId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(p));

        Pagesa result = service.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPagesaId()).isEqualTo(1L);
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

        boolean result = service.delete(1L);

        assertThat(result).isFalse();
        verify(repo, never()).deleteById(any());
    }

    @Test
    void update_success() {
        Pagesa existing = new Pagesa();
        existing.setPagesaId(1L);

        PagesaDto dto = new PagesaDto();
        dto.setKarta(List.of("9999-4444"));
        dto.setShuma(99.9);
        dto.setStatusi("DONE");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(existing);

        Pagesa result = service.update(1L, dto);

        assertThat(result.getKarta()).contains("9999-4444");
        verify(repo).save(existing);
    }

}
