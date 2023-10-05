package domain;

import java.time.LocalDate;

public class MonthData {

    LocalDate monthDate;
    Double amount;
    Boolean expense;

    public MonthData(LocalDate month, Double amount, Boolean is_expense) {
        this.monthDate = month;
        this.amount = amount;
        this.expense = is_expense;
    }

    public LocalDate getMonthDate() {
        return monthDate;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean isExpense() {
        return expense;
    }
}
