import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        FileReader reader = new FileReader();
        ArrayList<String> strings = reader.readFileContents("m.202101.csv");
        strings.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            String userInput = scanner.next().toLowerCase();
            switch (userInput) {
                case "1": System.out.println("hi");
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

