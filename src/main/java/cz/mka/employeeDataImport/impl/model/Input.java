package cz.mka.employeeDataImport.impl.model;

import java.time.LocalDateTime;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
public class Input {

    private Integer companyIco;
    private String companyTitle;
    private String companyAddress;
    private String employeeEmail;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDateTime dateLastUpdate;

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
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }
}
