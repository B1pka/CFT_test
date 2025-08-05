package solve.saver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import solve.models.ParsedData;

public class DataSaver {
	
    public DataSaver() {

    }

    public void saveData(ParsedData parsedData, String outputDirectory, String prefix, boolean append) {
        System.out.println("Начало сохранения коллекций в файлы...");
        System.out.println("Строки для сохранения: " + parsedData.getStrings());
        System.out.println("Целые числа для сохранения: " + parsedData.getIntegers());
        System.out.println("Числа с плавающей точкой для сохранения: " + parsedData.getFloats());

        parse(parsedData.getStrings(), "strings.txt", outputDirectory, prefix, append);
        parse(parsedData.getIntegers(), "integers.txt", outputDirectory, prefix, append);
        parse(parsedData.getFloats(), "floats.txt", outputDirectory, prefix, append);

        System.out.println("Данные были сохранены.");
    }

    private <T> void parse(List<T> data, String fileName, String outputDirectory, String prefix, boolean append) {
        if (data == null || data.isEmpty()) {
            System.out.println("Коллекция для файла '" + fileName + "' пуста. Файл не будет создан.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + File.separator + prefix + fileName, append))) {
            for (T item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println("Данные успешно записаны в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл " + fileName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
