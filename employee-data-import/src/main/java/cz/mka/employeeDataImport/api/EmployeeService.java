package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.api.model.Employee;
import cz.mka.employeeDataImport.impl.model.OutputEmployee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface EmployeeService {

    List<OutputEmployee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployee(Integer id);

    void deleteEmployee(Integer id);

}
