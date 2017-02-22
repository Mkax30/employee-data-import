package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.impl.jpa.Company;
import cz.mka.employeeDataImport.rest.model.OutputCompany;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Service
public interface CompanyService {

    /**
     * Retrieves all companies.
     * @return
     */
    List<OutputCompany> getAllCompanies();

    /**
     * Inserts new company or updates current.
     * @param company
     * @return
     */
    Company saveCompany(Company company);

    /**
     * Retrieves company by id.
     * @param id
     * @return
     */
    Company getCompany(Integer id);

    /**
     * Deletes company by id.
     * @param id
     */
    void deleteCompany(Integer id);

}
