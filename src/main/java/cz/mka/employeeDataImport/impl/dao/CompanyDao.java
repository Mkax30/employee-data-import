package cz.mka.employeeDataImport.impl.dao;

import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.impl.model.OutputCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class CompanyDao {

    @Autowired
    private EntityManager em;

    public List<OutputCompany> getAllCompanies() {
        Query query = em.createQuery("select new OutputCompany(c.title, c.ico) from Company c");
        return (List<OutputCompany>) query.getResultList();
    }

    @Transactional
    public Company saveCompany(Company company) {
        em.persist(company);
        return getCompany(company.getId());
    }

    @Transactional
    public Company updateCompany(Company company) {
        em.merge(company);
        return getCompany(company.getId());
    }

    public Company getCompany(Integer id) {
        return em.find(Company.class, id);
    }

    public void deleteCompany(Integer id) {
        // TODO
    }

    public Boolean companyExistsByICO(Integer ico) {
        Query query = em.createQuery("from Company where ico = :ico").setParameter("ico", ico);
        return !query.getResultList().isEmpty();
    }

    public Company getCompanyByIco(Integer ico) {
        Query query = em.createQuery("from Company where ico = :ico").setParameter("ico", ico);
        Company result;
        try {
            result = (Company) query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
        return result;
    }
}
