package com.example.Salimhan.service;

import com.example.Salimhan.entities.User;
import com.example.Salimhan.entities.Transaction;
import com.example.Salimhan.repository.UserRepository;
import com.example.Salimhan.repository.TransactionRepository;
import com.example.Salimhan.requests.TransactionCreateRequests;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private UserService userService;
    private final EndPointService endPointService;
    private UserRepository userRepository;
    public List<Transaction> getMasraflar(Optional<Long> userId) {
        if (userId.isPresent()) {
            return transactionRepository.findByUserId(userId);
        }
        return transactionRepository.findAll();
    }

    public Transaction createMasraf(TransactionCreateRequests newmasrafrequest) {
        User user = userService.getUserbyId(newmasrafrequest.getUserId());

        Transaction toSave = new Transaction();
        toSave.setId(newmasrafrequest.getId());
        toSave.setAmount(newmasrafrequest.getAmount());
        toSave.setDescription(newmasrafrequest.getDescription());
        toSave.setUser(user);
        userService.updateMasraf(user);

        return   transactionRepository.save(toSave);

    }

    public void deleteMasraf(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction getMasrafbyId(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kisi id not found"));
    }

    public void updateMasraf(Long id, Transaction newMasraf) {
        endPointService.updateMasraf(id, newMasraf);
    }
}
