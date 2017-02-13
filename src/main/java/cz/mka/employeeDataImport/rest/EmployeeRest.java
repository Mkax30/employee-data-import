package cz.mka.employeeDataImport.rest;

import cz.mka.employeeDataImport.api.EmployeeService;
import cz.mka.employeeDataImport.impl.model.OutputEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */

@Controller
@RequestMapping(path = "/")
public class EmployeeRest {

    @Autowired
    private EmployeeService service;

    @RequestMapping(path = "employees", method = RequestMethod.GET, produces = "application/json")
    public List<OutputEmployee> getAllEmployees() {
        return service.getAllEmployees();
    }

}
