package com.john.simpleexpensetracker.service;

import com.john.simpleexpensetracker.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseServiceTest {

    ExpenseService subject;

    @BeforeEach
    void beforeEach() {
        subject = new ExpenseService();
    }

    @Test
    void shouldAddValidExpense() {
        subject.addExpense(new Expense("Food", 12.50, LocalDate.of(2026, 6, 1)));

        assertEquals(1, subject.getAllExpenses().size());
    }

    @Test
    void shouldIgnoreInvalidExpenses() {
        subject.addExpense(new Expense(null, 12.50, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("   ", 12.50, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("Food", 0.00, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("Food", -10.00, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("Food", 12.50, null));

        assertEquals(0, subject.getAllExpenses().size());
    }

    @Test
    void shouldCalculateExpenseSummary() {
        addTestExpenses();

        Map<String, Double> categoryTotals = subject.getTotalByCategory();
        Map<LocalDate, Double> dateTotals = subject.getExpenseTrendByDate();

        assertEquals(9, subject.getAllExpenses().size());
        assertEquals(382.99, subject.getTotalExpense(), 0.001);

        assertEquals(32.24, categoryTotals.get("Food"), 0.001);
        assertEquals(90.00, categoryTotals.get("Gas"), 0.001);
        assertEquals(45.00, categoryTotals.get("Entertainment"), 0.001);
        assertEquals(90.00, categoryTotals.get("Bills"), 0.001);
        assertEquals(125.75, categoryTotals.get("Medical"), 0.001);

        assertEquals(42.50, dateTotals.get(LocalDate.of(2026, 6, 1)), 0.001);
        assertEquals(18.75, dateTotals.get(LocalDate.of(2026, 6, 2)), 0.001);
        assertEquals(40.00, dateTotals.get(LocalDate.of(2026, 6, 3)), 0.001);
        assertEquals(90.99, dateTotals.get(LocalDate.of(2026, 6, 4)), 0.001);
        assertEquals(185.75, dateTotals.get(LocalDate.of(2026, 6, 5)), 0.001);
        assertEquals(5.00, dateTotals.get(LocalDate.of(2026, 6, 6)), 0.001);

        assertEquals("Medical", subject.getHighestCategory());
        assertEquals("Food", subject.getLowestCategory());
    }

    @Test
    void shouldReturnDefaultValuesWhenNoExpensesExist() {
        assertEquals(0, subject.getAllExpenses().size());
        assertEquals(0.00, subject.getTotalExpense(), 0.001);
        assertEquals("", subject.getHighestCategory());
        assertEquals("", subject.getLowestCategory());
    }

    private void addTestExpenses() {
        subject.addExpense(new Expense("Food", 12.50, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("Gas", 30.00, LocalDate.of(2026, 6, 1)));
        subject.addExpense(new Expense("Food", 18.75, LocalDate.of(2026, 6, 2)));
        subject.addExpense(new Expense("Entertainment", 40.00, LocalDate.of(2026, 6, 3)));
        subject.addExpense(new Expense("Bills", 90.00, LocalDate.of(2026, 6, 4)));
        subject.addExpense(new Expense("Food", 0.99, LocalDate.of(2026, 6, 4)));
        subject.addExpense(new Expense("Medical", 125.75, LocalDate.of(2026, 6, 5)));
        subject.addExpense(new Expense("Gas", 60.00, LocalDate.of(2026, 6, 5)));
        subject.addExpense(new Expense("Entertainment", 5.00, LocalDate.of(2026, 6, 6)));
    }
}