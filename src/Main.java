import domain.ReportData;

import java.time.LocalDate;
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
                case "1": ReportUtils.getMonthsData(reportData);
                   reportData.getMonthlyReportHashMap().get(LocalDate.of(2021,3,1)).getTransactions().forEach(System.out::println);
                case "2": ReportUtils.getYearsData(reportData);
                case "exit": return;
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
        System.out.println("exit - выход из меню");

    }
}

