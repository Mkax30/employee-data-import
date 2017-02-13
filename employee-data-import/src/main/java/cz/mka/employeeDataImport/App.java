package cz.mka.employeeDataImport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App 
{


    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        /*System.out.println( "Hello World!" );


        CompanyDao dao = new CompanyDao();
        System.out.println( "Hello World!" );

        Company c = new Company();
        c.setTitle("t");
        System.out.println( "Hello World!" );
        c.setCompanyIco(12547896);
        System.out.println( "Hello World!" );

        dao.saveCompany(c);


        System.out.println( "Hello World!" );





*/
    }
}
