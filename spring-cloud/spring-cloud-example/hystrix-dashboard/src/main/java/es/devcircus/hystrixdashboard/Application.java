package es.devcircus.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@SpringBootApplication
@EnableHystrixDashboard
public class Application {

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
