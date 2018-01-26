package cz.mka.employeeDataImport.impl.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime dateLastUpdate;

/*    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", referencedColumnName="ID")
    private Company company;*/

    public Employee() {
    }

    public Employee(Integer id, Integer companyId, String firstName, String lastName, String email, LocalDateTime dateLastUpdate) {
        this.id = id;
        this.companyId = companyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateLastUpdate = dateLastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

/*    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/
}
