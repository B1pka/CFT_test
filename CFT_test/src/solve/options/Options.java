package solve.options;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import solve.statistics.StatisticsMode;

public class Options {

	private String outputDirectory = "."; // Путь по умолчанию (текущая директория)
	private String prefix = ""; // Префикс для файлов по умолчанию
	private boolean append = false; // Режим добавления в файл
	private StatisticsMode statMode = StatisticsMode.SHORT; // По умолчанию выводится краткая статистика
	private List<String> inputFiles = new ArrayList<>();

	private Options() {
	}

	public static Options fromArgs(String[] args) throws ParseOptionsException {
		Options config = new Options();
		config.parseArguments(args);
		if (config.inputFiles.isEmpty()) {
			System.err.println("Ошибка: не указаны входные файлы.");
		}

		try {
			config.validOutputDirectory(config.outputDirectory);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		return config;
	}

	private void parseArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];

			switch (arg) {
			case "-o":
				if (i + 1 < args.length) {
					outputDirectory = args[++i];
				}
				break;
			case "-p":
				if (i + 1 < args.length) {
					prefix = args[++i];
				}
				break;
			case "-a":
				append = true;
				break;
			case "-s":
				statMode = StatisticsMode.SHORT;
				break;
			case "-f":
				statMode = StatisticsMode.FULL;
				break;
			default:
				// Все остальные аргументы считаем путями к файлам
				inputFiles.add(arg);
				break;
			}
		}
	}

	public boolean validOutputDirectory(String outputDirectory) {
		File dir = new File(outputDirectory);

		if (!dir.exists()) {
			throw new IllegalArgumentException(
					"Ошибка: указанный путь для выходных файлов не существует: " + outputDirectory);
		}

		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("Ошибка: указанный путь не является директорией: " + outputDirectory);
		}

		return true;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public String getPrefix() {
		return prefix;
	}

	public boolean isAppend() {
		return append;
	}

	public StatisticsMode getStatisticsMode() {
		return statMode;
	}

	public List<String> getInputFiles() {
		return inputFiles;
	}
}
