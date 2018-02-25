package es.devcircus.services.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@RestController
public class Application {

    /**
     * Request mapping.
     * @return Return a String to test the service.
     */
    @RequestMapping("/")
    public String home() {
        return "Hello Docker World - Catalog Microservice";
    }

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
