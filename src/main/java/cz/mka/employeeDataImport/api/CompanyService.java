package cz.mka.employeeDataImport.api;

import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.impl.model.OutputCompany;
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
     * Inserts new company.
     * @param company
     * @return
     */
    Company saveCompany(Company company);

    /**
     * Updates company by id.
     * @param company
     * @return
     */
    Company updateCompany(Company company);

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
