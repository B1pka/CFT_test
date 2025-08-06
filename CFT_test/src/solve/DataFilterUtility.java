package solve;

import solve.models.ParsedData;
import solve.options.Options;
import solve.options.ParseOptionsException;
import solve.parser.DataParser;
import solve.parser.ParseDataException;
import solve.saver.DataSaver;
import solve.saver.SaveDataException;
import solve.statistics.Statistics;
import solve.statistics.StatisticsException;

public class DataFilterUtility {

	public static void main(String[] args) {
		try {
			Options options = Options.fromArgs(args);
			DataParser dataParser = new DataParser();
			ParsedData parsedData = dataParser.parse(options.getInputFiles());
			parsedData.sort();

			DataSaver dataSaver = new DataSaver();
			dataSaver.saveData(parsedData, options.getOutputDirectory(), options.getPrefix(), options.isAppend());

			Statistics.printStatistics(options.getStatisticsMode(), parsedData);
		} catch (SaveDataException e) {
			System.err.println("Ошибка при сохранении данных: " + e.getMessage());
		} catch (ParseOptionsException e) {
			System.err.println("Ошибка при парсинге опций: " + e.getMessage());
		} catch (ParseDataException e) {
			System.err.println("Ошибка при парсинге данных: " + e.getMessage());
		} catch (StatisticsException e) {
			System.err.println("Ошибка при выводе статистики: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Неизвестная ошибка: " + e.getMessage());
		}
	}
}