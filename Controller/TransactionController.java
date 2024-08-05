package ahmetcetinkaya.Solvo.Controller;

import ahmetcetinkaya.Solvo.Entity.Transaction;
import ahmetcetinkaya.Solvo.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")

public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/exceeded-limit")
    public ResponseEntity<List<Transaction>> getTransactionsByLimitExceeded() {
        List<Transaction> transactions = transactionService.getTransactionsByLimitExceeded();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Transaction> transactions = transactionService.getTransactionsByDate(startDate, endDate);
        return ResponseEntity.ok(transactions);
    }
}

