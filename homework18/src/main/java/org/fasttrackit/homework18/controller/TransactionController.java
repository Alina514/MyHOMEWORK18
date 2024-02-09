package org.fasttrackit.homework18.controller;
import org.fasttrackit.homework18.model.Transaction;
import org.fasttrackit.homework18.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transaction")

public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getAllTransaction(String id) {
        return service.getAllTransaction();
    }

    @GetMapping("{id}")
    public Transaction getOneTransaction( @PathVariable String id) {
        return service.getTransactionById(id);
    }

    @PostMapping
    public Transaction addTransaction (@RequestBody Transaction newTransaction){
        return service.addNewTransaction(newTransaction);
    }

    @DeleteMapping("{id}")
    public Transaction deleteById(@PathVariable String id) {
        return service.deleteById(id);
    }
    @PutMapping("{id}")
    public Transaction replaceTransaction(@PathVariable String id, @RequestBody Transaction replaceTransaction){
        return service.replaceTransaction(id, replaceTransaction);
    }

    @GetMapping("reports/type")
    public Map<String,List<Transaction>> getListFromTypeOfTransaction(){
        return service.groupByType();
    }

    @GetMapping("reports/product")
    public Map<String,List<Transaction>> getListFromProductOfTransaction(){
        return service.groupByProduct();
    }
}
