package com.example.Salimhan.service;

import com.example.Salimhan.entities.User;
import com.example.Salimhan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Salimhan.entities.Transaction;
import java.util.List;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EndPointService endPointService;
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newkisi) {
        return   userRepository.save(newkisi);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserbyId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kisi id not found"));
    }

    public void UpdateUser(Long id, User newuser) {
        User oldkisi = getUserbyId(id);
        oldkisi.setName(newuser.getName());
        oldkisi.setUsername(newuser.getUsername());
        oldkisi.setPassword(newuser.getPassword());
        userRepository.save(oldkisi);
    }
    public void updateMasraf(User user) {
        endPointService.updateMasraf(user);
    }

}
