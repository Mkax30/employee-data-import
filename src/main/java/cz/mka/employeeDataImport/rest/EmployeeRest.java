package cz.mka.employeeDataImport.rest;

import cz.mka.employeeDataImport.api.EmployeeService;
import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.rest.model.OutputEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */

@Controller
@RequestMapping(path = "/employee-processing")
public class EmployeeRest {

    @Autowired
    private EmployeeService service;

    @RequestMapping(path = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OutputEmployee>> getAllEmployees() {
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

    @RequestMapping(path = "/employees", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

        Employee result = service.saveEmployee(employee);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,
                                                   @RequestBody Employee employee) {
        employee.setId(id);
        Employee result = service.saveEmployee(employee);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/employees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {

        Employee result = service.getEmployee(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
