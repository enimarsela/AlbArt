package services;

import org.springframework.stereotype.Service;

import dtos.KlientDto;
import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.Klient;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.KlientRepository;
import repository.UserRepository;

import java.util.List;

@Service
public class KlientService {

    private final KlientRepository klientRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public KlientService(KlientRepository klientRepository, UserRepository userRepository) {
        this.klientRepository = klientRepository;
        this.userRepository = userRepository;
    }

    // REGISTER
    public Klient register(KlientDto klientDto) {

        if (userRepository.existsByEmail(klientDto.getUser().getEmail())) {
            throw new RuntimeException("Ky email është tashmë i regjistruar!");
        }

        User user = new User();
        user.setEmri(klientDto.getUser().getEmri());
        user.setEmail(klientDto.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(klientDto.getUser().getPassword()));
        user.setRoli("KLIENT");

        userRepository.save(user);

        Klient klient = new Klient();
        klient.setPreferenca(klientDto.getPreferenca());
        klient.setUser(user);

        return klientRepository.save(klient);
    }

    // LOGIN
    public Klient login(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email nuk ekziston!"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password i gabuar!");
        }

        return klientRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Klient nuk u gjet për këtë user!"));
    }

    // CRUD
    public Klient getById(Long id) {
        return klientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Klient nuk u gjet me id: " + id));
    }

    public List<Klient> getAll() {
        return klientRepository.findAll();
    }

    public Klient update(Long id, KlientDto klientDto) {
        Klient klient = getById(id);

        klient.setPreferenca(klientDto.getPreferenca());

        klient.getUser().setEmri(klientDto.getUser().getEmri());
        klient.getUser().setEmail(klientDto.getUser().getEmail());
        klient.getUser().setPassword(passwordEncoder.encode(klientDto.getUser().getPassword()));

        return klientRepository.save(klient);
    }

    public boolean delete(Long id) {
        if (!klientRepository.existsById(id)) {
            return false;
        }
        klientRepository.deleteById(id);
        return true;
    }
}
