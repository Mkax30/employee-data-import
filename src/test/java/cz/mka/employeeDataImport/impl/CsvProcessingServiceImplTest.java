package cz.mka.employeeDataImport.impl;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.impl.utils.CsvImportRow;
import cz.mka.employeeDataImport.rest.model.Statistics;

/**
 * Created by Martin Kaspar on 16/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvProcessingServiceImplTest extends TestCase {

    @Autowired
    private CsvProcessingService service;

    @Test
    public void testImportData() throws Exception {
        List<CsvImportRow> csvImportRowList = service.importData("test_data.csv");
        assertNotNull(csvImportRowList);
    }

    @Test
    public void testFileDoesNotExists() throws Exception {
        List<CsvImportRow> csvImportRowList = service.importData("sdf");
        assertNull(csvImportRowList);

        csvImportRowList = service.importData(null);
        assertNull(csvImportRowList);
    }

    @Test
    public void testBadCsvFile() throws Exception {
        List<CsvImportRow> csvImportRowList = service.importData("bad_test_data.csv");
        assertNull(csvImportRowList);
    }

    @Test
    public void testSaveData() throws Exception {
        List<CsvImportRow> csvImportRowList = service.importData("test_data.csv");
        Statistics statistics = service.saveData(csvImportRowList);
        assertNotNull(statistics);
    }
}