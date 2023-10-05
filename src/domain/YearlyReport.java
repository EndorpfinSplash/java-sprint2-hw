package domain;

import java.time.LocalDate;
import java.util.LinkedList;

public class YearlyReport {
    private final LocalDate yearDate;
    private final LinkedList<MonthData> monthData;

    public YearlyReport(LocalDate yearDate, LinkedList<MonthData> monthData) {
        this.yearDate = yearDate;
        this.monthData = monthData;
    }

    public LinkedList<MonthData> getMonthData() {
        return monthData;
    }

    public LocalDate getYearDate() {
        return yearDate;
    }

    public Double getAverageExpenses() {
        return monthData.stream()
                .filter(MonthData::isExpense)
                .mapToDouble(MonthData::getAmount)
                .average()
                .orElse(Double.NaN);
    }

    public Double getAverageIncomes() {
        return monthData.stream()
                .filter(monthData1 -> !monthData1.isExpense())
                .mapToDouble(MonthData::getAmount)
                .average()
                .orElse(Double.NaN);
    }

}
