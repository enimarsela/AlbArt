package services;

import dtos.UserDto;
import jakarta.persistence.EntityNotFoundException;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User nuk u gjet me id: " + id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(Long id, UserDto userDto) {
        User user = getById(id);

        user.setEmri(userDto.getEmri());
        user.setEmail(userDto.getEmail());

        if(userDto.getPassword() != null && !userDto.getPassword().isBlank()){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        user.setRoli(userDto.getRoli());

        return userRepository.save(user);
    }

    public boolean delete(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }
}
