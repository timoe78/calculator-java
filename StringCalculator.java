import java.util.Scanner;

public class StringCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");

        String input = scanner.nextLine();
        try {
            String result = calculate(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String calculate(String input) throws Exception {
        input = input.trim();

        // Проверка на операторы
        if (input.contains("+")) {
            String[] parts = input.split("\\+");
            if (parts.length == 2) {
                String str1 = extractString(parts[0]);
                String str2 = extractString(parts[1]);
                return limitLength(str1 + str2);
            } else {
                throw new Exception("Неверное выражение для сложения.");
            }
        } else if (input.contains("-")) {
            String[] parts = input.split("-");
            if (parts.length == 2) {
                String str1 = extractString(parts[0]);
                String str2 = extractString(parts[1]);
                return limitLength(str1.replace(str2, ""));
            } else {
                throw new Exception("Неверное выражение для вычитания.");
            }
        } else if (input.contains("*")) {
            String[] parts = input.split("\\*");
            if (parts.length == 2) {
                String str1 = extractString(parts[0]);
                int num = extractNumber(parts[1]);
                return limitLength(str1.repeat(num));
            } else {
                throw new Exception("Неверное выражение для умножения.");
            }
        } else if (input.contains("/")) {
            String[] parts = input.split("/");
            if (parts.length == 2) {
                String str1 = extractString(parts[0]);
                int num = extractNumber(parts[1]);
                if (str1.length() < num) throw new Exception("Строка слишком короткая для деления.");
                return limitLength(str1.substring(0, str1.length() / num));
            } else {
                throw new Exception("Неверное выражение для деления.");
            }
        } else {
            throw new Exception("Операция не поддерживается.");
        }
    }

    private static String extractString(String part) throws Exception {
        part = part.trim();
        if (part.startsWith("\"") && part.endsWith("\"")) {
            String str = part.substring(1, part.length() - 1);
            if (str.length() > 10) throw new Exception("Строка превышает допустимую длину.");
            return str;
        } else {
            throw new Exception("Ожидается строка в кавычках.");
        }
    }

    private static int extractNumber(String part) throws Exception {
        part = part.trim();
        try {
            int number = Integer.parseInt(part);
            if (number < 1 || number > 10) throw new Exception("Число вне допустимого диапазона (1-10).");
            return number;
        } catch (NumberFormatException e) {
            throw new Exception("Ожидается число.");
        }
    }

    private static String limitLength(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}