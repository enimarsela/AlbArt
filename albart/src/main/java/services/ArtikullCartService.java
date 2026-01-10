package services;

import dtos.ArtikullCartDto;
import jakarta.persistence.EntityNotFoundException;
import models.ArtikullCart;
import org.springframework.stereotype.Service;
import repository.ArtikullCartRepository;
import repository.ProduktiRepository;
import strategy.DiscountCartStrategy;
import strategy.NormalCartStrategy;
import strategy.ShoppingCart;
import strategy.VipCartStrategy;

import java.util.List;

@Service
public class ArtikullCartService {

    private final ArtikullCartRepository repo;
    private final ProduktiRepository produktiRepository;
    private final ShoppingCart cart = new ShoppingCart();

    public ArtikullCartService(ArtikullCartRepository repo, ProduktiRepository produktiRepository){
        this.repo = repo;
        this.produktiRepository = produktiRepository;
    }


    public ArtikullCart create(ArtikullCartDto dto){
        ArtikullCart a = new ArtikullCart();
        a.setProdukti(dto.getProdukti());
        a.setSasia(dto.getSasia());
        return repo.save(a);
    }

    public ArtikullCart getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artikulli nuk u gjet " + id));
    }

    public ArtikullCart update(Long id, ArtikullCartDto dto){
        ArtikullCart a = getById(id);
        a.setProdukti(dto.getProdukti());
        a.setSasia(dto.getSasia());
        return repo.save(a);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    //SHOPPING CART
    public List<ArtikullCart> getCartByKlient(Long klientId){
        return repo.findByKlientId(klientId);
    }

    public void clearCart(Long klientId){
        repo.deleteByKlientId(klientId);
    }

    public double calculateCartTotal(Long klientId, String strategy){

        List<ArtikullCart> cartItems = repo.findByKlientId(klientId);

        double total = cartItems.stream()
                .mapToDouble(item -> item.getProdukti().getCmimi() * item.getSasia())
                .sum();

        switch (strategy.toLowerCase()){
            case "vip" -> cart.setStrategy(new VipCartStrategy());
            case "discount" -> cart.setStrategy(new DiscountCartStrategy(0.10));
            default -> cart.setStrategy(new NormalCartStrategy());
        }

        return cart.execute(total);
    }
}
