package solve.parser;

import java.io.*;
import java.util.*;

import solve.models.ParsedData;

public class DataParser {

	public ParsedData parse(List<String> filePaths) throws ParseDataException {
		List<Integer> integers = new ArrayList<>();
		List<Float> floats = new ArrayList<>();
		List<String> strings = new ArrayList<>();

		for (String filePath : filePaths) {
			File file = new File(filePath);
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {

					if (line.trim().isEmpty()) {
						continue;
					}

					String[] tokens = line.split("\\s+");

					for (String token : tokens) {
						try {
							integers.add(Integer.parseInt(token));
						} catch (NumberFormatException e1) {
							try {
								floats.add(Float.parseFloat(token));
							} catch (NumberFormatException e2) {
								strings.add(token);
							}
						}
					}
				}

			} catch (IOException e) {
				throw new ParseDataException("Ошибка чтения файла " + filePath + ": " + e.getMessage() + "\n" + e);
			}
		}

		return new ParsedData(integers, floats, strings);
	}
}
