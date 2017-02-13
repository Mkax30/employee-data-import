package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.impl.model.Input;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface CsvProcessingService {

    List<Input> importData();

    void saveData(List<Input> inputList);
}
