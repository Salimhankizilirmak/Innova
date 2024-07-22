package com.example.Salimhan.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionCreateRequests {
    Long id;
    String description;
    Long amount;
    Long userId;
}
