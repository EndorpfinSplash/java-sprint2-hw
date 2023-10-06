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
                    reportData.readMonthsData();
                    break;
                case "2":
                    reportData.readYearsData();
                    break;
                case "3":
                    reportData.checkConsistency();
                    break;
                case "4":
                    reportData.printMonthData();
                    break;
                case "5":
                    reportData.printYearsData();
                    break;
                case "6":
                    reportData.readMonthsData();
                    reportData.readYearsData();
                    reportData.checkConsistency();
                    break;
                case "exit":
                    scanner.close();
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

