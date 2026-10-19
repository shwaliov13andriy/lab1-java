package app;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=== Конвертер одиниць (Довжина) ===");

        while (true) {
            System.out.print("Введіть значення (або 0 для виходу): ");
            while (!scanner.hasNextDouble()) {
                System.out.println("-> Помилка: введіть число. Спробуйте ще раз.");
                scanner.next();
                System.out.print("Значення: ");
            }

            double value = scanner.nextDouble();

            if (value == 0) {
                System.out.println("-> Вихід. Дякуємо за використання!");
                break;
            }

            System.out.print("З (mm/cm/m/km/mile): ");
            String fromUnit = scanner.next().toLowerCase();

            System.out.print("У (mm/cm/m/km/mile): ");
            String toUnit = scanner.next().toLowerCase();

            double result = convertLength(value, fromUnit, toUnit);

            if (result == -1.0) {
                System.out.println("-> Помилка: невідома одиниця вимірювання.");
            } else {
                System.out.printf("-> %.2f %s = %.4f %s\n", value, fromUnit, result, toUnit);
            }
            System.out.println("-----------------------------------");
        }
        scanner.close();
    }
    private static double convertLength(double value, String from, String to) {
        double inMeters;
        switch (from) {
            case "mm": inMeters = value / 1000.0; break;
            case "cm": inMeters = value / 100.0; break;
            case "m":  inMeters = value; break;
            case "km": inMeters = value * 1000.0; break;
            case "mile": inMeters = value * 1609.34; break;
            default: return -1.0;
        }
        switch (to) {
            case "mm": return inMeters * 1000.0;
            case "cm": return inMeters * 100.0;
            case "m":  return inMeters;
            case "km": return inMeters / 1000.0;
            case "mile": return inMeters / 1609.34;
            default: return -1.0;
        }
    }
}