package cz.mka.employeeDataImport.api.dao;

import cz.mka.employeeDataImport.impl.jpa.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findById(Integer id);

    Employee findByEmailIgnoreCase(String email);
}
