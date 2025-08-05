package solve;

import solve.models.ParsedData;
import solve.options.Options;
import solve.parser.DataParser;
import solve.saver.DataSaver;
import solve.statistics.Statistics;

public class DataFilterUtility {

	public static void main(String[] args) {
		
		Options config = Options.fromArgs(args);
		
		DataParser dataParser = new DataParser();
        ParsedData parsedData = null;
        try {
            parsedData = dataParser.parse(config.getInputFiles());
            parsedData.sort();
        } catch (Exception e) {
            System.err.println("Ошибка припарсинге данных: " + e.getMessage());
            return;
        }

        try {
            DataSaver dataSaver = new DataSaver();
            dataSaver.saveData(parsedData, config.getOutputDirectory(), config.getPrefix(), config.isAppend());
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
            return;
        }
        
        try {
            Statistics.printStatistics(config.getStatisticsMode(), parsedData);
        } catch (Exception e) {
            System.err.println("Ошибка при выводе статистики: " + e.getMessage());
        }

	}

}
