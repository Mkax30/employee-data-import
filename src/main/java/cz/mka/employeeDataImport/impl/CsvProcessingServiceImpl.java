package cz.mka.employeeDataImport.impl;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.api.model.Company;
import cz.mka.employeeDataImport.api.model.Employee;
import cz.mka.employeeDataImport.api.model.Statistics;
import cz.mka.employeeDataImport.impl.Utils.InputDataValidator;
import cz.mka.employeeDataImport.impl.dao.CompanyDao;
import cz.mka.employeeDataImport.impl.dao.EmployeeDao;
import cz.mka.employeeDataImport.impl.model.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

    private static final String BASE_PATH = "c:/test_repo/employee/";
    private static final String SOURCE_PATH = BASE_PATH + "data/";
    private static final String TARGET_PATH = BASE_PATH + "processed/";

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<Statistics> processDataFolder() {
        String[] files = new File(SOURCE_PATH).list();

        if (files == null || files.length == 0) {
            System.out.println("\nNo files for processing!");
            return new ArrayList<>();
        }

        List<Statistics> statisticsList = new ArrayList<>();

        for (String csvFile : files) {
            if (!csvFile.toLowerCase().endsWith(".csv")) {
                continue;
            }
            List<Input> inputList = importData(csvFile);

            if (inputList == null) {
                statisticsList.add(new Statistics(csvFile, "Corrupted file!"));
                continue;
            }
            Statistics statistics = saveData(inputList);

            statistics.setFileName(csvFile);
            statistics.setMessage("Import successful.");
            statisticsList.add(statistics);

            System.out.println("\nFile: " + csvFile + " processed successfully.");
            System.out.println(statistics);

            try {
                Path source = new File(SOURCE_PATH + csvFile).toPath();
                Path target = new File(TARGET_PATH + csvFile).toPath();

                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("\nMoving " + csvFile + " \nfrom " + SOURCE_PATH);
                System.out.println("to " + TARGET_PATH);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statisticsList;
    }

    public List<Input> importData(String dataFile) {
        if (StringUtils.isEmpty(dataFile)) {
            System.out.println("\nNo data file specified.");
            return null;
        }

        File sourceFile = new File(SOURCE_PATH + dataFile);
        InputStream is;
        try {
            is = new FileInputStream(sourceFile);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file specified: " + sourceFile.getPath());
            //e.printStackTrace();
            return null;
        }

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
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (list == null) {
            return null;
        }

        List<Input> inputList = new ArrayList<>();
        for (Object o : list) {
            inputList.add((Input) o);
        }

        if (!InputDataValidator.validateDataBeforeInsert(inputList)) {
            return null;
        }

        return inputList;
    }

    public Statistics saveData(List<Input> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            System.out.println("\nFile is empty.");
            return null;
        }
        // removing duplicates from input list
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

        return new Statistics(null, null, employeesInserted, employeesUpdated, companiesInserted,
                companiesUpdated, duplicitiesFound, notProcessed);
    }

}



