package com.example.demo.Controllers;

import com.example.demo.DTOS.TransactionRecordDto;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody @Valid TransactionRecordDto transactionRecordDto) {
        var transaction = new Transaction();
        BeanUtils.copyProperties(transactionRecordDto, transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionRepository.save(transaction));
    }

    @GetMapping
    public ResponseEntity<?> getTransaction(
            @RequestParam(name = "id", required = false) Integer id) {

        if (id != null) {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            return transaction.map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", "Transação com id " + id + " não encontrada");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Transaction) response);
                    });
        }

        List<Transaction> allTransactions = transactionRepository.findAll();
        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        return transaction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody @Valid TransactionRecordDto transactionRecordDto) {
        Optional<Transaction> existingTransactionOpt = transactionRepository.findById(id);

        if (!existingTransactionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Transaction existingTransaction = existingTransactionOpt.get();
        BeanUtils.copyProperties(transactionRecordDto, existingTransaction, "id");
        transactionRepository.save(existingTransaction);

        return ResponseEntity.ok(existingTransaction);
    }
}
