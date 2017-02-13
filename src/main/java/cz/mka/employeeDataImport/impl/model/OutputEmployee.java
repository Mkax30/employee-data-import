package cz.mka.employeeDataImport.impl.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */
@Entity
public class OutputEmployee {

    private String name;

    @Id
    private String email;

    public OutputEmployee() {
    }

    public OutputEmployee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
