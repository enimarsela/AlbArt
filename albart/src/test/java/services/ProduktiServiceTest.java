package services;

import dtos.ProduktiDto;
import jakarta.persistence.EntityNotFoundException;
import models.Produkti;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ProduktiRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduktiServiceTest {

    @Mock
    private ProduktiRepository produktiRepository;

    @InjectMocks
    private ProduktiService produktiService;

    private Produkti produkti;
    private ProduktiDto produktiDto;

    @BeforeEach
    void setUp() {
        produkti = new Produkti();
        produkti.setProduktId(1L);
        produkti.setEmri("Produkt Test");
        produkti.setCmimi(10.0);

        produktiDto = new ProduktiDto();
        produktiDto.setEmri("Produkt Test");
        produktiDto.setCmimi(10.0);
    }

    @Test
    void createProdukt_ShouldSaveProdukt() {
        when(produktiRepository.save(any(Produkti.class))).thenReturn(produkti);

        Produkti saved = produktiService.createProdukt(produktiDto);

        assertNotNull(saved);
        assertEquals("Produkt Test", saved.getEmri());
        verify(produktiRepository, times(1)).save(any(Produkti.class));
    }

    @Test
    void getProduktiById_ShouldReturnProdukt() {
        when(produktiRepository.findById(1L)).thenReturn(Optional.of(produkti));

        Produkti result = produktiService.getProduktiById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getProduktId());
        verify(produktiRepository).findById(1L);
    }

    @Test
    void getProduktiById_ShouldThrowException_WhenNotFound() {
        when(produktiRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> produktiService.getProduktiById(1L));
    }

    @Test
    void getAllProdukti_ShouldReturnList() {
        when(produktiRepository.findAll()).thenReturn(Arrays.asList(produkti));

        List<Produkti> list = produktiService.getAllProdukti();

        assertEquals(1, list.size());
        verify(produktiRepository).findAll();
    }

    @Test
    void updateProdukti_ShouldUpdateSuccessfully() {
        when(produktiRepository.findById(1L)).thenReturn(Optional.of(produkti));
        when(produktiRepository.save(any())).thenReturn(produkti);

        Produkti updated = produktiService.updateProdukti(produktiDto, 1L);

        assertEquals("Produkt Test", updated.getEmri());
        verify(produktiRepository).save(any());
    }

    @Test
    void updateProdukti_ShouldThrow_WhenProduktNotFound() {
        when(produktiRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> produktiService.updateProdukti(produktiDto, 1L));
    }

    @Test
    void deleteProdukti_ShouldReturnTrue_WhenExists() {
        when(produktiRepository.existsById(1L)).thenReturn(true);

        boolean result = produktiService.deleteProdukti(1L);

        assertTrue(result);
        verify(produktiRepository).deleteById(1L);
    }

    @Test
    void deleteProdukti_ShouldReturnFalse_WhenNotExists() {
        when(produktiRepository.existsById(1L)).thenReturn(false);

        boolean result = produktiService.deleteProdukti(1L);

        assertFalse(result);
        verify(produktiRepository, never()).deleteById(any());
    }

    @Test
    void getProduktiByKategoria_ShouldReturnList() {
        when(produktiRepository.findProduktiByKategoria(2L))
                .thenReturn(Arrays.asList(produkti));

        List<Produkti> list = produktiService.getProduktiByKategoria(2L);

        assertEquals(1, list.size());
        verify(produktiRepository).findProduktiByKategoria(2L);
    }

    @Test
    void getProduktiByArtisti_ShouldReturnList() {
        when(produktiRepository.findProduktiByArtisti(3L))
                .thenReturn(Arrays.asList(produkti));

        List<Produkti> list = produktiService.getProduktiByArtisti(3L);

        assertEquals(1, list.size());
        verify(produktiRepository).findProduktiByArtisti(3L);
    }
}
