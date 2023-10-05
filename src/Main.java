import domain.ReportData;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);

        ReportData reportData = new ReportData();

        while (true) {
            printMenu();

            String userInput = scanner.next().toLowerCase();
            switch (userInput) {
                case "1":
                    ReportUtils.getMonthsData(reportData);
//                    reportData.getMonthlyReportHashMap().get(LocalDate.of(2021, 3, 1)).getTransactions().forEach(System.out::println);
//                    MonthlyReport feb = reportData.getMonthlyReportHashMap().get(LocalDate.of(2021, 2, 1));
//                    System.out.println("feb.getMonthDate() = " + feb.getMonthDate());
//                    System.out.println("feb.countMonthIncome() = " + feb.countMonthIncome());
//                    System.out.println("feb.countMonthExpenses() = " + feb.countMonthExpenses());
//                    System.out.println("feb.getTheMostExpensiveTransaction() = " + feb.getTheMostExpensiveTransaction());
//                    System.out.println("feb.getTheMostProfitableTransaction() = " + feb.getTheMostProfitableTransaction());
                    break;
                case "2":
                    ReportUtils.getYearsData(reportData);
                    break;
                case "3":
                    ReportUtils.checkConsistency(reportData);
                    break;
                case "4":
                    ReportUtils.printMonthData(reportData);
                    break;
                case "5":
                    ReportUtils.printYearsData(reportData);
                    break;

                case "6":
                    ReportUtils.getMonthsData(reportData);
                    ReportUtils.getYearsData(reportData);
                    ReportUtils.checkConsistency(reportData);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }

    }

    static void printMenu() {
        System.out.println("Пожалуйста выберите один из пунктов меню:");
        System.out.println("1 - Считать месячные отчеты.");
        System.out.println("2 - Считать годовые отчеты.");
        System.out.println("3 - Сверить данные по месячным и годовому отчётам.");
        System.out.println("4 - Вывод информации о месячных отчётах.");
        System.out.println("5 - Вывод информации о годовых отчётах.");
        System.out.println("6 - Выполнить автоматическое считывание всех данных и сверку.");
        System.out.println("exit - выход из меню");

    }
}

