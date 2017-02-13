package cz.mka.employeeDataImport.api.model;

/**
 * Created by Martin Kaspar on 12/02/2017.
 */
public class Statistics {

    private Integer employeesInserted;
    private Integer employeesUpdated;
    private Integer companiesInserted;
    private Integer companiesUpdated;
    private Integer duplicitiesFound;
    private Integer notProcessed;

    public Statistics() {
    }

    public Statistics(Integer employeesInserted, Integer employeesUpdated, Integer companiesInserted, Integer companiesUpdated, Integer duplicitiesFound, Integer notProcessed) {
        this.employeesInserted = employeesInserted;
        this.employeesUpdated = employeesUpdated;
        this.companiesInserted = companiesInserted;
        this.companiesUpdated = companiesUpdated;
        this.duplicitiesFound = duplicitiesFound;
        this.notProcessed = notProcessed;
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
                "employeesInserted=" + employeesInserted +
                ", employeesUpdated=" + employeesUpdated +
                ", companiesInserted=" + companiesInserted +
                ", companiesUpdated=" + companiesUpdated +
                ", duplicitiesFound=" + duplicitiesFound +
                ", notProcessed=" + notProcessed +
                '}';
    }
}
