package cz.mka.employeeDataImport.impl.dao;

import cz.mka.employeeDataImport.impl.jpa.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public interface CompanyDao extends JpaRepository<Company, Integer> {

    Company findById(Integer id);

    Company findByIco(Integer ico);
}
