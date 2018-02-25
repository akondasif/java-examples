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
package es.devcircus.apache_cxf_examples.bootstraping.services;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
public class TestService {

    /**
     * Constructor por defecto
     */
    public TestService() {
    }

    /**
     * Servicio de prueba Lo anotamos indicando que es un POST y le asociamos la
     * tura de acceso.
     *
     * @return
     */
    @GET
    @javax.ws.rs.Path("/test/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTest() {
        // Retornamos un mensaje de prueba.
        return "{Hello World}";
    }

}
