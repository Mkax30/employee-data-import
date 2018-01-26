package cz.mka.employeeDataImport.impl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import cz.mka.employeeDataImport.impl.jpa.Employee;

@Component
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findById(Integer id);

    Employee findByEmailIgnoreCase(String email);
}
