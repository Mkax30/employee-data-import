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

    List<OutputCompany> getAllCompanies();

    Company saveCompany(Company company);

    Company updateCompany(Company company);

    Company getCompany(Integer id);

    void deleteCompany(Integer id);

}
