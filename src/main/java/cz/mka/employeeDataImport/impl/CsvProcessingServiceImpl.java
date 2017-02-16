package cz.mka.employeeDataImport.impl;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.api.model.Employee;
import cz.mka.employeeDataImport.api.model.Statistics;
import cz.mka.employeeDataImport.impl.dao.CompanyDao;
import cz.mka.employeeDataImport.impl.dao.EmployeeDao;
import cz.mka.employeeDataImport.impl.model.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Component
public class CsvProcessingServiceImpl implements CsvProcessingService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<Input> importData() {
        String dataFile = "data/test_data.csv";

        // load data file from resources
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(dataFile);

        HeaderColumnNameTranslateMappingStrategy<Input> mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<>();
        mappingStrategy.setType(Input.class);

        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("ico", "companyIco");
        columnMapping.put("title", "companyTitle");
        columnMapping.put("address", "companyAddress");
        columnMapping.put("email", "employeeEmail");
        columnMapping.put("firstName", "employeeFirstName");
        columnMapping.put("lastName", "employeeLastName");
        columnMapping.put("updated", "lastUpdate");
        mappingStrategy.setColumnMapping(columnMapping);

        CsvToBean ctb = new CsvToBean();
        List list = null;
        try {
            list = ctb.parse(mappingStrategy, new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (list == null) {
            return null;
        }

        List<Input> inputList = new ArrayList<>();
        for (Object o : list) {
            inputList.add((Input) o);
        }

        return inputList;
    }

    public Statistics saveData(List<Input> inputList) {

        // removing duplicates from input file
        inputList = inputList.stream().distinct().collect(Collectors.toList());

        Integer employeesInserted = 0;
        Integer employeesUpdated = 0;
        Integer duplicitiesFound = 0;
        Integer notProcessed = 0;

        // for remove duplicities of companies
        Map<Integer, Company> companyMap = new HashMap<>();

        // save employee data to db
        for (Input in : inputList) {
            // prepare company list
            companyMap.put(in.getCompanyIco(), new Company(null, in.getCompanyTitle(), in.getCompanyIco(), in.getCompanyAddress()));

            Employee currentEmployee = employeeDao.getEmployeeByEmail(in.getEmployeeEmail());
            Employee processedEmployee = new Employee(null, in.getCompanyIco(), in.getEmployeeFirstName(), in.getEmployeeLastName(), in.getEmployeeEmail(), in.getDateLastUpdate());

            if (currentEmployee == null) {
                // save new
                employeeDao.saveEmployee(processedEmployee);
                employeesInserted++;

            } else if (currentEmployee.getDateLastUpdate().isBefore(in.getDateLastUpdate())) {
                // update current
                processedEmployee.setId(currentEmployee.getId());
                employeeDao.updateEmployee(processedEmployee);
                employeesUpdated++;

            } else if (currentEmployee.getDateLastUpdate().isEqual(in.getDateLastUpdate())) {
                // duplicity
                duplicitiesFound++;

            } else {
                // old record
                notProcessed++;
            }
        }

        // process companies
        Integer companiesInserted = 0;
        Integer companiesUpdated = 0;

        for (Company company : companyMap.values()) {

            Company currentCompany = companyDao.getCompanyByIco(company.getIco());

            if (currentCompany == null) {
                companyDao.saveCompany(company);
                companiesInserted++;

            } else if (!currentCompany.getTitle().equals(company.getTitle()) ||
                    !currentCompany.getAddress().equals(company.getAddress())) {
                company.setId(currentCompany.getId());
                companyDao.updateCompany(company);
                companiesUpdated++;
            }
        }


        // todo remove csv file and put it to processed folder


        return new Statistics(employeesInserted, employeesUpdated, companiesInserted,
                companiesUpdated, duplicitiesFound, notProcessed);
    }

}



