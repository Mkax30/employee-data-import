package cz.mka.employeeDataImport.impl;

import cz.mka.employeeDataImport.api.EmployeeService;
import cz.mka.employeeDataImport.api.dao.EmployeeDao;
import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.impl.utilss.DataConverter;
import cz.mka.employeeDataImport.rest.model.OutputEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public List<OutputEmployee> getAllEmployees() {
        List<Employee> employeeList = dao.findAll();
        return employeeList.stream().map(DataConverter::convertEmployeeEntity).collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee employee) {
        return dao.save(employee);
    }

    public Employee getEmployee(Integer id) {
        return dao.findById(id);
    }

    public void deleteEmployee(Integer id) {
        dao.delete(id);
    }
}
