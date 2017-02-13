package cz.mka.employeeDataImport.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COMPANY_ICO", nullable = false)
    private Integer companyIco;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "LAST_UPDATE", nullable = false)
    private LocalDateTime dateLastUpdate;

    public Employee() {
    }

    public Employee(Integer id, Integer companyIco, String firstName, String lastName, String email, LocalDateTime dateLastUpdate) {
        this.id = id;
        this.companyIco = companyIco;
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

    public Integer getCompanyIco() {
        return companyIco;
    }

    public void setCompanyIco(Integer companyIco) {
        this.companyIco = companyIco;
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
}
