package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.rest.model.OutputEmployee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface EmployeeService {

    /**
     * Retrieves all employees.
     * @return
     */
    List<OutputEmployee> getAllEmployees();

    /**
     * Inserts new employee.
     * @param employee
     * @return
     */
    Employee saveEmployee(Employee employee);

    /**
     * Retrieve employee by id.
     * @param id
     * @return
     */
    Employee getEmployee(Integer id);

    /**
     * Deletes employee by id.
     * @param id
     */
    void deleteEmployee(Integer id);

}
