package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.api.model.Statistics;
import cz.mka.employeeDataImport.impl.model.Input;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface CsvProcessingService {

    /**
     * Processes all data files in specified folder.
     */
    void processDataFolder();

    /**
     * Imports data from csv file.
     * @param dataFile
     * @return
     */
    List<Input> importData(String dataFile);

    /**
     * Inserts new data from csv file.
     * @param inputList
     * @return
     */
    Statistics saveData(List<Input> inputList);
}
