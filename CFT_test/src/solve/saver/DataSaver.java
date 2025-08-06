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

	public void saveData(ParsedData parsedData, String outputDirectory, String prefix, boolean append)
			throws SaveDataException {

		save(parsedData.getStrings(), "strings.txt", outputDirectory, prefix, append);
		save(parsedData.getIntegers(), "integers.txt", outputDirectory, prefix, append);
		save(parsedData.getFloats(), "floats.txt", outputDirectory, prefix, append);

		System.out.println("Данные были сохранены.");
	}

	private <T> void save(List<T> data, String fileName, String outputDirectory, String prefix, boolean append)
			throws SaveDataException {
		if (data == null || data.isEmpty()) {
			System.out.println("Коллекция для файла '" + fileName + "' пуста. Файл не будет создан.");
			return;
		}

		File outputFile = new File(outputDirectory + File.separator + prefix + fileName);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, append))) {
			for (T item : data) {
				writer.write(item.toString());
				writer.newLine();
			}

		} catch (IOException e) {
			throw new SaveDataException("Ошибка при записи данных в файл: " + fileName + e.getMessage() + "\n" + e);
		}
		System.out.println("Данные успешно записаны в " + fileName);
	}
}
