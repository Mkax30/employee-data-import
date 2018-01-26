package cz.mka.employeeDataImport.api;

import java.util.List;

import cz.mka.employeeDataImport.impl.jpa.Company;
import cz.mka.employeeDataImport.rest.model.OutputCompany;

public interface CompanyService {

    /**
     * Retrieves all companies.
     */
    List<OutputCompany> getAllCompanies();

    /**
     * Inserts new company or updates current.
     */
    Company saveCompany(Company company);

    /**
     * Retrieves company by id.
     */
    Company getCompany(Integer id);

    /**
     * Deletes company by id.
     */
    void deleteCompany(Integer id);

}
