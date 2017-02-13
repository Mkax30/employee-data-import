package cz.mka.employeeDataImport.rest;

import cz.mka.employeeDataImport.api.CompanyService;
import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.impl.model.Input;
import cz.mka.employeeDataImport.impl.model.OutputCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/")
public class CompanyRest {

    @Autowired
    private CompanyService service;

    @Autowired
    private CsvProcessingService csvProcessingService;

    @RequestMapping(path = "companies", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OutputCompany>> getAllCompanies() {
        return new ResponseEntity<>(service.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(path = "companies", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {

        Company result = service.saveCompany(company);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(path = "companies/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Company> updateCompany(@PathVariable Integer id,
                                                 @RequestBody Company company) {
        company.setId(id);
        Company result = service.updateCompany(company);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "companies/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id) {

        Company result = service.getCompany(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // test method for process import csv file
    @RequestMapping(path = "importCsv", method = RequestMethod.GET)
    public ResponseEntity testProcessCsv() {

        List<Input> inputList = csvProcessingService.importData();

        csvProcessingService.saveData(inputList);

        return new ResponseEntity(HttpStatus.OK);
    }

}
