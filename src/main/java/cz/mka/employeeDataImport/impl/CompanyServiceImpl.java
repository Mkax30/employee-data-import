package cz.mka.employeeDataImport.impl;

import cz.mka.employeeDataImport.api.CompanyService;
import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.impl.dao.CompanyDao;
import cz.mka.employeeDataImport.impl.model.OutputCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao dao;

    public List<OutputCompany> getAllCompanies() {
        return dao.getAllCompanies();
    }

    public Company saveCompany(Company company) {
        if (dao.getCompanyByIco(company.getIco()) != null) {
            return null;
        }
        return dao.saveCompany(company);
    }

    public Company updateCompany(Company company) {
        if (dao.getCompany(company.getId()) == null) {
            return null;
        }
        return dao.updateCompany(company);
    }

    public Company getCompany(Integer id) {
        return dao.getCompany(id);
    }

    public void deleteCompany(Integer id) {
        dao.deleteCompany(id);
    }
}
