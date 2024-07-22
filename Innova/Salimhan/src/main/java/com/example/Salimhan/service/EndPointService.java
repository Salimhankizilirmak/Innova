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
public class EndPointService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    // Tüm transactionların toplamını hesaplamak için
    public void updateMasraf(User user) {
        List<Transaction> transactions = transactionRepository.findByUserId(Optional.ofNullable(user.getId()));
        Long totalMasraf = transactions.stream().mapToLong(Transaction::getAmount).sum();
        user.setMasraf(totalMasraf);
        userRepository.save(user);
    }
    public void updateMasraf(Long id, Transaction newMasraf) {
        Transaction oldMasraf = getMasrafbyId(id);
        oldMasraf.setDescription(newMasraf.getDescription());
        oldMasraf.setAmount(newMasraf.getAmount());
        transactionRepository.save(oldMasraf);
    }
    public Transaction getMasrafbyId(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction id not found"));
    }
    public List<Transaction> getMasraflar(Optional<Long> userId) {
        if (userId.isPresent()) {
            return transactionRepository.findByUserId(userId);
        }
        return transactionRepository.findAll();
    }


}
