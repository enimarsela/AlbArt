package services;

import dtos.TransportiDto;
import models.Transporti;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.TransportiRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransportiServiceTest {

    @Mock
    private TransportiRepository repo;

    @InjectMocks
    private TransportiService service;

    public TransportiServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_success() {
        TransportiDto dto = new TransportiDto();
        dto.setEmri("DHL");

        Transporti saved = new Transporti();
        saved.setTransportiId(1L);

        when(repo.save(any())).thenReturn(saved);

        Transporti result = service.create(dto);

        assertThat(result.getTransportiId()).isEqualTo(1L);
    }

    @Test
    void getById_success() {
        Transporti t = new Transporti();
        t.setTransportiId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        Transporti result = service.getById(1L);

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
    }
}
