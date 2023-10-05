package domain;

import java.time.LocalDate;

public class MonthData {

    LocalDate month;
    Double amount;
    Boolean is_expense;

    public MonthData(LocalDate month, Double amount, Boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
