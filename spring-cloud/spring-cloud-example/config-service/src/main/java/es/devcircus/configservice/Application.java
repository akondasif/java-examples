package es.devcircus.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@SpringBootApplication
@EnableConfigServer
public class Application {

    /**
     * Launch de config service.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
