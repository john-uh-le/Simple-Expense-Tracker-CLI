package com.john.simpleexpensetracker.service;

import com.john.simpleexpensetracker.model.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ExpenseService {

    private ArrayList<Expense> expenses = new ArrayList<>();

    public ExpenseService() {
        addSeedData();
    }

    public void addExpense(Expense expense) {
        if (expense.getCategory() == null || expense.getCategory().isBlank()) {
            return;
        }
        if (expense.getAmount() <= 0) {
            return;
        }
        if (expense.getDate() == null) {
            return;
        }
        expenses.add(expense);

    }

    public ArrayList<Expense> getAllExpenses() {
        return expenses;
    }

    public double getTotalExpense() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    public Map<String, Double> getTotalByCategory() {
        Map<String, Double> categoryTotals = new TreeMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
        }

        return categoryTotals;
    }

    public Map<LocalDate, Double> getExpenseTrendByDate() {
        Map<LocalDate, Double> dateTotals = new TreeMap<>();

        for (Expense expense : expenses) {
            LocalDate date = expense.getDate();
            double amount = expense.getAmount();

            dateTotals.put(date, dateTotals.getOrDefault(date, 0.0) + amount);
        }

        return dateTotals;
    }

    public String getHighestCategory() {
        Map<String, Double> categoryTotals = getTotalByCategory();

        String highestCategory = "";
        double highestAmount = 0;

        for (String category : categoryTotals.keySet()) {
            double total = categoryTotals.get(category);

            if (total > highestAmount) {
                highestAmount = total;
                highestCategory = category;
            }
        }
        return highestCategory;
    }

    public String getLowestCategory() {
        Map<String, Double> categoryTotals = getTotalByCategory();

        String lowestCategory = "";
        double lowestAmount = Double.MAX_VALUE;

        for (String category : categoryTotals.keySet()) {
            double total = categoryTotals.get(category);

            if (total < lowestAmount) {
                lowestAmount = total;
                lowestCategory = category;
            }
        }

        return lowestCategory;
    }

    public void clearExpense() {
        expenses.clear();
    }


    private void addSeedData() {
        expenses.add(new Expense("Food", 12.50, LocalDate.of(2026, 6, 1)));
        expenses.add(new Expense("Gas", 30.00, LocalDate.of(2026, 6, 1)));
        expenses.add(new Expense("Food", 18.75, LocalDate.of(2026, 6, 2)));
        expenses.add(new Expense("Entertainment", 40.00, LocalDate.of(2026, 6, 3)));
        expenses.add(new Expense("Bills", 90.00, LocalDate.of(2026, 6, 4)));
        expenses.add(new Expense("Food", 0.99, LocalDate.of(2026, 6, 4)));
        expenses.add(new Expense("Medical", 125.75, LocalDate.of(2026, 6, 5)));
        expenses.add(new Expense("Gas", 60.00, LocalDate.of(2026, 6, 5)));
        expenses.add(new Expense("Entertainment", 5.00, LocalDate.of(2026, 6, 6)));
    }
}