package cz.mka.employeeDataImport.api.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public Company() {
    }

    public Company(Integer id, String title, Integer ico, String address) {
        this.id = id;
        this.title = title;
        this.ico = ico;
        this.address = address;
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
}
