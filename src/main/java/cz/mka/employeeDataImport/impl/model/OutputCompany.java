package cz.mka.employeeDataImport.impl.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Entity
public class OutputCompany {

    @Id
    private Integer id;
    private String title;
    private Integer ico;

    public OutputCompany() {
    }

    public OutputCompany(Integer id, String title, Integer ico) {
        this.id = id;
        this.title = title;
        this.ico = ico;
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
}
