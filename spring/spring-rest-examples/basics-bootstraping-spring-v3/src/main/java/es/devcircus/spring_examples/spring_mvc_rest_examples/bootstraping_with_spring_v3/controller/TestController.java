package es.devcircus.spring_examples.spring_mvc_rest_examples.bootstraping_with_spring_v3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// Anotación que se encarga de registrar una controller para Spring MVC el cual
// se encargará de gestionar solicitudes HTTP.
@Controller
public class TestController {

    /**
     * Método de prueba.
     *
     * @return
     */
    @RequestMapping(value = "/helloworld",
            method = RequestMethod.GET,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"})
    @ResponseBody
    public String getHelloWorld() {
        return "{Hello World}";
    }

}
