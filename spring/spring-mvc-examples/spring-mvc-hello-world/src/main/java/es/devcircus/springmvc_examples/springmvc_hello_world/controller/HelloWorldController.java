/**
 * This file is part of spring-mvc-examples.
 *
 * springmvc-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * springmvc-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.springmvc_examples.springmvc_hello_world.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@Controller
public class HelloWorldController {

    /**
     * Cada vez que un usuario hace clic sobre un enlace oo envía un formulario
     * en su navegador Web, comienza a trabajar una petición. La descripción del
     * puesto de una petición es la de un mensajero. Igual que cualquier cartero
     * o mensajero, una petición vive para llevar información de un lugar a
     * otro.
     *
     * La petición es alguien muy ocupada. Desde el momento en que abandona el
     * navegador hasa el momento en que devuelve una respuesta, realizará varias
     * paradas, dejando cada vez un poco de información y recogiendo al más.
     *
     * Cuando una petición sale del navegador, lleva información sobre lo que
     * está pidiendo el usuario. Como poco, la petición llevará el URL
     * solicitado. Pero también puede llevar datos adicionales como la
     * información introducida por el usuario en un formulario.
     *
     * La primera parada del viaje de la petición es DispatcherServlet de
     * Spring. Como la mayoría de los framework de trabajo MVC basados en java,
     * Spring MVC canaliza las peticiones a través de un único servlet
     * controlador frontal. Un controlador frontal es un patrón común de
     * aplicación Web donde un único servlet delega la responsabilidad de un
     * peticción a otros componentes de una aplicación para que relicen el
     * procesamiento. En el caso de Spring MVC, DispatcherServlet es el
     * controlador frontal.
     *
     * El trabajo de DispatcherServlet es enviar la petición a un controlador
     * Spring MVC. Un controlador es un componente Spring que procesa la
     * petición. Pero una aplicación típica puede tener varios controladores y
     * DispatcherServlet necesita ayuda para decidir a qué contorlador enviar la
     * petición. Así pues, DispatcherServlet consulta uno o más manipuladores de
     * mapeo para descubrir cuál será la siguiente parada de la petición. El
     * manipulador de mapeo presentará especial atención al URL trnasportado por
     * la petición cuando tome su decisión.
     *
     * Una vez que se ha escogido el controlador adecuado, DispatcherServlet
     * envía la petición en su camino al controlador elegido. En el controlador,
     * la petición dejará su carga útil (la información enviada por el usuario)
     * y pacientemenete esperará a que el controlador procese la información.
     * (En realidad, un Controller bien diseñado realiza poco o casi ningún
     * procesamiento él mismo y en su lugar delega la responsabilidad de la
     * lógica empresarial a uno o más objetos de servicio.)
     *
     * La lógica realizada por un controlador a menudo resulta en cierata
     * información que debe llevarse de vuelat al usuario y mostrarse en el
     * navegador. Esta información se conoce mo modelo. Pero enviar información
     * sn procesar de vuelta al usuario no es suficiente; debe formatearse en un
     * fomato emable para el usuario, normalmente HTML. Para eso debe darse una
     * visualización a la información, normalmente JSP.
     *
     * Así, lo último que hace el controlador es empaquetar los datos modelo y
     * el nombbre de una visualización en un objeto ModelAndView. Envía entonces
     * la petición junto con su nuevo paquete ModelAndView, de vuelta al
     * DispatcherServlet. Como indica su nombre ModelAndView contiene tanto los
     * datos modelo como una pista sobre en qué visualización mostrar los
     * resultados.
     *
     * Para que el controlador no esté acoplado a una visualización en
     * particular, ModelAndView no lleva una referencia al JSP real. Sólo lleva
     * un nombre lógico que será utilizado para buscar la visualización real que
     * producirá el HTML resultante. Una vez se ha entregado ModelAndView al
     * DispatcherServlet, el DispatcherServlet le pide a un resolutor de
     * visualización que le ayude a econtrar el JSP.
     *
     * Ahora que el DispatcherServlet sabe a qué visualización dar los
     * resultados, el trabajo de la petición casi ha terminado. Su última parada
     * es la implementación de vista (probablemente un JSP) en el lque entrega
     * sus datos modelo. Con los datos modelo enregados a la visualización,
     * termina el trabajo de la petición. La visualización utilizará los datos
     * modelo para mostrar una página que será devuelta al navegador por el (no
     * tan trabajador) objeto de respuesta.
     *
     * Configuración del DispatcherServlet
     *
     * En el centro de Spring MVC está DispatcherServlet, un servlet que
     * funciona como controlador frontal de Spring MVC. Como cualquier servlet,
     * DispatchheerServlet se debe configurar en el archivo web.xml de la
     * aplicación Web. Coloque la siguiente declaración de <servlet> en el
     * archivo web.xml de su aplicación:
     *
     * <servlet>
     * <servlet-name>spring</servlet-name>
     * <servlet-class>
     * org.springframework.web.servlet.DispatcherServlet
     * </servlet-class>
     * <load-on-startup>1</load-on-startup>
     * </servlet>
     *
     * El <servlet-name> dado al servlet es importante. Por defecto, cuando se
     * abre DispatcherServlet, abrirá el contexto de aplicación de Spring desde
     * un archivo XML cuyo nombre está basado en el nombre del servlet. En este
     * caso, como el servlet se llama "spring", DispatcherServlet intentará
     * abrir el contexto de aplicación desde un archivo llamado
     * spring-servlet.xml.
     *
     * A continuación debe indicar qué URL serán manejados por el
     * DispatcherServlet. Añada esete <servlet-mapping> a web.xml para dejar que
     * DispatcherServlet maneje todos los URL que acaben en .htm:
     *
     * <servlet-mapping>
     * <servlet-name>spring</servlet-name>
     * <url-pattern>*.html</url-pattern>
     * </servlet-mapping>
     *
     * Probablemente se está preguntando por qué escogemos este patrón URL en
     * concreto. Podría se porque todo el contenido producido por nuestra
     * aplicación es HTML. También podría ser por que queremos engañar a
     * nuestros amigos haciéndoles pensar que toda nuestra aplicación está
     * compuesta por archivos HTML estáticos. Y podría ser que consideramos que
     * .do es una extensión estúpida.
     *
     * Pero la verdad es que el patrón URL es en cierto modo arbitrario y que
     * podríamos haber elegido cualquier patrón URL para DispatcherServlet.
     * Nuestra principal razón para escoger *.ht, es que ese patrón es el que se
     * utiliza por convención en la mayoría de aplicaciones Spring MVC que
     * producen contenido HTML. La razón tras esta convención es que el
     * contenido producido es HTML y por tanto el URL debería reflejar ese
     * hecho.
     *
     * Una vez configurado DispatcherServlet en el archivo web.xml y que tiene
     * un mapeo URL, estamos listos para escribir la capa Web de nuestra
     * aplicación.
     *
     * @return
     */
    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        // Mensaje que queremos retornar como resultado
        String message = "Hello World, Spring 3.0!";
        // Retornamos el mensaje.
        return new ModelAndView("hello", "message", message);
    }
}
