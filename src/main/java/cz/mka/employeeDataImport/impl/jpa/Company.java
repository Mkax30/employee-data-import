package cz.mka.employeeDataImport.impl.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "ICO", nullable = false)
    private Integer ico;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(mappedBy = "companyId", targetEntity = Employee.class)
    private List<Employee> employees;

    public Company() {
    }

    public Company(Integer id, String title, Integer ico, String address, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.ico = ico;
        this.address = address;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIco() {
        return ico;
    }

    public void setIco(Integer ico) {
        this.ico = ico;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
