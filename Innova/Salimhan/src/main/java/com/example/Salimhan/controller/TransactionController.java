package com.example.Salimhan.controller;

import com.example.Salimhan.entities.Transaction;
import com.example.Salimhan.requests.TransactionCreateRequests;
import com.example.Salimhan.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    @GetMapping
    public ResponseEntity<List<Transaction>>getMasraflar(@RequestParam Optional<Long> userId){

        return new ResponseEntity<>(transactionService.getMasraflar(userId), OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getMasraf(@PathVariable Long id)
    {
        return new ResponseEntity<>(getMasrafbyId(id), OK);

    }

    @PostMapping
    public ResponseEntity<Transaction> createMasraf(@RequestBody TransactionCreateRequests newmasrafrequest){
        return new ResponseEntity<>(transactionService.createMasraf(newmasrafrequest), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatemasraf(@PathVariable Long id,@RequestBody Transaction newmasraf){

        transactionService.updateMasraf(id, newmasraf);
        return new ResponseEntity<>(OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasraf(@PathVariable Long id){
        transactionService.deleteMasraf(id);
        return new ResponseEntity<>(OK);
    }
    private Transaction getMasrafbyId(Long id){
        return   transactionService.getMasrafbyId(id);


    }
}
