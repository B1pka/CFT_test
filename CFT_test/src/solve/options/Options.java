package solve.options;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import solve.statistics.StatisticsMode;

public class Options {

    private String outputDirectory = ".";  // Путь по умолчанию (текущая директория)
    private String prefix = "";  // Префикс для файлов по умолчанию
    private boolean append = false;  // Режим добавления в файл
    private StatisticsMode statMode = StatisticsMode.SHORT;  // По умолчанию выводится краткая статистика
    private List<String> inputFiles = new ArrayList<>();

<<<<<<< HEAD
    public static Options fromArgs(String[] args) throws ParseOptionsException {
=======
	public static Options fromArgs(String[] args) throws ParseOptionsException {
>>>>>>> a0a8e253db9cad17dbb4558239e2017dea7b8914
        Options config = new Options();
        config.parseArguments(args);
        
        if (config.inputFiles.isEmpty()) {
            throw new ParseOptionsException("Ошибка: не указаны входные файлы.");
        }
<<<<<<< HEAD
        try {
            config.validateInputFiles(config.inputFiles);
        } catch (ParseOptionsException e) {
            throw new ParseOptionsException(e.getMessage(), e);
        }
=======
>>>>>>> a0a8e253db9cad17dbb4558239e2017dea7b8914

        try {
            config.validateOutputDirectory(config.outputDirectory);
        } catch (IllegalArgumentException e) {
            throw new ParseOptionsException(e.getMessage(), e);
        }

        return config;
    }

    private void parseArguments(String[] args) throws ParseOptionsException {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o":
                    if (i + 1 < args.length) {
                        outputDirectory = args[++i];
                    } else {
                        throw new ParseOptionsException("Ошибка: путь к выходной директории не указан для опции -o");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        throw new ParseOptionsException("Ошибка: префикс не указан для опции -p");
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
                    inputFiles.add(arg);
                    break;
            }
        }
    }
    
    private void validateInputFiles(List<String> inputFiles) throws ParseOptionsException {
        for (String filePath : inputFiles) {
            validateInputFile(filePath);
        }
    }
    
    private void validateInputFile(String filePath) throws ParseOptionsException {
        File file = new File(filePath);

<<<<<<< HEAD
        if (!file.exists()) {
            throw new ParseOptionsException("Ошибка: файл не существует: " + filePath);
        }
    }
    
    
    
    private void validateOutputDirectory(String outputDirectory) {
        File dir = new File(outputDirectory);
=======
	public void validateOutputDirectory(String outputDirectory) {
		File dir = new File(outputDirectory);
>>>>>>> a0a8e253db9cad17dbb4558239e2017dea7b8914

        if (!dir.exists()) {
            throw new IllegalArgumentException("Ошибка: указанный путь для выходных файлов не существует: " + outputDirectory);
        }

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Ошибка: указанный путь не является директорией: " + outputDirectory);
        }

        System.out.println("Директория валидна: " + outputDirectory);
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




