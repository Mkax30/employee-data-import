package cz.mka.employeeDataImport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Martin Kaspar on 11/02/2017.
 */

@SpringBootApplication
@EnableScheduling
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
