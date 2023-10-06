package domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.LinkedList;

public class MonthlyReport {
    private final LinkedList<Transaction> transactions;
    private final LocalDate monthDate;

    public MonthlyReport(LocalDate monthDate, LinkedList<Transaction> transactions) {
        this.transactions = transactions;
        this.monthDate = monthDate;
    }

    public Month getMonthDate() {
        return monthDate.getMonth();
    }

    public Transaction getTheMostExpensiveTransaction() {
        return transactions.stream()
                .filter(transaction -> transaction.is_expense)
                .max(Comparator.comparingInt(o -> (int) (o.quantity * o.unit_price)))
                .get();

    }

    public Double countMonthExpenses() {
        return transactions.stream()
                .filter(transaction -> transaction.is_expense)
                .map(transaction -> transaction.quantity * transaction.unit_price)
                .reduce(0.0, Double::sum);
    }

    public Double countMonthIncome() {
        return transactions.stream()
                .filter(transaction -> !transaction.is_expense)
                .map(transaction -> transaction.quantity * transaction.unit_price)
                .reduce(0.0, Double::sum);
    }
}
