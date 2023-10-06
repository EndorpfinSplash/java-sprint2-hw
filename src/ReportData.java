import domain.MonthDataRecord;
import domain.MonthlyReport;
import domain.Transaction;
import domain.YearlyReport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReportData {
    private final HashMap<LocalDate, MonthlyReport> monthlyReportHashMap;
    private final HashMap<LocalDate, YearlyReport> yearlyReportHashMap;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String FILE_EXTENSION = ".csv";
    private static final String MONTHLY_REPORT_TYPE_PREFIX = "m.";
    private static final String YEARLY_REPORT_TYPE_PREFIX = "y.";
    public static final String READ_YEAR_DATA = "Пожалуйста выполните считывание итоговых данных по годам.";
    public static final String READ_MONTH_DATA = "Пожалуйста выполните считывание итоговых данных по месяцам.";
    static String year = "2021";
    static FileReader reader = new FileReader();

    public ReportData() {
        this.monthlyReportHashMap = new HashMap<>();
        this.yearlyReportHashMap = new HashMap<>();
    }

    public void readMonthsData() {
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
            monthlyReportHashMap.put(monthDate, parsedMonth);
        }
    }

    public void readYearsData() {
        for (int i = 1; i <= 1; i++) {
            ArrayList<String> strings = reader.readFileContents(YEARLY_REPORT_TYPE_PREFIX + year + FILE_EXTENSION);
            LocalDate yearDate = LocalDate.parse(year + "0101", DATE_TIME_FORMATTER);
            LinkedList<MonthDataRecord> monthDataRecordList = new LinkedList<>();
            strings.stream().skip(1).forEach(s -> {
                String[] lineContents = s.split(",");
                String monthNumber = lineContents[0];
                LocalDate monthDate = LocalDate.parse(year + monthNumber + "01", DATE_TIME_FORMATTER);
                Double amount = Double.parseDouble(lineContents[1]);
                Boolean is_expense = Boolean.parseBoolean(lineContents[2]);
                MonthDataRecord monthDataRecord = new MonthDataRecord(monthDate, amount, is_expense);
                monthDataRecordList.add(monthDataRecord);
            });
            YearlyReport parsedYear = new YearlyReport(yearDate, monthDataRecordList);
            yearlyReportHashMap.put(yearDate, parsedYear);
        }
    }

    public void checkConsistency() {
        if (this.monthlyReportHashMap.isEmpty()) {
            System.out.println(READ_MONTH_DATA);
            return;
        }

        if (this.yearlyReportHashMap.isEmpty()) {
            System.out.println(READ_YEAR_DATA);
            return;
        }
        Collection<YearlyReport> yearlyReports = yearlyReportHashMap.values();
        yearlyReports.forEach(yearlyReport -> {
            Optional<MonthDataRecord> optionalMonthData = yearlyReport.getMonthDataRecords().stream().filter(
                    totalMonthDataRecord -> {
                        MonthlyReport relatedMonthlyReport = monthlyReportHashMap.get(totalMonthDataRecord.getMonthDate());
                        return Double.compare(totalMonthDataRecord.getAmount(),
                                totalMonthDataRecord.isExpense() ?
                                        relatedMonthlyReport.countMonthExpenses() :
                                        relatedMonthlyReport.countMonthIncome()
                        ) != 0;
                    }
            ).findAny();

            if (optionalMonthData.isEmpty()) {
                System.out.println("Сверка выполнена успешно.");
            } else {
                MonthDataRecord failedMonth = optionalMonthData.get();
                System.out.println(
                        (failedMonth.isExpense() ?
                                "Расходы " :
                                "Доходы ") +
                                "за " +
                                failedMonth.getMonthDate().getMonth() +
                                " " +
                                failedMonth.getMonthDate().getYear() +
                                " не совпадают!"
                );
            }
        });
    }

    public void printMonthData() {
        if (this.monthlyReportHashMap.isEmpty()) {
            System.out.println(READ_MONTH_DATA);
            return;
        }
        this.monthlyReportHashMap.values().forEach(monthlyReport -> {
            Transaction theMostExpensiveTransaction = monthlyReport.getTheMostExpensiveTransaction();
            System.out.println(monthlyReport.getMonthDate() +
                    ": самый прибыльный товар - " +
                    theMostExpensiveTransaction.getItem_name() +
                    ", общаая сумма за данные товары - " +
                    String.format("%,.1f", theMostExpensiveTransaction.getQuantity() * theMostExpensiveTransaction.getUnit_price())
            );
        });

    }

    public void printYearsData() {
        if (this.yearlyReportHashMap.isEmpty()) {
            System.out.println(READ_YEAR_DATA);
            return;
        }
        this.yearlyReportHashMap.values().forEach(
                yearlyReport -> {
                    yearlyReport.getProfitMap().forEach((localDate, profit) ->
                            System.out.println(yearlyReport.getYearDate().getYear() +
                                    ": прибыль за месяц " +
                                    localDate.getMonth() +
                                    " составила " + String.format("%,.1f", profit)));
                    System.out.println("Средний расход за все имеющиеся операции в году: " + String.format("%,.1f", yearlyReport.getAverageExpenses()));
                    System.out.println("Средний доход за все имеющиеся операции в году: " + String.format("%,.1f", yearlyReport.getAverageIncomes()));
                }
        );
    }

}
