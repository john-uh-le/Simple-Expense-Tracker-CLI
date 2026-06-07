# Simple Expense Tracker

A simple Java Spring Boot application for tracking expenses by category, amount, and date.

This project was built as a small expense tracking application that stores data in memory and provides REST API endpoints to add and view expense information. The goal was to keep the application simple while still using a clean Spring Boot structure with a controller, service, and model layer.

## Features

* Add a new expense
* View all expenses
* Calculate total expenses
* View total spending by category
* View expense trends by date
* Find the highest spending category
* Find the lowest spending category

## Tech Stack

* Java
* Spring Boot
* Gradle
* JUnit
* REST API

## How to Run the Project

Clone the repository:

```bash
git clone https://github.com/john-uh-le/Simple-Expense-Tracker-CLI.git
```

Go into the project folder:

```bash
cd Simple-Expense-Tracker-CLI
```

Run the application:

```bash
./gradlew bootRun
```

The application will start at:

```text
http://localhost:8080
```

## API Endpoints

### View all expenses

```http
GET /api/expenses
```

### Add a new expense

```http
POST /api/expenses
```

Example request body:

```json
{
  "category": "Food",
  "amount": 15.50,
  "date": "2026-06-07"
}
```

### View total expenses

```http
GET /api/expenses/total
```

### View totals by category

```http
GET /api/expenses/category-totals
```

### View totals by date

```http
GET /api/expenses/date-totals
```

### View highest spending category

```http
GET /api/expenses/highest-category
```

### View lowest spending category

```http
GET /api/expenses/lowest-category
```

## Seed Data

The application includes seed data when it starts, so the API can be tested right away.

Example seed expenses include:

```text
Food - $12.50 - 2026-06-01
Gas - $30.00 - 2026-06-01
Food - $18.75 - 2026-06-02
Entertainment - $40.00 - 2026-06-03
Bills - $90.00 - 2026-06-04
```

This makes it easier to test totals, category totals, and date trends without manually adding data first.

## Validation

The application checks for invalid expense data before adding it.

An expense will not be added if:

* The category is blank
* The amount is less than or equal to zero
* The date is missing

This helps prevent bad data from affecting the expense totals.

## How to Run Tests

Run the test command:

```bash
./gradlew test
```

## Author

John Le

