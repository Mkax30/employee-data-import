package cz.mka.employeeDataImport.impl.Utils;

import com.google.common.base.Preconditions;
import cz.mka.employeeDataImport.impl.model.Input;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Martin Kaspar on 16/02/2017.
 */
public class InputDataValidator {

    public static boolean validateDataBeforeInsert(List<Input> inputList) throws IllegalArgumentException {
        try {
            for (Input in : inputList) {
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyIco()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyTitle()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getCompanyAddress()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeEmail()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeFirstName()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getEmployeeLastName()));
                Preconditions.checkArgument(!StringUtils.isEmpty(in.getLastUpdate()));
            }
        } catch (IllegalArgumentException e) {
            // todo log
            return false;
        }
        return true;
    }
}
