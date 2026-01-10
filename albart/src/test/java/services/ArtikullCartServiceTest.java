package services;

import jakarta.persistence.EntityNotFoundException;
import models.ArtikullCart;
import models.Produkti;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ArtikullCartRepository;
import repository.ProduktiRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtikullCartServiceTest {

    @Mock
    private ArtikullCartRepository repo;

    @Mock
    private ProduktiRepository produktiRepository;

    @InjectMocks
    private ArtikullCartService service;

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
    void calculateCartTotal_vip() {
        Produkti p = new Produkti();
        p.setCmimi(100);

        ArtikullCart a = new ArtikullCart();
        a.setProdukti(p);
        a.setSasia(1);

        when(repo.findByKlientId(1L)).thenReturn(List.of(a));

        double total = service.calculateCartTotal(1L, "vip");
        assertTrue(total > 0);
    }
}
