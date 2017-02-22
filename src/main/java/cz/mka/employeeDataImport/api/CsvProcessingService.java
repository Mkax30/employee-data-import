package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.impl.utilss.CsvImportRow;
import cz.mka.employeeDataImport.rest.model.Statistics;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface CsvProcessingService {

    /**
     * Processes all data files in specified folder.
     * @return
     */
    List<Statistics> processDataFolder();

    /**
     * Imports data from csv file.
     * @param dataFile
     * @return
     */
    List<CsvImportRow> importData(String dataFile) throws IOException;

    /**
     * Inserts new data from csv file.
     * @param csvImportRowList
     * @return
     */
    Statistics saveData(List<CsvImportRow> csvImportRowList);
}
