package org.fasttrackit.homework18.service;
import org.fasttrackit.homework18.controller.TransactionController;
import org.springframework.stereotype.Service;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.fasttrackit.homework18.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.util.UUID.randomUUID;

@Service
public class TransactionService {
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionService() {
        this.transactions = List.of(
                new Transaction(generateRandomTransactionId(), "Phone", "buy", 350.0),
                new Transaction(generateRandomTransactionId(), "HeadPhone", "sell", 890.0),
                new Transaction(generateRandomTransactionId(), "Tablet", "sell", 590.0));
    }
    private String generateRandomTransactionId() {
        return UUID.randomUUID().toString();
    }

    public List<Transaction> getAllTransaction() {
        return transactions;
    }

    public Transaction getTransactionById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id:%s was not found".formatted(id)));
    }

   public List<Transaction> getAllTransactionByProduct(String product){
        return transactions.stream()
                .filter(transaction -> transaction.product().equals((product)))
                .findFirst()
                .stream().toList();
   }

    public List<Transaction> getAllTransactionByType(String type){
        return transactions.stream()
                .filter(transaction -> transaction.type().equals(type))
                .toList();

    }

    public List<Transaction> getTransactionsByAmount(Double minAmount, Double maxAmount) {
        return transactions.stream()
                .filter(transaction -> minAmount == null || transaction.amount() >= minAmount)
                .filter(transaction -> maxAmount == null || transaction.amount() <= maxAmount)
                .toList();
    }

    public Transaction addNewTransaction(Transaction newTransaction) {
        transactions.add(newTransaction);
        return newTransaction;
    }

    public Transaction replaceTransaction(String id, Transaction replaceTransaction){
       Transaction foundTransaction = getTransactionById(id);
            transactions.remove(foundTransaction);

        Transaction updateTransaction = Transaction.builder()
                .id(foundTransaction.id())
                .product(replaceTransaction.product())
                .type(replaceTransaction.type())
                .amount(replaceTransaction.amount())
                .build();
        transactions.add(updateTransaction);
        return updateTransaction;
    }

    public Transaction deleteById(String id) {
       Transaction transactionToBeDeleted = getTransactionById(id);
        transactions.remove(transactionToBeDeleted);
        return transactionToBeDeleted;
    }


    public Map<String, List<Transaction>> groupByType() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public Map<String, List<Transaction>> groupByProduct() {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::product));
    }
}
