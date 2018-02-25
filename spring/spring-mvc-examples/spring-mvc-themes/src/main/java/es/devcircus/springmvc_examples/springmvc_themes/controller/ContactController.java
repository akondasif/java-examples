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
package es.devcircus.springmvc_examples.springmvc_themes.controller;

import es.devcircus.springmvc_examples.springmvc_themes.form.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@Controller
@SessionAttributes
public class ContactController {

    /**
     * Action que recoje los datos del formulario y los muestra por consola en
     * el servidor.
     *
     * @param contact Objeto Contact mapeado a partir del formulario.
     * @param result A donde redirigimos.
     * @return String con la redirección que queremos llevar a cabo.
     */
    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {
        // Sacamos por consola la información del contacto.
        System.out.println("First Name:" + contact.getFirstname()
                + "Last Name:" + contact.getLastname());
        // Una vez que hemos recogido los datos, redirigimos la solicitud a 
        // contacts para retornar a la página inicial del formulario con los 
        // campos vacíos.
        return "redirect:contacts.html";
    }

    /**
     * Action que nos lleva a un nuevo formulario vacío.
     *
     * @return ModelAndView object.
     */
    @RequestMapping("/contacts")
    public ModelAndView showContacts() {
        return new ModelAndView("contact", "command", new Contact());
    }
}
