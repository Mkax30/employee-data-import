package cz.mka.employeeDataImport.impl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import cz.mka.employeeDataImport.impl.jpa.Company;

@Component
public interface CompanyDao extends JpaRepository<Company, Integer> {

    Company findById(Integer id);

    Company findByIco(Integer ico);
}
