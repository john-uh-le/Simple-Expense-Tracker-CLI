package com.john.simpleexpensetracker.controller;

import com.john.simpleexpensetracker.model.Expense;
import com.john.simpleexpensetracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // GET all expenses
    @GetMapping
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // POST a new expense
    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {

    if (expense.getCategory() == null || expense.getCategory().isBlank()) {
            return ResponseEntity.badRequest().body("Category is required.");
        }

        if (expense.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Amount must be greater than 0.");
        }

        if (expense.getDate() == null) {
            return ResponseEntity.badRequest().body("Date is required. Use format yyyy-MM-dd.");
        }

        expenseService.addExpense(expense);

        return ResponseEntity.ok(expense);
    }

    // GET total expense
    @GetMapping("/total")
    public ResponseEntity<?> getTotalExpense() {
        return ResponseEntity.ok(expenseService.getTotalExpense());
    }

    // GET total by category
    @GetMapping("/categories")
    public ResponseEntity<?> getTotalByCategory() {
        return ResponseEntity.ok(expenseService.getTotalByCategory());
    }

    // GET expense trend by date
    @GetMapping("/dates")
    public ResponseEntity<?> getExpenseTrendByDate() {
        return ResponseEntity.ok(expenseService.getExpenseTrendByDate());
    }

    // GET highest category
    @GetMapping("/highest")
    public ResponseEntity<?> getHighestCategory() {
        String test = expenseService.getHighestCategory();
        return ResponseEntity.ok(expenseService.getHighestCategory());
    }

    // GET lowest category
    @GetMapping("/lowest")
    public ResponseEntity<?> getLowestCategory() {
        return ResponseEntity.ok(expenseService.getLowestCategory());
    }
}