package cz.mka.employeeDataImport.impl.dao;

import cz.mka.employeeDataImport.api.model.Employee;
import cz.mka.employeeDataImport.impl.model.OutputEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class EmployeeDao {

    @Autowired
    private EntityManager em;

    public List<OutputEmployee> getAllEmployees() {
        Query query = em.createQuery("select new OutputEmployee(c.title, c.ico) from Employee c");
        return (List<OutputEmployee>) query.getResultList();
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        em.persist(employee);
        return getEmployee(employee.getId());
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        em.merge(employee);
        return getEmployee(employee.getId());
    }

    public Employee getEmployee(Integer id) {
        return em.find(Employee.class, id);
    }

    public void deleteEmployee(Integer id) {
        // todo
    }

    public Employee getEmployeeByEmail(String employeeEmail) {
        Query query = em.createQuery("from Employee where email = :email").setParameter("email", employeeEmail);
        Employee result;
        try {
            result = (Employee) query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
        return result;
    }
}
