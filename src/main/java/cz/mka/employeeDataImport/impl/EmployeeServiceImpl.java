package cz.mka.employeeDataImport.impl;

import cz.mka.employeeDataImport.api.EmployeeService;
import cz.mka.employeeDataImport.api.model.Employee;
import cz.mka.employeeDataImport.impl.dao.EmployeeDao;
import cz.mka.employeeDataImport.impl.model.OutputEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public List<OutputEmployee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    public Employee saveEmployee(Employee employee) {
        return dao.saveEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return dao.updateEmployee(employee);
    }

    public Employee getEmployee(Integer id) {
        return dao.getEmployee(id);
    }

    public void deleteEmployee(Integer id) {
        dao.deleteEmployee(id);
    }
}
