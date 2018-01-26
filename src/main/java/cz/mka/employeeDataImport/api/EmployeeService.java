package cz.mka.employeeDataImport.api;

import java.util.List;

import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.rest.model.OutputEmployee;

public interface EmployeeService {

    /**
     * Retrieves all employees.
     */
    List<OutputEmployee> getAllEmployees();

    /**
     * Inserts new employee.
     */
    Employee saveEmployee(Employee employee);

    /**
     * Retrieve employee by id.
     */
    Employee getEmployee(Integer id);

    /**
     * Deletes employee by id.
     */
    void deleteEmployee(Integer id);

}
