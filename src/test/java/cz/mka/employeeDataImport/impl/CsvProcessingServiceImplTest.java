package cz.mka.employeeDataImport.impl;

import cz.mka.employeeDataImport.api.CsvProcessingService;
import cz.mka.employeeDataImport.api.model.Statistics;
import cz.mka.employeeDataImport.impl.model.Input;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Input> inputList = service.importData("test_data.csv");
        assertNotNull(inputList);
    }

    @Test
    public void testFileDoesNotExists() throws Exception {
        List<Input> inputList = service.importData("sdf");
        assertNull(inputList);

        inputList = service.importData(null);
        assertNull(inputList);
    }

    @Test
    public void testBadCsvFile() throws Exception {
        List<Input> inputList = service.importData("bad_test_data.csv");
        assertNull(inputList);
    }

    @Test
    public void testSaveData() throws Exception {
        List<Input> inputList = service.importData("test_data.csv");
        Statistics statistics = service.saveData(inputList);
        assertNotNull(statistics);
    }
}