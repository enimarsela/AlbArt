package services;

import dtos.KategoriaDto;
import jakarta.persistence.EntityNotFoundException;
import models.Kategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.KategoriaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KategoriaServiceTest {

    @Mock
    private KategoriaRepository repo;

    @InjectMocks
    private KategoriaService service;

    @Test
    void getById_notFound_throws() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void delete_notExists_false() {
        when(repo.existsById(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }

    @Test
    void update_success() {
        KategoriaDto dto = new KategoriaDto();
        dto.setEmri("New");
        dto.setPershkrimi("Desc");

        Kategoria k = new Kategoria();
        k.setKategoriId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(k));
        when(repo.save(k)).thenReturn(k);

        assertNotNull(service.update(1L, dto));
    }
}
