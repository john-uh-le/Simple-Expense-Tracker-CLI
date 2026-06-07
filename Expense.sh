#!/bin/bash

BASE_URL="http://localhost:8080/api/expenses"

print_line() {
    echo "--------------------------------------------------"
}

print_title() {
    print_line
    echo "$1"
    print_line
}

pretty_json() {
    python3 -m json.tool 2>/dev/null || cat
}

show_all_expenses() {
    print_title "ALL EXPENSES"
    curl -s "$BASE_URL" | pretty_json
    echo
}

add_expense() {
    print_title "ADD NEW EXPENSE"

    echo "Enter category:"
    read category

    echo "Enter amount:"
    read amount

    echo "Enter date using format YYYY-MM-DD:"
    read date

    curl -s -X POST "$BASE_URL" \
        -H "Content-Type: application/json" \
        -d "{\"category\":\"$category\",\"amount\":$amount,\"date\":\"$date\"}" | pretty_json
    echo
}

show_total() {
    print_title "TOTAL EXPENSE"
    curl -s "$BASE_URL/total" | pretty_json
    echo
}

show_category_totals() {
    print_title "TOTAL BY CATEGORY"
    curl -s "$BASE_URL/categories" | pretty_json
    echo
}

show_date_totals() {
    print_title "EXPENSE TREND BY DATE"
    curl -s "$BASE_URL/dates" | pretty_json
    echo
}

show_highest_category() {
    print_title "HIGHEST CATEGORY"
    curl -s "$BASE_URL/highest"
    echo
}

show_lowest_category() {
    print_title "LOWEST CATEGORY"
    curl -s "$BASE_URL/lowest"
    echo
}

while true
do
    print_line
    echo "Simple Expense Tracker CLI"
    print_line
    echo "1. View all expenses"
    echo "2. Add new expense"
    echo "3. View total expense"
    echo "4. View total by category"
    echo "5. View expense trend by date"
    echo "6. View highest category"
    echo "7. View lowest category"
    echo "0. Exit"
    print_line

    echo "Choose an option:"
    read option

    case $option in
        1)
            show_all_expenses
            ;;
        2)
            add_expense
            ;;
        3)
            show_total
            ;;
        4)
            show_category_totals
            ;;
        5)
            show_date_totals
            ;;
        6)
            show_highest_category
            ;;
        7)
            show_lowest_category
            ;;
        0)
            echo "Goodbye."
            exit 0
            ;;
        *)
            echo "Invalid option. Please try again."
            ;;
    esac
done