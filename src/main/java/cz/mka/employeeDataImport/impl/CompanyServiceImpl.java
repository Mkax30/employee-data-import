package cz.mka.employeeDataImport.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import cz.mka.employeeDataImport.api.CompanyService;
import cz.mka.employeeDataImport.impl.dao.CompanyDao;
import cz.mka.employeeDataImport.impl.jpa.Company;
import cz.mka.employeeDataImport.impl.utils.DataConverter;
import cz.mka.employeeDataImport.rest.model.OutputCompany;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao dao;

    public List<OutputCompany> getAllCompanies() {
        List<Company> companyList = dao.findAll();
        return companyList.stream().map(DataConverter::convertCompanyEntity).collect(Collectors.toList());
    }

    public Company saveCompany(Company company) {
        return dao.save(company);
    }

    public Company getCompany(Integer id) {
        return dao.findById(id);
    }

    public void deleteCompany(Integer id) {
        dao.delete(id);
    }
}
