package cz.mka.employeeDataImport.api.model;

/**
 * Created by Martin Kaspar on 12/02/2017.
 */
public class Statistics {

    private String fileName;
    private String message;
    private Integer employeesInserted;
    private Integer employeesUpdated;
    private Integer companiesInserted;
    private Integer companiesUpdated;
    private Integer duplicitiesFound;
    private Integer notProcessed;

    public Statistics() {
    }

    public Statistics(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public Statistics(String fileName, String message, Integer employeesInserted, Integer employeesUpdated,
                      Integer companiesInserted, Integer companiesUpdated, Integer duplicitiesFound,
                      Integer notProcessed) {

        this.fileName = fileName;
        this.message = message;
        this.employeesInserted = employeesInserted;
        this.employeesUpdated = employeesUpdated;
        this.companiesInserted = companiesInserted;
        this.companiesUpdated = companiesUpdated;
        this.duplicitiesFound = duplicitiesFound;
        this.notProcessed = notProcessed;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getEmployeesInserted() {
        return employeesInserted;
    }

    public void setEmployeesInserted(Integer employeesInserted) {
        this.employeesInserted = employeesInserted;
    }

    public Integer getEmployeesUpdated() {
        return employeesUpdated;
    }

    public void setEmployeesUpdated(Integer employeesUpdated) {
        this.employeesUpdated = employeesUpdated;
    }

    public Integer getCompaniesInserted() {
        return companiesInserted;
    }

    public void setCompaniesInserted(Integer companiesInserted) {
        this.companiesInserted = companiesInserted;
    }

    public Integer getCompaniesUpdated() {
        return companiesUpdated;
    }

    public void setCompaniesUpdated(Integer companiesUpdated) {
        this.companiesUpdated = companiesUpdated;
    }

    public Integer getDuplicitiesFound() {
        return duplicitiesFound;
    }

    public void setDuplicitiesFound(Integer duplicitiesFound) {
        this.duplicitiesFound = duplicitiesFound;
    }

    public Integer getNotProcessed() {
        return notProcessed;
    }

    public void setNotProcessed(Integer notProcessed) {
        this.notProcessed = notProcessed;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "fileName=" + fileName +
                ", message=" + message +
                ", employeesInserted=" + employeesInserted +
                ", employeesUpdated=" + employeesUpdated +
                ", companiesInserted=" + companiesInserted +
                ", companiesUpdated=" + companiesUpdated +
                ", duplicitiesFound=" + duplicitiesFound +
                ", notProcessed=" + notProcessed +
                '}';
    }
}
