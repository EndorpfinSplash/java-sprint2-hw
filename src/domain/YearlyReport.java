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
}
