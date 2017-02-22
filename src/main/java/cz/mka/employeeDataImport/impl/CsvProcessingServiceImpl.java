package cz.mka.employeeDataImport.impl;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.api.dao.CompanyDao;
import cz.mka.employeeDataImport.api.dao.EmployeeDao;
import cz.mka.employeeDataImport.impl.jpa.Company;
import cz.mka.employeeDataImport.impl.jpa.Employee;
import cz.mka.employeeDataImport.impl.utils.CsvImportRow;
import cz.mka.employeeDataImport.impl.utils.DataConverter;
import cz.mka.employeeDataImport.impl.utils.InputDataValidator;
import cz.mka.employeeDataImport.rest.model.Statistics;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    final static Logger logger = Logger.getLogger(CsvProcessingServiceImpl.class);

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
            logger.info("No files for processing!");
            return new ArrayList<>();
        }

        List<Statistics> statisticsList = new ArrayList<>();

        for (String csvFile : files) {
            if (!csvFile.toLowerCase().endsWith(".csv")) {
                continue;
            }

            List<CsvImportRow> csvImportRowList;
            try {
                csvImportRowList = importData(csvFile);
            } catch (IOException e) {
                logger.error("Cannot find the file specified: " + csvFile, e);
                continue;
            }

            if (csvImportRowList == null) {
                statisticsList.add(new Statistics(csvFile, "Corrupted file!"));
                logger.warn("Invalid csv file: " + csvFile);
                continue;
            }
            Statistics statistics = saveData(csvImportRowList);

            statistics.setFileName(csvFile);
            statistics.setMessage("Import successful");
            statisticsList.add(statistics);

            logger.info("File: " + csvFile + " processed successfully.");
            logger.info(statistics);

            try {
                Path source = new File(SOURCE_PATH + csvFile).toPath();
                Path target = new File(TARGET_PATH + csvFile).toPath();

                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                logger.info("Moving " + csvFile + " from " + SOURCE_PATH + " to " + TARGET_PATH);

            } catch (IOException e) {
                logger.error("Cannot move processed file " + csvFile, e);
            }
        }
        return statisticsList;
    }

    public List<CsvImportRow> importData(String dataFile) throws IOException {
        File sourceFile = new File(SOURCE_PATH + dataFile);

        if (!sourceFile.exists()) {
            logger.error("No data file specified.");
            return null;
        }

        HeaderColumnNameTranslateMappingStrategy<CsvImportRow> mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<>();
        mappingStrategy.setType(CsvImportRow.class);

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
        List list;



        try (InputStream is = new FileInputStream(sourceFile)) {
            /*FileChannel fileChannel = new RandomAccessFile(is., "rw").getChannel();
            FileLock lock = fileChannel.tryLock();
            fileChannel.*/

            list = ctb.parse(mappingStrategy, new InputStreamReader(is, "UTF-8"));

//            lock.release();
        }



        if (list == null) {
            return null;
        }

        List<CsvImportRow> csvImportRowList = new ArrayList<>();
        for (Object o : list) {
            csvImportRowList.add((CsvImportRow) o);
        }

        if (!InputDataValidator.validateDataBeforeInsert(csvImportRowList)) {
            return null;
        }

        return csvImportRowList;
    }

    public Statistics saveData(List<CsvImportRow> csvImportRowList) {

        if (csvImportRowList == null || csvImportRowList.isEmpty()) {
            logger.warn("File is empty.");
            return null;
        }

        // removing duplicates from input list
        csvImportRowList = csvImportRowList.stream().distinct().collect(Collectors.toList());

        // map companies
        Map<Integer, Company> companyMap = new HashMap<>();
        for (CsvImportRow row : csvImportRowList) {
            companyMap.put(row.getCompanyIco(), DataConverter.convertInputRowToCompany(row));
        }

        // map employees
        Map<String, Employee> employeeMap = new HashMap<>();
        for (CsvImportRow row : csvImportRowList) {
            employeeMap.put(row.getEmployeeEmail().toLowerCase(), DataConverter.convertInpuRowToEmployee(row));
        }

        // insert or update companies
        int companiesInserted = 0;
        int companiesUpdated = 0;

        for (Company c : companyMap.values()) {
            Company current = companyDao.findByIco(c.getIco());

            if (current == null) {
                companyDao.save(c);
                companiesInserted++;

            } else {
                c.setId(current.getId());
                companyDao.save(c);
                companiesUpdated++;
            }
        }

        // insert or update employees
        int employeesInserted = 0;
        int employeesUpdated = 0;
        int duplicitiesFound = 0;
        int notProcessed = 0;

        for (Employee e : employeeMap.values()) {
            Employee current = employeeDao.findByEmailIgnoreCase(e.getEmail());

            if (current == null) {
                e.setCompanyId(companyMap.get(e.getCompanyId()).getId());
                employeeDao.save(e);
                employeesInserted++;

            } else if (current.getDateLastUpdate().isBefore(e.getDateLastUpdate())) {
                e.setId(current.getId());
                e.setCompanyId(current.getCompanyId());
                employeeDao.save(e);
                employeesUpdated++;

            } else if (current.getDateLastUpdate().isEqual(e.getDateLastUpdate())) {
                duplicitiesFound++;

            } else {
                notProcessed++;

            }
        }

        return new Statistics(null, null, employeesInserted, employeesUpdated, companiesInserted,
                companiesUpdated, duplicitiesFound, notProcessed);
    }

}



