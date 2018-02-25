package es.devcircus.spring_examples.spring_mvc_rest_examples.restful_crud_example_xml.controller;

import es.devcircus.spring_examples.spring_mvc_rest_examples.restful_crud_example_xml.model.FooBar;
import es.devcircus.spring_examples.spring_mvc_rest_examples.restful_crud_example_xml.model.FooBarSet;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

// Anotación que se encarga de registrar una controller para Spring MVC el cual
// se encargará de gestionar solicitudes HTTP.
@Controller
// Anotación que se encarga de relacionar un método con una petición http. Esta
// anoración funciona tanto a nivel de método como de clase. En el caso de un 
// método asocia una URL a la ejecución de ese método. En el caso de una clase, 
// asocia todos los métodos de la misma a la URL. En este caso, capturamos todas 
// las peticiones /foobar y las asociamos a los métodos de la clase.
@RequestMapping("/foobar")
public class FooBarController {

    // Colleción de datos que usamos para simular el acceso a nuestra fuente de
    // información.
    private static final FooBarSet fooBarSet;

    // Código estático que se encarga de la carga inicial de los datos de prueba.
    static {
        // Instanciamos la colleción de datos.
        fooBarSet = new FooBarSet();
        // Variable auxiliar que usamos para crear las instancias de datos.
        FooBar foobar;
        // Generamos una colección de 10 elementos.
        for (int i = 0; i < 10; i++) {
            // Creamos una nueva instancia.
            foobar = new FooBar(i, "Techie Kernel " + i);
            // Anhadimos la nueva instancia a la colección.
            fooBarSet.add(foobar);
        }
    }

    /**
     * Servicio que nos permite recuperar una instancia de datos a partir de su
     * identificador.
     *
     * @param foobarId Identificador de la instancia que queremos recuperar.
     * @return Instancia que queremos recuperar.
     */
    @RequestMapping(value = "/{foobarId}",
            method = RequestMethod.GET,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"})
    @ResponseBody
    public FooBar getFoobar(@PathVariable int foobarId) {
        for (FooBar foobar : fooBarSet) {
            if (foobar.getId() == foobarId) {
                return foobar;
            }
        }
        return null;
    }

    /**
     * Método que nos permite recuperar la lista completas de instancias del
     * modelo. En este caso no es necesario definir el atributo value ya que
     * hemos definido el requestMapping en la definición de la clase. Para
     * invocar el servicio solamente debemos de llamar a la siguiente URL (por
     * ejemplo):
     *
     * http://localhost:8084/bootstraping-web-application/foobar
     *
     * @return Lista de instancias de modelo que hemos creado en el sistema.
     */
    @RequestMapping(method = RequestMethod.GET,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"})
    @ResponseBody
    public FooBarSet getFoobars() {
        return fooBarSet;
    }

    /**
     * Método que nos permite editar los valores de una instancia del modelo.
     *
     * @param foobar Nueva instancia que queremos que sustituya a la instancia
     * que tenemos en nuestro repositorio.
     * @param foobarId Identificador de la instancia que queremos modificar.
     * @return Instancia editada.
     */
    @RequestMapping(value = "/{foobarId}",
            method = RequestMethod.PUT,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"},
            consumes = {
                "application/json", "application/xml"})
    @ResponseBody
    public FooBar editFoobar(@RequestBody FooBar foobar,
            @PathVariable int foobarId) {
        for (FooBar foobar1 : fooBarSet) {
            if (foobarId == foobar1.getId()) {
                foobar1.setId(foobar.getId());
                foobar1.setName(foobar.getName());
                return foobar1;
            }
        }
        return null;
    }

    /**
     * Método que nos permite eliminar una instancia del modelo de datos.
     *
     * @param foobarId Identificador de la instancia que queremos eliminar.
     * @return Resultado de la operación de borrado. True si se ha borrado la
     * instancia correctamente, false en caso contrario.
     */
    @RequestMapping(value = "/{foobarId}",
            method = RequestMethod.DELETE,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"})
    @ResponseBody
    public boolean deleteFoobar(@PathVariable int foobarId) {
        System.out.println("Delete call.");
        Iterator<FooBar> fooIterator = fooBarSet.iterator();
        while (fooIterator.hasNext()) {
            FooBar foobar = fooIterator.next();
            System.out.println(foobar);
            if (foobar.getId() == foobarId) {
                fooIterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Método que nos permite crear una nueva instancia de datos en nuestro
     * respositorio.
     *
     * @param fooBar Nueva instancia que queremos crear.
     * @return Retorna el restultado de la operación. True si la operación se ha
     * creado con éxito. Flase en caso contrario.
     */
    @RequestMapping(method = RequestMethod.POST,
            headers = "Accept=application/xml, application/json",
            produces = {
                "application/json", "application/xml"},
            consumes = {
                "application/json", "application/xml"})
    @ResponseBody
    public boolean createFoobar(@RequestBody FooBar fooBar) {
        return fooBarSet.add(fooBar);
    }
}
