package cz.mka.employeeDataImport.impl.utils;

import com.google.common.base.Preconditions;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.List;

public class InputDataValidator {

    final static Logger logger = Logger.getLogger(InputDataValidator.class);

    public static boolean validateDataBeforeInsert(List<CsvImportRow> csvImportRowList) throws IllegalArgumentException {
        int row = 1;
        try {
            for (CsvImportRow in : csvImportRowList) {
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyIco()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyTitle()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyAddress()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeEmail()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeFirstName()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeLastName()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getLastUpdate()));
                row++;
            }
        } catch (IllegalArgumentException e) {
            logger.warn("Csv file contains invalid data on row " + row);
            return false;
        }
        return true;
    }
}
