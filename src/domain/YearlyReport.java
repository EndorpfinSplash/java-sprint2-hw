package domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class YearlyReport {
    private final LocalDate yearDate;
    private final LinkedList<MonthDataRecord> monthDataRecords;

    private final HashMap<LocalDate, Double> profitMap = new HashMap<>();

    public YearlyReport(LocalDate yearDate, LinkedList<MonthDataRecord> monthDataRecords) {
        this.yearDate = yearDate;
        this.monthDataRecords = monthDataRecords;
    }

    public LinkedList<MonthDataRecord> getMonthDataRecords() {
        return monthDataRecords;
    }

    public LocalDate getYearDate() {
        return yearDate;
    }

    public Double getAverageExpenses() {
        return monthDataRecords.stream()
                .filter(MonthDataRecord::isExpense)
                .mapToDouble(MonthDataRecord::getAmount)
                .average()
                .orElse(Double.NaN);
    }
    public HashMap<LocalDate, Double> getProfitMap() {
        if (this.profitMap.isEmpty()) {
            calculateProfitMap();
        }
        return this.profitMap;
    }

    public Double getAverageIncomes() {
        return monthDataRecords.stream()
                .filter(monthDataRecord1 -> !monthDataRecord1.isExpense())
                .mapToDouble(MonthDataRecord::getAmount)
                .average()
                .orElse(Double.NaN);
    }

    public void calculateProfitMap() {
        monthDataRecords.forEach(monthDataRecord -> {
            Double profit = monthDataRecord.isExpense() ? -monthDataRecord.getAmount() : monthDataRecord.getAmount();
            if (this.profitMap.containsKey(monthDataRecord.getMonthDate())) {
                this.profitMap.put(monthDataRecord.getMonthDate(), this.profitMap.getOrDefault(monthDataRecord.getMonthDate(), 0.0) + profit);
            } else {
                this.profitMap.put(monthDataRecord.getMonthDate(), profit);
            }
        });
    }


}
