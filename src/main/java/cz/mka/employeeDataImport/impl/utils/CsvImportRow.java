package cz.mka.employeeDataImport.impl.utils;

import com.google.common.base.Objects;

import java.time.LocalDateTime;

public class CsvImportRow {

    private Integer companyIco;
    private String companyTitle;
    private String companyAddress;
    private String employeeEmail;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDateTime dateLastUpdate;
    private String lastUpdate;

    public Integer getCompanyIco() {
        return companyIco;
    }

    public void setCompanyIco(Integer companyIco) {
        this.companyIco = companyIco;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public LocalDateTime getDateLastUpdate() {
        if (dateLastUpdate == null) {
            return LocalDateTime.parse(lastUpdate);
        }
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvImportRow csvImportRow = (CsvImportRow) o;
        return Objects.equal(companyIco, csvImportRow.companyIco) &&
               Objects.equal(companyTitle, csvImportRow.companyTitle) &&
               Objects.equal(companyAddress, csvImportRow.companyAddress) &&
               Objects.equal(employeeEmail, csvImportRow.employeeEmail) &&
               Objects.equal(employeeFirstName, csvImportRow.employeeFirstName) &&
               Objects.equal(employeeLastName, csvImportRow.employeeLastName) &&
               Objects.equal(lastUpdate, csvImportRow.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(companyIco, companyTitle, companyAddress, employeeEmail, employeeFirstName, employeeLastName, lastUpdate);
    }
}
