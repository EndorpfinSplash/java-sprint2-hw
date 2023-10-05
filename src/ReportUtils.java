import domain.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

public class ReportUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String FILE_EXTENSION = ".csv";
    private static final String MONTHLY_REPORT_TYPE_PREFIX = "m.";
    private static final String YEARLY_REPORT_TYPE_PREFIX = "y.";
    static String year = "2021";
    static FileReader reader = new FileReader();
    public static void getMonthsData(ReportData reportData) {
        for (int i = 1; i <= 3; i++) {
            String monthNumber = String.format("%02d", i);
            ArrayList<String> strings = reader.readFileContents(MONTHLY_REPORT_TYPE_PREFIX + year + monthNumber + FILE_EXTENSION);
            LocalDate monthDate = LocalDate.parse(year + monthNumber + "01", DATE_TIME_FORMATTER);
            LinkedList<Transaction> transactions = new LinkedList<>();
            strings.stream().skip(1).forEach(s -> {
                String[] lineContents = s.split(",");
                String item_name = lineContents[0];
                Boolean is_expense = Boolean.parseBoolean(lineContents[1]);
                Integer quantity = Integer.parseInt(lineContents[2]);
                Double unit_price = Double.parseDouble(lineContents[3]);
                Transaction transaction = new Transaction(item_name, is_expense, quantity, unit_price);
                transactions.add(transaction);
            });
            MonthlyReport parsedMonth = new MonthlyReport(monthDate, transactions);
            reportData.getMonthlyReportHashMap().put(monthDate, parsedMonth);
        }
    }

    public static void getYearsData(ReportData reportData) {
        for (int i = 1; i <= 1; i++) {
            ArrayList<String> strings = reader.readFileContents(YEARLY_REPORT_TYPE_PREFIX + year+ FILE_EXTENSION);
            LocalDate yearDate = LocalDate.parse(year + "0101", DATE_TIME_FORMATTER);
            LinkedList<MonthData> monthDataList = new LinkedList<>();
            strings.stream().skip(1).forEach(s -> {
                String[] lineContents = s.split(",");
                String monthNumber = lineContents[0];
                LocalDate monthDate = LocalDate.parse(year + monthNumber + "01", DATE_TIME_FORMATTER);
                Double amount = Double.parseDouble(lineContents[1]);
                Boolean is_expense = Boolean.parseBoolean(lineContents[2]);
                MonthData monthData = new MonthData(monthDate, amount, is_expense);
                monthDataList.add(monthData);
            });
            YearlyReport parsedYear = new YearlyReport(yearDate, monthDataList);
            reportData.getYearlyReportHashMap().put(yearDate, parsedYear);
        }
    }

}
