/**
 * This file is part of apache-cxf-examples.
 *
 * apache-cxf-examples is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * apache-cxf-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.apache_cxf_examples.crud_example.services;

import es.devcircus.apache_cxf_examples.crud_example.model.FooBar;
import es.devcircus.apache_cxf_examples.crud_example.model.FooBarSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
public class FooBarService {

    // Colleción de datos que usamos para simular el acceso a nuestra fuente de
    // información.
    private static final FooBarSet fooBarSet;

    // Código estático que se encarga de la carga inicial de los datos de prueba.
    static {
        // Instanciamos la colleción de datos.
        fooBarSet = new FooBarSet();
        // Lista de elemento de la collection
        List<FooBar> fooBars = new ArrayList<>();
        // Variable auxiliar que usamos para crear las instancias de datos.
        FooBar foobar;
        // Generamos una colección de 10 elementos.
        for (int i = 0; i < 10; i++) {
            // Creamos una nueva instancia.
            foobar = new FooBar(i, "Techie Kernel " + i);
            // Anhadimos la nueva instancia a la colección.
            fooBars.add(foobar);
        }
        // Seteamos la lista de instancias.
        fooBarSet.setFooBars(fooBars);
    }

    /**
     * Servicio que nos permite recuperar una instancia de datos a partir de su
     * identificador.
     *
     * @param foobarId Identificador de la instancia que queremos recuperar.
     * @return Instancia que queremos recuperar.
     */
    @GET
    @javax.ws.rs.Path("/{foobarId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FooBar getFoobar(@PathParam("foobarId") int foobarId) {
        for (FooBar foobar : fooBarSet.getFooBars()) {
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
    @GET
    @javax.ws.rs.Path("/")
    @Produces(MediaType.APPLICATION_JSON)
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
    @PUT
    @javax.ws.rs.Path("/{foobarId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FooBar editFoobar(@PathParam("foobarId") int foobarId,
            FooBar foobar) {
        for (FooBar foobar1 : fooBarSet.getFooBars()) {
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
    @DELETE
    @javax.ws.rs.Path("/{foobarId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteFoobar(@PathParam("foobarId") int foobarId) {
        System.out.println("Delete call.");
        Iterator<FooBar> fooIterator = fooBarSet.getFooBars().iterator();
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
    @POST
    @javax.ws.rs.Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean createFoobar(FooBar fooBar) {
        return fooBarSet.getFooBars().add(fooBar);
    }
}
