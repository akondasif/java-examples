package es.devcircus.spring_examples.spring_mvc_rest_examples.bootstraping_with_spring_v4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// La siguiente etiqueta es equivalente a <mvc:annotation-driven/>
@EnableWebMvc
// La siguiente etiqueta es equivalente a <context:component-scan base-package=”path.to.controller”/>
@ComponentScan(basePackages = {"es.devcircus.spring_examples.spring_mvc_rest_examples.bootstraping_with_spring_v4.controller"})
@Configuration
public class AppConfig {

}
