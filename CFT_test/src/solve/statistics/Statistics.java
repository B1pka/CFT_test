package solve.statistics;

import java.util.Collections;
import solve.models.ParsedData;

public class Statistics {

    public static void printStatistics(StatisticsMode mode, ParsedData data) {
        switch (mode) {
            case SHORT:
                System.out.println("Краткая статистика:");
                printShortStats(data);
                break;
            case FULL:
                System.out.println("Полная статистика:");
                printFullStats(data);
                break;
            default:
                System.out.println("Неизвестный режим статистики");
                break;
        }
    }

    private static void printShortStats(ParsedData data) {
        System.out.println("Количество строк: " + data.getStrings().size());
        System.out.println("Количество целых чисел: " + data.getIntegers().size());
        System.out.println("Количество чисел с плавающей точкой: " + data.getFloats().size());
    }

    private static void printFullStats(ParsedData data) {
        if (!data.getIntegers().isEmpty()) {
            int minInt = Collections.min(data.getIntegers());
            int maxInt = Collections.max(data.getIntegers());
            int sumInt = data.getIntegers().stream().mapToInt(Integer::intValue).sum();
            double avgInt = sumInt / (double) data.getIntegers().size();
            System.out.println("Целые числа:");
            System.out.println("Количество целых чисел: " + data.getIntegers().size());
            System.out.println("  min = " + minInt);
            System.out.println("  max = " + maxInt);
            System.out.println("  sum = " + sumInt);
            System.out.println("  avg = " + avgInt);
        }

        if (!data.getFloats().isEmpty()) {
            double minFloat = Collections.min(data.getFloats());
            double maxFloat = Collections.max(data.getFloats());
            double sumFloat = data.getFloats().stream().mapToDouble(Float::doubleValue).sum();
            double avgFloat = sumFloat / data.getFloats().size();
            System.out.println("Дробные числа:");
            System.out.println("Количество чисел с плавающей точкой: " + data.getFloats().size());
            System.out.println("  min = " + minFloat);
            System.out.println("  max = " + maxFloat);
            System.out.println("  sum = " + sumFloat);
            System.out.println("  avg = " + avgFloat);
        }

        if (!data.getStrings().isEmpty()) {
            int minLength = data.getStrings().stream().mapToInt(String::length).min().orElse(0);
            int maxLength = data.getStrings().stream().mapToInt(String::length).max().orElse(0);
            System.out.println("Строки:");
            System.out.println("Количество строк: " + data.getStrings().size());
            System.out.println("  minLength = " + minLength);
            System.out.println("  maxLength = " + maxLength);
        }
    }
}
