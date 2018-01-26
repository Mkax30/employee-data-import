package cz.mka.employeeDataImport.api;

import java.io.IOException;
import java.util.List;

import cz.mka.employeeDataImport.impl.utils.CsvImportRow;
import cz.mka.employeeDataImport.rest.model.Statistics;

public interface CsvProcessingService {

    /**
     * Processes all data files in specified folder.
     */
    List<Statistics> processDataFolder();

    /**
     * Imports data from csv file.
     */
    List<CsvImportRow> importData(String dataFile) throws IOException;

    /**
     * Inserts new data from csv file.
     */
    Statistics saveData(List<CsvImportRow> csvImportRowList);
}
