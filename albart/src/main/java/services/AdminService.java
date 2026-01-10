package services;

import dtos.AdminDto;
import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.Admin;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.AdminRepository;
import repository.UserRepository;

import java.util.List;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private UserService userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // REGISTER
    public Admin registerAdmin(UserDto userDto) {
        if (adminRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Ky email është tashmë i regjistruar!");
        }

        User user = new User();
        user.setEmri(userDto.getEmri());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoli(userDto.getRoli());

        Admin admin = new Admin();
        admin.setUser(user);

        return adminRepository.save(admin);
    }

    // LOGIN
    public Admin login(UserDto userDto) {
        Admin admin = adminRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email nuk ekziston!"));

        boolean passwordMatch = passwordEncoder.matches(userDto.getPassword(), admin.getUser().getPassword());

        if (!passwordMatch) {
            throw new RuntimeException("Incorrect password!");
        }

        return admin;
    }

    // CRUD BAZË
    public Admin getById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admini nuk u gjet me id: " + id));
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin update(Long id, AdminDto adminDto) {
        Admin admin = getById(id);
        admin.getUser().setEmri(adminDto.getUser().getEmri());
        admin.getUser().setEmail(adminDto.getUser().getEmail());
        admin.getUser().setPassword(passwordEncoder.encode(adminDto.getUser().getPassword()));
        admin.getUser().setRoli(adminDto.getUser().getRoli());

        return adminRepository.save(admin);
    }

    public boolean deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            return false;
        }
        adminRepository.deleteById(id);
        return true;
    }
}
