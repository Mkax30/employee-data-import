package cz.mka.employeeDataImport.impl.utils;

import cz.mka.employeeDataImport.impl.jpa.Company;
import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.rest.model.OutputCompany;
import cz.mka.employeeDataImport.rest.model.OutputEmployee;
import org.apache.log4j.Logger;

/**
 * Created by Martin Kaspar on 22/02/2017.
 */
public class DataConverter {

    final static Logger logger = Logger.getLogger(DataConverter.class);

    /**
     * Converts company entity to output structure.
     * @param company company entity
     * @return
     */
    public static OutputCompany convertCompanyEntity(Company company) {
        if (company == null) {
            logger.error("Cannot convert empty company object.");
            return null;
        }

        OutputCompany outputCompany = new OutputCompany();

        outputCompany.setTitle(company.getTitle());
        outputCompany.setIco(company.getIco());

        return outputCompany;
    }

    /**
     * Converts employee entity to output structure.
     * @param employee employee entity
     * @return
     */
    public static OutputEmployee convertEmployeeEntity(Employee employee) {
        if (employee == null) {
            logger.error("Cannot convert empty employee object.");
            return null;
        }

        OutputEmployee outputEmployee = new OutputEmployee();

        outputEmployee.setName(employee.getFirstName() + " " + employee.getLastName());
        outputEmployee.setEmail(employee.getEmail());

        return outputEmployee;
    }

    /**
     * Converts row data from csv file to company object.
     * @param csvImportRow data row from csv file
     * @return
     */
    public static Company convertInputRowToCompany(CsvImportRow csvImportRow) {
        if (csvImportRow == null) {
            logger.error("Cannot convert empty object to Company.");
            return null;
        }

        Company company = new Company();

        company.setIco(csvImportRow.getCompanyIco());
        company.setTitle(csvImportRow.getCompanyTitle());
        company.setAddress(csvImportRow.getCompanyAddress());

        return company;
    }

    /**
     * Converts row data from csv file to employee object.
     * @param csvImportRow data row from csv file
     * @return
     */
    public static Employee convertInpuRowToEmployee(CsvImportRow csvImportRow) {
        if (csvImportRow == null) {
            logger.error("Cannot convert empty object to Employee.");
            return null;
        }

        Employee employee = new Employee();

        // use companyId as companyIco for later use (find existed company)
        employee.setCompanyId(csvImportRow.getCompanyIco());
        employee.setFirstName(csvImportRow.getEmployeeFirstName());
        employee.setLastName(csvImportRow.getEmployeeLastName());
        employee.setEmail(csvImportRow.getEmployeeEmail().toLowerCase());
        employee.setDateLastUpdate(csvImportRow.getDateLastUpdate());

        return employee;
    }


}
