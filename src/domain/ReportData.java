package domain;

import java.time.LocalDate;
import java.util.HashMap;

public class ReportData {
    private final HashMap<LocalDate, MonthlyReport> monthlyReportHashMap ;
    private final HashMap<LocalDate, YearlyReport> yearlyReportHashMap;

    public ReportData() {
        this.monthlyReportHashMap =new HashMap<>();
        this.yearlyReportHashMap = new HashMap<>();
    }

    public HashMap<LocalDate, MonthlyReport> getMonthlyReportHashMap() {
        return monthlyReportHashMap;
    }

    public HashMap<LocalDate, YearlyReport> getYearlyReportHashMap() {
        return yearlyReportHashMap;
    }
}
