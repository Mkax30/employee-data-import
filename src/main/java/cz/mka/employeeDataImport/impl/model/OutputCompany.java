package cz.mka.employeeDataImport.impl.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Entity
public class OutputCompany {

    private String title;

    @Id
    private Integer ico;

    public OutputCompany() {
    }

    public OutputCompany(String title, Integer ico) {
        this.title = title;
        this.ico = ico;
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
